/******************************************************************************
Program: GUI (Guessing Game)

Description: This is GUI for this game. This class contains all of the
methods to run the GUI of the game, such as to draw and update the GUI,
as well as to attach listeners.

Author: Pranav Rao

Date: February 12, 2022
*******************************************************************************/

// import all necessary Swing components

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI {
  // declare all variables to represent various Swing components. All of these
  // are private, and will be accessed by other classes using the getters below
  private JPanel panel;
  private JFrame frame;
  private JTextArea textarea;
  private JScrollPane scrollPane;
  private JButton[] guessButtons;
  private JButton btn_quit, btn_restart;
  private JLabel lbl_prompt, lbl_tries, lbl_gamesWon, lbl_gamesLost;

  // this is the GUI constructor; whenever a new GUI is made these methods
  // will be called
  public GUI() {
    // draw the GUI
    drawGUI();
    // attach the listeners to the butotns on the GUI
    attachListeners();
  }

  // this method will draw the GUI by manually placing each component on to
  // the frame
  private void drawGUI() {
    frame = new JFrame(); // create a new JFrame and assign it to the frame
                          // variable declared earlier

    frame.setSize(380, 230); // set the size of the frame to 380x230
    frame.setDefaultCloseOperation(
        JFrame.EXIT_ON_CLOSE); // when the JFrame closes, exit the program
    frame.setResizable(false); // disable the user resizing

    panel = new JPanel(); // create a new JPanel and assign it to the panel
                          // variable declared earlier

    // initialize the information labels that will appear on the GUI
    lbl_tries = new JLabel("Tries left: 3");
    lbl_gamesWon = new JLabel("Games won: 0");
    lbl_gamesLost = new JLabel("Games lost: 0");
    lbl_prompt = new JLabel("Pick a number: ");

    // create an array of buttons, which will represent the six buttons that the
    // user will use to guess
    guessButtons = new JButton[6];

    // create six buttons with the numbers 1-6
    for (int i = 0; i < 6; i++) {
      guessButtons[i] = new JButton(String.valueOf(i + 1));
    }

    // make new buttons to quit and restart the program
    btn_quit = new JButton("Quit");
    btn_restart = new JButton("Restart");

    // initialize a textarea with the dimensions of 5x30 and make it
    // non-editable
    textarea = new JTextArea(5, 30);
    textarea.setEditable(false);

    // add a scroll bar to the textarea
    scrollPane = new JScrollPane(textarea);

    // add a label to prompt the user to pick a number
    panel.add(lbl_prompt);

    // add a scroll bar to the textarea
    for (int i = 0; i < 6; i++) {
      panel.add(guessButtons[i]);
    }

    // add all the labels to the panel
    panel.add(lbl_tries);
    panel.add(lbl_gamesWon);
    panel.add(lbl_gamesLost);

    // add the scrollable textarea to the panel
    panel.add(scrollPane);

    // add the buttons to quit and restart
    panel.add(btn_quit);
    panel.add(btn_restart);

    // add the panel with all the components to the frame and make the frame
    // visible
    frame.getContentPane().add(panel);
    frame.setVisible(true);
  }

  // this method attaches listeners to each of the buttons on the GUI. in
  // essence, these listeners will wait until these buttons are pressed to carry
  // out their associated methodality
  private void attachListeners() {
    // for each of the buttons, attach a listener to handle when the button is
    // pressed
    for (int i = 0; i < 6; i++) {
      final JButton currentButton = guessButtons[i];
      currentButton.addActionListener(new ActionListener() {
        public void actionPerformed(final ActionEvent e) {
          handleButtonPress(currentButton);
        }
      });
    }

    // attach a listner to the quit button that will make it stop the engine
    // when clicked
    btn_quit.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent e) {
        Engine.setAppRunning(false);
      }
    });

    // attach a listner to the quit button that will make it restart the game
    // when clicked
    btn_restart.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent e) {
        Engine.setLosses(Engine.getLosses() + 1);
        textarea.append(
            "Restarting game - the correct number was: " +
            Engine.getCurrentRandomNumber() +
            "\n"); // inform the user that the game is over and they lost
        Engine.setGameRunning(false);
      }
    });
  }

  // this method will the situation when any button is pressed. this method
  // takes a button and returns nothing
  private void handleButtonPress(final JButton button) {
    // if the text on the button pressed has the same value as the random number
    if (Integer.parseInt(button.getText()) == Engine.getCurrentRandomNumber()) {
      Engine.setWins(Engine.getWins() + 1); // add one to the wins
      textarea.append(
          "That was the correct number - you won!\n"); // output a message that
                                                       // tells the user they
                                                       // won
      Engine.setGameRunning(false); // mark this game as no longer running
    } else { // else if the text on the button pressed does not have teh same
             // value as the random number
      Engine.setTriesLeft(Engine.getTriesLeft() -
                          1); // decrement the tries left
      updateTriesLeftLabel(); // update the "Tries Left" label
      textarea.append(
          "That was not the correct number - please try again!\n"); // inform
                                                                    // the user
                                                                    // that they
                                                                    // did not
                                                                    // get the
                                                                    // right
                                                                    // answer
      button.setEnabled(false); // disable the button that was pressed so that
                                // they cannot click it again
    }

    // if the number of tries left is 0
    if (Engine.getTriesLeft() == 0) {
      Engine.setGameRunning(false); // mark the current game as ended
      Engine.setLosses(Engine.getLosses() +
                       1); // add a loss to the total loss cont
      textarea.append(
          "Game over - the correct number was: " +
          Engine.getCurrentRandomNumber() +
          "\n"); // inform the user that the game is over and they lost
    }
  }

  // this is a utility method to update the tries left label with the actual
  // number of tries left. This method takes no arguments and returns nothing
  public void updateTriesLeftLabel() {
    lbl_tries.setText("Tries left: " + Engine.getTriesLeft());
  }

  // getters and setters

  public JButton[] getGuessButtons() { return guessButtons; }

  public JButton getBtn_quit() { return btn_quit; }

  public JButton getBtn_restart() { return btn_restart; }

  public JLabel getLbl_prompt() { return lbl_prompt; }

  public JLabel getLbl_tries() { return lbl_tries; }

  public JLabel getLbl_gamesWon() { return lbl_gamesWon; }

  public JLabel getLbl_gamesLost() { return lbl_gamesLost; }
}
