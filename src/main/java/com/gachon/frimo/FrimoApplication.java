package com.gachon.frimo;

import java.util.Collections;

import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FrimoApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FrimoApplication.class);

        app.setDefaultProperties(Collections

            .singletonMap("server.port", "80"));

        app.run(args);

    }
    // private static SshTunneling tunnel;

    // public FrimoApplication() {
    // tunnel = new SshTunneling().init( res->{
    // if(!res) {
    // System.out.println("PortForwarding Failed, 연결종료");
    // System.exit(0);
    // }
    // });
    // }

    // //터널링한 커넥션을 끊어주도록
    // @PreDestroy
    // public void end() {
    // try {
    // tunnel.shutdown();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

}
