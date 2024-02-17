package com.application.library.desktop;

import com.application.library.desktop.gui.login.LoginFrame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class DesktopApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConfigurableApplicationContext run = SpringApplication.run(DesktopApplication.class, args);
            LoginFrame bean = run.getBean(LoginFrame.class);
            bean.showFrame();
        });
    }

}
