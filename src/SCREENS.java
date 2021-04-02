public enum SCREENS {
  LOGIN(0),
  MANAGERHOME(1),
  CREATENEWGUEST(2),
  FLOORCHART(3),
  HOUSEKEEPING(4),
  MAINTENANCE(5),
  VALET(6),
  EMPLOYEELIST(7),
  GUESTHOME(8),
  MAINTENANCEREQUEST(9),
  HOUSEKEEPINGREQUEST(10),
  FOODSERVICE(11),
  CONCIERGE(12),
  FOODSERVICESVIEW(13),
  VALETVIEW(14),
  EMPLOYEEACCOUNTFORM(15),
  GUESTLIST(16);

  private final int value;

  SCREENS(final int newValue){
    value = newValue;
  }

  public int getValue(){
    return value;
  }
}
