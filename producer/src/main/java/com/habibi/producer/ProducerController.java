package com.habibi.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @GetMapping("/kirim")
    public String kirim(@RequestParam(defaultValue = "Halo dari Spring Boot Habibi!") String pesan) {
        producerService.kirimPesan(pesan);
        return "Berhasil mengirim pesan ke RabbitMQ: " + pesan;
    }
}