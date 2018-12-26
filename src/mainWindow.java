/**************************************
 * Project: M4-A4 Pig Latin
 * File   : mainWindow
 * @author  Alex Isaac
 * Date   : 10/26/16
 * 
 * Description:
 * 	1.) The purpose of this code is to create a graphical user interface that takes one sentence and transforms it into pig latin.
 *  2.) Data structures include multiple for loops that sort through the sentence, as well as if statements that determine whether the word is
 *  	capitalized or punctuated.
 *  3.) The main algorithm of this program takes individual words and converts them to pig latin. The function gets the first letter of the word
 *  	(converts it to lowercase if its capitalized), moves it to the end of the word, and then finally adds "ay" to the end. The function then 
 *  	returns the modified word and appends it to the stringBuilder finalString.
 *
 * Changes:
 * 
 *************************************************/
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class mainWindow {

	private JFrame frmPigLatin;
	private JTextField txtSentence;
	private JTextField txtPigLatin;
	Scanner input = new Scanner(System.in);

	/**
	 * Method  : main
	 * Purpose : Launch the application
	 * @param String[] args
	 * @returns void, nothing
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
					window.frmPigLatin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainWindow() {
		initialize();
	}
	
	/**
	 * Method  : convertToLatin
	 * Purpose : Convert each word sent to it to pig latin
	 * @param String s, individual words of the sentence
	 * @returns String, the pig Latin version of the word
	 */
	public static String convertToLatin(String s)
	{
		//if the first letter is uppercase
		if (Character.isUpperCase(s.charAt(0)))
		{
			char lowerCap = Character.toLowerCase(s.charAt(0)); //make the first letter lowercase
			char newCap = Character.toUpperCase(s.charAt(1)); //make the second letter uppercase
			//if there's punctuation after the word(One word sentence)
			if (s.matches(".*[.!?]"))
			{
				char punctuation = s.charAt(s.length()-1);//get the punctuation.
				
				//structure the word into pig Latin.
				return String.format("%c%s%c%s%c ",newCap, s.substring(2,s.length()-1), lowerCap, "ay", punctuation);
			}
			//if there's no punctuation return structured word without any punctuation
			return String.format("%c%s%c%s ",newCap, s.substring(2), lowerCap, "ay");
		}
		//if the first letter isn't uppercase
		else 
		{
			//if there's punctuation
			if (s.matches(".*[.!?]"))
			{
				//get punctuation
				char punctuation = s.charAt(s.length()-1);
				
				//structure with punctuation
				return String.format("%s%c%s%c ", s.substring(1,s.length()-1), s.charAt(0), "ay", punctuation);
			}
			//if no punctuation, return without punctuation
			return String.format("%s%c%s ", s.substring(1), s.charAt(0), "ay");
		}
		
	}

	/**
	 * Method  : initialize
	 * Purpose : initialize the contents of the frame
	 * @param none
	 * @returns void, nothing
	 */
	private void initialize() {
		frmPigLatin = new JFrame();
		frmPigLatin.getContentPane().setBackground(Color.WHITE);
		frmPigLatin.setTitle("Pig Latin");
		frmPigLatin.setBounds(100, 100, 535, 310);
		frmPigLatin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPigLatin.getContentPane().setLayout(null);
		
		txtSentence = new JTextField();
		txtSentence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				StringBuilder finalString;
				
				String sentence = txtSentence.getText(); //get sentence from box
				finalString = new StringBuilder(sentence.length()); //initialize StringBuilder with length of sentence
				
				String[] split = sentence.split("\\s+");//breakup sentence by spaces
				
				//pass each word through convertToLatin function, append each re-structured word to finalString
				for (int i = 0; i < split.length; i++)
				{
					finalString.append(convertToLatin(split[i]));
				}
				
				//put pig Latin sentence in textbox
				txtPigLatin.setText(finalString.toString());
				
			}
		});
		txtSentence.setBounds(33, 52, 449, 26);
		frmPigLatin.getContentPane().add(txtSentence);
		txtSentence.setColumns(10);
		
		JLabel lblEnterASentence = new JLabel("Enter a Sentence");
		lblEnterASentence.setBounds(33, 16, 128, 20);
		frmPigLatin.getContentPane().add(lblEnterASentence);
		
		txtPigLatin = new JTextField();
		txtPigLatin.setEditable(false);
		txtPigLatin.setBounds(33, 201, 449, 26);
		frmPigLatin.getContentPane().add(txtPigLatin);
		txtPigLatin.setColumns(10);
		
		JButton btnConvertToPig = new JButton("Convert to Pig Latin");
		btnConvertToPig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				StringBuilder finalString;
				
				String sentence = txtSentence.getText(); //get sentence from box
				finalString = new StringBuilder(sentence.length()); //initialize StringBuilder with length of sentence
				
				String[] split = sentence.split("\\s+");//breakup sentence by spaces
				
				//pass each word through convertToLatin function, append each re-structured word to finalString
				for (int i = 0; i < split.length; i++)
				{
					finalString.append(convertToLatin(split[i]));
				}
				
				//put pig Latin sentence in textbox
				txtPigLatin.setText(finalString.toString());
			}
		});
		btnConvertToPig.setBounds(33, 106, 211, 29);
		frmPigLatin.getContentPane().add(btnConvertToPig);
		
		JLabel lblPigLatinVersion = new JLabel("Pig Latin Version:");
		lblPigLatinVersion.setBounds(33, 165, 128, 20);
		frmPigLatin.getContentPane().add(lblPigLatinVersion);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(mainWindow.class.getResource("/Images/pig.jpg")));
		lblNewLabel.setBounds(273, 94, 150, 93);
		frmPigLatin.getContentPane().add(lblNewLabel);
	}
}
