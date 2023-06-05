package com.nadia.hw4;

import com.nadia.hw4.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.NoSuchElementException;

@Slf4j
@SpringBootApplication
public class Hw4SpringDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw4SpringDataApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(PersonRepository repository) {
        return args -> {
            repository
               .findOneByName("Taras")
               .ifPresentOrElse(
                  personEntity -> log.info("Found by name: {}\n", personEntity),
                  () -> {
                      throw new NoSuchElementException("Taras not found");
                  }
               );

            log.info("Persons aged between 20 and 40: ");
            repository.findAllByAgeBetween(20, 40).forEach(personEntity -> log.info(personEntity.toString()));
            log.info("\n");

            repository
               .findOneByName("Grigoriy")
               .ifPresentOrElse(
                  personEntity -> {
                      log.info("Entity before updating name: {}", personEntity);

                      repository.updateNameById(personEntity.getId(), "Grigoriy renamed");

                      repository
                         .findById(personEntity.getId())
                         .ifPresent(updatedEntity -> log.info("Entity after updating name: {}\n", updatedEntity));
                  },
                  () -> {
                      throw new NoSuchElementException("Grigoriy not found");
                  });

            log.info("Records with name = Paraska: ");
            repository.findAllByName("Paraska").forEach(personEntity -> log.info(personEntity.toString()));

            log.info("Deleting all persons with name = Paraska..");
            repository.deleteAllByName("Paraska");
            log.info("Done");

            repository.findOneByName("Paraska").ifPresent(personEntity -> log.info(personEntity.toString()));
        };
    }
}
