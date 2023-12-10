package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Pair;
import medialib.App;
import utils.Models.Book;
import utils.Models.Category;
import utils.Models.User;
import utils.Models.Book.Review;
import utils.Models.User.Borrows;
import utils.Query;
import utils.ResourceLoader;

public class AdminController implements Initializable {
  private Stage stage;
  private Scene scene;
  private Parent root;
  private static Category currentCategory;
  private static User currentUser;

  @FXML
  private Label title;
  @FXML
  private Label author;
  @FXML
  private Label publisher;
  @FXML
  private Label isbn;
  @FXML
  private Label date;
  @FXML
  private Label available;
  @FXML
  private Label average;
  @FXML
  private Label category;
  @FXML
  private Label reviews;

  @FXML
  private ListView<String> reviewList;
  @FXML
  private ListView<String> borrowList;

  @FXML
  private TextField titleField;
  @FXML
  private TextField authorField;
  @FXML
  private TextField publisherField;
  @FXML
  private TextField copiesField;
  @FXML
  private TextField dateField;
  @FXML
  private TextField pagesField;
  @FXML
  private TextField isbnField;
  @FXML
  private TextArea descriptionField;
  @FXML
  private ChoiceBox<String> categoryField;
  @FXML
  private Button submitButton;

  @FXML
  private ListView<String> categoryList;
  @FXML
  private ListView<String> userList;

  @FXML
  private TextField categoryTitleField;

  @FXML
  private TextField username;
  @FXML
  private TextField password;
  @FXML
  private TextField email;
  @FXML
  private TextField firstName;
  @FXML
  private TextField lastName;
  @FXML
  private TextField address;
  @FXML
  private TextField phoneNumber;
  @FXML
  private TextField id;
  @FXML
  private DatePicker dateofbirth;

  public void initialize(URL location, ResourceBundle resources) {
    Book currentBook = BookController.getCurrentBook();
    List<Category> categories = App.getCategories();

    if(currentBook != null && title != null) {
      Book book = App.getBookByTitle(currentBook.getTitle());
      title.setText(book.getTitle());
      author.setText(book.getAuthor());
      publisher.setText(book.getPublisher());
      isbn.setText(String.valueOf(book.getISBN()));
      date.setText(String.valueOf(book.getPublicationDate()));
      available.setText(String.valueOf(book.getCopies() - book.getBorrows()));
      average.setText(String.format("%.1f", book.averageRating()));
      category.setText(book.getGenre());
      reviews.setText(String.valueOf(book.getReviews().size()));

      reviewList.getItems().clear();
      for (Review review : book.getReviews()) {
        String fullReview = review.getUsername() + ": " + review.getRating() + "/5 - " + review.getComment();
        reviewList.getItems().add(fullReview);
      }
    }

    if(currentBook != null && titleField != null) {
      titleField.setText(currentBook.getTitle());
      authorField.setText(currentBook.getAuthor());
      publisherField.setText(currentBook.getPublisher());
      copiesField.setText(String.valueOf(currentBook.getCopies()));
      dateField.setText(String.valueOf(currentBook.getPublicationDate()));
      pagesField.setText(String.valueOf(currentBook.getPages()));
      isbnField.setText(String.valueOf(currentBook.getISBN()));
      descriptionField.setText(currentBook.getDescription());
      categoryField.setValue(currentBook.getGenre());
    }

    if(categoryField != null) {
      for (Category category : categories) {
        categoryField.getItems().add(category.getTitle());
      }
    }

    if(categoryList != null) {
      for (Category category : categories) {
        categoryList.getItems().add(category.getTitle());
      }

      categoryList.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          if(event.getClickCount() == 2) {
            String item = categoryList.getSelectionModel().getSelectedItem();
            Category category = App.getCategoryByTitle(item);
            System.out.println("Selected category: " + category.getTitle());
            currentCategory = category;

            try {
              System.out.println("Switching to category page: " + category.getTitle());
              switchToEditCategory(event);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      });
    }

    if(categoryTitleField != null && currentCategory != null) {
      if(currentCategory != null) {
        categoryTitleField.setText(currentCategory.getTitle());
      }
    }

    if(userList != null) {
      for (User user : App.getUsers()) {
        String username = user.getUsername();
        userList.getItems().add(username);
      }

      userList.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          if(event.getClickCount() == 2) {
            String item = userList.getSelectionModel().getSelectedItem();
            User user = App.getUserByUsername(item);
            System.out.println("Selected user: " + user.getUsername());
            currentUser = user;

            try {
              System.out.println("Switching to user page: " + user.getUsername());
              switchToEditUser(event);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      });
    }

    if(currentUser != null && username != null) {
      username.setText(currentUser.getUsername());
      password.setText(currentUser.getPassword());
      email.setText(currentUser.getEmail());
      firstName.setText(currentUser.getFirstName());
      lastName.setText(currentUser.getLastName());
      address.setText(currentUser.getAddress());
      phoneNumber.setText(currentUser.getPhoneNumber());
      id.setText(currentUser.getId());
      dateofbirth.setValue(LocalDate.parse(currentUser.getDateofbirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    if(borrowList != null) {
      for (Pair<String, Borrows> fullBorrow : Query.getAllActiveBorrows()) {
        String username = fullBorrow.getKey();
        Borrows borrow = fullBorrow.getValue();
        String status = "Active";
        String fullTitle = "(" + status + ") " + borrow.getBook() + "\nHolder: " + username + "\nISBN: " + borrow.getISBN() + "\nBorrowed: " + borrow.getDate() + "\nReturn: " + borrow.getReturnDate();
        borrowList.getItems().add(fullTitle);
      }
    }
  }

  public void switchToBrowse(ActionEvent event) throws IOException {
    BookController.setCurrentBook(null);
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/browse.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToBorrows(ActionEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/admin-borrow.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToUsers(ActionEvent event) throws IOException {
    currentUser = null;
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/browse-user.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToCategories(ActionEvent event) throws IOException {
    currentCategory = null;
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/browse-categ.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToHome(ActionEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/Home3.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void delete(ActionEvent event) throws IOException {
    Book currentBook = BookController.getCurrentBook();
    String title = currentBook.getTitle();

    Query.removeBook(currentBook);

    System.out.println("Deleted book: " + title);
    switchToBrowse(event);
  }

  public void switchToEdit(ActionEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/book-edit.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToAddCategory(ActionEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/add-categ.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToEditCategory(MouseEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/edit-categ.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToEditUser(MouseEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/edit-user.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void submitAdd(ActionEvent event) throws IOException {
    String title = titleField.getText();
    String author = authorField.getText();
    String publisher = publisherField.getText();
    String copies = copiesField.getText();
    String date = dateField.getText();
    String pages = pagesField.getText();
    String isbn = isbnField.getText();
    String description = descriptionField.getText();
    String category = categoryField.getValue();

    if(title.isEmpty() || author.isEmpty() || publisher.isEmpty() || copies.isEmpty() || date.isEmpty() || pages.isEmpty() || isbn.isEmpty() || description.isEmpty() || category.isEmpty()) {
      showAlert("Please fill in all fields");
      return;
    }

    Book newBook = new Book(title, author, category, publisher, "English", Integer.parseInt(date), Integer.parseInt(pages), description, Integer.parseInt(copies), 0, isbn);
    App.addBook(newBook);
    System.out.println("Added book: " + title);
    switchToBrowse(event);
  }

  public void submitEdit(ActionEvent event) throws IOException {
    String title = titleField.getText();
    String author = authorField.getText();
    String publisher = publisherField.getText();
    String copies = copiesField.getText();
    String date = dateField.getText();
    String pages = pagesField.getText();
    String isbn = isbnField.getText();
    String description = descriptionField.getText();
    String category = categoryField.getValue();

    if(title.isEmpty() || author.isEmpty() || publisher.isEmpty() || copies.isEmpty() || date.isEmpty() || pages.isEmpty() || isbn.isEmpty() || description.isEmpty() || category.isEmpty()) {
      showAlert("Please fill in all fields");
      return;
    }

    Book currentBook = BookController.getCurrentBook();
    String originalTitle = currentBook.getTitle();

    currentBook.setTitle(title);
    currentBook.setAuthor(author);
    currentBook.setPublisher(publisher);
    currentBook.setGenre(category);
    currentBook.setPublicationDate(Integer.parseInt(date));
    currentBook.setPages(Integer.parseInt(pages));
    currentBook.setISBN(isbn);
    currentBook.setDescription(description);
    currentBook.setCopies(Integer.parseInt(copies));

    Query.changeBookTitle(originalTitle, title, isbn);

    System.out.println("Edited book: " + title);
    switchToBrowse(event);
  }

  private void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Management");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  public void logout(ActionEvent event) throws IOException {
    App.setCurrentUser(null);
    System.out.println("Logout successful");

    switchToHome1(event);
  }

  public void switchToHome1(ActionEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/Home.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();

  }

  public void submitEditCategory(ActionEvent event) throws IOException {
    String title = categoryTitleField.getText();

    if(title.isEmpty()) {
      showAlert("Please fill in all fields");
      return;
    }

    String originalTitle = currentCategory.getTitle();

    currentCategory.setTitle(title);

    Query.changeCategoryTitle(originalTitle, title);

    System.out.println("Edited category: " + title);
    switchToCategories(event);
  }

  public void submitAddCategory(ActionEvent event) throws IOException {
    String title = categoryTitleField.getText();

    if(title.isEmpty()) {
      showAlert("Please fill in all fields");
      return;
    }

    Category newCategory = new Category(title);
    App.addCategory(newCategory);
    System.out.println("Added category: " + title);
    switchToCategories(event);
  }

  public void deleteCategory(ActionEvent event) throws IOException {
    String title = currentCategory.getTitle();

    Query.removeCategory(currentCategory);

    System.out.println("Deleted category: " + title);
    switchToCategories(event);
  }

  public void editUser(ActionEvent event) throws IOException {
    String username = this.username.getText();
    String password = this.password.getText();
    String email = this.email.getText();
    String firstName = this.firstName.getText();
    String lastName = this.lastName.getText();
    String address = this.address.getText();
    String phoneNumber = this.phoneNumber.getText();
    String id = this.id.getText();
    String dateofbirth = this.dateofbirth.getValue().toString();

    if(username.isEmpty() || password.isEmpty() || email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || phoneNumber.isEmpty() || id.isEmpty() || dateofbirth.isEmpty()) {
      showAlert("Please fill in all fields");
      return;
    }

    String originalUsername = currentUser.getUsername();

    currentUser.setUsername(username);
    currentUser.setPassword(password);
    currentUser.setEmail(email);
    currentUser.setFirstName(firstName);
    currentUser.setLastName(lastName);
    currentUser.setAddress(address);
    currentUser.setPhoneNumber(phoneNumber);
    currentUser.setId(id);
    currentUser.setDateofbirth(dateofbirth);

    Query.changeUsername(originalUsername, username);

    System.out.println("Edited user: " + username);
    switchToUsers(event);
  }

  public void deleteUser(ActionEvent event) throws IOException {
    String username = currentUser.getUsername();

    Query.removeUser(currentUser);

    System.out.println("Deleted user: " + username);
    switchToUsers(event);
  }

  public void returnBook(ActionEvent event) throws IOException {
    String item = borrowList.getSelectionModel().getSelectedItem();
    if(item == null) {
      showAlert("Please select a book");
      return;
    }

    String[] lines = item.split("\n");
    String title = lines[0].substring(9);
    String username = lines[1].substring(8);

    Query.returnBook(username, title);

    System.out.println("Returned book: " + title + " by " + username);
    switchToBorrows(event);
  }

  public void searchBorrow(ActionEvent event) throws IOException {
    TextField searchField = (TextField) event.getSource();
    String query = searchField.getText();

    System.out.println("Searching for: " + query);

    Set<Pair<String, Borrows>> borrows = new HashSet<>();

    List<Pair<String, Borrows>> byTitle = Query.searchBorrowByTitle(query);
    List<Pair<String, Borrows>> byUsername = Query.searchBorrowByUsername(query);

    borrows.addAll(byTitle);
    borrows.addAll(byUsername);

    borrowList.getItems().clear();

    for (Pair<String, Borrows> fullBorrow : borrows) {
      String username = fullBorrow.getKey();
      Borrows borrow = fullBorrow.getValue();
      String status = "Active";
      String fullTitle = "(" + status + ") " + borrow.getBook() + "\nHolder: " + username + "\nISBN: " + borrow.getISBN() + "\nBorrowed: " + borrow.getDate() + "\nReturn: " + borrow.getReturnDate();
      borrowList.getItems().add(fullTitle);
    }
  }
}
