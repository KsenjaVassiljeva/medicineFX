package customer.newcustomer;

import medicine.newmedicine.NewmedicineController;
import entity.Customer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewcustomerController implements Initializable {

    private NewmedicineController newmedicineController;
    
    @FXML private TextField tfFirstname;
    @FXML private TextField tfLastname;
    @FXML private Label lbInfoNewAuthor;
    
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setNewmedicineController(NewmedicineController newmedicineController) {
        this.newmedicineController = newmedicineController;
    }

    @FXML
    private void clickAddCustomer() {
        if (tfFirstname.getText().isEmpty() || tfLastname.getText().isEmpty()) {
            lbInfoNewAuthor.getStyleClass().clear();
            lbInfoNewAuthor.getStyleClass().add("infoError");
            lbInfoNewAuthor.setText("Заполните все поля формы");
            return;
        }
        Customer customer = new Customer();
        customer.setFirstName(tfFirstname.getText());
        customer.setLastName(tfLastname.getText());
        try {
            newmedicineController.getHomeController().getApp().getEntityManager().getTransaction().begin();
            newmedicineController.getHomeController().getApp().getEntityManager().persist(customer);
            newmedicineController.getHomeController().getApp().getEntityManager().getTransaction().commit();
            newmedicineController.getHomeController().getLbInfo().getStyleClass().clear();
            newmedicineController.getHomeController().getLbInfo().getStyleClass().add("info");
            newmedicineController.getHomeController().getLbInfo().setText("Автор добавлен");
        } catch (Exception e) {
            newmedicineController.getHomeController().getLbInfo().getStyleClass().clear();
            newmedicineController.getHomeController().getLbInfo().getStyleClass().add("infoError");
            newmedicineController.getHomeController().getLbInfo().setText("Автора добавить не удалось");
        }
        this.stage.close();
    }

    public void setStage(Stage modalWindowsAddAuthors) {
        this.stage = modalWindowsAddAuthors;
    }
}
