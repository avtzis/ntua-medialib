package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import medialib.App;
import javafx.scene.Node;
import utils.Query;
import utils.ResourceLoader;
import utils.Models.Book;
import utils.Models.User;
import utils.Models.Book.Review;
import utils.Models.User.Borrows;

public class BookController implements Initializable {
  private Stage stage;
  private Scene scene;
  private Parent root;
  private static Book currentBook;

  @FXML
  private ListView<String> bookList;

  @FXML
  private MenuButton categories;

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
  private Button borrowButton;
  @FXML
  private Button reviewButton;

  @FXML
  private ChoiceBox<Integer> rating;

  @FXML
  private TextArea comment;

  public void switchToHome(ActionEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/Home2.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToBook(MouseEvent event) throws IOException {
    // currentBook = event.getSource();
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/item.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }
  public void switchToBook(ActionEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/item.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToBrowse(ActionEvent event) throws IOException {
    currentBook = null;
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/browse.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  public void switchToReview(ActionEvent event) throws IOException {
    root = FXMLLoader.load(ResourceLoader.loadURL("/views/review.fxml"));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    List<Book> books = App.getBooks();
    List<String> categories = Query.availableCategories();
    User currentUser = App.getCurrentUser();

    if(bookList != null) {
      for (Book book : books) {
        String fullTitle = book.getTitle() + "\nby " + book.getAuthor() + "\n" + book.getPublicationDate() + "\n" + book.averageRating() + "/5 (" + book.reviews() + " reviews)";
        bookList.getItems().add(fullTitle);
      }

      bookList.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          if(event.getClickCount() == 2) {
            String item = bookList.getSelectionModel().getSelectedItem();
            String title = item.split("\n")[0];
            Book book = App.getBookByTitle(title);
            currentBook = book;
            System.out.println("Selected book: " + book.getTitle());

            try {
              System.out.println("Switching to book page: " + book.getTitle());
              switchToBook(event);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
      });
    }

    if(this.categories != null) {
      for (String category : categories) {
        MenuItem item = new MenuItem(category);
        this.categories.getItems().add(item);

        item.setOnAction(event -> {
          try {
            filterByCategory(event);
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
      }
    }

    // Book page
    if(currentBook != null && title != null) {
      Book book = App.getBookByTitle(currentBook.getTitle());
      title.setText(book.getTitle());
      author.setText(book.getAuthor());
      publisher.setText(book.getPublisher());
      // isbn.setText(String.valueOf(book.getISBN()));
      date.setText(String.valueOf(book.getPublicationDate()));
      available.setText(String.valueOf(book.getCopies() - book.getBorrows()));
      average.setText(String.valueOf(book.averageRating()));
      category.setText(book.getGenre());
      reviews.setText(String.valueOf(book.getReviews().size()));

      reviewList.getItems().clear();
      for (Review review : book.getReviews()) {
        String fullReview = review.getUsername() + ": " + review.getRating() + "/5 - " + review.getComment();
        reviewList.getItems().add(fullReview);
      }
    }

    if(borrowButton != null) {
      if(currentBook.getBorrows() >= currentBook.getCopies() || !currentUser.canBorrow()) {
        borrowButton.setDisable(true);
      }

      if(!currentUser.hasBorrowed(currentBook.getTitle())) {
        reviewButton.setDisable(true);
      }

      if(currentBook.hasBeenReviewedBy(currentUser.getUsername())) {
        reviewButton.setDisable(true);
      }
    }

    if(rating != null) {
      for (int i = 1; i <= 5; i++) {
        rating.getItems().add(i);
      }
    }
  }

  public void filterByCategory(ActionEvent event) throws IOException {
    MenuItem item = (MenuItem) event.getSource();
    String category = item.getText();

    System.out.println("Filtering by category: " + category);

    List<Book> books = Query.filterByCategory(category);

    bookList.getItems().clear();

    for (Book book : books) {
      String fullTitle = book.getTitle() + "\nby " + book.getAuthor() + "\n" + book.getPublicationDate() + "\n" + book.averageRating() + "/5 (" + book.reviews() + " reviews)";
      bookList.getItems().add(fullTitle);
    }
  }

  public void search(ActionEvent event) throws IOException {
    TextField searchField = (TextField) event.getSource();
    String query = searchField.getText();

    System.out.println("Searching for: " + query);

    Set<Book> books = new HashSet<>();

    List<Book> byTitle = Query.searchByTitle(query);
    List<Book> byAuthor = Query.searchByAuthor(query);

    try {
      int converted = Integer.parseInt(query);
      List<Book> byDate = Query.searchByDate(converted);
      books.addAll(byDate);
    } catch (NumberFormatException e) {}

    books.addAll(byTitle);
    books.addAll(byAuthor);

    bookList.getItems().clear();

    for (Book book : books) {
      bookList.getItems().add(book.getTitle());
    }
  }

  public void borrow(ActionEvent event) throws IOException {
    if(currentBook != null) {
      User currentUser = App.getCurrentUser();
      LocalDate currentDate = LocalDate.now();
      LocalDate dueDate = currentDate.plusDays(5);

      currentUser.addBorrow(new Borrows(currentBook.getTitle(), currentDate.toString(), dueDate.toString(), true, "000"));
      currentBook.addBorrow();
      System.out.println("Borrowed book: " + currentBook.getTitle());

      if(!currentUser.canBorrow()) {
        borrowButton.setDisable(true);
      }

      switchToBrowse(event);
    }
  }

  public void submit(ActionEvent event) throws IOException {
    if(currentBook != null) {
      User currentUser = App.getCurrentUser();
      int rating = this.rating.getValue();
      String comment = this.comment.getText();

      currentBook.addReview(new Review(rating, comment, currentUser.getUsername()));
      System.out.println("Added review to book: " + currentBook.getTitle());

      switchToBook(event);
    }
  }
}
