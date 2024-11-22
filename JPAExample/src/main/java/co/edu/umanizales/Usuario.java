package co.edu.umanizales;

import jakarta.persistence.*;

// Marca esta clase como una entidad de JPA
@Entity
@Table(name = "usuarios") // Especifica el nombre de la tabla

public class Usuario {   //Esta clase está mapeada a una tabla llamada usuarios en la base de datos.

    // Define el ID como clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor del ID
    private Long id;

    // Define columnas adicionales con restricciones
    @Column(nullable = false, length = 100) // Campo obligatorio con longitud máxima de 100
    private String nombre;

    @Column(unique = true, length = 150) // El email debe ser único con una longitud máxima de 150
    private String email;

    // Constructor vacío (requerido por JPA)
    public Usuario() {
    }

    // Constructor con parámetros (útil para inicializar objetos)
    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }


    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método para imprimir la información del objeto
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
        ", email='" + email + '\'' +
        '}';
    }
}
