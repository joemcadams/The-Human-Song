package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CodonWindow extends JFrame {
  public static JTextArea textBox;

  public CodonWindow() {
    super();
    setLayout(new BorderLayout());
    setTitle("The Human Song");
    setVisible(true);
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(Color.LIGHT_GRAY);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setOpaque(true);
    buttonPanel.setLayout(new GridLayout(1, 2));

    JButton play = new JButton();
    play.addActionListener(new PlayListener());
    play.setLabel("Play the sequence");
    play.setOpaque(true);
    buttonPanel.add(play);

    JButton stop = new JButton();
    stop.addActionListener(new StopListener());
    stop.setOpaque(true);
    buttonPanel.add(stop);

    add(buttonPanel, BorderLayout.SOUTH);
  }
}

class PlayListener implements ActionListener {

  @Override public void actionPerformed(ActionEvent e) {
    try {
      CodonDriver.Driver();
    }
    catch (IOException e1) {
      e1.printStackTrace();
    }
  }
}

class StopListener implements ActionListener {

  @Override public void actionPerformed(ActionEvent e) {
    System.exit(0);
  }
}
