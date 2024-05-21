/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicine.newmedicine;

import entity.Medicine;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import medicinesptv22.HomeController;

public class NewmedicineController implements Initializable {

    private HomeController homeController;
    private File selectedFile;

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfManufactureYear;
    @FXML
    private TextField tfQuantity;
    @FXML
    private Button btAddMedicine;
    @FXML
    private Button btAddCover;

    @FXML
    private void clickAddMedicine() {
        if (tfName.getText().isEmpty() || tfManufactureYear.getText().isEmpty() || tfQuantity.getText().isEmpty()) {
            homeController.getLbInfo().getStyleClass().clear();
            homeController.getLbInfo().getStyleClass().add("infoError");
            homeController.getLbInfo().setText("Заполните все поля формы");
            return;
        }
        Medicine medicine = new Medicine();
        medicine.setName(tfName.getText());
        medicine.setManufactureYear(Integer.parseInt(tfManufactureYear.getText()));
        medicine.setQuantity(Integer.parseInt(tfQuantity.getText()));
        medicine.setCount(medicine.getQuantity());
        try {
            if (selectedFile != null) {
                try (FileInputStream fis = new FileInputStream(selectedFile)) {
                    byte[] fileContent = new byte[(int) selectedFile.length()];
                    fis.read(fileContent);
                    medicine.setCover(fileContent);
                }
            }
            homeController.getApp().getEntityManager().getTransaction().begin();
            homeController.getApp().getEntityManager().persist(medicine);
            homeController.getApp().getEntityManager().getTransaction().commit();
            tfName.setText("");
            tfManufactureYear.setText("");
            tfQuantity.setText("");
            btAddCover.setText("Выберите обложку");
            selectedFile = null;
            homeController.getLbInfo().getStyleClass().clear();
            homeController.getLbInfo().getStyleClass().add("info");
            homeController.getLbInfo().setText("Лекарство добавлено");
        } catch (Exception e) {
            e.printStackTrace();
            homeController.getLbInfo().getStyleClass().clear();
            homeController.getLbInfo().getStyleClass().add("infoError");
            homeController.getLbInfo().setText("Лекарство добавить не удалось");
        }
    }

    @FXML
    private void clickAddCover() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите обложку");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Изображения", "*.jpg", "*.png", "*.jpeg"),
                new FileChooser.ExtensionFilter("Все файлы", "*.*")
        );
        selectedFile = fileChooser.showOpenDialog(homeController.getApp().getPrimaryStage());
        if (selectedFile != null) {
            btAddCover.setText("Выбран файл: " + selectedFile.getName());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization code goes here
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    public HomeController getHomeController() {
        return homeController;
    }
}
