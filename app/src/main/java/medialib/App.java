/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package medialib;

import java.util.List;

import utils.Parser;
import utils.Entities.Admin;
import utils.Entities.User;
import utils.Entities.Book;


public class App {
  private static List<Admin> admins;
  private static List<User> users;
  private static List<Book> books;

  public static void populate() {
    Parser parser = new Parser();

    parser.parse();

    admins = parser.getAdmins();
    users = parser.getUsers();
    books = parser.getBooks();
  }

  public String getGreeting() {
    return "Hello World!";
  }

  public static void printAdmins() {
    System.out.println("-----------------Admins-----------------");
    for (Admin admin : admins) {
      System.out.println(admin.getUsername());
    }
  }
  public static void printUsers() {
    System.out.println("-----------------Users-----------------");
    for (User user : users) {
      System.out.println(user.getUsername());
    }
  }
  public static void printBooks() {
    System.out.println("-----------------Books-----------------");
    for (Book book : books) {
      System.out.println(book.getTitle());
    }
  }
  public static void printAll() {
    printAdmins();
    printUsers();
    printBooks();
  }

  public static void main(String[] args) {
    System.out.println(new App().getGreeting());

    populate();
    printAll();
  }
}
