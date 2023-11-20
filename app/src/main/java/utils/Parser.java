package utils;

import java.io.IOException;
import java.util.List;
import java.io.FileReader;

import com.fasterxml.jackson.databind.ObjectMapper;

import utils.Models.Admin;
import utils.Models.User;
import utils.Models.Book;

public class Parser {
  private static List<Admin> admins;
  private static List<User> users;
  private static List<Book> books;

  public static void parse() {
    try {
      ObjectMapper om = new ObjectMapper();

      admins = om.readValue(new FileReader("src/main/resources/models/admins.json"), om.getTypeFactory().constructCollectionType(List.class, Admin.class));
      users = om.readValue(new FileReader("src/main/resources/models/users.json"), om.getTypeFactory().constructCollectionType(List.class, User.class));
      books = om.readValue(new FileReader("src/main/resources/models/books.json"), om.getTypeFactory().constructCollectionType(List.class, Book.class));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<Admin> getAdmins() {
    return admins;
  }
  public List<User> getUsers() {
    return users;
  }
  public List<Book> getBooks() {
    return books;
  }
}
