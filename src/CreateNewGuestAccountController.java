import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * Class CreateNewGuestAccountController allows managers to create a new guest account (whenever a guest checks in).
 *
 * TODO: Make use of some of the extra fields for guests(credit card, address, etc.)
 * TODO: Better error checking for input fields
 */
public class CreateNewGuestAccountController {

  private String userFirstName;
  private String userLastName;
  private String userEmail;
  private String userPhone;
  private String userName;
  private String userStatus;
  private int userPIN;
  private int userIsEmployee;
  private int userEmpType;
  private int roomNumber;
  private boolean insertSuccessful;

  @FXML
  public TextField firstName;
  @FXML
  public TextField lastName;
  @FXML
  private TextField pinField;
  @FXML
  public TextField billingAddress;
  @FXML
  public TextField emailField;
  @FXML
  public TextField userPhoneField;
  @FXML
  public TextField roomNumField;
  @FXML
  public TextField empTypeField;
  @FXML
  public ToggleGroup empGuestRadio;
  @FXML
  public TextField nameOnCard;
  @FXML
  public TextField securityCode;
  @FXML
  public PasswordField creditCard;
  @FXML
  public Button buttonCreateAccount;
  @FXML
  public ImageView logoHome;

  public String getFirstName(ActionEvent actionEvent) {
    return firstName.getText();
  }

  /**
   * Function CreateAccount retrieves data from the textBoxes and creates a query that inserts the new guest in the
   * user table.
   *
   * TODO - Query that creates user
   *
   * @param actionEvent - Mouse click
   */
  public void CreateAccount(ActionEvent actionEvent) {
    int newUserID;
    userIsEmployee = 0;
    userStatus = "active";
    userFirstName = firstName.getText();
    userLastName = lastName.getText();
    userEmail = emailField.getText();
    userPhone = userPhoneField.getText();

    //In the event that the PIN is not an integer, catches the exception
    try {
      userPIN = Integer.parseInt(pinField.getText());
    }
    catch (Exception e) {
      System.out.println("Type mismatch exception. PIN must be an integer.");
    }
    userName = userFirstName.toLowerCase() + userLastName.toLowerCase().charAt(0);

    //Room number must be an integer as well
    try {
      roomNumber = Integer.parseInt(roomNumField.getText());
    }
    catch (Exception e) {
      System.out.println("Type mismatch exception. Room Number must be an integer.");
    }
    User newGuest = new User(0, userName, userFirstName, userLastName, userEmail, userPhone, userPIN,roomNumber,
            userStatus);
    newUserID = newGuest.insertUserInDB();

    System.out.println("new userID: " + newUserID);
    Room thisRoom = new Room(roomNumber, newUserID);
    thisRoom.checkIn(newUserID);
//    FloorChartController.initialize();


//    Room thisRoom = new Room(roomNumber);

    if (newUserID > 0) {
      //After DB method returns true
      String dialogText = userFirstName + " " + userLastName + " with userName: " + userName + " checked into room " + roomNumber + ".";
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Success!");
      alert.setHeaderText("Account has been created successfully!");
      alert.setContentText(dialogText);
      alert.showAndWait();

      Main.setPane(SCREENS.MANAGERHOME.getValue());
    }
  }

  /**
   * Brings manager back to its home page.
   *
   * @param mouseEvent - Mouse click
   */
  public void getBackHome(MouseEvent mouseEvent) {
    Main.setPane(SCREENS.MANAGERHOME.getValue());
  }
}
