import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Class LoginController checks for username and password in the DB table user. If the user
 */
public class LoginController {

  //keep variables public otherwise you may encounter issues in fetching data
  public TextField userName;
  public PasswordField password;
  public Button buttonLogin;
  public User currentUser;

  public String getUserName() {
    System.out.println("getUserName");
//    return userName.getText();
    return "testReturnGetUserName";
  }

  public String getPassword() {
    System.out.println("getPassword");
//    return userName.getText();
    return "testReturnGetUserPassword";
  }

  public void login(ActionEvent actionEvent) {
    boolean authenticated = false;
    try {
      String userNameInput = userName.getText();
      int userPINInput = Integer.parseInt(password.getText());
      currentUser = new User(userNameInput, userPINInput);
      authenticated = currentUser.getAuthStatus();
    }
    catch(Exception e) {
      password.clear();
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error!");
      alert.setHeaderText(null);
      alert.setContentText("Wrong username or password!");

      alert.showAndWait();
    }

    if (authenticated) {
      if(currentUser.isEmployee() == 1) {
        int empTypeIndex = currentUser.getEmpType();
        System.out.println(empTypeIndex);
        System.out.println("Logged in as employee type: " + EMPTYPES.getEmpTypeByIndex(empTypeIndex));
        switch(empTypeIndex) {
          case 1: //Manager
            Main.setPane(SCREENS.MANAGERHOME.getValue());
            break;
          case 2: //Housekeeping
            Main.setPane(SCREENS.HOUSEKEEPING.getValue());
            break;
          case 3:
            Main.setPane(SCREENS.MAINTENANCE.getValue());
            break;
          case 4:
            Main.setPane(SCREENS.VALET.getValue());
            break;
          case 5:
            Main.setPane(SCREENS.FOODSERVICESVIEW.getValue());
        }
        userName.clear();
        password.clear();
      }
      else {
        Main.setPane(SCREENS.GUESTHOME.getValue());
      }
    }
  }
}