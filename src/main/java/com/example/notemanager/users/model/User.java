package com.example.notemanager.users.model;

import com.example.notemanager.notes.model.Note;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INTEGER")
    private Long id;

    @Column(name = "username", nullable = false)
    @NotNull(message = "Username must not be null")
    @NotEmpty(message = "Username must not be empty")
    private String userName;

    @Column(name = "email", nullable = false, unique = true)
    @NotNull(message = "Email must not be null")
    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "password", nullable = false)
    @NotNull(message = "Password must not be null")
    @NotEmpty(message = "Password must not be empty")
    private String password;

    @Column(name = "role", nullable = false)
    @NotNull(message = "Role must not be null")
    @NotEmpty(message = "Role must not be empty")
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Note> notes;
}