package utils;

import java.io.IOException;
import java.util.List;
import java.io.File;
import java.io.FileReader;

import com.fasterxml.jackson.databind.ObjectMapper;

import utils.Models.Admin;
import utils.Models.User;
import utils.Models.Book;
import utils.Models.Category;

/**
 * The Parser class is responsible for parsing JSON files and providing access to the parsed data.
 */
public class Parser {
  private static List<Admin> admins;
  private static List<User> users;
  private static List<Book> books;
  private static List<Category> categories;

  /**
   * Parses the JSON files and populates the corresponding lists.
   */
  public void parse() {
    String adminPath = "src/main/resources/models/admins.json";
    String userPath = "src/main/resources/models/users.json";
    String bookPath = "src/main/resources/models/books.json";
    String categoryPath = "src/main/resources/models/categories.json";

    File adminFile = new File(adminPath);
    File userFile = new File(userPath);
    File bookFile = new File(bookPath);
    File categoryFile = new File(categoryPath);

    try {
      ObjectMapper om = new ObjectMapper();

      if(adminFile.exists()) admins = om.readValue(new FileReader(adminPath), om.getTypeFactory().constructCollectionType(List.class, Admin.class));
      if(userFile.exists()) users = om.readValue(new FileReader(userPath), om.getTypeFactory().constructCollectionType(List.class, User.class));
      if(bookFile.exists()) books = om.readValue(new FileReader(bookPath), om.getTypeFactory().constructCollectionType(List.class, Book.class));
      if(categoryFile.exists()) categories = om.readValue(new FileReader(categoryPath), om.getTypeFactory().constructCollectionType(List.class, Category.class));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns the list of admins.
   *
   * @return the list of admins
   */
  public List<Admin> getAdmins() {
    return admins;
  }

  /**
   * Returns the list of users.
   *
   * @return the list of users
   */
  public List<User> getUsers() {
    return users;
  }

  /**
   * Returns the list of books.
   *
   * @return the list of books
   */
  public List<Book> getBooks() {
    return books;
  }

  /**
   * Returns the list of categories.
   *
   * @return the list of categories
   */
  public List<Category> getCategories() {
    return categories;
  }
}
