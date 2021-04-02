import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Class EmployeeAccountFormController allows managers to create a user of type employee. The employee type will be
 * identified by the integer variable userEmpType.
 *
 */
public class EmployeeAccountFormController {

  private String userFirstName;
  private String userLastName;
  private String userEmail;
  private String userName;
  private String userStatus;
  private String userPhone;
  private int userPIN;
  private int userIsEmployee;
  private int userEmpType;
  private boolean insertSuccessful;

  @FXML
  public TextField firstName;
  @FXML
  public TextField lastName;
  @FXML
  private TextField pinField;
  @FXML
  public TextField emailField;
  @FXML
  public TextField phoneNumberField;
  @FXML
  public TextField empTypeField;
  @FXML
  public Button buttonCreateAccount;
  @FXML
  public ImageView logoHome;

  /**
   * Function CreateAccount, utilizes userType = 1 since we are creating an employee and not a guest.
   * Fetches data from textBoxes and creates a query to insert the employee in the user table of the DB.
   *
   * @param actionEvent - Mouse click
   */
  public void CreateAccount(ActionEvent actionEvent) {
    userIsEmployee = 1;
    userStatus = "active";
    userFirstName = firstName.getText();
    userLastName = lastName.getText();
    userEmail = emailField.getText();
    userPhone = phoneNumberField.getText();

    //PIN must be an integer
    try {
      userPIN = Integer.parseInt(pinField.getText());
    } catch (Exception e) {
      System.out.println("Type mismatch exception. PIN must be an integer.");

      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error!");
      alert.setHeaderText(null);
      alert.setContentText("Type mismatch exception. PIN must be an integer.");

      alert.showAndWait();
    }
    userName = userFirstName.toLowerCase() + userLastName.toLowerCase().charAt(0);

    //Employee type must be an integer
    try {
      userEmpType = Integer.parseInt(empTypeField.getText());
    } catch (Exception e) {
      System.out.println("Type mismatch exception. Employee type must be an integer.");

      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error!");
      alert.setHeaderText(null);
      alert.setContentText("Type mismatch exception. Employee Type must be an integer.");

      alert.showAndWait();
    }

    User newEmployee = new User(0, userName, userFirstName, userLastName, userEmail, userPhone, userPIN,
            userIsEmployee, userEmpType, userStatus);

    int userID = newEmployee.insertUserInDB();

    if (insertSuccessful) {
      String dialogText = userFirstName + " " + userLastName + " with userName: " + userName + " and userID: " + userID;

      //After user is created successfully
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("User Created!");
      alert.setHeaderText("Account has been created successfully!");
      alert.setContentText(dialogText);
      alert.showAndWait();
      Main.setPane(SCREENS.EMPLOYEELIST.getValue());
    }
  }

  /**
   * Brings the manager back to the Employee List screen.
   *
   * @param mouseEvent - Mouse click
   */
  public void getBackHome(MouseEvent mouseEvent) {
    Main.setPane(SCREENS.EMPLOYEELIST.getValue());
  }
}
