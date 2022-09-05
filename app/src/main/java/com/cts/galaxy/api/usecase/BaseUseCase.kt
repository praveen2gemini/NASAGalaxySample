package com.cts.galaxy.api.usecase

import com.cts.galaxy.api.Resource
import kotlinx.coroutines.*

abstract class BaseUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Resource<Type>

    open operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (Resource<Type>) -> Unit = {}
    ) {
        val backgroundJob = scope.async { run(params) }
        scope.launch { onResult(backgroundJob.await()) }
    }
}