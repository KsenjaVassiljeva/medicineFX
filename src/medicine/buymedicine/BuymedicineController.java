package medicine.buymedicine;

import entity.Medicine;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import medicinesptv22.HomeController;

public class BuymedicineController implements Initializable {

    private HomeController homeController;
    private Medicine medicine;

    @FXML
    private TextField tfMedicineName;

    @FXML
    private TextField tfQuantity;

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    public HomeController getHomeController() {
        return homeController;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
        tfMedicineName.setText(medicine.getName());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Инициализация, если необходимо
    }

    @FXML
    private void buyMedicine(ActionEvent event) {
        String quantityStr = tfQuantity.getText().trim();
        if (quantityStr.isEmpty()) {
            showAlert("Введите количество!");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                showAlert("Количество должно быть положительным числом!");
                return;
            }
            // Ваш код для обработки покупки лекарства
            // Например, вы можете добавить информацию о покупке в базу данных
            // и отобразить сообщение об успешной покупке
            showInformation("Покупка успешно завершена!");
        } catch (NumberFormatException e) {
            showAlert("Неверный формат количества!");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInformation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
