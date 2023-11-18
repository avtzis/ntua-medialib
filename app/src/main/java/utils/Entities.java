package utils;

import java.util.List;

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
      return false;
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
    private String publicationDate;
    private String pages;
    private String description;
    private int copies;
    private int rating;
    private int reviews;
    private List<String> comments;
    private int likes;
    private int dislikes;
    private int borrows;
    
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

    public String getPublicationDate() {
      return publicationDate;
    }
    public void setPublicationDate(String publicationDate) {
      this.publicationDate = publicationDate;
    }

    public String getPages() {
      return pages;
    }
    public void setPages(String pages) {
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

    public int getRating() {
      return rating;
    }
    public void setRating(int rating) {
      this.rating = rating;
    }
    public void addRating(int rating) {
      this.rating += rating;
    }
    public void removeRating(int rating) {
      this.rating -= rating;
    }

    public int getReviews() {
      return reviews;
    }
    public void setReviews(int reviews) {
      this.reviews = reviews;
    }
    public void addReview() {
      this.reviews++;
    }
    public void removeReview() {
      this.reviews--;
    }

    public List<String> getComments() {
      return comments;
    }
    public void addComment(String comment) {
      this.comments.add(comment);
    }
    public void removeComment(String comment) {
      this.comments.remove(comment);
    }
    public void setComments(List<String> comments) {
      this.comments = comments;
    }

    public int getLikes() {
      return likes;
    }
    public void setLikes(int likes) {
      this.likes = likes;
    }
    public void addLike() {
      this.likes++;
    }
    public void removeLike() {
      this.likes--;
    }

    public int getDislikes() {
      return dislikes;
    }
    public void setDislikes(int dislikes) {
      this.dislikes = dislikes;
    }
    public void addDislike() {
      this.dislikes++;
    }
    public void removeDislike() {
      this.dislikes--;
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
  }
}
