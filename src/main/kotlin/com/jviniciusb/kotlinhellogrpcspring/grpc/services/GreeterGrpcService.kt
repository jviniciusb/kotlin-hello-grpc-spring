package com.jviniciusb.kotlinhellogrpcspring.grpc.services

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import org.lognet.springboot.grpc.GRpcService
import proto.greeter.v1.Greeter
import proto.greeter.v1.GreeterServiceGrpcKt

@GRpcService
class GreeterGrpcService : GreeterServiceGrpcKt.GreeterServiceCoroutineImplBase() {

    override suspend fun sayHello(request: Greeter.HelloRequest): Greeter.HelloReply {
        delay(COMPUTATION_SIMULATION_TIME)
        return Greeter.HelloReply.newBuilder().setMessage("Hello ${request.name}!").build()
    }

    override suspend fun sayHelloToStream(requests: Flow<Greeter.HelloRequest>): Greeter.HelloReply {
        val names = mutableListOf<String>()
        requests.collect {
            names.add(it.name)
        }
        return Greeter.HelloReply.newBuilder().setMessage("Hello ${names.joinToString()}!").build()
    }

    override fun sayStreamHello(request: Greeter.HelloRequest): Flow<Greeter.HelloReply> = flow {
        repeat(5) {
            delay(COMPUTATION_SIMULATION_TIME)
            emit(Greeter.HelloReply.newBuilder().setMessage("Hello ${request.name} $it!").build())
        }
    }

    override fun sayStreamHelloToStream(requests: Flow<Greeter.HelloRequest>): Flow<Greeter.HelloReply> {
        return requests.transform { request ->
            repeat(2) {
                delay(COMPUTATION_SIMULATION_TIME)
                emit(Greeter.HelloReply.newBuilder().setMessage("Hello ${request.name} $it!").build())
            }
        }
    }

    private companion object {
        // Value in milliseconds to be used with dealy() to simulates some computation
        const val COMPUTATION_SIMULATION_TIME = 1000L
    }
}