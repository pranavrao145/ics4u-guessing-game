package game;

import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class App {
  JPanel panel;
  JFrame frame;
  JTextArea textarea;
  JScrollPane scrollPane;
  JButton btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_quit, btn_restart;
  JLabel lbl_prompt, lbl_tries, lbl_gamesWon, lbl_gamesLost;

  Random generator;

  volatile boolean appRunning, gameRunning;
  int triesLeft, wins, losses, currentRandomNumber;

  public App() {
    setup();
    runMainEventLoop();
  }

  private void setup() {
    appRunning = true;
    gameRunning = true;

    triesLeft = 3;
    wins = 0;
    losses = 0;

    generator = new Random();

    // draw you GUI
    drawGUI();
    // attach the listeners to the buttons
    attachListeners();
  }

  private void runMainEventLoop() {
    while (appRunning) {
      runGameEventLoop();
      resetGame();
    }

    System.exit(0);
  }

  private void resetGame() {
    triesLeft = 3;
    updateTriesLeftLabel();

    lbl_tries.setText("Tries left: " + triesLeft);
    lbl_gamesWon.setText("Games won: " + wins);
    lbl_gamesLost.setText("Games lost: " + losses);
    lbl_prompt.setText("Pick a number: ");

    btn_1.setEnabled(true);
    btn_2.setEnabled(true);
    btn_3.setEnabled(true);
    btn_4.setEnabled(true);
    btn_5.setEnabled(true);
    btn_6.setEnabled(true);

    gameRunning = true;
  }

  private void runGameEventLoop() {
    currentRandomNumber = generator.nextInt(5) + 1;
    System.out.println(currentRandomNumber);

    while (gameRunning) {
      if (!appRunning) {
        gameRunning = false;
      }
    }
  }

  private void drawGUI() {
    frame = new JFrame();

    frame.setSize(380, 230);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new JPanel();

    // intialize labels
    lbl_tries = new JLabel("Tries left: " + triesLeft);
    lbl_gamesWon = new JLabel("Games won: " + wins);
    lbl_gamesLost = new JLabel("Games lost: " + losses);
    lbl_prompt = new JLabel("Pick a number: ");

    btn_1 = new JButton("1");
    btn_2 = new JButton("2");
    btn_3 = new JButton("3");
    btn_4 = new JButton("4");
    btn_5 = new JButton("5");
    btn_6 = new JButton("6");

    btn_quit = new JButton("Quit");
    btn_restart = new JButton("Restart");

    textarea = new JTextArea(5, 30);
    textarea.setEditable(false);

    scrollPane = new JScrollPane(textarea);

    // add all the elements to the panel
    panel.add(lbl_prompt);
    panel.add(btn_1);
    panel.add(btn_2);
    panel.add(btn_3);
    panel.add(btn_4);
    panel.add(btn_5);
    panel.add(btn_6);
    panel.add(lbl_tries);
    panel.add(lbl_gamesWon);
    panel.add(lbl_gamesLost);
    panel.add(scrollPane);
    panel.add(btn_quit);
    panel.add(btn_restart);

    frame.getContentPane().add(panel);
    frame.setVisible(true);
  }

  private void attachListeners() {
    btn_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { handleButtonPress(btn_1); }
    });
    btn_2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { handleButtonPress(btn_2); }
    });
    btn_3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { handleButtonPress(btn_3); }
    });
    btn_4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { handleButtonPress(btn_4); }
    });
    btn_5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { handleButtonPress(btn_5); }
    });
    btn_6.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { handleButtonPress(btn_6); }
    });
    btn_quit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { appRunning = false; }
    });
    btn_restart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        losses++;
        textarea.append("Restarting game...\n");
        gameRunning = false;
      }
    });
  }

  private void handleButtonPress(JButton button) {
    if (Integer.parseInt(button.getText()) == currentRandomNumber) {
      wins++;
      textarea.append("That was the correct number - you won!\n");
      gameRunning = false;
    } else {
      triesLeft--;
      updateTriesLeftLabel();
      textarea.append("That was not the correct number - please try again!\n");
      button.setEnabled(false);
    }

    if (triesLeft == 0) {
      gameRunning = false;
      losses++;
      textarea.append(
          "Game over - the correct number was: " + currentRandomNumber + "\n");
    }
  }

  private void updateTriesLeftLabel() {
    lbl_tries.setText("Tries left: " + triesLeft);
  }

  public static void main(String[] args) { new App(); }
}
