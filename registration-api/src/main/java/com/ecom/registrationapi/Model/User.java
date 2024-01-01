package com.ecom.registrationapi.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "UserTable")
@Data
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private int phoneNumber;



    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
    }

    public void setLastName(String lastName) {
    }

    public void setPhoneNumber(Integer phoneNumber) {
    }
}
