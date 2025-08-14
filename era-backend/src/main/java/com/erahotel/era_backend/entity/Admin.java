package com.erahotel.era_backend.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entity class representing an admin user in the system.
 * <p>
 * Maps to the "admins" table in the database and contains basic admin information.
 * </p>
 *
 * @author alwin roble
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admins") // specifies the table name in MySQL
public class Admin {

    /**
     * The unique identifier for the admin.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    /**
     * The full name of the admin.
     */
    private String name;

    /**
     * The email address of the admin.
     */
    private String email;

    /**
     * The password of the admin.
     */
    private String password;
}
