package utils;

import java.util.List;
import java.util.ArrayList; // Add this import statement
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import utils.Models.Admin;
import utils.Models.User;
import utils.Models.Book;

public class Writer {
  public static void write(/* List<Admin> admins, */ List<User> users, List<Book> books) {
    try {
      ObjectMapper om = new ObjectMapper();

      // om.writeValue(new File("src/main/resources/admins.json"), admins);
      om.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/models/users.json"), users);
      om.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/models/books.json"), books);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void populate() {
    List<User> users = new ArrayList<>(); // Initialize the users list
    List<Book> books = new ArrayList<>(); // Initialize the books list

    Faker faker = new Faker();

    for(int i=0; i<10; ++i) {
      users.add(new User(faker.name().username(), faker.internet().password(), faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), faker.phoneNumber().phoneNumber(), faker.address().fullAddress(), faker.date().birthday().toString(), faker.idNumber().valid()));

      List<Book.Review> reviews = new ArrayList<>();
      for(int j=0; j<faker.number().randomDigitNotZero(); ++j) {
        reviews.add(new Book.Review(
          faker.number().numberBetween(1, 5),
          faker.lorem().sentence(),
          Reaction.values()[faker.number().numberBetween(0, Reaction.values().length)],
          faker.name().username()
        ));
      }

      int copies = faker.number().numberBetween(1, 20);

      books.add(new Book(
        faker.book().title(),
        faker.book().author(),
        faker.book().genre(),
        faker.book().publisher(),
        faker.nation().language(),
        faker.number().numberBetween(1980, 2023),
        faker.number().numberBetween(50, 1500),
        faker.lorem().paragraph(),
        copies,
        faker.number().numberBetween(0, copies),
        reviews
      ));
    }

    write(users, books);
  }
}
