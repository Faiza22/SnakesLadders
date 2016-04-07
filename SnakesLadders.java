/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ELEGANT
 */

import java.awt.geom.*;		//importing required packages			
import javax.swing.*;			
import java.awt.*;
import java.awt.event.*;		
import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.io.*;

class Game extends JPanel   //this is the main panel where all game will be played
{
	JButton roll_btn = new JButton("Roll Dice!");
	JLabel title=new JLabel("Snakes & Ladders");
	JLabel dice_no=new JLabel("");  //to show number on the dice
	JLabel turn=new JLabel("");     
	JLabel msg=new JLabel("");      

	int red_x, red_y, green_x, green_y, blue_x, blue_y, purple_x, purple_y; //x and y coordinates for all players colors

	//array of x positions
   	int [] x = {360, 440, 492, 546, 600, 655, 709, 763, 817, 871, 925, 925, 871, 817, 763, 709, 655, 600, 546, 492, 440, 440, 492, 546, 600, 655, 709, 763, 817, 871, 925, 925, 871, 817, 763, 709, 655, 600, 546, 492, 440, 440, 492, 546, 600, 655, 709, 763, 817, 871, 925, 925, 871, 817, 763, 709, 655, 600, 546, 492, 440, 440, 492, 546, 600, 655, 709, 763, 817, 871, 925, 925, 871, 817, 763, 709, 655, 600, 546, 492, 440, 440, 492, 546, 600, 655, 709, 763, 817, 871, 925, 925, 871, 817, 763, 709, 655, 600, 546, 492, 440};

   	//array of y positions
   	int [] y = {595, 595, 595, 595, 595, 595, 595, 595, 595, 595, 595, 540, 540, 540, 540, 540, 540, 540, 540, 540, 540, 485, 485, 485, 485, 485, 485, 485, 485, 485, 485, 432, 432, 432, 432, 432, 432, 432, 432, 432, 432, 377, 377, 377, 377, 377, 377, 377, 377, 377, 377, 324, 324, 324, 324, 324, 324, 324, 324, 324, 324, 270, 270, 270, 270, 270, 270, 270, 270, 270, 270, 215, 215, 215, 215, 215, 215, 215, 215, 215, 215, 162, 162, 162, 162, 162, 162, 162, 162, 162, 162, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108};

   	//initializing the board
   	int [] board = new int[101];

	static int red_cur=0, green_cur=0, blue_cur=0, purple_cur=0;	//initializing current positions of all players

	Game(){
            //initial x and y coordinates of all players
		red_x=x[0];
		red_y=y[0];

		green_x=300;
		green_y=595;

		blue_x=360;
		blue_y=530;

		purple_x=300;
		purple_y=530;
                
                ////initializing the board
   		for(int i=0; i<101; i++)
   			board[i]=i;
 
   		board[3]=39;	//for ladders
   		board[10]=12;
   		board[27]=53;
   		board[56]=84;
   		board[61]=99;
   		board[72]=90;

   		board[16]=13;	//for snakes
   		board[31]=4;
   		board[47]=25;
   		board[63]=60;
   		board[66]=52;
   		board[97]=75;
   	}

	{
            //adding labels on the jpanel
		this.setLayout(null);
		title.setFont(new Font("Buxton Sketch",Font.BOLD,50));
		title.setForeground(Color.RED);
		title.setBounds(300,20,800,50);
		this.add(title);

		turn.setFont(new Font("Arial",Font.BOLD,22));
		turn.setForeground(Color.BLACK);
		turn.setBounds(150,120,800,50);
		this.add(turn);

		dice_no.setFont(new Font("Arial",Font.PLAIN,70));
		dice_no.setForeground(Color.BLUE);
		dice_no.setBounds(180,330,800,60);
		this.add(dice_no);

		Color pink = Color.decode("#f609c9");
		msg.setFont(new Font("Buxton Sketch",Font.PLAIN,30));
		msg.setForeground(pink);
		msg.setBounds(130,450,800,50);
		this.add(msg);

                //button to roll dice
		roll_btn.setFont(new Font("Buxton Sketch",Font.BOLD,25));
		Color green = Color.decode("#27ae60");
		roll_btn.setForeground(green);
		roll_btn.setBounds(100,230,200,50);
		this.add(roll_btn);
		this.setVisible(true);
	}

	{
		Color yellow = Color.decode("#FFFF99");
		this.setBackground(yellow);		//setting background to yellow color
	}

	BufferedImage board_img = null;

	MainMenu mm = new MainMenu();

	public void paintComponent(Graphics g)
	{

		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;

		try{
			board_img = ImageIO.read(new File("images/board.jpg"));			
		}
		catch(IOException ie){}
		g.drawImage(board_img, 420,90,this);	//draw image of the board			

		int no_of_players = mm.num_of_players;

		Ellipse2D.Double red = new Ellipse2D.Double(red_x, red_y, 45, 45);  //draw red circle for red player
		g2d.setColor(Color.RED);
   		g2d.fill(red);

   		Color gr = Color.decode("#18a518");
   		Ellipse2D.Double green = new Ellipse2D.Double(green_x, green_y, 45, 45); //draw green circle for green player
		g2d.setColor(gr);
   		g2d.fill(green);

   		if(no_of_players>2)
   		{
   			Ellipse2D.Double blue = new Ellipse2D.Double(blue_x, blue_y, 45, 45); //if 3rd player exists, draw blue circle for blue player
			g2d.setColor(Color.BLUE);
   			g2d.fill(blue);

   			if(no_of_players==4)
   			{
   				Color p = Color.decode("#9b59b6");

		   		Ellipse2D.Double purple = new Ellipse2D.Double(purple_x, purple_y, 45, 45); //if 4th player exists, draw purple circle for purple player
				g2d.setColor(p);
		   		g2d.fill(purple);
   			}
   		}
	}
}

class MainMenu extends JPanel   //starting panel of the app
{
	static int num_of_players=0;

	JLabel title=new JLabel("Snakes & Ladders");
	JLabel players=new JLabel("Select number of players:");
	String [] no_of_players={"2","3","4"};
	JComboBox combo = new JComboBox(no_of_players);
	JButton play_btn = new JButton("PLAY");
		
	{
		this.setLayout(null);

		title.setFont(new Font("Buxton Sketch",Font.BOLD,50));
		title.setForeground(Color.RED);
		title.setBounds(300,80,800,50);
		this.add(title);

		players.setFont(new Font("Buxton Sketch",Font.BOLD,25));
		players.setForeground(Color.BLUE);
		players.setBounds(100,250,500,100);
		this.add(players);

                //ask user to select no of players between 2 to 4
		ItemListener itemListener = new ItemListener() {
	      	public void itemStateChanged(ItemEvent itemEvent) {
		        int state = itemEvent.getStateChange();
		        num_of_players = Integer.parseInt(itemEvent.getItem()+"");
		        System.out.println((state == ItemEvent.SELECTED) ? "Selected" : "Deselected");
		        System.out.println("No. of players: " + num_of_players);
		    }
    	};
    	combo.addItemListener(itemListener);
    	combo.setSelectedItem(null);
		combo.setBounds(400,285,80,30);
		this.add(combo);

		play_btn.setFont(new Font("Buxton Sketch",Font.BOLD,25));
		Color green = Color.decode("#27ae60");
		play_btn.setForeground(green);
		play_btn.setBounds(200,420,200,50);
		this.add(play_btn);
		this.setVisible(true);
	}
	
	BufferedImage snake = null;
	{
		Color yellow = Color.decode("#FFFF99");
		this.setBackground(yellow);		//setting background to yellow color
	}


	public void paintComponent(Graphics g)
	{

		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;

		try{
			snake = ImageIO.read(new File("images/snake.png"));	//reading logo image		
		}
		catch(IOException ie){}
		g.drawImage(snake, 670,350,this);				//drawing logo image
	}

}

class Winner extends JPanel //this panel is displayed when any player wins and game ends
{
	JLabel win=new JLabel("");
	{
		this.setLayout(null);

		win.setFont(new Font("Buxton Sketch",Font.BOLD,50));
		win.setForeground(Color.RED);
		win.setBounds(150,150,800,50);
		this.add(win);
		this.setVisible(true);
	}

	BufferedImage snake = null;
	{
		Color yellow = Color.decode("#FFFF99");
		this.setBackground(yellow);		//setting background to yellow color
	}


	public void paintComponent(Graphics g)
	{

		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;

		try{
			snake = ImageIO.read(new File("images/snake.png"));	//reading logo image		
		}
		catch(IOException ie){}
		g.drawImage(snake, 670,350,this);				//drawing logo image
	}
}

public class SnakesLadders extends JFrame implements ActionListener {

CardLayout  cl = new CardLayout();		//making card layout for frame
MainMenu mm = new MainMenu();
Game g = new Game();
Winner w = new Winner();
int roll;
int turn=1;		//1st turn : red
int ladder_red=0, ladder_green=0, ladder_blue=0, ladder_purple=0;   //flag to check if a player has just climbed a ladder
int poison_red=0, poison_green=0, poison_blue=0, poison_purple=0;   //flag to check if a player has been poisoned by a snake
int red_exceeds=0, green_exceeds=0, blue_exceeds=0, purple_exceeds=0;   //flag to check if position of player exceed 100
int winner=0;   //flag to check if someone has won or not

SnakesLadders() 
{
	this.setLayout(cl);			//setting card layout
	this.add(mm);				//adding main menu

	(mm.play_btn).addActionListener(this);	//adding action listener to play btn
	(g.roll_btn).addActionListener(this); 	//adding action listener to roll btn
}

	public static void main(String [] args) throws Exception
	{
		SnakesLadders sl = new SnakesLadders();					//object of our class
	
		sl.setBounds(150,10,1000,700);				//setting bounds of frame
		sl.setResizable(false);
		sl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//to exit the application
		sl.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae){			// over-riding actionListener's method

		String s= ae.getActionCommand();
		if(s.equals("PLAY")){				// checking which button is pressed			
			this.add(g);    // add Game panel and remove main menu panel
			this.remove(mm);
			
		}

		if(s.equals("Roll Dice!")){     //if button for roll dice is pressed
			roll = 1 + (int)(Math.random() * ((6 - 1) + 1));		//generating random number between 1 and 6
			System.out.println("Roll is: "+roll);
			play(roll);
		}
	}

	public String wins(int winner)
	{
            String s="";
            //changing text of the label to whoever wins the game
		if(winner==1){
			w.win.setText("Red Won!");
                        s="Red Won!";
                }
		else if(winner==2){
			w.win.setText("Green Won!");
                        s="Green Won!";
                }
		else if(winner==3){
			w.win.setText("Blue Won!");
                        s="Blue Won!";
                }
		else if(winner==4){
			w.win.setText("Purple Won!");
                        s="Purple Won!";
                }

		this.add(w);    //adding Winner panel and removing Game panel
		this.remove(g);
                
                return(s);
	}

	public boolean play(int roll)  //function dealing with all the game logic
	{
            String s;
		g.dice_no.setText("" + roll);

		if(turn==1) //red's turn
		{
			if(poison_red==1 && roll != 6)  //if red is poisoned and doesn't roll a 6, its next player's turn
				turn=2;
			else
			{
				poison_red=0;

				if(roll==6) //get another chance to if 6 is rolled
					turn=1;
				else
					turn=2;		

				if(!(g.red_cur+roll>100))   //increase the current position only if it doesn't exceed 100
					g.red_cur+=roll;
				else
					red_exceeds=1;  //flag set to 1 if current position exceeds 100

                                //if player is positioned at start of a snake or ladder
				if(g.red_cur==3 || g.red_cur==10 || g.red_cur==16 || g.red_cur==27 || g.red_cur==31 || g.red_cur==47 || g.red_cur==56 || g.red_cur==61 || g.red_cur==63 || g.red_cur==66 || g.red_cur==72 || g.red_cur==97)
				{
                                    //positioned at start of ladder, flag set to 1
					if(g.red_cur==3 || g.red_cur==10 || g.red_cur==27 || g.red_cur==56 || g.red_cur==61 || g.red_cur==72)
						ladder_red=1;
					else    //positioned at mouth of snake, flag set to 1
						poison_red=1;
					g.red_cur=g.board[g.red_cur];   //take player directly to end of ladder or tail of snake
				}
			   	System.out.println("red is on: "+g.red_cur);
                                
                                //if user has just climbed the ladder and is at end of ladder, he gets another chance
			   	if(ladder_red==1 && (g.red_cur==39 || g.red_cur==12 || g.red_cur==53 || g.red_cur==84 || g.red_cur==90 || g.red_cur==99))	//if on top of ladder, get another chance
			   		turn=1;

			   	ladder_red=0;

			   	if(g.red_cur==100)  //if player reaches 100, red wins
			   	{
			   		winner=1;
			   		s=wins(winner);
			   		System.out.println(s);
			   	}
			   		
			   	else if(red_exceeds==1){    //if position exceeds 100, pass the turn to next player
			   		turn=2;
			   		System.out.println("red's turn is: "+ turn);
			   	}
			   	else
			   	{
			   		g.red_x=g.x[g.red_cur]; //change position to new x and y coordinates
					g.red_y=g.y[g.red_cur];
			   	}
			}
			
		}
                
		else if(turn==2)    //green's turn
		{	
			if(poison_green==1 && roll != 6)//if green is poisoned and does not roll 6, its next player's turn
			{
				if(mm.num_of_players==2)
					turn=1;
				else
					turn=3;
			}

			else
			{
				poison_green=0;
				if(roll==6) //if 6 is rolled, player gets another chance
					turn=2;
				else    //next player gets turn
				{
					if(mm.num_of_players==2)
						turn=1;
					else
						turn=3;
				}
				
				if(!(g.green_cur+roll>100)) //increase current position only if it doesn't exceed 100
					g.green_cur+=roll;
				else
					green_exceeds=1;    //set flag to true if exceeds 100
                                
                                //if player positioned at start of ladder or mouth of snake
				if(g.green_cur==3 || g.green_cur==10 || g.green_cur==16 || g.green_cur==27 || g.green_cur==31 || g.green_cur==47 || g.green_cur==56 || g.green_cur==61 || g.green_cur==63 || g.green_cur==66 || g.green_cur==72 || g.green_cur==97)
				{
                                    
                                    //at start of ladder, flag set to 1
					if(g.green_cur==3 || g.green_cur==10 || g.green_cur==27 || g.green_cur==56 || g.green_cur==61 || g.green_cur==72)
						ladder_green=1;
					else    //at mouth of snake, flag set to 1
						poison_green=1;
					g.green_cur=g.board[g.green_cur];   //take player directly to top of ladder or tail of snake
				}
			   	System.out.println("green is on: "+g.green_cur+ " ladder_green is: "+ladder_green);
                                
                                //if player just climbed ladder and is positioned at end of ladder, he gets another turn
			   	if(ladder_green==1 && (g.green_cur==39 || g.green_cur==12 || g.green_cur==53 || g.green_cur==84 || g.green_cur==90 || g.green_cur==99))	//if on top of ladder, get another chance
			   		turn=2;

			   	ladder_green=0; //unset the flag

			   	if(g.green_cur==100)    //if green reaches 100, it wins
			   	{
			   		winner=2;
			   		s=wins(winner);
			   		System.out.println(s);
			   	}

			   	else if(green_exceeds==1)   //if position exceeds 100, pass the turn to next player
			   	{
			   		if(mm.num_of_players==2)
						turn=1;
					else
						turn=3;
					System.out.println("green's turn is: "+turn);
			   	}

			   	else
			   	{
			   		g.green_x=g.x[g.green_cur]; //change position to new x and y coordinates
					g.green_y=g.y[g.green_cur];
			   	}
				
			}
			
		}
                
                //same logic as above follows for other 2 players

		else if(turn==3)    //blue's turn
		{	
			if(poison_blue==1 && roll != 6)
			{
				if(mm.num_of_players==3)
					turn=1;
				else
					turn=4;
			}

			else
			{
				poison_blue=0;
				if(roll==6)
					turn=3;
				else
				{
					if(mm.num_of_players==3)
						turn=1;
					else
						turn=4;
				}		

				if(!(g.blue_cur+roll>100))
					g.blue_cur+=roll;
				else
					blue_exceeds=1;

				if(g.blue_cur==3 || g.blue_cur==10 || g.blue_cur==16 || g.blue_cur==27 || g.blue_cur==31 || g.blue_cur==47 || g.blue_cur==56 || g.blue_cur==61 || g.blue_cur==63 || g.blue_cur==66 || g.blue_cur==72 || g.blue_cur==97)
				{
					if(g.blue_cur==3 || g.blue_cur==10 || g.blue_cur==27 || g.blue_cur==56 || g.blue_cur==61 || g.blue_cur==72)
						ladder_blue=1;
					else
						poison_blue=1;
					g.blue_cur=g.board[g.blue_cur];
				}
			   	System.out.println("blue is on: "+g.blue_cur);

			   	if(ladder_blue==1 && (g.blue_cur==39 || g.blue_cur==12 || g.blue_cur==53 || g.blue_cur==84 || g.blue_cur==90 || g.blue_cur==99))	//if on top of ladder, get another chance
			   		turn=3;

			   	ladder_blue=0;

			   	if(g.blue_cur==100)
			   	{
			   		winner=3;
			   		s=wins(winner);
			   		System.out.println(s);
			   	}

			   	else if(blue_exceeds==1)
			   	{
			   		if(mm.num_of_players==3)
						turn=1;
					else
						turn=4;
					System.out.println("blue's turn is: "+turn);
			   	}
			   	else
			   	{
			   		g.blue_x=g.x[g.blue_cur];
					g.blue_y=g.y[g.blue_cur];
			   	}

			}
			
		}

		else if(turn==4)    //purple's turn
		{	
			if(poison_purple==1 && roll != 6)
				turn=1;
			else
			{
				poison_purple=0;
				if(roll==6)
					turn=4;
				else
					turn=1;
			
				if(!(g.purple_cur+roll>100))
					g.purple_cur+=roll;
				else
					purple_exceeds=1;

				if(g.purple_cur==3 || g.purple_cur==10 || g.purple_cur==16 || g.purple_cur==27 || g.purple_cur==31 || g.purple_cur==47 || g.purple_cur==56 || g.purple_cur==61 || g.purple_cur==63 || g.purple_cur==66 || g.purple_cur==72 || g.purple_cur==97)
				{
					if(g.purple_cur==3 || g.purple_cur==10 || g.purple_cur==27 || g.purple_cur==56 || g.purple_cur==61 || g.purple_cur==72)
						ladder_purple=1;
					else
						poison_purple=1;
					g.purple_cur=g.board[g.purple_cur];
				}
			   	System.out.println("purple is on: "+g.purple_cur);

			   	if(ladder_purple==1 && (g.purple_cur==39 || g.purple_cur==12 || g.purple_cur==53 || g.purple_cur==84 || g.purple_cur==90 || g.purple_cur==99))	//if on top of ladder, get another chance
			   		turn=4;

			   	ladder_purple=0;

			   	if(g.purple_cur==100)
			   	{
			   		winner=4;
			   		s=wins(winner);
			   		System.out.println(s);
			   	}

			   	else if(purple_exceeds==1)
			   	{
			   		turn=1;
			   		System.out.println("purple's turn is: "+turn);
			   	}
			   	else
			   	{
			   		g.purple_x=g.x[g.purple_cur];
					g.purple_y=g.y[g.purple_cur];
			   	}
			}
		}

		g.repaint();    //call paint component again
                return true;
	}
}
