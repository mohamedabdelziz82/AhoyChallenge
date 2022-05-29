package com.mohamedabdelaziz.ahoytask.viewmodel

import com.mohamedabdelaziz.ahoytask.core.utils.preference.PreferenceDataSourceImpl
import com.mohamedabdelaziz.ahoytask.coroutines.CoroutinesTestRule
import com.mohamedabdelaziz.ahoytask.data.remote.ApiService
import com.mohamedabdelaziz.ahoytask.domain.repository.WeatherRepository
import com.mohamedabdelaziz.ahoytask.domain.usecases.FavouriteWeatherUseCase
import com.mohamedabdelaziz.ahoytask.domain.usecases.WeatherUseCase
import com.mohamedabdelaziz.ahoytask.presentation.WeatherViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    @get:Rule
    var coroutinesTestRules = CoroutinesTestRule()
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var repositoryRemote: WeatherRepository
    private lateinit var weatherUseCase: WeatherUseCase
    private lateinit var preferenceDataSource: PreferenceDataSourceImpl
    private lateinit var favouriteWeatherUseCase: FavouriteWeatherUseCase
    private lateinit var weatherViewModel: WeatherViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        weatherUseCase = WeatherUseCase(repositoryRemote)
        weatherViewModel = WeatherViewModel(
            weatherUseCase = weatherUseCase,
            preferenceDataSource = preferenceDataSource,
            favouriteWeatherUseCase = favouriteWeatherUseCase
        )
        weatherViewModel.loadWeatherFromApi("tanta","metric")
    }

    @Test
    fun getWeather() = runTest {
        setUp()
        Assert.assertEquals(
            weatherViewModel.stateWeather.value.weather.weather.size,
            4
        )
    }
}


