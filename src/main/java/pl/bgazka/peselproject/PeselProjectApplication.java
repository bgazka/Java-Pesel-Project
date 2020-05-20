package pl.bgazka.peselproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class PeselProjectApplication {

  public static void main(String[] args) {
    SpringApplication.run(PeselProjectApplication.class, args);
  }

  @Bean
  public Scanner scanner() {
    return new Scanner(System.in);
  }
}
