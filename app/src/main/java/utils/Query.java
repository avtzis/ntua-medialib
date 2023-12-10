package utils;

import java.util.List;

import javafx.util.Pair;

import java.util.ArrayList;

import medialib.App;
import utils.Models.Book;
import utils.Models.Category;
import utils.Models.User;
import utils.Models.Book.Review;
import utils.Models.User.Borrows;

public class Query {
  public static List<Book> searchByTitle(String title) {
    List<Book> books = App.getBooks();
    List<Book> results = new ArrayList<Book>();

    for (Book book : books) {
      if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
        results.add(book);
      }
    }

    return results;
  }

  public static List<Book> searchByAuthor(String author) {
    List<Book> books = App.getBooks();
    List<Book> results = new ArrayList<Book>();

    for (Book book : books) {
      if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
        results.add(book);
      }
    }

    return results;
  }

  public static List<Book> searchByGenre(String genre) {
    List<Book> books = App.getBooks();
    List<Book> results = new ArrayList<Book>();

    for (Book book : books) {
      if (book.getGenre().toLowerCase().contains(genre.toLowerCase())) {
        results.add(book);
      }
    }

    return results;
  }

  public static List<Book> searchByDate(int date) {
    List<Book> books = App.getBooks();
    List<Book> results = new ArrayList<Book>();

    for (Book book : books) {
      if (book.getPublicationDate() == date) {
        results.add(book);
      }
    }

    return results;
  }

  public static List<Book> filterByCategory(String category) {
    List<Book> books = App.getBooks();
    List<Book> results = new ArrayList<Book>();

    if(category.equals("All")) {
      return books;
    }

    for (Book book : books) {
      if (book.getGenre().toLowerCase().contains(category.toLowerCase())) {
        results.add(book);
      }
    }

    return results;
  }

  public static List<String> availableCategories() {
    List<Book> books = App.getBooks();
    List<String> categories = new ArrayList<String>();

    for (Book book : books) {
      if (!categories.contains(book.getGenre())) {
        categories.add(book.getGenre());
      }
    }

    return categories;
  }

  public static List<Book> topFive() {
    List<Book> books = new ArrayList<>(App.getBooks());
    List<Book> results = new ArrayList<Book>();

    books.sort((a, b) -> Double.compare(a.averageRating(), b.averageRating()));

    results.add(books.get(books.size() - 1));
    results.add(books.get(books.size() - 2));
    results.add(books.get(books.size() - 3));
    results.add(books.get(books.size() - 4));
    results.add(books.get(books.size() - 5));

    return results;
  }

  public static List<Borrows> filterByActive(String active) {
    List<Borrows> borrows = App.getCurrentUser().getBorrows();
    List<Borrows> results = new ArrayList<Borrows>();

    if(active.equals("All")) {
      return borrows;
    }

    boolean status = active.equals("True");
    for (Borrows borrow : borrows) {
      if (borrow.isActive() == status) {
        results.add(borrow);
      }
    }

    return results;
  }

  public static void changeBookTitle(String oldTitle, String newTitle, String newISBN) {
    List<User> users = App.getUsers();

    for(User user : users) {
      for(Borrows borrow : user.getBorrows()) {
        if(borrow.getBook().equals(oldTitle)) {
          borrow.setBook(newTitle);
          borrow.setISBN(newISBN);
        }
      }
    }
  }

  public static void removeBook(Book book) {
    List<User> users = App.getUsers();

    for(User user : users) {
      List<Borrows> toDelete = new ArrayList<Borrows>();
      for(Borrows borrow : user.getBorrows()) {
        if(borrow.getBook().equals(book.getTitle())) {
          toDelete.add(borrow);
        }
      }
      for(Borrows borrow : toDelete) {
        user.removeBorrow(borrow);
      }
    }

    App.removeBook(book);
  }

  public static void changeCategoryTitle(String oldTitle, String newTitle) {
    List<Book> books = App.getBooks();

    for(Book book : books) {
      if(book.getGenre().equals(oldTitle)) {
        book.setGenre(newTitle);
      }
    }
  }

  public static void removeCategory(Category category) {
    List<Book> books = App.getBooks();
    List<Book> originalBooks = new ArrayList<Book>(books);

    for(Book book : originalBooks) {
      if(book.getGenre().equals(category.getTitle())) {
        removeBook(book);
      }
    }

    App.removeCategory(category);
  }

  public static void changeUsername(String oldUsername, String newUsername) {
    List<Book> books = App.getBooks();

    for(Book book : books) {
      for(Review review : book.getReviews()) {
        if(review.getUsername().equals(oldUsername)) {
          review.setUsername(newUsername);
          break;
        }
      }
    }
  }

  public static void removeUser(User user) {
    List<Book> books = App.getBooks();

    for(Book book : books) {
      for(Review review : book.getReviews()) {
        if(review.getUsername().equals(user.getUsername())) {
          book.removeReview(review);
          break;
        }
      }
      if(user.isCurrentlyBorrowing(book.getTitle())) {
        book.removeBorrow();
      }
    }

    App.removeUser(user);
  }

  public static List<Pair<String, Borrows>> getAllActiveBorrows() {
    List<User> users = App.getUsers();
    List<Pair<String, Borrows>> borrows = new ArrayList<Pair<String, Borrows>>();

    for(User user : users) {
      for(Borrows borrow : user.getBorrows()) {
        if(borrow.isActive()) {
          borrows.add(new Pair<String, Borrows>(user.getUsername(), borrow));
        }
      }
    }

    // System.out.println(borrows);
    return borrows;
  }

  public static void returnBook(String username, String Title) {
    User user = App.getUserByUsername(username);

    for(Borrows borrow : user.getBorrows()) {
      if(borrow.getBook().equals(Title)) {
        borrow.setActive(false);
        break;
      }
    }

    Book book = App.getBookByTitle(Title);
    book.removeBorrow();
  }

  public static List<Pair<String, Borrows>> searchBorrowByTitle(String title) {
    List<Pair<String, Borrows>> borrows = getAllActiveBorrows();
    List<Pair<String, Borrows>> results = new ArrayList<Pair<String, Borrows>>();

    for(Pair<String, Borrows> borrow : borrows) {
      if(borrow.getValue().getBook().toLowerCase().contains(title.toLowerCase())) {
        results.add(borrow);
      }
    }

    return results;
  }

  public static List<Pair<String, Borrows>> searchBorrowByUsername(String username) {
    List<Pair<String, Borrows>> borrows = getAllActiveBorrows();
    List<Pair<String, Borrows>> results = new ArrayList<Pair<String, Borrows>>();

    for(Pair<String, Borrows> borrow : borrows) {
      if(borrow.getKey().toLowerCase().contains(username.toLowerCase())) {
        results.add(borrow);
      }
    }

    return results;
  }
}
