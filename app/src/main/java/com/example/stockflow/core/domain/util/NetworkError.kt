package com.example.stockflow.core.domain.util

enum class NetworkError : Error {
    REQUEST_ERROR,
    TOO_MANY_REQUESTS,
    SERVER_ERROR,
    SERIALIZATION_ERROR,
    NO_INTERNET,
    UNKNOWN
}