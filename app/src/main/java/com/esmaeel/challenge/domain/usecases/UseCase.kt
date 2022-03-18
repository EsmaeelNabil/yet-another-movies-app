package com.esmaeel.challenge.domain.usecases

interface UseCase<ReturnType> {
    suspend fun invoke(): ReturnType
}