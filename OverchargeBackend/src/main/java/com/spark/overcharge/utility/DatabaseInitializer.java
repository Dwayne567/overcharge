package com.spark.overcharge.utility;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spark.overcharge.entity.Card;
import com.spark.overcharge.entity.Deck;
import com.spark.overcharge.entity.User;
import com.spark.overcharge.enums.UserRole;
import com.spark.overcharge.repository.CardRepository;
import com.spark.overcharge.repository.DeckRepository;
import com.spark.overcharge.repository.UserRepository;

@Configuration
public class DatabaseInitializer {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, DeckRepository deckRepository,
                                             CardRepository cardRepository, BCryptPasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepository.count() == 1) {
				// User 1: bob
				User user1 = new User();
				user1.setRole(UserRole.USER);
				user1.setId(2L);
				user1.setEmail("bob@test.com");
				user1.setName("bob");
				user1.setPassword(passwordEncoder.encode("money"));
				user1.setImg(null);
				userRepository.save(user1);
	            
				// Final Fantasy XI - Gameplay deck
				Deck user1deck1 = new Deck();
				user1deck1.setUser(user1);
				user1deck1.setTitle("Final Fantasy XI - Gameplay");
				deckRepository.save(user1deck1);

				String[][] user1cards1 = { { "What is the main objective in Final Fantasy XI?",
						"The main objective in Final Fantasy XI is to defeat enemies, complete quests, and progress through the storyline." },
						{ "What are job classes in Final Fantasy XI?",
								"Job classes in Final Fantasy XI are professions or roles that characters can take on, such as Warrior, Black Mage, White Mage, Thief, and many more." },
						{ "What is the skill chaining system in Final Fantasy XI?",
								"Skill chaining is a gameplay mechanic where players use specific weapon skills or magic spells in a sequence to create chains, which increase damage and grant bonuses to party members." },
						{ "How does the party system work in Final Fantasy XI?",
								"Parties in Final Fantasy XI typically consist of up to six players. Each player can choose a job class to fulfill a specific role within the party, such as tanking, healing, or damage dealing." },
						{ "What are Limit Breaks in Final Fantasy XI?",
								"Limit Breaks are powerful abilities that characters can use after meeting certain conditions, such as accumulating enough TP (Tactical Points). These abilities often have significant effects and are crucial in difficult battles." } };

				for (String[] cardData : user1cards1) {
					Card card = new Card();
					card.setDeck(user1deck1);
					card.setUser(user1);
					card.setQuestion(cardData[0]);
					card.setAnswer(cardData[1]);
					cardRepository.save(card);
				}

				// Final Fantasy XI - Characters deck
				Deck user1deck2 = new Deck();
				user1deck2.setUser(user1);
				user1deck2.setTitle("Final Fantasy XI - Characters");
				deckRepository.save(user1deck2);

				String[][] user1cards2 = { { "Who are the prominent races in Final Fantasy XI?",
						"The prominent races in Final Fantasy XI include Humes, Elvaan, Tarutaru, Mithra, Galka, and the later introduced Moogle and Kuluu." },
						{ "What are the major cities in Final Fantasy XI?",
								"Major cities in Final Fantasy XI include Bastok, San d'Oria, Windurst, Jeuno, and the expansion-added cities such as Aht Urhgan, Tavnazia, and Adoulin." },
						{ "Who are the primary antagonists in Final Fantasy XI?",
								"The primary antagonists in Final Fantasy XI include the Shadow Lord, Promathia, and the various Beastmen tribes such as the Orcs, Quadav, and Yagudo." },
						{ "What is the role of the Crystal War in Final Fantasy XI's lore?",
								"The Crystal War is a significant event in Final Fantasy XI's lore, marking a conflict between the nations of Vana'diel and the Shadow Lord's forces over control of powerful crystals." },
						{ "Who are the leaders of the three main nations in Final Fantasy XI, and what are their titles?", "The leaders of the three main nations in Final Fantasy XI are: Bastok - President Karst Ironheart, San d''Oria - King Destin R d''Oraguille, Windurst - Chief Minister Ajido-Marujido." } };

				for (String[] cardData : user1cards2) {
					Card card = new Card();
					card.setDeck(user1deck2);
					card.setUser(user1);
					card.setQuestion(cardData[0]);
					card.setAnswer(cardData[1]);
					cardRepository.save(card);
				}

				// Final Fantasy XI - Lore deck
				Deck user1deck3 = new Deck();
				user1deck3.setUser(user1);
				user1deck3.setTitle("Final Fantasy XI - Lore");
				deckRepository.save(user1deck3);

				String[][] user1cards3 = { { "What is the significance of Linkshells in Final Fantasy XI?",
						"Linkshells are player-created chat channels or communities in Final Fantasy XI. They serve as a means for players to communicate, organize events, and form parties." },
						{ "How does the Trust system work in Final Fantasy XI?",
								"The Trust system allows players to summon NPC allies to aid them in battles. These NPCs are based on characters from the game's storyline and can be customized to fit different roles." },
						{ "What are some notable endgame activities in Final Fantasy XI?",
								"Notable endgame activities in Final Fantasy XI include Dynamis, where players battle through dreamlike zones to obtain powerful equipment, and Voidwatch, a system involving battling notorious monsters in various regions." },
						{ "How does the crafting system function in Final Fantasy XI?",
								"The crafting system in Final Fantasy XI allows players to create various items using materials obtained from gathering or defeating monsters. Players can level up their crafting skills to produce higher-quality items." },
						{ "What is the role of the Auction House in Final Fantasy XI's economy?",
								"The Auction House is a marketplace where players can buy and sell items to each other. It serves as a crucial component of the game's economy, allowing players to obtain rare materials and equipment." } };

				for (String[] cardData : user1cards3) {
					Card card = new Card();
					card.setDeck(user1deck3);
					card.setUser(user1);
					card.setQuestion(cardData[0]);
					card.setAnswer(cardData[1]);
					cardRepository.save(card);
				}
				
	            // User 2: bill
	            User user2 = new User();
	            user2.setRole(UserRole.USER);
	            user2.setId(3L);
	            user2.setEmail("bill@test.com");
	            user2.setName("bill");
	            user2.setPassword(passwordEncoder.encode("money"));
	            user2.setImg(null);
	            userRepository.save(user2);
	            
	            // Software Development deck
	            Deck user2deck1 = new Deck();
	            user2deck1.setUser(user2);
	            user2deck1.setTitle("Software Development");
	            deckRepository.save(user2deck1);

	            String[][] user2cards1 = {
	                {"What is the role of a software developer?", "Software developers design, code, and test software applications."},
	                {"Explain the Agile development methodology.", "Agile is an iterative and incremental approach to software development."},
	                {"What is version control?", "Version control is a system that records changes to a file or set of files over time so that you can recall specific versions later."},
	                {"Define Continuous Integration (CI).", "Continuous Integration is a development practice that requires developers to integrate code into a shared repository several times a day."},
	                {"What is the purpose of unit testing?", "Unit testing is the practice of testing individual units or pieces of code to ensure they work correctly."}
	            };

	            for (String[] cardData : user2cards1) {
	                Card card = new Card();
	                card.setDeck(user2deck1);
	                card.setUser(user2);
	                card.setQuestion(cardData[0]);
	                card.setAnswer(cardData[1]);
	                cardRepository.save(card);
	            }

	            // DevOps deck
	            Deck user2deck2 = new Deck();
	            user2deck2.setUser(user2);
	            user2deck2.setTitle("DevOps");
	            deckRepository.save(user2deck2);

	            String[][] user2cards2 = {
	                {"Explain the concept of DevOps.", "DevOps is a set of practices that combines software development (Dev) and IT operations (Ops) to shorten the systems development life cycle."},
	                {"What is Continuous Deployment?", "Continuous Deployment is the practice of automatically deploying every change that passes automated tests to production."},
	                {"Define Infrastructure as Code (IaC).", "Infrastructure as Code is the process of managing and provisioning computing infrastructure through machine-readable script files."},
	                {"Name popular DevOps tools.", "Popular DevOps tools include Jenkins, Docker, Ansible, and Kubernetes."},
	                {"What is the goal of continuous monitoring in DevOps?", "Continuous monitoring aims to ensure that applications and infrastructure are performing as expected and to identify and resolve issues quickly."}
	            };

	            for (String[] cardData : user2cards2) {
	                Card card = new Card();
	                card.setDeck(user2deck2);
	                card.setUser(user2);
	                card.setQuestion(cardData[0]);
	                card.setAnswer(cardData[1]);
	                cardRepository.save(card);
	            }

	            // Java deck
	            Deck user2deck3 = new Deck();
	            user2deck3.setUser(user2);
	            user2deck3.setTitle("Java");
	            deckRepository.save(user2deck3);

	            String[][] user2cards3 = {
	                {"What is Java?", "Java is a high-level, class-based, object-oriented programming language designed to have as few implementation dependencies as possible."},
	                {"Explain the difference between JDK and JRE.", "JDK (Java Development Kit) is a software development kit used to develop Java applications. JRE (Java Runtime Environment) is the runtime environment for running Java applications."},
	                {"What is the purpose of the \"public static void main\" method in Java?", "The \"public static void main\" method is the entry point of a Java program. It is the method that the Java Virtual Machine (JVM) calls to run the program."},
	                {"What is an interface in Java?", "An interface in Java is a collection of abstract methods. Classes implement interfaces to provide specific behavior."},
	                {"Explain exception handling in Java.", "Exception handling in Java involves using try, catch, and throw statements to handle runtime errors and exceptions."}
	            };

	            for (String[] cardData : user2cards3) {
	                Card card = new Card();
	                card.setDeck(user2deck3);
	                card.setUser(user2);
	                card.setQuestion(cardData[0]);
	                card.setAnswer(cardData[1]);
	                cardRepository.save(card);
	            }
			}
		};
	}

}
