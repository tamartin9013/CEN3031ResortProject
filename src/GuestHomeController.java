import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Class GuestHomeController is the main page for guests. They have the ability to pick between 4 types of
 * services that they can request.
 *
 */
public class GuestHomeController {

  public Button buttonHousekeeping;
  public Button buttonMaintenance;
  public Button buttonFooServices;
  public Button buttonConcierge;

  /**
   * Brings guest to the housekeeping request screen.
   *
   * @param actionEvent - Mouse click
   */
  public void getHousekeepingRequest(ActionEvent actionEvent) {
    Main.setPane(SCREENS.HOUSEKEEPINGREQUEST.getValue());
  }

  /**
   * Brings guest to the maintenance request screen.
   *
   * @param actionEvent - Mouse click
   */
  public void getMaintenanceRequest(ActionEvent actionEvent) {
    Main.setPane(SCREENS.MAINTENANCEREQUEST.getValue());
  }

  /**
   * Brings guest to the food services request screen.
   *
   * @param actionEvent - Mouse click
   */
  public void getFoodServices(ActionEvent actionEvent) {
    Main.setPane(SCREENS.FOODSERVICE.getValue());
  }

  /**
   * Brings guest to the concierge request screen.
   *
   * @param actionEvent - Mouse click
   */
  public void getConciergeServices(ActionEvent actionEvent) {
    Main.setPane(SCREENS.CONCIERGE.getValue());
  }

}
