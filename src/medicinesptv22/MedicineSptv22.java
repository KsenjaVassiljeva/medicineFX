package medicinesptv22;

import entity.Customer;
import entity.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import tools.PassEncrypt;

public class MedicineSptv22 extends Application {

    private Stage primaryStage;
    public static enum ROLES {ADMINISTRATOR, MANAGER, USER};
    public static User user;
    private EntityManagerFactory emf;
    private final EntityManager entityManager;

    public MedicineSptv22() {
        emf = Persistence.createEntityManagerFactory("MedicineSptv22PU");
        entityManager = emf.createEntityManager();
        checkSuperUser();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/medicinesptv22/home.fxml"));
            VBox root = loader.load();
            HomeController controller = loader.getController();
            controller.setApp(this);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("MedicineSptv22");
            primaryStage.show();
        } catch (IOException e) {
            Logger.getLogger(MedicineSptv22.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    private void checkSuperUser() {
        try {
            if (entityManager.createQuery("SELECT u FROM User u").getResultList().isEmpty()) {
                User admin = new User();
                admin.setLogin("admin");
                PassEncrypt pe =  new PassEncrypt();
                admin.setPassword(pe.getEncryptPassword("12345", pe.getSalt()));
                admin.getRoles().add(ROLES.ADMINISTRATOR.toString());
                admin.getRoles().add(ROLES.MANAGER.toString());
                admin.getRoles().add(ROLES.USER.toString());
                Customer customer = new Customer();
                customer.setFirstName("Anna");
                customer.setLastName("Wesker");
                customer.setPhoneNumber("5654456565");
                entityManager.getTransaction().begin();
                entityManager.persist(customer);
                admin.setCustomer(customer);
                entityManager.persist(admin);
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
