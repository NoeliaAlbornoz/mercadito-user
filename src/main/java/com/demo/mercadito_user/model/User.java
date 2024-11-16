package com.demo.mercadito_user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String firstName;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String lastName;

    @Email(message = "El email debe tener un formato válido")
    @NotBlank(message = "El email no puede estar vacío")
    private String email;

    @Pattern(regexp = "\\d{1,10}", message = "El celular debe contener solo números y un máximo de 10 dígitos")
    private String phone;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    public void encryptPassword() {
        this.password = new BCryptPasswordEncoder().encode(this.password);
    }
}
