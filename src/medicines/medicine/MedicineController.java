package medicines.medicine;

import medicine.listmedicine.ListmedicineController;
import entity.Medicine;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 */
public class MedicineController implements Initializable {

    private ListmedicineController listMedicineController;
    @FXML private Label lbName;
    @FXML private Label lbManufacturer;
    @FXML private Label lbExpiryDate;
    @FXML private Label lbQuantity;
    @FXML private Label lbCount;
    @FXML private ImageView ivImage;
    @FXML private VBox vbMedicineAttributes;

    public void setListMedicinesController(ListmedicineController listMedicineController) {
        this.listMedicineController = listMedicineController;
    }

    public void setMedicine(Medicine medicine) {
        ivImage.setImage(new Image(new ByteArrayInputStream(medicine.getImage())));
        lbName.setText(medicine.getName());
        lbManufacturer.setText(medicine.getManufacturer());
        lbExpiryDate.setText(medicine.getExpiryDate().toString());
        lbQuantity.setText(Integer.toString(medicine.getQuantity()));
        lbCount.setText(Integer.toString(medicine.getCount()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public ImageView getIvImage() {
        return ivImage;
    }

    public Label getLbName() {
        return lbName;
    }

    public void setLbName(Label lbName) {
        this.lbName = lbName;
    }

    public Label getLbManufacturer() {
        return lbManufacturer;
    }

    public void setLbManufacturer(Label lbManufacturer) {
        this.lbManufacturer = lbManufacturer;
    }

    public Label getLbExpiryDate() {
        return lbExpiryDate;
    }

    public void setLbExpiryDate(Label lbExpiryDate) {
        this.lbExpiryDate = lbExpiryDate;
    }

    public Label getLbQuantity() {
        return lbQuantity;
    }

    public void setLbQuantity(Label lbQuantity) {
        this.lbQuantity = lbQuantity;
    }

    public Label getLbCount() {
        return lbCount;
    }

    public void setLbCount(Label lbCount) {
        this.lbCount = lbCount;
    }

    public VBox getVbMedicineAttributes() {
        return vbMedicineAttributes;
    }

    public void setVbMedicineAttributes(VBox vbMedicineAttributes) {
        this.vbMedicineAttributes = vbMedicineAttributes;
    }
}
