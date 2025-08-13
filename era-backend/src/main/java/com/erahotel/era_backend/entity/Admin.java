package com.erahotel.era_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admins") // table name in the database
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment primary key
    private Long adminId; // column: admin_id

    private String name; // column: name

    @Column(nullable = false, unique = true)
    private String email; // column: email (must be unique)

    @Column(nullable = false)
    private String password; // column: password (plain text in this simple version)
}

