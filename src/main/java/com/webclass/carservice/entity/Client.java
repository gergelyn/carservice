package com.webclass.carservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String carType;
    private String licenseCode;
    private String issue;
    private String progress = "Felvett munka";
    private @ManyToOne Manager manager;
    public Client(String firstName, String lastName, String carType, String licenseCode, String issue, Manager manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.carType = carType;
        this.licenseCode = licenseCode;
        this.issue = issue;
        this.manager = manager;
    }


}
