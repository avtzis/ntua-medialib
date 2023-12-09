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
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

import medialib.App;
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
  @FXML
  private TextField email;
  @FXML
  private TextField phoneNumber;
  @FXML
  private TextField firstName;
  @FXML
  private TextField lastName;
  @FXML
  private TextField address;
  @FXML
  private DatePicker dateofbirth;
  @FXML
  private TextField id;


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

  private void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Login Status");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  public void login(ActionEvent event) throws IOException {
    String username = this.username.getText();
    String password = this.password.getText();

    Admin admin = App.getAdminByUsername(username);
    if (admin != null && admin.getPassword().equals(password)) {
      switchToHome2(event);
      showAlert("Login successful");
      App.setCurrentUser(admin);
      return;
    }

    User user = App.getUserByUsername(username);
    if (user != null && user.getPassword().equals(password)) {
      switchToHome2(event);
      showAlert("Login successful");
      App.setCurrentUser(user);
    } else {
      showAlert("Invalid credentials");
    }
  }

  public void register(ActionEvent event) throws IOException {
    String username = this.username.getText();
    String password = this.password.getText();
    String email = this.email.getText();
    String phoneNumber = this.phoneNumber.getText();
    String firstName = this.firstName.getText();
    String lastName = this.lastName.getText();
    String address = this.address.getText();
    String dateofbirth = this.dateofbirth.getValue().toString();
    String id = this.id.getText();

    if(username.equals("") || password.equals("") || email.equals("") || phoneNumber.equals("") || firstName.equals("") || lastName.equals("") || address.equals("") || dateofbirth.equals("") || id.equals("")) {
      showAlert("Please fill in all fields");
      return;
    } else if (App.getUserByUsername(username) != null) {
      showAlert("Username already exists");
      return;
    } else if (App.getUserByEmail(email) != null) {
      showAlert("Email already exists");
      return;
    } else if (App.getUserById(id) != null) {
      showAlert("ID already exists");
      return;
    } else {
      System.out.println("username: " + username);
      System.out.println("password: " + password);
      System.out.println("email: " + email);
      System.out.println("phoneNumber: " + phoneNumber);
      System.out.println("firstName: " + firstName);
      System.out.println("lastName: " + lastName);
      System.out.println("address: " + address);
      System.out.println("dateofbirth: " + dateofbirth);
      System.out.println("id: " + id);
    }

    User user = new User(username, password, firstName, lastName, email, phoneNumber, address, dateofbirth, id);
    App.addUser(user);
    showAlert("Registration successful");
    App.setCurrentUser(user);
    switchToHome2(event);
  }
}
