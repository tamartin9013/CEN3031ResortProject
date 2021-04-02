import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.joda.time.DateTime;

import java.sql.*;

public class Room {
  private int roomNumber;
  private int roomStatus = 0;
  private int guestUserID;
  private String roomNotes;
  private String roomCheckedIn;
  private String roomCheckingOut;
  private int roomHousekeepingStatus;
  static Connection databaseConnection = null;

  public Room(int roomNumber, int roomStatus, int guestUserID, String roomNotes, String roomCheckedIn,
              String roomCheckingOut, int roomHousekeepingStatus) {
    this.roomNumber = roomNumber;
    this.roomStatus = roomStatus;
    this.guestUserID = guestUserID;
    this.roomNotes = roomNotes;
    this.roomCheckedIn = roomCheckedIn;
    this.roomCheckingOut = roomCheckingOut;
  }

  public Room(int roomNumber, int guestUserID) {
    this.roomNumber = roomNumber;
    this.guestUserID = guestUserID;
  }

  public boolean checkIn(int guestUserID) {
    roomStatus = 1;
    DateTime currentDT = new DateTime();
    roomCheckedIn = currentDT.toString();
    DateTime dt2 = new DateTime();
    return updateRoomDB();
  }

  public boolean checkOut() {
    roomStatus = 0;
    guestUserID = 0;
    return true;
  }

  public static Connection establishDBConnection() {
    databaseConnection = null;
    try {
      databaseConnection = DriverManager.getConnection("jdbc:sqlite:database/ResortProject.db");
      System.out.println("Returning connection");
      return databaseConnection;
    }
    catch(SQLException e) {
      System.err.println(e.getMessage());
    }
    return null;
  }

  public static ObservableList<Room> initRoomsFromDB() {
    ObservableList<Room> returnRoomList = FXCollections.observableArrayList();
    try
    {
      databaseConnection = establishDBConnection();
      System.out.println("Connection success");

      Statement statement = Room.databaseConnection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      ResultSet rs = statement.executeQuery("select * from Rooms" );
      while(rs.next())
      {
        // read the result set and instantiate an object for each user
        Room tempRoom;
        // Use employee constructor if user is an employee
        tempRoom = new Room(
                rs.getInt("roomNumber"),
                rs.getInt("roomStatus"),
                rs.getInt("guestUserID"),
                rs.getString("roomNotes"),
                rs.getString("roomCheckedIn"),
                rs.getString("roomCheckingOut"),
                rs.getInt("roomHousekeepingStatus"));

        returnRoomList.add(tempRoom);
      }
    }
    catch(SQLException e)
    {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
    finally {
      try
      {
        if(databaseConnection != null)
          databaseConnection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e.getMessage());
      }
    }
    return returnRoomList;
  }

  public boolean updateRoomDB() {
    System.out.println("Updating Rooms database");
    databaseConnection = establishDBConnection();
    int updateResult = 0;
    try {
      Statement statement = databaseConnection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      String updateQuery = "update Rooms set roomStatus = " + roomStatus + ", guestUserID = "
              + guestUserID + ", roomCheckedIn = '" + roomCheckedIn + "' where roomNumber = " + roomNumber;
      System.out.println(updateQuery);

      updateResult = statement.executeUpdate("update Rooms set roomStatus = " + roomStatus + ", guestUserID = "
              + guestUserID + ", roomCheckedIn = '" + roomCheckedIn + "' where roomNumber = " + roomNumber);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    } finally {
      try {
       if (databaseConnection != null)
          databaseConnection.close();
      } catch (SQLException e) {
        System.err.println(e.getMessage());
      }
    }
    System.out.println("Update Result " + updateResult);
    if (updateResult == 1) return true;
    return false;
  }

  public int getRoomNumber() {
    return roomNumber;
  }
  public int getRoomStatus() {
    return roomStatus;
  }

}