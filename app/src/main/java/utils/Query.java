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

/**
 * The Query class contains static methods that are used to query the application data.
 */
public class Query {
  /**
   * Searches for books by title.
   *
   * @param title the title to search for
   * @return a list of books matching the given title
   */
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

  /**
   * Searches for books by author.
   *
   * @param author The author's name to search for.
   * @return A list of books written by the specified author.
   */
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

  /**
   * Searches for books by genre.
   *
   * @param genre the genre to search for
   * @return a list of books matching the genre
   */
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

  /**
   * Searches for books by a specific publication date.
   *
   * @param date The publication date to search for.
   * @return A list of books published on the specified date.
   */
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

  /**
   * Filters the list of books by category.
   *
   * @param category the category to filter by
   * @return a list of books that belong to the specified category
   */
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

  /**
   * Returns a list of available categories based on the genres of the books in the application.
   *
   * @return a list of available categories
   */
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

  /**
   * Returns the top five books based on their average rating.
   *
   * @return a list of the top five books
   */
  public static List<Book> topFive() {
    List<Book> books = new ArrayList<>(App.getBooks());
    List<Book> results = new ArrayList<Book>();

    books.sort((a, b) -> Double.compare(a.averageRating(), b.averageRating()));

    if(books.size() < 5) {
      return books;
    }

    results.add(books.get(books.size() - 1));
    results.add(books.get(books.size() - 2));
    results.add(books.get(books.size() - 3));
    results.add(books.get(books.size() - 4));
    results.add(books.get(books.size() - 5));

    return results;
  }

  /**
   * Filters the list of borrows by their active status.
   *
   * @param active The active status to filter by. Can be "All", "True", or "False".
   * @return A list of borrows that match the specified active status.
   */
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

  /**
   * Changes the title and ISBN of a book in the library system.
   *
   * @param oldTitle The current title of the book.
   * @param newTitle The new title to be assigned to the book.
   * @param newISBN The new ISBN (International Standard Book Number) to be assigned to the book.
   */
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

  /**
   * Removes a book from the library and all associated borrows by users.
   *
   * @param book The book to be removed.
   */
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

  /**
   * Changes the category title of books from oldTitle to newTitle.
   *
   * @param oldTitle The old category title to be changed.
   * @param newTitle The new category title to replace the old title.
   */
  public static void changeCategoryTitle(String oldTitle, String newTitle) {
    List<Book> books = App.getBooks();

    for(Book book : books) {
      if(book.getGenre().equals(oldTitle)) {
        book.setGenre(newTitle);
      }
    }
  }

  /**
   * Removes a category and all books associated with it.
   *
   * @param category The category to be removed.
   */
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

  /**
   * Changes the username of all reviews associated with a given old username to a new username.
   *
   * @param oldUsername The old username to be replaced.
   * @param newUsername The new username to replace the old username.
   */
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

  /**
   * Removes a user from the application, including their reviews and borrowed books.
   *
   * @param user The user to be removed.
   */
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

  /**
   * Retrieves all active borrows from the list of users.
   *
   * @return A list of pairs containing the username and the active borrow for each user.
   */
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

  /**
   * Returns a book by marking it as inactive in the user's borrowed books list and removing the borrow record from the book.
   *
   * @param username the username of the user returning the book
   * @param title the title of the book being returned
   */
  public static void returnBook(String username, String title) {
    User user = App.getUserByUsername(username);

    for (Borrows borrow : user.getBorrows()) {
      if (borrow.getBook().equals(title)) {
        borrow.setActive(false);
        break;
      }
    }

    Book book = App.getBookByTitle(title);
    book.removeBorrow();
  }

  /**
   * Searches for borrows by title.
   *
   * @param title the title to search for
   * @return a list of pairs containing the title and corresponding borrow objects
   */
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

  /**
   * Searches for borrows by username.
   *
   * @param username the username to search for
   * @return a list of pairs containing the username and corresponding borrows
   */
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
