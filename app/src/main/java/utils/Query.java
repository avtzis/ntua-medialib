package utils;

import java.util.List;
import java.util.ArrayList;

import medialib.App;
import utils.Models.Admin;
import utils.Models.Book;
import utils.Models.User;
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
}
