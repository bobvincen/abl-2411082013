package com.habibi.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Autowired
    private JavaMailSender mailSender;

    // Mengambil alamat email pengirim dari application.properties
    @Value("${spring.mail.username}")
    private String pengirim;

    // 1. Method untuk mendengarkan pesan masuk dari RabbitMQ
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void terimaPesan(String pesan) {
        System.out.println("=========================================");
        System.out.println("Pesan masuk dari Producer: " + pesan);

        // Langsung memanggil method kirimEmail di bawah
        String emailTujuan = "habibiariskiclg@gmail.com"; // Ganti dengan email tujuan Anda
        String subjek = "Notifikasi Pesanan Baru (RabbitMQ)";
        String isiEmail = "Halo,\n\nSistem Consumer baru saja menerima pesan dari Producer:\n" + pesan;

        kirimEmail(emailTujuan, subjek, isiEmail);
        System.out.println("=========================================");
    }

    // 2. Method tambahan khusus untuk mengirim email
    public void kirimEmail(String tujuan, String subjek, String isiPesan) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(pengirim);
            message.setTo(tujuan);
            message.setSubject(subjek);
            message.setText(isiPesan);

            mailSender.send(message);
            System.out.println("Email berhasil dikirim ke: " + tujuan);
        } catch (Exception e) {
            System.err.println("Gagal mengirim email: " + e.getMessage());
        }
    }
}