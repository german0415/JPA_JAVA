package co.edu.umanizales;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

    public class App {
        public static void main(String[] args) {
            // Crear el EntityManagerFactory y EntityManager
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("UsuarioPU");
            EntityManager em = emf.createEntityManager();

            try {
                // Iniciar la transacción
                em.getTransaction().begin();

                // Crear e insertar un nuevo Usuario
                Usuario usuario = new Usuario("Juan Pérez", "juan.perez@example.com");
                em.persist(usuario);

                // Confirmar la transacción
                em.getTransaction().commit();

                // Buscar el usuario en la base de datos
                Usuario resultado = em.find(Usuario.class, usuario.getId());
                System.out.println("Usuario encontrado: " + resultado);

            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                // Cerrar el EntityManager y EntityManagerFactory
                em.close();
                emf.close();
            }
        }
    }


