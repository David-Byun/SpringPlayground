package com.mang.atdd.membership;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

@SpringBootApplication
public class MembershipApplication {

    public static void main(String[] args) {
        SpringApplication.run(MembershipApplication.class, args);

    }

}
