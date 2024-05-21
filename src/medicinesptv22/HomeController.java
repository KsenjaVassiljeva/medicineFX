package medicinesptv22;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import admin.adminpanel.AdminpanelController;
import medicines.medicine.MedicineController;
import medicine.listmedicine.ListmedicineController;

import medicine.newmedicine.NewmedicineController;
import entity.Medicine;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import range.RangepageController;
import users.listusers.ListusersController;
import users.loginform.LoginformController;
import users.newuser.NewuserController;
import users.userprofile.UserprofileController;

/**
 *
 * @author admin
 */
public class HomeController implements Initializable {
    private MedicineSptv22 app;
    private Stage loginWindow;
    @FXML private Label lbHello;
    @FXML private Label lbInfo;
    @FXML private VBox vbContent;
    
    
    @FXML
    public void showRangepage(){
        if(medicinesptv22.MedicineSptv22.user == null){
            getLbInfo().getStyleClass().clear();
            getLbInfo().getStyleClass().add("infoError");
            getLbInfo().setText("Войдите в программу со своим логином!"); 
            return;
        }
        if(!medicinesptv22.MedicineSptv22.user.getRoles().contains(medicinesptv22.MedicineSptv22.ROLES.ADMINISTRATOR.toString())){
            getLbInfo().getStyleClass().clear();
            getLbInfo().getStyleClass().add("infoError");
            getLbInfo().setText("У вас нет прав на этот ресурс. Только для администраторов!"); 
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/range/rangepage.fxml"));
        try {
            VBox vbAdminpanelRoot = loader.load();
            RangepageController rangepageController = loader.getController();
            rangepageController.setHomeController(this);
            rangepageController.showRangeMedicine();
            rangepageController.showRangeReaders();
            vbContent.getChildren().clear();
            vbContent.getChildren().add(vbAdminpanelRoot);
            
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
    }
    @FXML
    public void showAdminPanel(){
        if(medicinesptv22.MedicineSptv22.user == null){
            getLbInfo().getStyleClass().clear();
            getLbInfo().getStyleClass().add("infoError");
            getLbInfo().setText("Войдите в программу со своим логином!"); 
            return;
        }
        if(!medicinesptv22.MedicineSptv22.user.getRoles().contains(medicinesptv22.MedicineSptv22.ROLES.ADMINISTRATOR.toString())){
            getLbInfo().getStyleClass().clear();
            getLbInfo().getStyleClass().add("infoError");
            getLbInfo().setText("У вас нет прав на этот ресурс. Только для администраторов!"); 
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/admin/adminpanel/adminpanel.fxml"));
        try {
            VBox vbAdminpanelRoot = loader.load();
            AdminpanelController adminpanelController = loader.getController();
            adminpanelController.setHomeController(this);
            adminpanelController.loadUsers();
            adminpanelController.loadRoles();
            vbContent.getChildren().clear();
            vbContent.getChildren().add(vbAdminpanelRoot);
            
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
    }
        
    @FXML
    public void login() {
        loginWindow = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/users/loginform/loginform.fxml"));
        try {
            VBox vbLoginFormRoot = loader.load();
            LoginformController loginformController = loader.getController();
            loginformController.setHomeController(this);
            Scene scene = new Scene(vbLoginFormRoot, 401, 180);
            loginWindow.setTitle("Вход");
            loginWindow.initModality(Modality.WINDOW_MODAL);
            loginWindow.initOwner(getApp().getPrimaryStage());
            loginWindow.setScene(scene);
            loginWindow.show();
        } catch (IOException e) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, "Error loading login form FXML", e);
        }
    }


    
    @FXML
    private void addNewUser(){
        getLbInfo().getStyleClass().clear();
        getLbInfo().getStyleClass().add("info");
        getLbInfo().setText("");
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/users/newuser/newuser.fxml"));
            VBox vbNewUser = loader.load();
            NewuserController newuserController = loader.getController();
            newuserController.setHomeController(this);
            app.getPrimaryStage().setTitle("MedicineSptv22 - Добавление нового пользователя");
            vbContent.getChildren().clear();
            vbContent.getChildren().add(vbNewUser);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void userProfile(){
         try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/users/userprofile/userprofile.fxml"));
            VBox vbUserProfileRoot = loader.load();
            UserprofileController userprofileController = loader.getController();
            userprofileController.setHomeController(this);
             try {
                userprofileController.loadUser();
             } catch (Exception e) {
                getLbInfo().getStyleClass().clear();
                getLbInfo().getStyleClass().add("infoError");
                getLbInfo().setText("Войдите в программу со своим логином!");  
                return;
             }
            app.getPrimaryStage().setTitle("MedicineSptv22 - Профиль пользователя");
            vbContent.getChildren().clear();
            vbContent.getChildren().add(vbUserProfileRoot);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void addNewMedicine() {
        if (medicinesptv22.MedicineSptv22.user == null) {
            getLbInfo().getStyleClass().clear();
            getLbInfo().getStyleClass().add("infoError");
            getLbInfo().setText("Войдите в программу со своим логином!");
            return;
        }
        if (!medicinesptv22.MedicineSptv22.user.getRoles().contains(medicinesptv22.MedicineSptv22.ROLES.MANAGER.toString())) {
            getLbInfo().getStyleClass().clear();
            getLbInfo().getStyleClass().add("infoError");
            getLbInfo().setText("У вас нет прав на этот ресурс. Только для менеджеров!");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/medicine/newmedicine/newmedicine.fxml"));
            VBox vbNewMedicine = loader.load();
            NewmedicineController newmedicineController = loader.getController();
            newmedicineController.setHomeController(this);
            app.getPrimaryStage().setTitle("MedicineSptv22 - Добавление нового лекарства");
            this.lbInfo.setText("");
            vbContent.getChildren().clear();
            vbContent.getChildren().add(vbNewMedicine);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML 
    private void listUsers(){
        if(medicinesptv22.MedicineSptv22.user == null){
            getLbInfo().getStyleClass().clear();
            getLbInfo().getStyleClass().add("infoError");
            getLbInfo().setText("Войдите в программу со своим логином!"); 
            return;
        }
        if(!medicinesptv22.MedicineSptv22.user.getRoles().contains(medicinesptv22.MedicineSptv22.ROLES.MANAGER.toString())){
            getLbInfo().getStyleClass().clear();
            getLbInfo().getStyleClass().add("infoError");
            getLbInfo().setText("У вас нет прав на этот ресурс. Только для менеджеров!"); 
            return;
        }
         try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/users/listusers/listusers.fxml"));
            VBox vbListUsers = loader.load();
            ListusersController listusersController = loader.getController();
            listusersController.setHomeController(this);
            listusersController.loadUsers();
            app.getPrimaryStage().setTitle("MedicineSptv22 - Список пользователей");
            this.lbInfo.setText("");
            vbContent.getChildren().clear();
            vbContent.getChildren().add(vbListUsers);
            
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void listMedicine() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/medicine/listmedicine/listmedicine.fxml"));
            VBox vbListBooks = loader.load();
            ListmedicineController listmedicineController = loader.getController();
            listmedicineController.setHomeController(this);
            listmedicineController.loadMedicines();
            app.getPrimaryStage().setTitle("MedicineSptv22 - Список список лекарств");
            this.lbInfo.setText("");
            vbContent.getChildren().clear();
            vbContent.getChildren().add(vbListBooks);

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbHello.setText("Добро пожаловать в нашу Аптеку!");
    }   

    void setApp(MedicineSptv22 app) {
        this.app = app;
    }

    public MedicineSptv22 getApp() {
        return app;
    }

    public Label getLbInfo() {
        return lbInfo;
    }

    public Stage getLoginWindow() {
        return loginWindow;
    }

    void loadMedicines() {
        List<Medicine> listBooks = app.getEntityManager()
                .createQuery("SELECT b FROM Book b")
                .getResultList();
        ObservableList medicine = FXCollections.observableArrayList(listBooks);
        HBox hbListmedicine = new HBox();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/medicines/medicine/medicine.fxml"));
            VBox vbBook = loader.load();
            MedicineController medicineController = loader.getController();
           
            
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
}
