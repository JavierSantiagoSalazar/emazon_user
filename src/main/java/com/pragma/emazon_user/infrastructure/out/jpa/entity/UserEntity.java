package com.pragma.emazon_user.infrastructure.out.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name", nullable = false, length = 150)
    private String userName;

    @Column(name = "user_last_name", nullable = false, length = 150)
    private String userLastName;

    @Column(name = "user_document", nullable = false, length = 10, unique = true)
    private String userDocument;

    @Column(name = "user_phone", nullable = false, length = 13, unique = true)
    private String userPhone;

    @Column(name = "user_birthdate", nullable = false)
    private LocalDate userBirthdate;

    @Column(name = "user_email", nullable = false, length = 150)
    private String userEmail;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity userRole;

}