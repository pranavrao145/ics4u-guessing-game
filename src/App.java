/******************************************************************************
Program: App Class (Guessing Game)

Description: This is the driver code for this guessing game. This class' main
method will start the entire program.

Author: Pranav Rao

Date: February 12, 2022
*******************************************************************************/

public class App {
  // this is the entrypoint for the entire program
  public static void main(String[] args) {
    Engine.run(new GUI()); // create a new GUI and run a new Engine with it
  }
}
