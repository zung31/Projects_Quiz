package com.projects.quizGame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Quiz implements ActionListener{
	String[] questions = { // set of questions
			"Which company created Java?", 
			"Which year was Java created?",
			"What was Java originally called?", 
			"Which is SNSD's song?",
			"Which of the following is a group from YG?" };
	String[][] options = { // set of options for each question
            {"Sun Microsystems", "Starbucks", "Microsoft", "Alibaba"},
            {"1989", "1996", "1972", "1492"},
            {"Apple", "Latte", "Oak", "Coffeine"},
            {"Dancing Queen", "Lion", "Bad boy", "Lil touch"},
            {"Twice", "Blackpink", "Red Velvet", "SNSD"}
	};
	
	char[] answers = { // set of correct answers
			'A', 'B', 'C', 'A', 'B' 
	};
	
	char guess; // the answer the user chooses
	char answer; // the correct answer
	int index; // the index of the current question
	int correct_guesses = 0; // number of correct guesses
	int total_questions = questions.length; // total number of questions
	int result; // result of the quiz
	int seconds = 10; // seconds to answer each question
	
	JFrame frame = new JFrame(); 
	JTextField textfield = new JTextField(); // textfield to display the countdown
	JTextArea textarea = new JTextArea(); // textarea to display the questions
	JButton buttonA = new JButton(); // button for option A
	JButton buttonB = new JButton(); // button for option B
	JButton buttonC = new JButton(); // button for option C
	JButton buttonD = new JButton(); // button for option D
	JLabel answer_labelA = new JLabel(); // label for option A
	JLabel answer_labelB = new JLabel(); // label for option B
	JLabel answer_labelC = new JLabel(); // label for option C
	JLabel answer_labelD = new JLabel(); // label for option D
	JLabel time_label = new JLabel(); // label for the countdown
	JLabel seconds_left = new JLabel(); // label to display the seconds left
	JTextField number_right = new JTextField(); // textfield to display the number of correct answers
	JTextField percentage = new JTextField(); // textfield to display the percentage of correct answers
	
	// timer for the countdown
	Timer timer = new Timer(1000, new ActionListener() {
		// this function is called every second
		@Override
		public void actionPerformed(ActionEvent e) {
			seconds--; // decrease the seconds by 1
			seconds_left.setText(String.valueOf(seconds)); // set the text of seconds_left to the value of seconds
			if (seconds <= 0) { // if the seconds is less than or equal to 0
				displayAnswer(); // if the time is up, display the answer and move to the next question
			}
		}
	});
	
	//Constructor
	public Quiz() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(650, 650);
		frame.getContentPane().setBackground(new Color(50, 50, 50)); // set the background color to gray
		frame.setLayout(null); // set the layout to null
		frame.setResizable(false); // set the frame to not resizable
		
		textfield.setBounds(0, 0, 650, 50); // set the size and position of the textfield wwith 650 width and 50 height
		textfield.setBackground(new Color(25, 25, 25)); // set the background color of the textfield to gray
		textfield.setForeground(new Color(25, 255, 0)); // set the text color to green
		textfield.setFont(new Font("Ink Free", Font.BOLD, 30)); // set the font of the textfield
		textfield.setBorder(BorderFactory.createBevelBorder(1)); // set the border of the textfield
		textfield.setHorizontalAlignment(JTextField.CENTER); // set the text to be centered
		textfield.setEditable(false); // set the textfield to not editable
		
		//textarea to display the questions
		textarea.setBounds(0, 50, 650, 50); // set the size and position of the textarea wwith 650 width and 50 height
		textarea.setLineWrap(true); // set the line wrap to true
		textarea.setWrapStyleWord(true); // set the wrap style word to true
		textarea.setBackground(new Color(25, 25, 25)); // set the background color of the textarea to gray
		textarea.setForeground(new Color(25, 255, 0)); // set the text color to green
		textarea.setFont(new Font("MV Boli", Font.BOLD, 20)); // set the font of the textarea
		textarea.setBorder(BorderFactory.createBevelBorder(1)); // set the border of the textarea
		textarea.setEditable(false); // set the textarea to not editable
		
		//button
		buttonA.setBounds(0, 100, 100, 100); // set the size and position of the buttonA with 100 width and 100 height
		buttonA.setFont(new Font("MV Boli", Font.BOLD, 35)); // set the font of the buttonA
		buttonA.setText("A"); // set the text of the buttonA to A
		buttonA.setFocusable(false); // set the buttonA to not focusable
		buttonA.addActionListener(this); // add an action listener to the buttonA
		
		buttonB.setBounds(0, 200, 100, 100); 
		buttonB.setFont(new Font("MV Boli", Font.BOLD, 35)); 
		buttonB.setText("B"); 
		buttonB.setFocusable(false); 
		buttonB.addActionListener(this); 
		
		buttonC.setBounds(0, 300, 100, 100);
		buttonC.setFont(new Font("MV Boli", Font.BOLD, 35));
		buttonC.setText("C");
		buttonC.setFocusable(false);
		buttonC.addActionListener(this);
		
		buttonD.setBounds(0, 400, 100, 100);
		buttonD.setFont(new Font("MV Boli", Font.BOLD, 35));
		buttonD.setText("D");
		buttonD.setFocusable(false);
		buttonD.addActionListener(this);
		
		// labels for the options
		answer_labelA.setBounds(125, 100, 500, 100);
		answer_labelA.setBackground(new Color(50, 50, 50)); // set color to gray
		answer_labelA.setForeground(new Color(25, 255, 0)); // set color to green
		answer_labelA.setFont(new Font("MV Boli", Font.PLAIN, 35));
		
		answer_labelB.setBounds(125, 200, 500, 100);
		answer_labelB.setBackground(new Color(50, 50, 50));
		answer_labelB.setForeground(new Color(25, 255, 0));
		answer_labelB.setFont(new Font("MV Boli", Font.PLAIN, 35));
		
		answer_labelC.setBounds(125, 300, 500, 100);
		answer_labelC.setBackground(new Color(50, 50, 50));
		answer_labelC.setForeground(new Color(25, 255, 0));
		answer_labelC.setFont(new Font("MV Boli", Font.PLAIN, 35));
		
		answer_labelD.setBounds(125, 400, 500, 100);
		answer_labelD.setBackground(new Color(50, 50, 50));
		answer_labelD.setForeground(new Color(25, 255, 0));
		answer_labelD.setFont(new Font("MV Boli", Font.PLAIN, 35));
		
		//timer
		seconds_left.setBounds(535, 510, 100, 100);
		seconds_left.setBackground(new Color(25, 25, 25)); // gray
		seconds_left.setForeground(new Color(255, 0, 0)); // red
		seconds_left.setFont(new Font("Ink Free", Font.BOLD, 60));
		seconds_left.setBorder(BorderFactory.createBevelBorder(1)); 
		seconds_left.setOpaque(true); // set the background to be visible
		seconds_left.setHorizontalAlignment(JTextField.CENTER); // set the text to be centered
		seconds_left.setText(String.valueOf(seconds)); // set the text to the value of seconds
		//add "timer" above the seconds_left
		time_label.setBounds(535, 475, 100, 25);
		time_label.setBackground(new Color(50, 50, 50)); // gray
		time_label.setForeground(new Color(255, 0, 0)); // red
		time_label.setFont(new Font("MV Boli", Font.PLAIN, 16));
		time_label.setHorizontalAlignment(JTextField.CENTER); // set the text to be centered
		time_label.setText("Timer");
		
		//number of correct answers
		number_right.setBounds(225, 225, 200, 100);
		number_right.setBackground(new Color(25, 25, 25)); // gray
		number_right.setForeground(new Color(25, 255, 0)); // green
		number_right.setFont(new Font("Ink Free", Font.BOLD, 50));
		number_right.setBorder(BorderFactory.createBevelBorder(1));
		number_right.setHorizontalAlignment(JTextField.CENTER);
		number_right.setEditable(false);
		
		percentage.setBounds(225, 325, 200, 100);
		percentage.setBackground(new Color(25, 25, 25)); // gray
		percentage.setForeground(new Color(25, 255, 0)); // green
		percentage.setFont(new Font("Ink Free", Font.BOLD, 50));
		percentage.setBorder(BorderFactory.createBevelBorder(1));
		percentage.setHorizontalAlignment(JTextField.CENTER);
		percentage.setEditable(false);
		
		//frame
		frame.add(time_label);
		frame.add(seconds_left);
		frame.add(answer_labelA);
		frame.add(answer_labelB);
		frame.add(answer_labelC);
		frame.add(answer_labelD);
		frame.add(buttonA);
		frame.add(buttonB);
		frame.add(buttonC);
		frame.add(buttonD);
		frame.add(textarea);
		frame.add(textfield);
		frame.setVisible(true);
		
		nextQuestion(); // call the nextQuestion method
	}
	
	public void nextQuestion() {
		if(index >= total_questions) {
            results();
		} else {
			textfield.setText("Question " + (index + 1)); // question number
			textarea.setText(questions[index]); // question text
			answer_labelA.setText(options[index][0]);
			answer_labelB.setText(options[index][1]);
			answer_labelC.setText(options[index][2]);
			answer_labelD.setText(options[index][3]);
			timer.start(); // start the timer
		}
	}

	@Override
	// this function is called when a button is clicked
	public void actionPerformed(ActionEvent e) {
		// disable the buttons
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		if (e.getSource() == buttonA) { // if the buttonA is clicked
			guess = 'A';
			if (guess == answers[index]) {
				correct_guesses++;
			}
		}
		if (e.getSource() == buttonB) { // if the buttonB is clicked
			guess = 'B';
			if (guess == answers[index]) {
				correct_guesses++;
			}
		}
		if (e.getSource() == buttonC) { // if the buttonC is clicked
			guess = 'C';
			if (guess == answers[index]) {
				correct_guesses++;
			}
		}
		if (e.getSource() == buttonD) { // if the buttonD is clicked
			guess = 'D';
			if (guess == answers[index]) {
				correct_guesses++;
			}
		}
		
		displayAnswer(); // call the displayAnswer method
	}
	
	public void displayAnswer() {
		//stop the timer after the answer is displayed
		timer.stop();
		// disable the buttons
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);

		// check if the answer is correct in the current question
		// turn the wrong answer to red
		if (answers[index] != 'A') {
			answer_labelA.setForeground(Color.red);
		}
		if (answers[index] != 'B') {
			answer_labelB.setForeground(Color.red);
		}
		if (answers[index] != 'C') {
			answer_labelC.setForeground(Color.red);
		}
		if (answers[index] != 'D') {
			answer_labelD.setForeground(Color.red);
		}

		Timer pause = new Timer(2000, new ActionListener() {
			// this function is called after 2 seconds
			// move to the next question
			// turn the answer back to green
			// enable the buttons
			@Override
			public void actionPerformed(ActionEvent e) {
				answer_labelA.setForeground(new Color(25, 255, 0));
				answer_labelB.setForeground(new Color(25, 255, 0));
				answer_labelC.setForeground(new Color(25, 255, 0));
				answer_labelD.setForeground(new Color(25, 255, 0));

				answer = ' '; // set the answer to empty
				seconds = 10;
				seconds_left.setText(String.valueOf(seconds));
				buttonA.setEnabled(true);
				buttonB.setEnabled(true);
				buttonC.setEnabled(true);
				buttonD.setEnabled(true);
				index++;
				nextQuestion();
			}
		});
		pause.setRepeats(false); // set the timer to run once
		pause.start(); // start the timer
	}
	
	public void results() {
		buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        
        result = (int)((correct_guesses / (double)total_questions) * 100); // calculate the result on 100 scale
        textfield.setText("RESULTS!"); // set the textfield to RESULTS
        
        //clear the frame
        textarea.setText(""); // set the textarea to empty
        answer_labelA.setText(""); // set the answer_labelA to empty
        answer_labelB.setText(""); // set the answer_labelB to empty
        answer_labelC.setText(""); // set the answer_labelC to empty
        answer_labelD.setText(""); // set the answer_labelD to empty
        
        number_right.setText("("+correct_guesses+"/"+total_questions+")"); // set the number of correct answers
        percentage.setText(result + "%"); // set the percentage of correct answers
        
        frame.add(number_right);
        frame.add(percentage);
	}
}
