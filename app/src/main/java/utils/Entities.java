package utils;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Entities {
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

    public User() {}

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
    }
    
    public String getUsername() {
      return username;
    }
    public void setUsername(String username) {
      this.username = username;
    }
    
    public String getPassword() {
      return password;
    }
    public void setPassword(String password) {
      this.password = password;
    }
    
    public boolean isAdmin() {
      return admin;
    }

    public String getFirstName() {
      return firstName;
    }
    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getLastName() {
      return lastName;
    }
    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public String getEmail() {
      return email;
    }
    public void setEmail(String email) {
      this.email = email;
    }

    public String getPhoneNumber() {
      return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
      return address;
    }
    public void setAddress(String address) {
      this.address = address;
    }

    public String getDateofbirth() {
      return dateofbirth;
    }
    public void setDateofbirth(String dateofbirth) {
      this.dateofbirth = dateofbirth;
    }

    public String getId() {
      return id;
    }
    public void setId(String id) {
      this.id = id;
    }

    //
  }
  
  public static class Admin extends User {
    public Admin() { super(); }

    public Admin(String username, String password, String firstName, String lastName, String email, String phoneNumber, String address, String dateofbirth, String id) {
      super(username, password, firstName, lastName, email, phoneNumber, address, dateofbirth, id);
    }
    
    @Override
    public boolean isAdmin() {
      return true;
    }
  }

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
    private List<Review> reviews;
    
    public static class Review {
        private int rating;
        private String comment;
        private Reaction reaction;
        private String username;

        public Review() {}
        public Review(int rating, String comment, Reaction reaction, String username) {
          this.rating = rating;
          this.comment = comment;
          this.reaction = reaction;
          this.username = username;
        }

        public int getRating() {
          return rating;
        }
        public void setRating(int rating) {
          this.rating = rating;
        }

        public String getComment() {
          return comment;
        }
        public void setComment(String comment) {
          this.comment = comment;
        }

        public Reaction getReaction() {
          return reaction;
        }
        public void setReaction(Reaction reaction) {
          this.reaction = reaction;
        }

        public String getUsername() {
          return username;
        }
        public void setUsername(String username) {
          this.username = username;
        }
      }

    public Book() {}

    public Book(String title, String author, String genre, String publisher, String language, int publicationDate, int pages, String description, int copies, int borrows, List<Review> reviews) {
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
      this.reviews = reviews;
    }
    
    public String getTitle() {
      return title;
    }
    public void setTitle(String title) {
      this.title = title;
    }

    public String getAuthor() {
      return author;
    }
    public void setAuthor(String author) {
      this.author = author;
    }

    public String getGenre() {
      return genre;
    }
    public void setGenre(String genre) {
      this.genre = genre;
    }

    public String getPublisher() {
      return publisher;
    }
    public void setPublisher(String publisher) {
      this.publisher = publisher;
    }

    public String getLanguage() {
      return language;
    }
    public void setLanguage(String language) {
      this.language = language;
    }

    public int getPublicationDate() {
      return publicationDate;
    }
    public void setPublicationDate(int publicationDate) {
      this.publicationDate = publicationDate;
    }

    public int getPages() {
      return pages;
    }
    public void setPages(int pages) {
      this.pages = pages;
    }

    public String getDescription() {
      return description;
    }
    public void setDescription(String description) {
      this.description = description;
    }

    public int getCopies() {
      return copies;
    }
    public void setCopies(int copies) {
      this.copies = copies;
    }
    public void addCopy() {
      this.copies++;
    }
    public void removeCopy() {
      this.copies--;
    }

    public int getBorrows() {
      return borrows;
    }
    public void setBorrows(int borrows) {
      this.borrows = borrows;
    }
    public void addBorrow() {
      this.borrows++;
    }
    public void removeBorrow() {
      this.borrows--;
    }
    
    public List<Review> getReviews() {
      return reviews;
    }
    public void setReviews(List<Review> reviews) {
      this.reviews = reviews;
    }

    public List<Integer> ratings() {
        return new ArrayList<Integer>() {{
          for (Review review : reviews) {
            add(review.getRating());
          }
        }};
      }
      public int averageRating() {
        int sum = 0;
        for (Review review : reviews) {
          sum += review.getRating();
        }
        return sum / reviews.size();
      }

      public int reviews() {
        return reviews.size();
      }

      public List<String> comments() {
        return new ArrayList<String>() {{
          for (Review review : reviews) {
            add(review.getComment());
          }
        }};
      }

      public int likes() {
        return new ArrayList<Reaction>() {{
          for (Review review : reviews) {
            add(review.getReaction());
          }
        }}.stream().filter(reaction -> reaction == Reaction.LIKE).toArray().length;
      }

      public int dislikes() {
        return new ArrayList<Reaction>() {{
          for (Review review : reviews) {
            add(review.getReaction());
          }
        }}.stream().filter(reaction -> reaction == Reaction.DISLIKE).toArray().length;
      }

      public List<Reaction> reactions() {
        return new ArrayList<Reaction>() {{
          for (Review review : reviews) {
            add(review.getReaction());
          }
        }};
      }

  }
}