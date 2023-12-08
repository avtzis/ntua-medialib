package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import org.checkerframework.checker.units.qual.t;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import medialib.App;
import utils.Models.Book;
import utils.Models.User;
import utils.Models.User.Borrows;
import utils.Query;
import utils.ResourceLoader;

public class BorrowController implements Initializable {
  private Stage stage;
  private Scene scene;
  private Parent root;

  @FXML
  private ListView<String> bookList;

  @FXML
  private MenuButton active;

  public void switchToHome(ActionEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/Home2.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    User currentUser = App.getCurrentUser();

    if(bookList != null) {
      List<Borrows> activeBorrows = currentUser.activeBorrows();
      for (Borrows borrow : activeBorrows) {
        String status = borrow.isActive() ? "Active" : "Returned";
        String fullTitle = "(" + status + ") " + borrow.getBook() + "\nISBN: " + borrow.getISBN() + "\nBorrowed: " + borrow.getDate() + "\nReturn: " + borrow.getReturnDate();
        bookList.getItems().add(fullTitle);
      }
    }

    if(this.active != null) {
      MenuItem trueItem = new MenuItem("True");
      this.active.getItems().add(trueItem);
      trueItem.setOnAction(event -> {
        try {
          filterByActive(event);
        } catch (IOException e) {
          e.printStackTrace();
        }
      });

      MenuItem falseItem = new MenuItem("False");
      this.active.getItems().add(falseItem);
      falseItem.setOnAction(event -> {
        try {
          filterByActive(event);
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    }
  }

  public void filterByActive(ActionEvent event) throws IOException {
    MenuItem item = (MenuItem) event.getSource();
    String statusItem = item.getText();

    System.out.println("Filtering by category: " + statusItem);

    List<Borrows> borrows = Query.filterByActive(statusItem);

    bookList.getItems().clear();

    for (Borrows borrow : borrows) {
      String status = borrow.isActive() ? "Active" : "Returned";
      String fullTitle = "(" + status + ") " + borrow.getBook() + "\nISBN: " + borrow.getISBN() + "\nBorrowed: " + borrow.getDate() + "\nReturn: " + borrow.getReturnDate();
      bookList.getItems().add(fullTitle);
    }
  }
}
