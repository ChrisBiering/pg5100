package src.main.model;

import javax.persistence.*;

/**
 * Created by Chris on 05/10/15.
 */

@Entity(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 50)
    private String email;
    @Column(length = 20)
    private UserType userType;
    @Column(length = 100)
    private String password;

    public User(String email, String password, UserType userType) {
        this.email = email;
        this.userType = userType;
        this.password = password;
    }

    public User() {

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", userType=" + userType +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return !(id != null ? !id.equals(user.id) : user.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
