/******************************************************************************
Program: Guessing Game

Description: Program to control the Laser Tower 3000. This includes manual and
automatic modes, an LCD screen, a servo, a buzzer, a laser (an LED for our
purposes), and an infrared remote control.

Author: Pranav Rao

Date: January 9, 2021
 ******************************************************************************/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI {
  private JPanel panel;
  private JFrame frame;
  private JTextArea textarea;
  private JScrollPane scrollPane;
  private JButton[] guessButtons;
  private JButton btn_quit, btn_restart;
  private JLabel lbl_prompt, lbl_tries, lbl_gamesWon, lbl_gamesLost;

  public GUI() {
    drawGUI();
    attachListeners();
  }

  private void drawGUI() {
    frame = new JFrame();

    frame.setSize(380, 230);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);

    panel = new JPanel();

    // intialize labels
    lbl_tries = new JLabel("Tries left: 3");
    lbl_gamesWon = new JLabel("Games won: 0");
    lbl_gamesLost = new JLabel("Games lost: 0");
    lbl_prompt = new JLabel("Pick a number: ");

    guessButtons = new JButton[6];

    for (int i = 0; i < 6; i++) {
      guessButtons[i] = new JButton(String.valueOf(i + 1));
    }

    btn_quit = new JButton("Quit");
    btn_restart = new JButton("Restart");

    textarea = new JTextArea(5, 30);
    textarea.setEditable(false);

    scrollPane = new JScrollPane(textarea);

    // add all the elements to the panel
    panel.add(lbl_prompt);

    for (int i = 0; i < 6; i++) {
      panel.add(guessButtons[i]);
    }

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
    for (int i = 0; i < 6; i++) {
      JButton currentButton = guessButtons[i];
      currentButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          handleButtonPress(currentButton);
        }
      });
    }

    btn_quit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Engine.setAppRunning(false);
      }
    });

    btn_restart.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Engine.setLosses(Engine.getLosses() + 1);
        textarea.append("Restarting game...\n");
        Engine.setGameRunning(false);
      }
    });
  }

  private void handleButtonPress(JButton button) {
    if (Integer.parseInt(button.getText()) == Engine.getCurrentRandomNumber()) {
      System.out.println(Engine.getWins());
      Engine.setWins(Engine.getWins() + 1);
      textarea.append("That was the correct number - you won!\n");
      Engine.setGameRunning(false);
    } else {
      Engine.setTriesLeft(Engine.getTriesLeft() - 1);
      updateTriesLeftLabel();
      textarea.append("That was not the correct number - please try again!\n");
      button.setEnabled(false);
    }

    if (Engine.getTriesLeft() == 0) {
      Engine.setGameRunning(false);
      Engine.setLosses(Engine.getLosses() + 1);
      textarea.append("Game over - the correct number was: " +
                      Engine.getCurrentRandomNumber() + "\n");
    }
  }

  public void updateTriesLeftLabel() {
    lbl_tries.setText("Tries left: " + Engine.getTriesLeft());
  }

  public JButton[] getGuessButtons() { return guessButtons; }

  public JButton getBtn_quit() { return btn_quit; }

  public JButton getBtn_restart() { return btn_restart; }

  public JLabel getLbl_prompt() { return lbl_prompt; }

  public JLabel getLbl_tries() { return lbl_tries; }

  public JLabel getLbl_gamesWon() { return lbl_gamesWon; }

  public JLabel getLbl_gamesLost() { return lbl_gamesLost; }
}
