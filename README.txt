LastCall is a program which simulates three different plane boarding algorithms, calculating the relative times for each, 
allowing us to find the most efficient one.
This program runs in terminal and displays the different frames of the boarding process before it presents a final time.

:::Files and Directories :::
README.txt               -This file
Location.java            -Creates Location objects which fill the plane in the simulation, consists of a seated and standing 
                         passenger
Passenger.java           -Allows user to assign seats to passengers, these seats will not change throughout
Plane.java               -Holds algorithms for all plane boarding options, calculates time for each
Seat.java                -Generates row and column coordinates for a 2D plane, based on user inputted dimensions of the plane


::: To test :::
1.In the main method of the Plane file, choose the queue generator based on the type of algorithm you want to test.
  -Note:getRandomPassengerQueue boards the plane in a random order, getPassengerQueueWindowToAisle boards the plane back to 
  front and window seat to aise seat,and getPassengerQueue boards the plane back to front by row.
2.In the main method choose the printing method
  -Note: printPlane displays only the seated passengers, and printPlane2 prints both the seated and standing passengers, 
   separated by a colon
3.Run Plane file in command line with the dimensions of the plane as arguments: java Plane <numberRows> <numberColumns>
  -Note: make sure to always add one to the number of columns to take care of the aisle
