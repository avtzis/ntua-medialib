package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import medialib.App;
import javafx.scene.Node;
import utils.Query;
import utils.ResourceLoader;
import utils.Models.Book;

public class HomeController implements Initializable {
  private Stage stage;
  private Scene scene;
  private Parent root;

  @FXML
  private ListView<String> topFiveList;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    List<Book> topFive = Query.topFive();

    for (Book book : topFive) {
      String fullTitle = book.getTitle() + "\nby " + book.getAuthor() + "\n" + book.getPublicationDate() + "\n" + String.format("%.1f", book.averageRating()) + "/5 (" + book.reviews() + " reviews)";
      topFiveList.getItems().add(fullTitle);
    }

    topFiveList.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if(event.getClickCount() == 2 && App.getCurrentUser() != null) {
          String selected = topFiveList.getSelectionModel().getSelectedItem();
          String title = selected.split("\n")[0];

          System.out.println("Selected book: " + title);

          BookController.setCurrentBook(App.getBookByTitle(title));
          try {
            switchToBook(event);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    });
  }

  public void switchToLogin(ActionEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/login.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToRegister(ActionEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/register.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToHome(ActionEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/Home.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToBrowse(ActionEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/browse.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToBorrows(ActionEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/borrows.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToBook(MouseEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/item.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void logout(ActionEvent event) throws IOException {
    App.setCurrentUser(null);
    System.out.println("Logout successful");

    switchToHome(event);
  }
}
