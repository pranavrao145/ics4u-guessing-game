package game;

import java.awt.event.*;
import javax.swing.*;

public class GUI {
  static JPanel panel;
  static JFrame frame;
  static JButton btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_quit,
      btn_restart;
  static JLabel lbl_prompt, lbl_tries, lbl_gamesWon, lbl_gamesLost;

  // constructor
  public GUI() {
    // draw you GUI
    drawGUI();
    // attach the listeners to the buttons
    attachListeners();
  }

  private void drawGUI() {
    frame = new JFrame();

    frame.setSize(350, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new JPanel();

    // intialize labels
    lbl_prompt = new JLabel("Pick a number:");
    lbl_tries = new JLabel("Tries left: 3");
    lbl_gamesWon = new JLabel("Games won: 0");
    lbl_gamesLost = new JLabel("Games lost: 0");

    btn_1 = new JButton("1");
    btn_2 = new JButton("2");
    btn_3 = new JButton("3");
    btn_4 = new JButton("4");
    btn_5 = new JButton("5");
    btn_6 = new JButton("6");

    btn_quit = new JButton("Quit");
    btn_restart = new JButton("Restart");

    // add all the elements to the panel
    panel.add(lbl_prompt);
    panel.add(lbl_tries);
    panel.add(lbl_gamesWon);
    panel.add(lbl_gamesLost);
    panel.add(btn_1);
    panel.add(btn_2);
    panel.add(btn_3);
    panel.add(btn_4);
    panel.add(btn_5);
    panel.add(btn_6);
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
  }

  private void handleButtonPress(JButton button) {}
}
