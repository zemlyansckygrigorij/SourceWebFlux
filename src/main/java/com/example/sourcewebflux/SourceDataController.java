package com.example.sourcewebflux;

import com.example.sourcewebflux.producer.DataProducer;
import com.example.sourcewebflux.producer.StringValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class SourceDataController {
    private static final Logger log = LoggerFactory.getLogger(SourceDataController.class);

    private final DataProducer<Flux<StringValue>> dataProducer;

    public SourceDataController(DataProducer<Flux<StringValue>> dataProducer) {
        this.dataProducer = dataProducer;
    }

    @GetMapping(value = "/data/{seed}", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<StringValue> data(@PathVariable("seed") long seed) {
        log.info("request for string data, seed:{}", seed);
        var stringData = dataProducer.produce(seed);

        log.info("Method request for string data done");
        return stringData;
    }
}