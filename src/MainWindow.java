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

public class MainWindow {
	private JFrame frame;
	private JTextField playerOneTextField;
	private JTextField playerTwoTextField;
	private JTextField playerThreeTextField;
	private JTextField playerFourTextField;
	
	/*public static void main(String[] args) {
		MainWindow window = new MainWindow();
		window.frame.setVisible(true);
    }*/
	
	public MainWindow() {
		initialize();
	}
	
	private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 590, 445);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(true);
	
	ButtonGroup buttonPannel = new ButtonGroup();
	
	JRadioButton beginnerButton = new JRadioButton("Beginners");
	beginnerButton.setFont(new Font("Tahoma", Font.BOLD, 12));
	
	JRadioButton challengerButton = new JRadioButton("Challengers");
	challengerButton.setFont(new Font("Tahoma", Font.BOLD, 12));
	
	buttonPannel.add(beginnerButton);
	buttonPannel.add(challengerButton);
	
	JLabel lblUwlScrabble = new JLabel("UWL Scrabble");
	lblUwlScrabble.setForeground(new Color(204, 51, 51));
	lblUwlScrabble.setFont(new Font("Tahoma", Font.BOLD, 33));
	
	JLabel lblSelectDifficulty = new JLabel("Select Difficulty:");
	lblSelectDifficulty.setFont(new Font("Tahoma", Font.BOLD, 18));
	
	JLabel lblPlayerOneName = new JLabel("Player One Name:");
	lblPlayerOneName.setFont(new Font("Tahoma", Font.BOLD, 18));
	lblPlayerOneName.setVisible(false);
	
	JLabel lblPlayerTwoName = new JLabel("Player Two Name:");
	lblPlayerTwoName.setFont(new Font("Tahoma", Font.BOLD, 18));
	lblPlayerTwoName.setVisible(false);
	
	JLabel lblPlayerThreeName = new JLabel("Player Three Name:");
	lblPlayerThreeName.setFont(new Font("Tahoma", Font.BOLD, 18));
	lblPlayerThreeName.setVisible(false);
	
	JLabel lblPlayerFourName = new JLabel("Player Four Name:");
	lblPlayerFourName.setFont(new Font("Tahoma", Font.BOLD, 18));
	lblPlayerFourName.setVisible(false);
	
	playerOneTextField = new JTextField();
	playerOneTextField.setColumns(10);
	playerOneTextField.setVisible(false);
	
	playerTwoTextField = new JTextField();
	playerTwoTextField.setColumns(10);
	playerTwoTextField.setVisible(false);
	
	playerThreeTextField = new JTextField();
	playerThreeTextField.setColumns(10);
	playerThreeTextField.setVisible(false);
	
	playerFourTextField = new JTextField();
	playerFourTextField.setColumns(10);
	playerFourTextField.setVisible(false);
	
	JButton playButton = new JButton("Begin Game!");
	playButton.setFont(new Font("Tahoma", Font.BOLD, 18));
	playButton.setVisible(false);
	
	JLabel lblNotEnoughPlayers = new JLabel("Two Players Are Required To Begin A Game!");
	lblNotEnoughPlayers.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
	lblNotEnoughPlayers.setForeground(new Color(178, 34, 34));
	lblNotEnoughPlayers.setVisible(false);
	
	JLabel lblYouMustSelect = new JLabel("You Must Select A Difficulty Before Starting A Game!");
	lblYouMustSelect.setForeground(new Color(178, 34, 34));
	lblYouMustSelect.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
	lblYouMustSelect.setVisible(false);
	
	
	
	GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
	groupLayout.setHorizontalGroup(
		groupLayout.createParallelGroup(Alignment.TRAILING)
			.addGroup(groupLayout.createSequentialGroup()
				.addGap(45)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblPlayerOneName)
							.addComponent(lblPlayerTwoName)
							.addComponent(lblPlayerThreeName)
							.addComponent(lblPlayerFourName))
						.addGap(96)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(playerFourTextField, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
							.addComponent(playerTwoTextField, 180, 180, 180)
							.addComponent(playerThreeTextField, 180, 180, 180)
							.addComponent(playerOneTextField, GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblYouMustSelect, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblSelectDifficulty, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(beginnerButton)
							.addGap(51)
							.addComponent(challengerButton))))
				.addGap(73))
			.addGroup(groupLayout.createSequentialGroup()
				.addGap(198)
				.addComponent(playButton)
				.addContainerGap(481, Short.MAX_VALUE))
			.addGroup(groupLayout.createSequentialGroup()
				.addContainerGap(375, Short.MAX_VALUE)
				.addComponent(lblNotEnoughPlayers, GroupLayout.PREFERRED_SIZE, 320, GroupLayout.PREFERRED_SIZE)
				.addGap(131))
			.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
				.addGap(159)
				.addComponent(lblUwlScrabble, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(408, Short.MAX_VALUE))
	);
	groupLayout.setVerticalGroup(
		groupLayout.createParallelGroup(Alignment.LEADING)
			.addGroup(groupLayout.createSequentialGroup()
				.addGap(25)
				.addComponent(lblUwlScrabble, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
					.addComponent(beginnerButton)
					.addComponent(challengerButton)
					.addComponent(lblSelectDifficulty, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
				.addGap(8)
				.addComponent(lblYouMustSelect)
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblPlayerOneName)
					.addComponent(playerOneTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblPlayerTwoName)
					.addComponent(playerTwoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblPlayerThreeName)
					.addComponent(playerThreeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblPlayerFourName)
					.addComponent(playerFourTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addComponent(playButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(lblNotEnoughPlayers)
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	);
	frame.getContentPane().setLayout(groupLayout);
	
	
	
	beginnerButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == beginnerButton) {
				lblNotEnoughPlayers.setVisible(false);
				lblYouMustSelect.setVisible(false);
				
				playerThreeTextField.setVisible(false);
				playerFourTextField.setVisible(false);
				lblPlayerThreeName.setVisible(false);
				lblPlayerFourName.setVisible(false);
				
				playerOneTextField.setText("");
				playerTwoTextField.setText("");
				playerThreeTextField.setText("");
				playerFourTextField.setText("");
				
				playerOneTextField.setVisible(true);
				playerTwoTextField.setVisible(true);
				lblPlayerOneName.setVisible(true);
				lblPlayerTwoName.setVisible(true);
				
				playButton.setVisible(true);
				
				//get text from the two fields, both fields MUST have data
				
			}
		}
	});
	
	challengerButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == challengerButton) {
				lblNotEnoughPlayers.setVisible(false);
				lblYouMustSelect.setVisible(false);
				
				playerOneTextField.setVisible(true);
				playerTwoTextField.setVisible(true);
				playerThreeTextField.setVisible(true);
				playerFourTextField.setVisible(true);
				playerOneTextField.setText("");
				playerTwoTextField.setText("");
				playerThreeTextField.setText("");
				playerFourTextField.setText("");
				lblPlayerOneName.setVisible(true);
				lblPlayerTwoName.setVisible(true);
				lblPlayerThreeName.setVisible(true);
				lblPlayerFourName.setVisible(true);
				
				//get text from all 4, if field is blank dont create those last players, NEED 2 players
				
				playButton.setVisible(true);
				
				
				
			}
		}
	});
	
	playButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			//if beginner
			if(beginnerButton.isSelected()) {
				if(playerTwoTextField.getText().equals("") || playerOneTextField.getText().equals("")) {
					lblNotEnoughPlayers.setVisible(true);
					return;
				}
				//CREATE THE PLAYERS
			//BeginnerGameWindow window = new BeginnerGameWindow(playerOneTextField.getText(), playerTwoTextField.getText());
			frame.setVisible(false);
			}
			else if(challengerButton.isSelected()) {
				int amountOfPlayers = 0;
				if(!playerFourTextField.getText().equals("")) {
					//CREATE A PLAYER
					amountOfPlayers++;
				}
				if(!playerThreeTextField.getText().equals("")) {
					//CREATE A PLAYER
					amountOfPlayers++;
					
				}
				if(!playerTwoTextField.getText().equals("")) {
					//CREATE A PLAYER
					amountOfPlayers++;
				}
				if(!playerOneTextField.getText().equals("")) {
					//CREATE A PLAYER
					amountOfPlayers++;
				}
				if(amountOfPlayers <= 1) {
					lblNotEnoughPlayers.setVisible(true);
					return;
				}
				
				//ChallengerGameWindow
				Scrabble s = new Scrabble(playerOneTextField.getText(),playerTwoTextField.getText());
				javax.swing.SwingUtilities.invokeLater(s);
				frame.setVisible(false);
			}
			else {
				lblYouMustSelect.setVisible(true);
				return;
			}
			
		}
	});
	
	
	
}
}
