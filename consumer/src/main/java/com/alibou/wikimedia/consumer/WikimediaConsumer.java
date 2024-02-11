package com.alibou.wikimedia.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WikimediaConsumer {

    @KafkaListener(topics = "wikimedia-stream", groupId = "myGroup")
    public void consumeJsonMsg(String msg) {
        log.info(String.format("Consuming the message from wikimedia-stream Topic:: %s", msg));
        // Please feel free to do anything you want with the data
    }
}
