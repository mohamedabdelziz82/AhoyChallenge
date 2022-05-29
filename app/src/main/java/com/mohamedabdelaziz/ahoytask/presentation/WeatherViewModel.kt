package com.mohamedabdelaziz.ahoytask.presentation

import android.Manifest
import android.content.Context
import android.os.Looper
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.mohamedabdelaziz.ahoytask.core.utils.Constants
import com.mohamedabdelaziz.ahoytask.core.utils.commons.NetworkResult
import com.mohamedabdelaziz.ahoytask.core.utils.preference.PreferenceDataSourceImpl
import com.mohamedabdelaziz.ahoytask.core.workmanager.WeatherWorker
import com.mohamedabdelaziz.ahoytask.domain.model.WeatherResponse
import com.mohamedabdelaziz.ahoytask.domain.usecases.FavouriteWeatherUseCase
import com.mohamedabdelaziz.ahoytask.domain.usecases.WeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private var weatherUseCase: WeatherUseCase,
    private var preferenceDataSource: PreferenceDataSourceImpl,
    private var favouriteWeatherUseCase: FavouriteWeatherUseCase
) : ViewModel() {
    lateinit var temperature: String
    init {
        viewModelScope.launch {
            preferenceDataSource.getValue(Constants.TEMP_TYPE, "metric").collect() {
                temperature = it.toString()
            }
        }
    }

    private val _stateWeather = mutableStateOf(WeatherState())
    val stateWeather: State<WeatherState> = _stateWeather

    private val _stateForecast = mutableStateOf(ForecastState())
    val stateForecast: State<ForecastState> = _stateForecast

    private val _stateFavourite = mutableStateOf(FavouriteState())
    val stateFavourite: State<FavouriteState> = _stateFavourite

    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    fun getLocationAndFetchWeather(
        context: Context, isForecast: Boolean = false
    ) {
        val client = LocationServices.getFusedLocationProviderClient(context)
        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                if (temperature == "")
                    temperature = "metric"
                if (isForecast) {
                    loadForecastLatLngFromApi(
                        result.lastLocation.latitude,
                        result.lastLocation.longitude,
                        temperature
                    )
                } else {
                    loadWeatherLatLngFromApi(
                        result.lastLocation.latitude,
                        result.lastLocation.longitude,
                        temperature
                    )
                }

            }
        }
        val request = LocationRequest.create()
            .setInterval(10_000)
            .setFastestInterval(5_000)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setSmallestDisplacement(170f)
        client.requestLocationUpdates(request, callback, Looper.getMainLooper())
    }

    fun loadWeatherFromApi(city: String, unit: String) =
        viewModelScope.launch {
            weatherUseCase.invokeWeather(city, Constants.APP_ID, unit).collect { result ->
                when (result) {
                    is NetworkResult.Success -> _stateWeather.value =
                        WeatherState(weather = result.data)
                    is NetworkResult.Failure -> _stateWeather.value =
                        WeatherState(error = result.errorResponse.message)
                    is NetworkResult.Loading -> _stateWeather.value = WeatherState(isLoading = true)
                }
            }
        }

    fun loadWeatherLatLngFromApi(lat: Double, lng: Double, unit: String) =
        viewModelScope.launch {
            weatherUseCase.invokeWeatherLatLang(lat, lng, Constants.APP_ID, unit)
                .collect { result ->
                    when (result) {
                        is NetworkResult.Success -> _stateWeather.value =
                            WeatherState(weather = result.data)
                        is NetworkResult.Failure -> _stateWeather.value =
                            WeatherState(error = result.errorResponse.message)
                        is NetworkResult.Loading -> _stateWeather.value =
                            WeatherState(isLoading = true)
                    }
                }
        }

    fun loadForecastLatLngFromApi(lat: Double, lng: Double, unit: String) =
        viewModelScope.launch {
            weatherUseCase.invokeForecastByLatLng(lat, lng, Constants.APP_ID, unit)
                .collect { result ->
                    when (result) {
                        is NetworkResult.Success -> _stateForecast.value =
                            ForecastState(forecast = result.data)
                        is NetworkResult.Failure -> _stateForecast.value =
                            ForecastState(error = result.errorResponse.message)
                        is NetworkResult.Loading -> _stateForecast.value =
                            ForecastState(isLoading = true)
                    }
                }
        }

    suspend fun setTempType(temp: String) {
        preferenceDataSource.setValue(Constants.TEMP_TYPE, temp)
    }

    suspend fun addToFavourite(weather: WeatherResponse){
        favouriteWeatherUseCase.insertFavourite(weather = weather)
    }
    suspend fun loadAllFavouritesFromDb() {
        favouriteWeatherUseCase.invokeFavourite().collect{ result->
            _stateFavourite.value= FavouriteState(result)
        }
    }


    fun weatherWork(context: Context, message: String ,title:String) {
        val constraints = Constraints.Builder()
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(false)
            .setRequiresBatteryNotLow(true)
            .build()
        val weatherRequest = PeriodicWorkRequest.Builder(
            WeatherWorker::class.java,
            15,
            TimeUnit.SECONDS,
        ).setConstraints(constraints).build()

        val myWorkRequest = OneTimeWorkRequestBuilder<WeatherWorker>()
            .setInitialDelay(24, TimeUnit.HOURS)
            .setInputData(workDataOf(
                "title" to title,
                "message" to message,
            )
            )
            .build()
        WorkManager
            .getInstance(context)
            .enqueue(myWorkRequest)


    }


}