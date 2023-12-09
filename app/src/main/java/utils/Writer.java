package utils;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList; // Add this import statement
import java.util.HashSet;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import utils.Models.User;
import utils.Models.Book.Review;
import utils.Models.User.Borrows;
import utils.Models.Book;
import utils.Models.Category;

public class Writer {
  public static void write(/* List<Admin> admins, */ List<User> users, List<Book> books, List<Category> categories) {
    try {
      ObjectMapper om = new ObjectMapper();

      // om.writeValue(new File("src/main/resources/admins.json"), admins);
      om.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/models/users.json"), users);
      om.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/models/books.json"), books);
      om.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/models/categories.json"), categories);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void populate() {
    List<User> users = new ArrayList<>();
    List<Book> books = new ArrayList<>();
    List<Category> categories = new ArrayList<>();

    Set<String> uniqueCategories = new HashSet<>();
    while(uniqueCategories.size() < 20) {
      uniqueCategories.add(getRandomCategory());
    }
    for(String category : uniqueCategories) {
      categories.add(new Category(category));
    }
    System.out.println("Created categories.");

    Set<String> uniqueTitles = new HashSet<>();
    while(uniqueTitles.size() < 180) {
      Random random = new Random();
      int randomIndex = random.nextInt(categories.size());
      String randomCategory = categories.get(randomIndex).getTitle();
      Book book = getRandomBook(randomCategory);

      if(!uniqueTitles.contains(book.getTitle())) {
        uniqueTitles.add(book.getTitle());
        books.add(book);
      }
    }
    System.out.println("Created books.");

    for(int i=0; i<50; ++i) {
      users.add(getRandomUser());
    }
    System.out.println("Created users.");

    for(User user : users) {
      Faker faker = new Faker();
      List<Integer> usedBooks = new ArrayList<>();

      int n = faker.number().numberBetween(25, 366);
      while(n > 0) {
        Book chosenBook;
        while(true) {
          int randomIndex;
          while(usedBooks.contains(randomIndex = new Random().nextInt(books.size())));

          Book provisionalBook = books.get(randomIndex);
          if(provisionalBook.getBorrows() < provisionalBook.getCopies()) {
            chosenBook = provisionalBook;
            usedBooks.add(randomIndex);
            break;
          }
        }

        boolean randomProbability = new Random().nextDouble() < 0.7;
        if(randomProbability) {
          int rating = faker.number().numberBetween(1, 6);
          String comment = faker.lorem().sentence();
          Review review = new Review(rating, comment, user.getUsername());
          chosenBook.addReview(review);
        }

        LocalDate borrowDate = LocalDate.now().minusDays(n);
        n -= faker.number().numberBetween(1, 6);

        LocalDate returnDate;
        if(n < 0) {
          returnDate = borrowDate.plusDays(5);
          chosenBook.addBorrow();
        } else {
          returnDate = LocalDate.now().minusDays(n);
        }

        Borrows borrow = new Borrows(chosenBook.getTitle(), borrowDate.toString(), returnDate.toString(), n < 0, chosenBook.getISBN());
        user.addBorrow(borrow);

        n -= faker.number().numberBetween(0, 15);
      }

      n = faker.number().numberBetween(25, 365);
      while(n > 0) {
        Book chosenBook;
        while(true) {
          int randomIndex;
          while(usedBooks.contains(randomIndex = new Random().nextInt(books.size())));

          Book provisionalBook = books.get(randomIndex);
          if(provisionalBook.getBorrows() < provisionalBook.getCopies()) {
            chosenBook = provisionalBook;
            usedBooks.add(randomIndex);
            break;
          }
        }

        boolean randomProbability = new Random().nextDouble() < 0.8;
        if(randomProbability) {
          int rating = faker.number().numberBetween(1, 6);
          String comment = faker.lorem().sentence();
          Review review = new Review(rating, comment, user.getUsername());
          chosenBook.addReview(review);
        }

        LocalDate borrowDate = LocalDate.now().minusDays(n);
        n -= faker.number().numberBetween(1, 6);

        LocalDate returnDate;
        if(n < 0) {
          returnDate = borrowDate.plusDays(5);
          chosenBook.addBorrow();
        } else {
          returnDate = LocalDate.now().minusDays(n);
        }

        Borrows borrow = new Borrows(chosenBook.getTitle(), borrowDate.toString(), returnDate.toString(), n < 0, chosenBook.getISBN());
        user.addBorrow(borrow);

        n -= faker.number().numberBetween(0, 15);
      }
    }
    System.out.println("Created borrows and reviews.");

    write(users, books, categories);
    System.out.println("Files successfully written.");
  }

  private static User getRandomUser() {
    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();

    String username = firstName.toLowerCase() + "." + lastName.toLowerCase();
    String password = faker.internet().password();

    String email = firstName.toLowerCase() + lastName.toLowerCase() + "@gmail.com";
    String phoneNumber = faker.phoneNumber().phoneNumber();
    String address = faker.address().fullAddress();
    String dateofbirth = faker.date().birthday().toString();

    int idno = faker.number().numberBetween(100000, 999999);
    String id = faker.letterify("??" + Integer.toString(idno), true);

    return new User(username, password, firstName, lastName, email, phoneNumber, address, dateofbirth, id);
  }

  private static Book getRandomBook(String randomCategory) {
    Faker faker = new Faker();

    String title = faker.book().title();
    String author = faker.book().author();
    String genre = randomCategory;
    String publisher = faker.book().publisher();

    String language = "English";
    int publicationDate = faker.number().numberBetween(1980, 2023);
    int pages = faker.number().numberBetween(50, 1500);
    String description = faker.lorem().paragraph();

    int copies = faker.number().numberBetween(1, 20);
    int borrows = 0;

    String ISBN = generateISBN();

    return new Book(title, author, genre, publisher, language, publicationDate, pages, description, copies, borrows, ISBN);
  }

  private static String generateISBN() {
    Faker faker = new Faker();

    String prefix = "978";
    String digit1 = faker.number().digit();
    String digit2 = faker.number().digit();
    String digit3 = faker.number().digit();
    String digit4 = faker.number().digit();
    String digit5 = faker.number().digit();
    String digit6 = faker.number().digit();
    String digit7 = faker.number().digit();
    String digit8 = faker.number().digit();
    String digit9 = faker.number().digit();
    String digit10 = faker.number().digit();

    return prefix + digit1 + digit2 + digit3 + digit4 + digit5 + digit6 + digit7 + digit8 + digit9 + digit10;
  }

  private static String getRandomCategory() {
    Faker faker = new Faker();

    String category = faker.book().genre();

    return category;
  }
}
