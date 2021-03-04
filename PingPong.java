/*
* Ian Drake
* 11/5/2017
*Recreate Pong
*/
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.Random;
public class PingPong extends JPanel implements ActionListener,KeyListener {

	

	
		Random random = new Random();
	
		Timer tm = new Timer(5, this);
		
		//begins drawn animation at top left corner
		int x = 0;
		static	int y = 0;
		
		int velY = 2;
		int velY2 = 2;

		int xPlayer2 = 360;
		static int yPlayer2 = 0;
		
		int xBall = 30;
		int yBall = 30;
		
		int xBallVel = 1;
		int yBallVel = 0;
		
		int stopMove = 322;
		
		public static boolean overlap1( int x, int x2, int y, int y2) {
		      //  System.out.println("called the method overlap1");
		        boolean b1 = false;
		        if (x == x2 && y == y2) {
		        	System.out.println("overlap");
		        	b1 = true;
		        }
		        return b1;
			}
		
		public void paintComponent(Graphics g) {
			
			super.paintComponent(g);
			
			requestFocus(true);
			g.setColor(Color.RED);
			
			//character 1
			g.fillRect(x,y,20,30);
			
			//character 2
			g.setColor(Color.gray);
			g.fillRect(xPlayer2,yPlayer2, 20,30);	
			
			//ball
			g.setColor(Color.GREEN);
			g.fillRect(xBall, yBall, 10, 10);
			
			//center line
			g.setColor(Color.blue);
			g.drawLine(xPlayer2/2, -2, xPlayer2/2, 1000);
			
			tm.start();
		}
		
		
	    
	    
		
		
		public PingPong(){
			tm.start();

			addKeyListener(this);

			setFocusable(true);

			setFocusTraversalKeysEnabled(true);
		}
		
		
		public void actionPerformed(ActionEvent e) {
			
			if (yPlayer2 > stopMove ) {
				yPlayer2 = stopMove-2;
			}
			
			if (yPlayer2 <= 0) {
				yPlayer2 = 1;
			}
			
			if (y > stopMove ) {
				
				y = stopMove-2;
			}
			
			if (y < 0) {
				y=0;
			}
			
			//set ball to always move
			//if (xBall != -50) {
			//	xBallVel= 3;
		//	}
		//	if (yBall != -999999999) {
		//		yBallVel = 3;
		//	}
			
			
			
			//restricting ball to player zone's
			if (xBall >= xPlayer2 && yBall >= yPlayer2 ) {
				xBallVel=2;
				xBallVel = -xBallVel;
			//	System.out.println("works");
				
				//collision algorithm
				
					System.out.println("Collision between player 2 and ball");
						 
					//ball will bounce in three ways,right middle top,right middle, right middle bottom
					int ballPath=random.nextInt(4);
					System.out.println(ballPath);
					 if (ballPath ==1) {
							 //top middle path
						 xBallVel=-2;
						 yBallVel=2;
							// System.out.println("Top");
							 
					 }
						 
					 if (ballPath == 2) {
							 //middle path
						 xBallVel = -2;
						//	 System.out.println("Middle");
					 }
						 
					 if (ballPath == 3) {
						 
							 //bottom middle path
						 xBallVel = -2;
						 yBallVel = -2;
						//	 System.out.println("Bottom");
					 }	
				
			}
			
			
			//  xBall >= xPlayer2 && yBall >= yPlayer2
			if (xBall <= x +3		  && yBall >= y ) {
				xBallVel= 2;
				xBallVel = +xBallVel;
				
				//bouncing ball for player 1
				
					System.out.println("Collision between player 1 and ball");
					 
					//ball will bounce in three ways,right middle top,right middle, right middle bottom
					int ballPath=random.nextInt(4);
					System.out.println(ballPath);
					 if (ballPath ==1) {
						 //top middle path
						 xBallVel = 2;
						 yBallVel = -2;
					//	 System.out.println("Top");
						 
					 }
					 
					 if (ballPath == 2) {
						 //middle path
						 xBallVel = 2;
					//	 System.out.println("Middle");
					 }
					 
					 if (ballPath == 3) {
						 xBallVel = 2;
						 yBallVel = 2;
						 //bottom middle path
					//	 System.out.println("Bottom");
					 }
				}	
			
			
			if (xBall <= x) {
				xBallVel = +xBallVel;
			}
			
			
				
				
			
			
			
			
			//bounces off bottom
			if (xBall > 0 && yBall > 322) {
			System.out.println("Bouncing ball");
			
			yBallVel = -yBallVel;
			
			
			
		}
			
			//bounces off top
			if (xBall > 0 && yBall < 0) {
				System.out.println("Bouncing ball off top");
				
				if (xBallVel == -2) {
				xBallVel = -xBallVel;
				yBallVel = -yBallVel;
				}
				
				if (xBallVel == 2) {
					xBallVel = xBallVel;
					yBallVel = yBallVel;
				}
				
			}		
				
			
			
			xBall += xBallVel;
			yBall += yBallVel;
			y = y+velY;
			
			yPlayer2+= velY2;
			repaint();
			int player1Sc =0;
			int player2Sc =0;
			
			if (xBall < -3) {
				player2Sc +=1;
				System.out.println("Player 2 scores");
				xBall = 30;
				yBall = 50;
				xBallVel = 2;
				yBallVel =0;
				y = 50;
				yPlayer2 = 50;
			}
			
			if (xBall > 362) {
				player2Sc +=1;
				xBall = 30;
				yBall = 30;
				xBallVel = 2;
				yBallVel = 0;
				y = 50;
				yPlayer2 = 50;
				System.out.println("Player 1 scores");
			}
		//	System.out.printf(" Ball Coordinates: x(%d) y(%d) \n\n\n", xBall,yBall);
			//System.out.printf("\n Player1:  x(%d) y(%d)\n" ,x,y);
			//System.out.printf(" Player2: x(%d) y(%d)\n\n",xPlayer2, yPlayer2);
			
		}
		
		public static void main(String[]args) {
			
			PingPong t= new PingPong();
					
			JFrame jf = new JFrame();
			
			jf.setTitle("PracticingPaintComponent");
			jf.setSize(600, 400);
			jf.setVisible(true);
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jf.add(t);
			
			
			
		}

		
	

	public void keyReleased(KeyEvent arg0) {
		
		velY = 0;
		velY2=0;
	}

	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if ( c == KeyEvent.VK_UP) {
			
			velY2 = -2;
		}
		
		if ( c == KeyEvent.VK_DOWN) {
			
			velY2 = 2;
	}
		
		if (c == KeyEvent.VK_W) {
			
			velY = -2;
		}
		
		if ( c== KeyEvent.VK_S) {
			
			velY = 2;
		}

	
	

	}




	
	public void keyTyped(KeyEvent e) {

		
	}
}
