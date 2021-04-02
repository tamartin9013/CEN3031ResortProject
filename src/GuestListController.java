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
 * Class GuestListController shows all guests in a tableView, as well as allow managers to check out a selected guest.
 *
 */
public class GuestListController implements Initializable {
    @FXML
    public TableView<User> guestTable;

    /**
     * Populate list of guests.
     */
    public void populateGuestList() {
        ObservableList<User> currentGuestList = User.getUserList(0);
        TableColumn userIDCol = new TableColumn("UserID");
        userIDCol.setMinWidth(60.0);
        userIDCol.setCellValueFactory(
                new PropertyValueFactory<User, Integer>("userID"));


        TableColumn roomNumber = new TableColumn("Room #");
        roomNumber.setMinWidth(15.0);
        roomNumber.setCellValueFactory(
                new PropertyValueFactory<User, String>("guestRoomNumber"));

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

        guestTable.setItems(currentGuestList);
        guestTable.getColumns().clear();
        guestTable.getColumns().addAll(userIDCol, roomNumber, userNameCol, firstNameCol, lastNameCol, emailCol);
    }

    /**
     * Get user that's selected in tableView and check out through query of DB.
     *
     * @param actionEvent - Mouse click
     */
    public void checkoutGuest(ActionEvent actionEvent) {
        User selectedUser;
        selectedUser = guestTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            System.out.println("Checking out" + selectedUser.getUserName());
            selectedUser.deleteUser();
            populateGuestList();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("User not checked out.");
            alert.setContentText("Select a user and then click check out button.");
            alert.showAndWait();
        }
    }

    /**
     * Brings manager back to the home screen.
     *
     * @param mouseEvent - Mouse click
     */
    public void getBackHome(MouseEvent mouseEvent) {
        Main.setPane(SCREENS.MANAGERHOME.getValue());
    }

    @Override
    /**
     * When the screen loads, populate guest table.
     */
    public void initialize(URL location, ResourceBundle resources) {
        populateGuestList();
    }
}