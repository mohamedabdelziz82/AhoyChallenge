package com.mohamedabdelaziz.ahoytask.core.utils.commons


abstract class BaseApiErrorResponse(
    @field:Transient var httpCode: Int,
    @field:Transient open val details: String
) {
    abstract val message: String?
    abstract val description: String?
}
