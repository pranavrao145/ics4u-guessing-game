/******************************************************************************
Program: Engine Class (Guessing Game)

Description: This is the engine for this game. This class contains all of the
methods to run the game itself, such as to intialize and reset the game,
as well as the event loops.

Author: Pranav Rao

Date: February 12, 2022
*******************************************************************************/

import java.util.Random;
import javax.swing.JButton;

public class Engine {
  private static Random generator;

  private static volatile boolean appRunning, gameRunning;
  private static int triesLeft, wins, losses, currentRandomNumber;

  private static GUI gui;

  private static void initialize(GUI currentGui) {
    appRunning = true;
    gameRunning = true;

    triesLeft = 3;
    wins = 0;
    losses = 0;

    generator = new Random();
    gui = currentGui;
  }

  private static void resetGame() {
    triesLeft = 3;
    gui.updateTriesLeftLabel();

    gui.getLbl_tries().setText("Tries left: " + triesLeft);
    gui.getLbl_gamesWon().setText("Games won: " + wins);
    gui.getLbl_gamesLost().setText("Games lost: " + losses);
    gui.getLbl_prompt().setText("Pick a number: ");

    JButton[] guessButtons = gui.getGuessButtons();

    for (int i = 0; i < 6; i++) {
      guessButtons[i].setEnabled(true);
    }

    gameRunning = true;
  }

  private static void runMainEventLoop() {
    while (appRunning) {
      runGameEventLoop();
      resetGame();
    }

    System.exit(0);
  }

  private static void runGameEventLoop() {
    currentRandomNumber = generator.nextInt(5) + 1;

    while (gameRunning) {
      if (!appRunning) {
        gameRunning = false;
      }
    }
  }

  public static void setAppRunning(boolean appRunning) {
    Engine.appRunning = appRunning;
  }

  public static boolean isGameRunning() { return gameRunning; }

  public static void setGameRunning(boolean gameRunning) {
    Engine.gameRunning = gameRunning;
  }

  public static int getTriesLeft() { return triesLeft; }

  public static void setTriesLeft(int triesLeft) {
    Engine.triesLeft = triesLeft;
  }

  public static int getWins() { return wins; }

  public static void setWins(int wins) { Engine.wins = wins; }

  public static int getLosses() { return losses; }

  public static void setLosses(int losses) { Engine.losses = losses; }

  public static int getCurrentRandomNumber() { return currentRandomNumber; }

  public static void run(GUI currentGui) {
    initialize(currentGui);
    runMainEventLoop();
  }
}
