/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicine.listmedicine;

import entity.Medicine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos; // Добавлен импорт Pos
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import medicines.medicine.MedicineController;
import medicinesptv22.HomeController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListmedicineController implements Initializable {

    private HomeController homeController;
    @FXML private HBox hbListMedicinesContent;
    private Stage modalWindow;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    public HomeController getHomeController() {
        return homeController;
    }

    public void loadMedicines() {
        List<Medicine> listMedicines = homeController.getApp().getEntityManager()
                .createQuery("SELECT m FROM Medicine m")
                .getResultList();
        try {
            hbListMedicinesContent.getChildren().clear();
            for (Medicine medicine : listMedicines) {
                FXMLLoader medicineLoader = new FXMLLoader();
                medicineLoader.setLocation(getClass().getResource("/medicines/medicine/medicine.fxml"));
                VBox vbMedicineRoot = medicineLoader.load();
                MedicineController medicineController = medicineLoader.getController();
                medicineController.setListMedicinesController(this); // Исправлено на setListMedicinesController
                medicineController.setMedicine(medicine);

                vbMedicineRoot.setOnMouseEntered(event -> {
                    vbMedicineRoot.setCursor(Cursor.HAND);
                });

                vbMedicineRoot.setOnMouseExited(event -> {
                    vbMedicineRoot.setCursor(Cursor.DEFAULT);
                });

                vbMedicineRoot.setOnMouseClicked(event -> {
                    System.out.println("Вы нажали на лекарство: " + medicine.getName());
                    showMedicine(medicine);
                });

                hbListMedicinesContent.getChildren().add(vbMedicineRoot);
            }
        } catch (IOException ex) {
            Logger.getLogger(ListmedicineController.class.getName()).log(Level.SEVERE, "not found medicine.fxml", ex);
        }
    }

    private void showMedicine(Medicine medicine) {
        try {
            modalWindow = new Stage();
            FXMLLoader medicineLoader = new FXMLLoader();
            medicineLoader.setLocation(getClass().getResource("/medicines/medicine/medicine.fxml"));
            VBox vbMedicineRoot = medicineLoader.load();
            MedicineController medicineController = medicineLoader.getController();
            medicineController.setMedicine(medicine);
            vbMedicineRoot.setAlignment(Pos.CENTER);

            Scene scene = new Scene(vbMedicineRoot, 400, 600);
            modalWindow.setTitle(medicine.getName());
            modalWindow.initModality(Modality.WINDOW_MODAL);
            modalWindow.initOwner(homeController.getApp().getPrimaryStage());
            modalWindow.setScene(scene);
            modalWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(ListmedicineController.class.getName()).log(Level.SEVERE, "not found medicine.fxml", ex);
        }
    }

    public void loadMedicine(Medicine medicine) {
        try {
            modalWindow = new Stage();
            FXMLLoader medicineLoader = new FXMLLoader();
            medicineLoader.setLocation(getClass().getResource("/medicines/medicine/medicine.fxml"));
            VBox vbMedicineRoot = medicineLoader.load();
            MedicineController medicineController = medicineLoader.getController();
            medicineController.setMedicine(medicine);
            vbMedicineRoot.setAlignment(Pos.CENTER);

            Scene scene = new Scene(vbMedicineRoot, 400, 600);
            modalWindow.setTitle(medicine.getName());
            modalWindow.initModality(Modality.WINDOW_MODAL);
            modalWindow.initOwner(homeController.getApp().getPrimaryStage());
            modalWindow.setScene(scene);
            modalWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(ListmedicineController.class.getName()).log(Level.SEVERE, "not found medicine.fxml", ex);
        }
    }
}
