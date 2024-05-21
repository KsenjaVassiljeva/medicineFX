/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.loginform;

import entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import medicinesptv22.HomeController;
import tools.PassEncrypt;

public class LoginformController implements Initializable {
    private HomeController homeController;
    
    @FXML 
    private Label lbLoginInfo;
    
    @FXML 
    private TextField tfLogin;
    
    @FXML 
    private TextField tfPassword;
    
    @FXML 
    private void clickLogin() {
        String login = tfLogin.getText();
        String password = tfPassword.getText();
        
        try {
            User user = (User) homeController.getApp().getEntityManager().createQuery("SELECT u FROM User u WHERE u.login=:login")
                .setParameter("login", login)
                .getSingleResult();
            
            PassEncrypt pe = new PassEncrypt();
            String encryptedPass = pe.getEncryptPassword(password, pe.getSalt());
            
            if (!user.getPassword().equals(encryptedPass)) {
                lbLoginInfo.setText("Пароли не совпадают");
                System.out.println("Пароли не совпадают");
                return;
            }
            
            medicinesptv22.MedicineSptv22.user = user;
            homeController.getLbInfo().getStyleClass().clear();
            homeController.getLbInfo().getStyleClass().add("info");
            homeController.getLbInfo().setText("Вы вошли как " + user.getLogin());
            homeController.getLoginWindow().close();
        } catch (Exception e) {
            lbLoginInfo.setText("Нет такого пользователя");
            System.out.println("Error: ");
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
