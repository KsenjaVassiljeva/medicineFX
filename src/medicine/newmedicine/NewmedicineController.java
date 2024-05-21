/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicine.newmedicicne;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import medicinesptv22.HomeController;

public class NewmedicineController implements Initializable {

    private HomeController homeController;

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
}
