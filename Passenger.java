//Passenger class allows us to assign seat objects to specific passengers
public class Passenger{

private Seat target_seat; //assigned seat
private Seat current_seat; //current position in the plane
private String name;

public Passenger(Seat _s, String _name){
  target_seat = _s;
  name=_name;
}

public Seat getTargetSeat() {
  return target_seat;
}

public String getName(){
  return name;
}

public Seat getCurrentSeat(){
  return current_seat;
}

public void setCurrentSeat(Seat seat){
    current_seat=seat;
}

public String toString(){
  return name;
}

public boolean hasReachedTargetRow() {
  if (current_seat.getRow() == target_seat.getRow()) {
    return true;
  }
  else {
    return false;
  }
}

public boolean hasReachedTargetSeat() {
  if (current_seat.getRow() == target_seat.getRow()  && current_seat.getColumn() == target_seat.getColumn()) {
    return true;
  }
  else {
    return false;
  }
}
}
