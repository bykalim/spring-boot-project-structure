package com.kalim.cms.utils

class BaseResponse {
    companion object {
        fun success(data: Any?, message: String): Map<String, Any?> {
            return mapOf(
                "status" to "success",
                "message" to message,
                "data" to data
            )
        }

        fun error(data: Any?, message: String): Map<String, Any?> {
            return mapOf(
                "status" to "error",
                "message" to message,
                "data" to data,
            )
        }

    }
}