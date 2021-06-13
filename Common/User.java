package Common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {
    private final String username;
    private String password;
    private String firstname;
    private String lastName;
    private String phoneNumber;
    private static List<Post> posts=new ArrayList<>();

    public User(String username) {
        this.username = username;
    }

    public User authenticate(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) return this;
        return null;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return firstname;
    }

    public void setName(String name) {
        this.firstname = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        try {
            return this.username.equals(((User)obj).getUsername());
        } catch (Exception e) {
            return false;
        }
    }
}
