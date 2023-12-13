package com.spark.millhouse.initializer;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spark.millhouse.model.Card;
import com.spark.millhouse.model.Deck;
import com.spark.millhouse.repository.CardRepository;
import com.spark.millhouse.repository.DeckRepository;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;

    public DatabaseInitializer(DeckRepository deckRepository, CardRepository cardRepository) {
        this.deckRepository = deckRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create 'Software Development' deck
        Deck softwareDevelopmentDeck = new Deck();
        softwareDevelopmentDeck.setTitle("Software Development");
        deckRepository.save(softwareDevelopmentDeck);

        // Save 5 cards for the 'Software Development' deck
        cardRepository.saveAll(List.of(
                new Card("What is the role of a software developer?", "Software developers design, code, and test software applications.", softwareDevelopmentDeck),
                new Card("Explain the Agile development methodology.", "Agile is an iterative and incremental approach to software development.", softwareDevelopmentDeck),
                new Card("What is version control?", "Version control is a system that records changes to a file or set of files over time so that you can recall specific versions later.", softwareDevelopmentDeck),
                new Card("Define Continuous Integration (CI).", "Continuous Integration is a development practice that requires developers to integrate code into a shared repository several times a day.", softwareDevelopmentDeck),
                new Card("What is the purpose of unit testing?", "Unit testing is the practice of testing individual units or pieces of code to ensure they work correctly.", softwareDevelopmentDeck)
        ));

        // Create 'DevOps' deck
        Deck devOpsDeck = new Deck();
        devOpsDeck.setTitle("DevOps");
        deckRepository.save(devOpsDeck);

        // Save 5 cards for the 'DevOps' deck
        cardRepository.saveAll(List.of(
                new Card("Explain the concept of DevOps.", "DevOps is a set of practices that combines software development (Dev) and IT operations (Ops) to shorten the systems development life cycle.", devOpsDeck),
                new Card("What is Continuous Deployment?", "Continuous Deployment is the practice of automatically deploying every change that passes automated tests to production.", devOpsDeck),
                new Card("Define Infrastructure as Code (IaC).", "Infrastructure as Code is the process of managing and provisioning computing infrastructure through machine-readable script files.", devOpsDeck),
                new Card("Name popular DevOps tools.", "Popular DevOps tools include Jenkins, Docker, Ansible, and Kubernetes.", devOpsDeck),
                new Card("What is the goal of continuous monitoring in DevOps?", "Continuous monitoring aims to ensure that applications and infrastructure are performing as expected and to identify and resolve issues quickly.", devOpsDeck)
        ));

        // Create 'Java' deck
        Deck javaDeck = new Deck();
        javaDeck.setTitle("Java");
        deckRepository.save(javaDeck);

        // Save 5 cards for the 'Java' deck
        cardRepository.saveAll(List.of(
                new Card("What is Java?", "Java is a high-level, class-based, object-oriented programming language designed to have as few implementation dependencies as possible.", javaDeck),
                new Card("Explain the difference between JDK and JRE.", "JDK (Java Development Kit) is a software development kit used to develop Java applications. JRE (Java Runtime Environment) is the runtime environment for running Java applications.", javaDeck),
                new Card("What is the purpose of the \"public static void main\" method in Java?", "The \"public static void main\" method is the entry point of a Java program. It is the method that the Java Virtual Machine (JVM) calls to run the program.", javaDeck),
                new Card("What is an interface in Java?", "An interface in Java is a collection of abstract methods. Classes implement interfaces to provide specific behavior.", javaDeck),
                new Card("Explain exception handling in Java.", "Exception handling in Java involves using try, catch, and throw statements to handle runtime errors and exceptions.", javaDeck)
        ));
    }
}


