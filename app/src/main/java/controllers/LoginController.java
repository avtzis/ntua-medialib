package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import medialib.App;
import javafx.scene.Node;

import utils.ResourceLoader;
import utils.Models.Admin;
import utils.Models.User;

public class LoginController implements Initializable {
  private Stage stage;
  private Scene scene;
  private Parent root;

  @FXML
  private TextField username;
  @FXML
  private TextField password;

  @Override
  public void initialize(URL location, ResourceBundle resources) {}

  public void switchToHome(ActionEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/Home.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToHome2(ActionEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/Home2.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void login(ActionEvent event) throws IOException {
    String username = this.username.getText();
    String password = this.password.getText();

    Admin admin = App.getAdminByUsername(username);
    if (admin != null && admin.getPassword().equals(password)) {
      switchToHome2(event);
      System.out.println("Login successful");
      App.setCurrentUser(admin);
      return;
    }

    User user = App.getUserByUsername(username);
    if (user != null && user.getPassword().equals(password)) {
      switchToHome2(event);
      System.out.println("Login successful");
      App.setCurrentUser(user);
    } else {
      System.out.println("User not found");
    }
  }
}
