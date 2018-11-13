//Seat class generates row and column coordinates for specific positions in a 2d array (on the plane)
public class Seat{

private int row;
private int column;

public Seat(int _row, int _column){
  row=_row;
  column=_column;
}

public int getRow(){
  return row;
}

public int getColumn(){
  return column;
}

public void setRow(int _row){
  row=_row;
}

public void setColumn(int _column){
  column=_column;
}

}
