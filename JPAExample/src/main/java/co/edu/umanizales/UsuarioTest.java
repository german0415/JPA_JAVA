package co.edu.umanizales;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    public static void init() {
        // Crear EntityManagerFactory y EntityManager
        emf = Persistence.createEntityManagerFactory("UsuarioPU");
        em = emf.createEntityManager();
    }

    @AfterAll
    public static void close() {
        // Cerrar EntityManager y EntityManagerFactory
        if (em != null) em.close();
        if (emf != null) emf.close();
    }

    @Test
    public void testInsertarUsuario() {
        em.getTransaction().begin();

        // Crear e insertar un nuevo Usuario
        Usuario usuario = new Usuario("Carlos Mendoza", "carlos.mendoza@example.com");
        em.persist(usuario);

        // Confirmar la transacción
        em.getTransaction().commit();

        // Recuperar el usuario insertado
        Usuario usuarioGuardado = em.find(Usuario.class, usuario.getId());
        assertNotNull(usuarioGuardado);
        assertEquals("Carlos Mendoza", usuarioGuardado.getNombre());
        assertEquals("carlos.mendoza@example.com", usuarioGuardado.getEmail());
    }

    @Test
    public void testUsuarioDuplicadoEmail() {
        em.getTransaction().begin();

        // Crear e insertar un nuevo Usuario con un email único
        Usuario usuario1 = new Usuario("Maria Gomez", "maria.gomez@example.com");
        em.persist(usuario1);

        em.getTransaction().commit();

        // Intentar insertar un usuario con el mismo email debería lanzar una excepción
        em.getTransaction().begin();
        Usuario usuario2 = new Usuario("Jose Lopez", "maria.gomez@example.com");
        assertThrows(Exception.class, () -> em.persist(usuario2));
        em.getTransaction().rollback();
    }
}
