package parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.io.FileReader;

public class Parser {
  public static void parse() {
    try {
      ObjectMapper om = new ObjectMapper();
      
      List<Admin> admins = om.readValue(new FileReader("src/main/resources/admins.json"), om.getTypeFactory().constructCollectionType(List.class, Admin.class));

      for (Admin admin: admins) {
        System.out.println("Username: " + admin.getUsername());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static class Admin {
    private String username;
    private String password;

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
  }
}
