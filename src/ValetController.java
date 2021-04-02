import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class ValetController {


  //created fields only for room 100-104.
  //I imagined that once room is clicked you can get info of a guest
  public ImageView logoHome;
  public Rectangle room101;
  public Rectangle room102;
  public Rectangle room100;
  public Rectangle room103;

  @FXML
  public Button refreshRequestButton;

  @FXML
  public Button requestDoneButton;

  @FXML
  public TableView<Request> requestsTable;


  public void initialize(){
    ObservableList<Request> selectedRequests = Request.getSelectedRequestList(4);

    TableColumn requestIDCol = new TableColumn("RequestID");
    requestIDCol.setMinWidth(20);
    requestIDCol.setCellValueFactory(
            new PropertyValueFactory<Request, Integer>("requestID"));

    TableColumn assignedEmployeeCol = new TableColumn("Assigned Employee");
    assignedEmployeeCol.setMinWidth(10);
    assignedEmployeeCol.setCellValueFactory(
            new PropertyValueFactory<Request, Integer>("empType"));

    TableColumn requestEnteredTimestampCol = new TableColumn("Request submitted on");
    requestEnteredTimestampCol.setMinWidth(60);
    requestEnteredTimestampCol.setCellValueFactory(
            new PropertyValueFactory<Request, String>("requestEnteredTimestamp"));

    TableColumn detailsCol = new TableColumn("Details");
    detailsCol.setMinWidth(60);
    detailsCol.setCellValueFactory(
            new PropertyValueFactory<Request, String>("requestDetail"));

    TableColumn requestStatusCol = new TableColumn("Status");
    requestStatusCol.setMinWidth(20);
    requestStatusCol.setCellValueFactory(
            new PropertyValueFactory<Request, String>("requestStatus"));

    TableColumn requestedCompletedTimeStampCol = new TableColumn("Completed on");
    requestedCompletedTimeStampCol.setMinWidth(60);
    requestedCompletedTimeStampCol.setCellValueFactory(
            new PropertyValueFactory<Request, String>("requestCompletedTimestamp"));

    TableColumn requestEmpNotesCol = new TableColumn("Employee Notes");
    requestEmpNotesCol.setMinWidth(60);
    requestEmpNotesCol.setCellValueFactory(
            new PropertyValueFactory<Request, String>("requestEmpNotes"));

    requestsTable.setItems(selectedRequests);
    requestsTable.getColumns().clear();
    requestsTable.getColumns().addAll(requestIDCol, assignedEmployeeCol, requestEnteredTimestampCol, detailsCol, requestStatusCol,
            requestedCompletedTimeStampCol, requestEmpNotesCol);
  }

  public void getInforoom101(MouseEvent mouseEvent) {
  }

  public void getInfo(MouseEvent mouseEvent) {
  }

  public void getInfoRoom100(MouseEvent mouseEvent) {
  }

  public void getInforoom103(MouseEvent mouseEvent) {
  }

  public void logOut(){
    Main.setPane(SCREENS.LOGIN.getValue());
  }

  public void loadRequests(ActionEvent actionEvent) {
    ObservableList<Request> selectedRequests = Request.getSelectedRequestList(4);

    TableColumn requestIDCol = new TableColumn("RequestID");
    requestIDCol.setMinWidth(20);
    requestIDCol.setCellValueFactory(
            new PropertyValueFactory<Request, Integer>("requestID"));

    TableColumn assignedEmployeeCol = new TableColumn("Assigned Employee");
    assignedEmployeeCol.setMinWidth(10);
    assignedEmployeeCol.setCellValueFactory(
            new PropertyValueFactory<Request, Integer>("empType"));

    TableColumn requestEnteredTimestampCol = new TableColumn("Request submitted on");
    requestEnteredTimestampCol.setMinWidth(60);
    requestEnteredTimestampCol.setCellValueFactory(
            new PropertyValueFactory<Request, String>("requestEnteredTimestamp"));

    TableColumn detailsCol = new TableColumn("Details");
    detailsCol.setMinWidth(60);
    detailsCol.setCellValueFactory(
            new PropertyValueFactory<Request, String>("requestDetail"));

    TableColumn requestStatusCol = new TableColumn("Status");
    requestStatusCol.setMinWidth(20);
    requestStatusCol.setCellValueFactory(
            new PropertyValueFactory<Request, String>("requestStatus"));

    TableColumn requestedCompletedTimeStampCol = new TableColumn("Completed on");
    requestedCompletedTimeStampCol.setMinWidth(60);
    requestedCompletedTimeStampCol.setCellValueFactory(
            new PropertyValueFactory<Request, String>("requestCompletedTimestamp"));

    TableColumn requestEmpNotesCol = new TableColumn("Employee Notes");
    requestEmpNotesCol.setMinWidth(60);
    requestEmpNotesCol.setCellValueFactory(
            new PropertyValueFactory<Request, String>("requestEmpNotes"));

    requestsTable.setItems(selectedRequests);
    requestsTable.getColumns().clear();
    requestsTable.getColumns().addAll(requestIDCol, assignedEmployeeCol, requestEnteredTimestampCol, detailsCol, requestStatusCol,
            requestedCompletedTimeStampCol, requestEmpNotesCol);

  }

  public void markRequestDone(ActionEvent actionEvent) {
  }
}
