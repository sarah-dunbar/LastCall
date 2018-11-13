//Location class stores two Passengers: a seated and a standing. These will be the objects that fill the plane.
public class Location{

private Passenger seated_passenger;
private Passenger waiting_passenger;

//waiting passenger is the same as standing passenger
public Location(Passenger seated, Passenger waiting){
seated_passenger=seated;
waiting_passenger=waiting;
}

public Passenger getSitting(){
  return seated_passenger;
}

public Passenger getStanding(){
  return waiting_passenger;
}

public void setSitting(Passenger p){
  seated_passenger=p;
}

public void setStanding(Passenger p){
  waiting_passenger=p;
}


}
