//Plane class holds the logic for all of the different boarding algorithms 
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Plane{

private int row;
private int column;
private Location[][] plane;
private double time;

  public Plane(int _row, int _column){
    row=_row;
    column=_column;
    time=0.0;
    plane = new Location[_row][_column];
  }

  public void loadLocations(){ //initialize the Locations array
    for(int i=0; i<row; i++){
      for(int j=0; j<column; j++){
        Location l = new Location(null, null);
        plane[i][j]=l;
      }
    }
  }

  public double getTime(){
    return time;
  }

  public void addTime(double add_time){
    time+=add_time;
  }

  public void printTime(){
    double final_time=getTime();
    System.out.print("Final boarding time: ");
    System.out.printf("%.2f",final_time);
    System.out.println(" minutes!");
  }

  //returns pasenger that is sitting at inputted location
  public Passenger getSitting(int row, int column){
    return plane[row][column].getSitting();
  }

  //returns passenger that is standing at inputted location
  public Passenger getStanding(int row, int column){
    return plane[row][column].getStanding();
  }

  public boolean checkSitting(Seat potential_position){
    if(plane[potential_position.getRow()][potential_position.getColumn()].getSitting()==null){
      return false;
    }
    else{
      return true;
    }
  }

  public boolean checkStanding(Seat potential_position){
    if(plane[potential_position.getRow()][potential_position.getColumn()].getStanding()==null){
      return false;
    }
    else{
      return true;
    }
  }

  //prints passenger name where someone is seated, 0 where no one is seated
  public void printPassenger(int i, int j) {
    try {
      if(getSitting(i,j)==null){
        System.out.print("0 ");
      }
      else{
        System.out.print(getSitting(i,j).getName() + " ");
      }
    }
    catch(NullPointerException n){
      System.out.print("0 ");
    }
  }


  //prints only the seated passengers on the plane
  public void printPlane(){
  for(int i=0;i<row;i++){
    for(int j=0; j<column; j++){
      this.printPassenger(i,j);
    }
    System.out.println();
  }
  System.out.println();
  }

  //prints both passenger seated and passenger standing at certain location
  public void printPlane2(){
    for(int i=0; i<row; i++){
      for(int j=0; j<column; j++){
        System.out.print(getSitting(i,j) + ":" + getStanding(i,j) + " ");
        }

      System.out.println();
      }
      System.out.println();
    }

  //sets the boarding order of the plane to be back to front by row
  public ArrayList<Passenger> getPassengerQueue() {
    ArrayList<Passenger> q = new ArrayList<Passenger>();
    int counter= 1;
    for(int i=0;i<row;i++){
      for(int j=0; j<column; j++){
        if(j!=column/2){ //minus the aisle column
          Seat s = new Seat(i,j);
          Passenger p = new Passenger(s, Integer.toString(counter));
          q.add(p);
          counter++;
        }
        else{
        }
      }
    }
    return q; //returns an arraylist that determines our boarding ORDER
  }

  //this boarding procedure produces a random boarding order
  public ArrayList<Passenger> getRandomPassengerQueue() {
    ArrayList<Passenger> q = new ArrayList<Passenger>();
    int counter= 1;
    for(int i=0;i<row;i++){
      for(int j=0; j<column; j++){
        if(j!=column/2){
          Seat s = new Seat(i,j);
          Passenger p = new Passenger(s, Integer.toString(counter));
          q.add(p);
          counter++;
        }
        else{
        }
      }
    }
    Collections.shuffle(q); //randomizes arraylist order
    return q; //this arraylist that determines our boarding ORDER is now shuffled, but each individual Passenger is still assigned the same seat
  }

  //sets boarding order to be back to front, window seats first, then middle, then aisle
  public ArrayList<Passenger> getPassengerQueueWindowToAisle() {
    ArrayList<Passenger> q = new ArrayList<Passenger>();
    int counter= 1;
    for(int i=0;i<row;i++){
          for(int k=0; k<column/2; k++){
          Seat s = new Seat(i,k);
          Passenger p = new Passenger(s, Integer.toString(counter));
          q.add(p);
          counter++;
        }
          for(int k = column -1; k >column/2; k--){ //k starts at the window on the right
          Seat s = new Seat(i,k);
          Passenger p = new Passenger(s, Integer.toString(counter));
          q.add(p);
          counter++;
          }
        }

    return q;
  }

  //moves location of passenger, checks if someone is in the way and adds time accordingly
  public void movePersonUp(Passenger p){
    Seat previous_seat=p.getCurrentSeat();
    Seat new_seat=new Seat(p.getCurrentSeat().getRow()-1, p.getCurrentSeat().getColumn());
    if(checkStanding(new_seat)==false){
      p.setCurrentSeat(new_seat);
      plane[p.getCurrentSeat().getRow()][p.getCurrentSeat().getColumn()].setStanding(p);
      plane[previous_seat.getRow()][previous_seat.getColumn()].setStanding(null);
      addTime(.02);
      }
    else{
      addTime(.04); //if someone is standing where someone else needs to go, more time is added
    }
  }

  public void movePersonLeft(Passenger p){
    Seat previous_seat=p.getCurrentSeat();
    Seat new_seat= new Seat(p.getCurrentSeat().getRow(), p.getCurrentSeat().getColumn()-1);
    if(checkStanding(new_seat)==false){
      p.setCurrentSeat(new_seat);
      plane[p.getCurrentSeat().getRow()][p.getCurrentSeat().getColumn()].setStanding(p);
      plane[previous_seat.getRow()][previous_seat.getColumn()].setStanding(null);
       if(checkSitting(new_seat)==false){
         addTime(.02);
      }
       else{
        this.addTime(.2); //if there is someone sitting where someone else needs to pass, time is added
       }
    }
    else{
      addTime(.04);
    }
  }

  public void movePersonRight(Passenger p){
    Seat previous_seat=p.getCurrentSeat();
    Seat new_seat= new Seat(p.getCurrentSeat().getRow(), p.getCurrentSeat().getColumn()+1);
    if(checkStanding(new_seat)==false){
      p.setCurrentSeat(new_seat);
      plane[p.getCurrentSeat().getRow()][p.getCurrentSeat().getColumn()].setStanding(p);
      plane[previous_seat.getRow()][previous_seat.getColumn()].setStanding(null);
      if(checkSitting(new_seat)==false){
      addTime(.02);
      }
      else{
        this.addTime(.2);
      }
    }
    else{
      addTime(.04);
    }
  }


  public void moveOneStep(Passenger p){
    if(p.hasReachedTargetSeat()==false){
      if(p.hasReachedTargetRow()==false){
        movePersonUp(p); //passenger needs to reach the row where their assigned seat is
      }
      else if(p.getTargetSeat().getColumn()< p.getCurrentSeat().getColumn()){
        movePersonLeft(p); //passenger is to the right of where their assigned seat is
      }
      else{
        movePersonRight(p);
      }
    }
    else{ //if the passenger has reached their target seat, they sit
      plane[p.getTargetSeat().getRow()][p.getTargetSeat().getColumn()].setSitting(p);
      plane[p.getTargetSeat().getRow()][p.getTargetSeat().getColumn()].setStanding(null);
    }
  }

  //used in loadPlane to put new passenger in line for their seat as the plane boards
  public void placeNewPassenger(Passenger p){
    int aisle=column/2;
    int enter=row-1;
    Seat current_seat=new Seat(enter, aisle);
    p.setCurrentSeat(current_seat);
    plane[enter][aisle].setStanding(p);
  }

  //keeps boarding process moving if all passengers are on plane but not yet sitting
  public boolean everyoneSeated(int q){
    int counter=0;
    for(int i=0; i<row; i++){
      for(int j=0; j<column; j++){
        if(getSitting(i,j)!=null){
          counter++;
        }
      }
    }
    if (counter == q){
      return true;
    }
    else{
      return false;
    }
  }


  public void loadPlane(ArrayList<Passenger> queue){
    int q_size = queue.size();
    while(this.everyoneSeated(q_size)==false){
      for(int i=0; i<row; i++){
        for (int j=0; j<column; j++){
          try{
            if(getStanding(i,j)==null){
            }
            else{
              Passenger current_passenger=plane[i][j].getStanding();
              moveOneStep(current_passenger);
              // printPlane(); //uncomment to view plane with just seated passengers
              printPlane2();
            }
          }
          catch(NullPointerException n){
          }
        }
      }
      if(queue.size()>0){ //removes someone from the queue until the queue is empty
      placeNewPassenger(queue.remove(0));
      }
  }
 }

  public static void main(String[] args){
    Plane pl=new Plane(Integer.parseInt(args[0]),Integer.parseInt(args[1])); //type dimensions of plane into command line
    pl.loadLocations(); //initializing the Locations array
    //pl.loadPlane(pl.getPassengerQueue()); //uncomment to load a plane back to front but row by row
    pl.loadPlane(pl.getRandomPassengerQueue()); //uncomment to load a plane randomly
    //pl.loadPlane(pl.getPassengerQueueWindowToAisle()); //uncomment to load a plane back to from window to aisle
    pl.printPlane();
    //System.out.println();
    pl.printTime();
  }
}
