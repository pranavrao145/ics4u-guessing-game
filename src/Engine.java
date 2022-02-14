/******************************************************************************
Program: Engine Class (Guessing Game)

Description: This is the engine for this game. This class contains all of the
methods to run the game itself, such as to intialize and reset the game,
as well as the event loops.

Author: Pranav Rao

Date: February 12, 2022
*******************************************************************************/

// import the random class and the JButton Swing component
import java.util.Random;

import javax.swing.JButton;

public class Engine {
  // all of these variables are private, and will be exclusively
  // manipulated using the getters and setters defined below

  // create a new object of the Random class to generate random integers
  private static Random generator;

  // create two volatile booleans to signify if the app is running and
  // if the game is running. These must be volatile because they are
  // being changed by various methods on the fly
  private static volatile boolean appRunning, gameRunning;

  // declare variables to keep track of the number of tries left, the
  // losses, the wins, and the current random number
  private static int triesLeft, wins, losses, currentRandomNumber;

  // this is a GUI object that will be set in the intialize method
  // later on. It represents the current GUI instance and will be used
  // to manipulate the UI
  private static GUI gui;

  // this is the initialize method. It will be called to start the
  // game. This method takes a GUI instance (generated in the App
  // class) and return nothing
  private static void initialize(final GUI currentGui) {
    appRunning = true;  // mark the app as running
    gameRunning = true; // mark a game as running

    triesLeft = 3; // set the initial tries to 3
    wins = 0;      // the initial number of wins is 0
    losses = 0;    // the initial number of losses is 0

    // create a new Random generator to generate random integers
    generator = new Random();
    gui = currentGui; // set the GUI instance for this game as the GUI instance
                      // passed to this method
  }

  // this method is used to reset the game after a win or a loss. It
  // takes nothing and returns nothing
  private static void resetGame() {
    triesLeft = 3; // reset the number of tries left to 3

    // update each of the labels with the information in the variables
    gui.getLbl_tries().setText("Tries left: " + triesLeft);
    gui.getLbl_gamesWon().setText("Games won: " + wins);
    gui.getLbl_gamesLost().setText("Games lost: " + losses);
    gui.getLbl_prompt().setText("Pick a number: ");

    // get an array of all the guess buttons from the GUI
    final JButton[] guessButtons = gui.getGuessButtons();

    // enable all buttons to reset whichever ones were disabled during
    // the game
    for (int i = 0; i < 6; i++) {
      guessButtons[i].setEnabled(true);
    }

    gameRunning = true; // mark the game as running again
  }

  // this is the method to run the main event loop. This method
  // takes nothing and returns nothing.
  private static void runMainEventLoop() {
    // while the app is running
    while (appRunning) {
      runGameEventLoop(); // run the game event loop
      resetGame(); // once the current game is done, reset the game before
                   // running the game loop again
    }

    System.exit(0); // when the main event loop is done, exit
  }

  // this functoin is to run the game event loop (run the events of the
  // game). This method takes nothing and returns nothing.
  private static void runGameEventLoop() {
    currentRandomNumber =
        generator.nextInt(6) +
        1; // at the start of each game, generate a random number and store it
           // in the currentRandomNumber variable

    // while the game is marked as running
    while (gameRunning) {
      if (!appRunning) { // if at any point the app is no longer running, the
                         // game should not run either and the exit sequence
                         // should begin
        gameRunning = false;
      }
    }
  }

  // getters and setters

  public static void setAppRunning(final boolean appRunning) {
    Engine.appRunning = appRunning;
  }

  public static boolean isGameRunning() { return gameRunning; }

  public static void setGameRunning(final boolean gameRunning) {
    Engine.gameRunning = gameRunning;
  }

  public static int getTriesLeft() { return triesLeft; }

  public static void setTriesLeft(final int triesLeft) {
    Engine.triesLeft = triesLeft;
  }

  public static int getWins() { return wins; }

  public static void setWins(final int wins) { Engine.wins = wins; }

  public static int getLosses() { return losses; }

  public static void setLosses(final int losses) { Engine.losses = losses; }

  public static int getCurrentRandomNumber() { return currentRandomNumber; }

  public static void run(final GUI currentGui) {
    initialize(currentGui);
    runMainEventLoop();
  }
}
