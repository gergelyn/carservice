package com.webclass.carservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Objects;

@Entity

public class Manager {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private @JsonIgnore String password;

    private String[] roles;

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }

    protected Manager() {}

    public Manager(String firstName, String lastName, String email, String password, String... roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.setPassword(password);
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return Objects.equals(id, manager.id) &&
                Objects.equals(firstName, manager.firstName) &&
                Objects.equals(lastName, manager.lastName) &&
                Objects.equals(email, manager.email) &&
                Objects.equals(password, manager.password) &&
                Arrays.equals(roles, manager.roles);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, firstName, lastName, email, password);
        result = 31 * result + Arrays.hashCode(roles);
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + Arrays.toString(roles) +
                '}';
    }
}
