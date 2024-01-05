package utils;

import java.util.List;
import java.util.ArrayList;

/**
 * This class represents the Models class, which contains nested classes representing different entities in the application.
 */
public class Models {

  /**
   * This class represents a User in the application.
   */
  public static class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String dateofbirth;
    private String id;
    private boolean admin;
    private List<Borrows> borrows;

    /**
     * This class represents a Borrowed book by a User.
     */
    public static class Borrows {
      private String book;
      private String date;
      private String returnDate;
      private boolean active;
      private String ISBN;

      /**
       * Default constructor for the Borrows class.
       */
      public Borrows() {}

      /**
       * Parameterized constructor for the Borrows class.
       * @param book The name of the book being borrowed.
       * @param date The date the book was borrowed.
       * @param returnDate The expected return date of the book.
       * @param active The status of the borrow (active or not).
       * @param ISBN The ISBN of the borrowed book.
       */
      public Borrows(String book, String date, String returnDate, boolean active, String ISBN) {
        this.book = book;
        this.date = date;
        this.returnDate = returnDate;
        this.active = active;
        this.ISBN = ISBN;
      }

      /**
       * Getter for the book name.
       * @return The name of the book.
       */
      public String getBook() {
        return book;
      }

      /**
       * Setter for the book name.
       * @param book The name of the book.
       */
      public void setBook(String book) {
        this.book = book;
      }

      /**
       * Getter for the borrow date.
       * @return The date the book was borrowed.
       */
      public String getDate() {
        return date;
      }

      /**
       * Setter for the borrow date.
       * @param date The date the book was borrowed.
       */
      public void setDate(String date) {
        this.date = date;
      }

      /**
       * Getter for the return date.
       * @return The expected return date of the book.
       */
      public String getReturnDate() {
        return returnDate;
      }

      /**
       * Setter for the return date.
       * @param returnDate The expected return date of the book.
       */
      public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
      }

      /**
       * Getter for the borrow status.
       * @return The status of the borrow (active or not).
       */
      public boolean isActive() {
        return active;
      }

      /**
       * Setter for the borrow status.
       * @param active The status of the borrow (active or not).
       */
      public void setActive(boolean active) {
        this.active = active;
      }

      /**
       * Getter for the ISBN of the borrowed book.
       * @return The ISBN of the borrowed book.
       */
      public String getISBN() {
        return ISBN;
      }

      /**
       * Setter for the ISBN of the borrowed book.
       * @param ISBN The ISBN of the borrowed book.
       */
      public void setISBN(String ISBN) {
        this.ISBN = ISBN;
      }

      /**
       * Method to mark the borrow as returned.
       */
      public void returnBook() {
        this.active = false;
      }
    }

    /**
     * Default constructor for the User class.
     */
    public User() {}

    /**
     * Parameterized constructor for the User class.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param email The email address of the user.
     * @param phoneNumber The phone number of the user.
     * @param address The address of the user.
     * @param dateofbirth The date of birth of the user.
     * @param id The ID of the user.
     */
    public User(String username, String password, String firstName, String lastName, String email, String phoneNumber, String address, String dateofbirth, String id) {
      this.username = username;
      this.password = password;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.phoneNumber = phoneNumber;
      this.address = address;
      this.dateofbirth = dateofbirth;
      this.id = id;
      this.admin = false;
      this.borrows = new ArrayList<Borrows>();
    }

    /**
     * Getter for the username.
     * @return The username of the user.
     */
    public String getUsername() {
      return username;
    }

    /**
     * Setter for the username.
     * @param username The username of the user.
     */
    public void setUsername(String username) {
      this.username = username;
    }

    /**
     * Getter for the password.
     * @return The password of the user.
     */
    public String getPassword() {
      return password;
    }

    /**
     * Setter for the password.
     * @param password The password of the user.
     */
    public void setPassword(String password) {
      this.password = password;
    }

    /**
     * Getter for the admin status.
     * @return True if the user is an admin, false otherwise.
     */
    public boolean isAdmin() {
      return admin;
    }

    /**
     * Getter for the first name.
     * @return The first name of the user.
     */
    public String getFirstName() {
      return firstName;
    }

    /**
     * Setter for the first name.
     * @param firstName The first name of the user.
     */
    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    /**
     * Getter for the last name.
     * @return The last name of the user.
     */
    public String getLastName() {
      return lastName;
    }

    /**
     * Setter for the last name.
     * @param lastName The last name of the user.
     */
    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    /**
     * Getter for the email address.
     * @return The email address of the user.
     */
    public String getEmail() {
      return email;
    }

    /**
     * Setter for the email address.
     * @param email The email address of the user.
     */
    public void setEmail(String email) {
      this.email = email;
    }

    /**
     * Getter for the phone number.
     * @return The phone number of the user.
     */
    public String getPhoneNumber() {
      return phoneNumber;
    }

    /**
     * Setter for the phone number.
     * @param phoneNumber The phone number of the user.
     */
    public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
    }

    /**
     * Getter for the address.
     * @return The address of the user.
     */
    public String getAddress() {
      return address;
    }

    /**
     * Setter for the address.
     * @param address The address of the user.
     */
    public void setAddress(String address) {
      this.address = address;
    }

    /**
     * Getter for the date of birth.
     * @return The date of birth of the user.
     */
    public String getDateofbirth() {
      return dateofbirth;
    }

    /**
     * Setter for the date of birth.
     * @param dateofbirth The date of birth of the user.
     */
    public void setDateofbirth(String dateofbirth) {
      this.dateofbirth = dateofbirth;
    }

    /**
     * Getter for the ID.
     * @return The ID of the user.
     */
    public String getId() {
      return id;
    }

    /**
     * Setter for the ID.
     * @param id The ID of the user.
     */
    public void setId(String id) {
      this.id = id;
    }

    /**
     * Getter for the list of borrows.
     * @return The list of borrows by the user.
     */
    public List<Borrows> getBorrows() {
      return borrows;
    }

    /**
     * Setter for the list of borrows.
     * @param borrows The list of borrows by the user.
     */
    public void setBorrows(List<Borrows> borrows) {
      this.borrows = borrows;
    }

    /**
     * Method to add a borrow to the user's list of borrows.
     * @param borrow The borrow to be added.
     */
    public void addBorrow(Borrows borrow) {
      this.borrows.add(borrow);
    }

    /**
     * Method to remove a borrow from the user's list of borrows.
     * @param borrow The borrow to be removed.
     */
    public void removeBorrow(Borrows borrow) {
      this.borrows.remove(borrow);
    }

    /**
     * Method to check if the user can borrow more books.
     * @return True if the user can borrow more books, false otherwise.
     */
    public boolean canBorrow() {
      if(borrows == null) return true;

      int activeBorrows = 0;
      for (Borrows borrow : borrows) {
        if(borrow.isActive()) activeBorrows++;
      }
      System.out.println("Active borrows: " + activeBorrows);
      return activeBorrows < 2;
    }

    /**
     * Method to get a list of active borrows by the user.
     * @return The list of active borrows by the user.
     */
    public List<Borrows> activeBorrows() {
      return new ArrayList<Borrows>() {{
        for (Borrows borrow : borrows) {
          if(borrow.isActive()) add(borrow);
        }
      }};
    }

    /**
     * Method to check if the user has borrowed a specific book.
     * @param book The name of the book to check.
     * @return True if the user has borrowed the book, false otherwise.
     */
    public boolean hasBorrowed(String book) {
      for (Borrows borrow : borrows) {
        if(borrow.getBook().equals(book)) return true;
      }
      return false;
    }

    /**
     * Method to check if the user is currently borrowing a specific book.
     * @param book The name of the book to check.
     * @return True if the user is currently borrowing the book, false otherwise.
     */
    public boolean isCurrentlyBorrowing(String book) {
      for (Borrows borrow : borrows) {
        if(borrow.getBook().equals(book) && borrow.isActive()) return true;
      }
      return false;
    }

    // Additional methods and properties can be added here
  }

  /**
   * This class represents an Admin user in the application, which is a subclass of the User class.
   */
  public static class Admin extends User {
    /**
     * Default constructor for the Admin class.
     */
    public Admin() { super(); }

    /**
     * Parameterized constructor for the Admin class.
     * @param username The username of the admin.
     * @param password The password of the admin.
     * @param firstName The first name of the admin.
     * @param lastName The last name of the admin.
     * @param email The email address of the admin.
     * @param phoneNumber The phone number of the admin.
     * @param address The address of the admin.
     * @param dateofbirth The date of birth of the admin.
     * @param id The ID of the admin.
     */
    public Admin(String username, String password, String firstName, String lastName, String email, String phoneNumber, String address, String dateofbirth, String id) {
      super(username, password, firstName, lastName, email, phoneNumber, address, dateofbirth, id);
    }

    /**
     * Constructor for the Admin class with only username and password.
     * @param username The username of the admin.
     * @param password The password of the admin.
     */
    public Admin(String username, String password) {
      super(username, password, "", "", "", "", "", "", "");
    }

    /**
     * Overridden method to check if the user is an admin.
     * @return True, as the user is an admin.
     */
    @Override
    public boolean isAdmin() {
      return true;
    }
  }

  /**
   * This class represents a Book in the application.
   */
  public static class Book {
    private String title;
    private String author;
    private String genre;
    private String publisher;
    private String language;
    private int publicationDate;
    private int pages;
    private String description;
    private int copies;
    private int borrows;
    private String ISBN;
    private List<Review> reviews;

    /**
     * This class represents a review for a media item.
     */
    public static class Review {
      private int rating;
      private String comment;
      private String username;

      /**
       * Constructs an empty Review object.
       */
      public Review() {}

      /**
       * Constructs a Review object with the specified rating, comment, and username.
       *
       * @param rating   the rating given to the media item
       * @param comment  the comment provided by the user
       * @param username the username of the user who wrote the review
       */
      public Review(int rating, String comment, String username) {
        this.rating = rating;
        this.comment = comment;
        this.username = username;
      }

      /**
       * Returns the rating of the review.
       *
       * @return the rating of the review
       */
      public int getRating() {
        return rating;
      }

      /**
       * Sets the rating of the review.
       *
       * @param rating the rating to be set
       */
      public void setRating(int rating) {
        this.rating = rating;
      }

      /**
       * Returns the comment of the review.
       *
       * @return the comment of the review
       */
      public String getComment() {
        return comment;
      }

      /**
       * Sets the comment of the review.
       *
       * @param comment the comment to be set
       */
      public void setComment(String comment) {
        this.comment = comment;
      }

      /**
       * Returns the username of the user who wrote the review.
       *
       * @return the username of the user who wrote the review
       */
      public String getUsername() {
        return username;
      }

      /**
       * Sets the username of the user who wrote the review.
       *
       * @param username the username to be set
       */
      public void setUsername(String username) {
        this.username = username;
      }
    }

    /**
     * Constructs an empty Book object.
     */
    public Book() {}

    /**
     * Constructs a Book object with the specified attributes.
     *
     * @param title           the title of the book
     * @param author          the author of the book
     * @param genre           the genre of the book
     * @param publisher       the publisher of the book
     * @param language        the language of the book
     * @param publicationDate the publication date of the book
     * @param pages           the number of pages in the book
     * @param description     the description of the book
     * @param copies          the number of copies available for the book
     * @param borrows         the number of times the book has been borrowed
     * @param ISBN            the ISBN (International Standard Book Number) of the book
     */
    public Book(String title, String author, String genre, String publisher, String language, int publicationDate, int pages, String description, int copies, int borrows,/*  List<Review> reviews, */ String ISBN) {
      this.title = title;
      this.author = author;
      this.genre = genre;
      this.publisher = publisher;
      this.language = language;
      this.publicationDate = publicationDate;
      this.pages = pages;
      this.description = description;
      this.copies = copies;
      this.borrows = borrows;
      // this.reviews = reviews;
      this.reviews = new ArrayList<>();
      this.ISBN = ISBN;
    }

    /**
     * Returns the title of the book.
     *
     * @return the title of the book
     */
    public String getTitle() {
      return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title the title to be set
     */
    public void setTitle(String title) {
      this.title = title;
    }

    /**
     * Returns the author of the book.
     *
     * @return the author of the book
     */
    public String getAuthor() {
      return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author the author to be set
     */
    public void setAuthor(String author) {
      this.author = author;
    }

    /**
     * Returns the genre of the book.
     *
     * @return the genre of the book
     */
    public String getGenre() {
      return genre;
    }

    /**
     * Sets the genre of the book.
     *
     * @param genre the genre to be set
     */
    public void setGenre(String genre) {
      this.genre = genre;
    }

    /**
     * Returns the publisher of the book.
     *
     * @return the publisher of the book
     */
    public String getPublisher() {
      return publisher;
    }

    /**
     * Sets the publisher of the book.
     *
     * @param publisher the publisher to be set
     */
    public void setPublisher(String publisher) {
      this.publisher = publisher;
    }

    /**
     * Returns the language of the book.
     *
     * @return the language of the book
     */
    public String getLanguage() {
      return language;
    }

    /**
     * Sets the language of the book.
     *
     * @param language the language to be set
     */
    public void setLanguage(String language) {
      this.language = language;
    }

    /**
     * Returns the publication date of the book.
     *
     * @return the publication date of the book
     */
    public int getPublicationDate() {
      return publicationDate;
    }

    /**
     * Sets the publication date of the book.
     *
     * @param publicationDate the publication date to be set
     */
    public void setPublicationDate(int publicationDate) {
      this.publicationDate = publicationDate;
    }

    /**
     * Returns the number of pages in the book.
     *
     * @return the number of pages in the book
     */
    public int getPages() {
      return pages;
    }

    /**
     * Sets the number of pages in the book.
     *
     * @param pages the number of pages to be set
     */
    public void setPages(int pages) {
      this.pages = pages;
    }

    /**
     * Returns the description of the book.
     *
     * @return the description of the book
     */
    public String getDescription() {
      return description;
    }

    /**
     * Sets the description of the book.
     *
     * @param description the description to be set
     */
    public void setDescription(String description) {
      this.description = description;
    }

    /**
     * Returns the number of copies available for the book.
     *
     * @return the number of copies available for the book
     */
    public int getCopies() {
      return copies;
    }

    /**
     * Sets the number of copies available for the book.
     *
     * @param copies the number of copies to be set
     */
    public void setCopies(int copies) {
      this.copies = copies;
    }

    /**
     * Increases the number of copies available for the book by 1.
     */
    public void addCopy() {
      this.copies++;
    }

    /**
     * Decreases the number of copies available for the book by 1.
     */
    public void removeCopy() {
      this.copies--;
    }

    /**
     * Returns the number of times the book has been borrowed.
     *
     * @return the number of times the book has been borrowed
     */
    public int getBorrows() {
      return borrows;
    }

    /**
     * Sets the number of times the book has been borrowed.
     *
     * @param borrows the number of times the book has been borrowed
     */
    public void setBorrows(int borrows) {
      this.borrows = borrows;
    }

    /**
     * Increases the number of times the book has been borrowed by 1.
     */
    public void addBorrow() {
      this.borrows++;
    }

    /**
     * Decreases the number of times the book has been borrowed by 1.
     */
    public void removeBorrow() {
      this.borrows--;
    }

    /**
     * Returns the list of reviews for the book.
     *
     * @return the list of reviews for the book
     */
    public List<Review> getReviews() {
      return reviews;
    }

    /**
     * Sets the list of reviews for the book.
     *
     * @param reviews the list of reviews to be set
     */
    public void setReviews(List<Review> reviews) {
      this.reviews = reviews;
    }

    /**
     * Adds a review to the list of reviews for the book.
     *
     * @param review the review to be added
     */
    public void addReview(Review review) {
      this.reviews.add(review);
    }

    /**
     * Removes a review from the list of reviews for the book.
     *
     * @param review the review to be removed
     */
    public void removeReview(Review review) {
      this.reviews.remove(review);
    }

    /**
     * Returns the ISBN (International Standard Book Number) of the book.
     *
     * @return the ISBN of the book
     */
    public String getISBN() {
      return ISBN;
    }

    /**
     * Sets the ISBN (International Standard Book Number) of the book.
     *
     * @param ISBN the ISBN to be set
     */
    public void setISBN(String ISBN) {
      this.ISBN = ISBN;
    }

    /**
     * Returns a list of ratings given by the users for the book.
     *
     * @return a list of ratings
     */
    public List<Integer> ratings() {
      return new ArrayList<Integer>() {{
        for (Review review : reviews) {
          add(review.getRating());
        }
      }};
    }

    /**
     * Returns the average rating for the book.
     *
     * @return the average rating
     */
    public double averageRating() {
      if (reviews.size() == 0) return 0;
      int sum = 0;
      for (Review review : reviews) {
        sum += review.getRating();
      }
      return (double) sum / reviews.size();
    }

    /**
     * Returns the number of reviews.
     *
     * @return The number of reviews.
     */
    public int reviews() {
      return reviews.size();
    }

    /**
     * Returns a list of comments extracted from the reviews.
     *
     * @return A list of comments.
     */
    public List<String> comments() {
      return new ArrayList<String>() {{
        for (Review review : reviews) {
          add(review.getComment());
        }
      }};
    }

    /**
     * Checks if the specified username has reviewed the model.
     *
     * @param username the username to check
     * @return true if the username has reviewed the model, false otherwise
     */
    public boolean hasBeenReviewedBy(String username) {
      for (Review review : reviews) {
        if (review.getUsername().equals(username)) return true;
      }
      return false;
    }
  }

  public static class Category {
    private String title;
    private List<Book> books;

    /**
     * Constructs an empty Category object.
     */
    public Category() {}

    /**
     * Constructs a Category object with the specified title.
     *
     * @param title the title of the category
     */
    public Category(String title) {
      this.title = title;
      this.books = new ArrayList<>();
    }

    /**
     * Returns the title of the category.
     *
     * @return the title of the category
     */
    public String getTitle() {
      return title;
    }

    /**
     * Sets the title of the category.
     *
     * @param title the title of the category
     */
    public void setTitle(String title) {
      this.title = title;
    }

    /**
     * Returns the list of books in the category.
     *
     * @return the list of books in the category
     */
    public List<Book> getBooks() {
      return books;
    }

    /**
     * Sets the list of books in the category.
     *
     * @param books the list of books in the category
     */
    public void setBooks(List<Book> books) {
      this.books = books;
    }

    /**
     * Adds a book to the category.
     *
     * @param book the book to be added
     */
    public void addBook(Book book) {
      this.books.add(book);
    }

    /**
     * Removes a book from the category.
     *
     * @param book the book to be removed
     */
    public void removeBook(Book book) {
      this.books.remove(book);
    }
  }
}
