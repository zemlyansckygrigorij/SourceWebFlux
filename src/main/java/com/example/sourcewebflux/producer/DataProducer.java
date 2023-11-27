package com.example.sourcewebflux.producer;

public interface DataProducer<T> {

    T produce(long seed);
}