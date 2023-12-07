/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package medialib;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.enums.ButtonType;

import org.checkerframework.checker.units.qual.s;

import controllers.AppController;
import controllers.HomeController;
import utils.Parser;
import utils.Writer;

import utils.Models.Admin;
import utils.Models.User;
import utils.Models.Book;


public class App extends Application {
  private static List<Admin> admins;
  private static List<User> users;
  private static List<Book> books;
  private static User currentUser;

  public static void initialize() {
    Parser parser = new Parser();

    parser.parse();

    admins = parser.getAdmins();
    users = parser.getUsers();
    books = parser.getBooks();
  }

  public static void save() {
    Writer.write(users, books);
  }

  public String getGreeting() {
    return "\n\n\nHello World!\n\n\n";
  }

  public static void printAdmins() {
    System.out.println("-----------------Admins-----------------");
    for (Admin admin : admins) {
      System.out.println(admin.getUsername());
    }
  }
  public static void printUsers() {
    System.out.println("-----------------Users------------------");
    for (User user : users) {
      System.out.println(user.getUsername());
    }
  }
  public static void printBooks() {
    System.out.println("-----------------Books------------------");
    for (Book book : books) {
      System.out.println(book.getTitle());
    }
  }
  public static void printAll() {
    printAdmins();
    printUsers();
    printBooks();
  }

  public static void randomize() {
    Writer.populate();
  }

  public static void main(String[] args) {
    System.out.println(new App().getGreeting());

    if(false) {
      randomize();
    }

    initialize();
    // printAll();
    System.out.println("\n\nInitialization complete.\n\n\n");

    launch(args);

    save();
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Parent root = FXMLLoader.load(getClass().getResource("App.fxml"));
    // Group root = new Group();

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Home.fxml"));
    // loader.setControllerFactory(c -> new HomeController(primaryStage));
    Parent root = loader.load();

    UserAgentBuilder.builder()
      .themes(JavaFXThemes.MODENA)
      .themes(MaterialFXStylesheets.forAssemble(true))
      .setDeploy(true)
      .setResolveAssets(true)
      .build()
      .setGlobal();

    // MFXButton button = new MFXButton("Click me!");
    // button.setOnAction(e -> System.out.println("Button clicked!"));
    // button.setButtonType(ButtonType.RAISED);

    // StackPane root = new StackPane();
    // root.getChildren().add(button);

    primaryStage.setTitle("Medialab Library");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  public static Admin getAdminByUsername(String username) {
    for (Admin admin : admins) {
      if (admin.getUsername().equals(username)) {
        return admin;
      }
    }
    return null; // Admin not found
  }

  public static User getUserByUsername(String username) {
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        return user;
      }
    }
    return null; // User not found
  }

  public static void setCurrentUser(User user) {
    currentUser = user;
  }
  public static User getCurrentUser() {
    return currentUser;
  }

}
