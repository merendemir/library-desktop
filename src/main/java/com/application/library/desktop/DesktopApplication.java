package com.application.library.desktop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class DesktopApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SpringApplication.run(DesktopApplication.class, args);
        });
    }

}
