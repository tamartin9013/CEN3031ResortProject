import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Class EmployeeListController shows all employees in a tableView, as well as allow managers to add or remove
 * an employee.
 *
 */
public class EmployeeListController implements Initializable {
  @FXML
  public TableView<User> employeeTable;

  /**
   * Creates columns in tableView based on user fields. Fetches each employeee via query, and displays its
   * information.
   */
  public void populateEmployeeList() {
    ObservableList<User> currentEmployeeList = User.getUserList(1);
    TableColumn userIDCol = new TableColumn("UserID");
      userIDCol.setMinWidth(60.0);
      userIDCol.setCellValueFactory(
              new PropertyValueFactory<User, Integer>("userID"));

    TableColumn userNameCol = new TableColumn("UserName");
      userNameCol.setMinWidth(75.0);
      userNameCol.setCellValueFactory(
              new PropertyValueFactory<User, String>("userName"));

    TableColumn firstNameCol = new TableColumn("FirstName");
      firstNameCol.setMinWidth(90.0);
      firstNameCol.setCellValueFactory(
              new PropertyValueFactory<User, String>("userFirstName"));

    TableColumn lastNameCol = new TableColumn("LastName");
      lastNameCol.setMinWidth(90.0);
      lastNameCol.setCellValueFactory(
              new PropertyValueFactory<User, String>("userLastName"));

    TableColumn emailCol = new TableColumn("Email");
      emailCol.setMinWidth(220.0);
      emailCol.setPrefWidth(220.0);
      emailCol.setCellValueFactory(
              new PropertyValueFactory<User, String>("userEmail"));

    TableColumn empTypeTextCol = new TableColumn("Employee Type");
        empTypeTextCol.setMinWidth(110.0);
        empTypeTextCol.setCellValueFactory(
                new PropertyValueFactory<User, String>("empTypeText"));

    employeeTable.setItems(currentEmployeeList);
    employeeTable.getColumns().clear();
    employeeTable.getColumns().addAll(userIDCol, userNameCol, firstNameCol, lastNameCol, emailCol, empTypeTextCol);
  }

  /**
   * Removes a selected employee of the tableView from DB.
   *
   * @param actionEvent - Mouse click
   */
  public void deleteUser(ActionEvent actionEvent) {
    User selectedUser;
    selectedUser = employeeTable.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
      System.out.println("Delete User" + selectedUser.getUserName());
      selectedUser.deleteUser();
      populateEmployeeList();
    }
    else {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Error");
      alert.setHeaderText("User not deleted.");
      alert.setContentText("Select a user and then click Delete User button.");
      alert.showAndWait();
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

  /**
   * Brings manager to the form utilized to create a new employee.
   *
   */
  public void launchNewEmployeeForm(ActionEvent actionEvent) {
    Main.setPane(SCREENS.EMPLOYEEACCOUNTFORM.getValue());
  }

  @Override
  /**
   * When the screen loads, populate table with employees.
   */
  public void initialize(URL location, ResourceBundle resources) {
    populateEmployeeList();
  }
}
