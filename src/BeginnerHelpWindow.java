import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class BeginnerHelpWindow {
	private JFrame frame;
	
	
	public BeginnerHelpWindow() {
		frame = new JFrame();
		initialize();
		JLabel lblScrabbleHelpFor = new JLabel("Scrabble Tips & Help For Beginners!");
		lblScrabbleHelpFor.setForeground(new Color(204, 0, 51));
		lblScrabbleHelpFor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblScrabbleHelpFor.setBounds(123, 11, 331, 57);
		frame.getContentPane().add(lblScrabbleHelpFor);
		
		JTextArea firstTip = new JTextArea();
		firstTip.setWrapStyleWord(true);
		firstTip.setLineWrap(true);
		firstTip.setEditable(false);
		firstTip.setFont(new Font("Tahoma", Font.BOLD, 13));
		firstTip.setText("-The first player combines two or more of his or her letters to form a word and places it on the board to read either across or down with one letter on the center square. Diagonal words are not allowed.");
		firstTip.setBounds(89, 64, 418, 68);
		frame.getContentPane().add(firstTip);
		
		JTextArea secondTip = new JTextArea();
		secondTip.setFont(new Font("Tahoma", Font.BOLD, 13));
		secondTip.setText("-Complete your turn by counting and announcing your score for that turn. Then draw as many new letters as you played; always keep seven letters on your rack, as long as there are enough tiles left in the bag.");
		secondTip.setWrapStyleWord(true);
		secondTip.setLineWrap(true);
		secondTip.setBounds(89, 143, 418, 68);
		frame.getContentPane().add(secondTip);
		
		JTextArea finalTip = new JTextArea();
		finalTip.setWrapStyleWord(true);
		finalTip.setFont(new Font("Tahoma", Font.BOLD, 13));
		finalTip.setText("-The game ends when all letters have been drawn and one player uses his or her last letter; or when all possible plays have been made.");
		finalTip.setBounds(89, 338, 418, 57);
		finalTip.setLineWrap(true);
		frame.getContentPane().add(finalTip);
		
		JTextArea thirdTip = new JTextArea();
		thirdTip.setWrapStyleWord(true);
		thirdTip.setFont(new Font("Tahoma", Font.BOLD, 13));
		thirdTip.setText("-New words may be formed by:  Adding one or more letters to a word or letters already on the board, Placing a word at right angles to a word already on the board. The new word must use one of the letters already on the board or must add a letter to it,  Placing a complete word parallel to a word already played so that adjacent letters also form complete words.");
		thirdTip.setBounds(89, 222, 418, 105);
		thirdTip.setLineWrap(true);
		frame.getContentPane().add(thirdTip);
	}
	
	private void initialize() {
		frame.setBounds(100, 100, 590, 445);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		
	}
}
