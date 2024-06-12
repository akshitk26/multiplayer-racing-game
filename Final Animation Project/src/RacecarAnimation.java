
//required import statements
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
//TODO: Change the name of the class from AnimationShell to the name of your class
public class RacecarAnimation extends JPanel {

	//TODO: set the initial width and height of your image
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;

	//required global variables
	private BufferedImage image;
	private Graphics g;
	private Timer timer;

	private boolean gameOver = false;
	private boolean blueWon = false;
	private boolean redWon = false;
	
	private Racecar blueCar;
	private Racecar redCar;
	
	private RacetrackWalls track;

	//Constructor required by BufferedImage
	public RacecarAnimation() {
		//set up Buffered Image and Graphics objects
		image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();

				
		/*TODO: Code to setup the objects you will animate goes here
		        All objects declared above should be initialized here
		*/
		
		blueCar = new Racecar(250, 60, Color.BLUE, track);
		redCar = new Racecar(200, 110, Color.RED, track);
		
		track = new RacetrackWalls();
	
		//set up and start the Timer
		timer = new Timer(10, new TimerListener());
		timer.start();
		addMouseListener(new Mouse());
		addKeyListener(new Keyboard()); 
		setFocusable(true); 

	}
	
	private class Mouse implements MouseListener {
		public void mouseClicked(MouseEvent e) {
		}
		public void mouseEntered(MouseEvent e) { }
		public void mouseExited(MouseEvent e) { 
		}
		public void mousePressed(MouseEvent e) { }
		public void mouseReleased(MouseEvent e) {
		}
	}
	
	private class Keyboard implements KeyListener {
		public void keyPressed(KeyEvent e) {
			double key = e.getKeyCode();

			//Arrow key movement (BLUE)
	        if (key == KeyEvent.VK_UP) {
	            blueCar.increaseSpeed();
	        } else if (key == KeyEvent.VK_DOWN ) {
	            blueCar.decreaseSpeed();
	        } else if (key == KeyEvent.VK_LEFT) {
	            blueCar.rotateLeft();
	        } else if (key == KeyEvent.VK_RIGHT) {
	            blueCar.rotateRight();
	        }

			//WASD movement (RED)
			if (key == KeyEvent.VK_W) {
	            redCar.increaseSpeed();
	        } else if (key == KeyEvent.VK_S ) {
	            redCar.decreaseSpeed();
	        } else if ( key == KeyEvent.VK_A) {
	            redCar.rotateLeft();
	        } else if (key == KeyEvent.VK_D) {
	            redCar.rotateRight();
	        }
	    }

		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();

			if(gameOver && key == KeyEvent.VK_SPACE) {

				gameOver = false;
				timer.start();
				redWon = false;
				blueWon = false;

				//reset scores
				blueCar.setScore(0);
            	redCar.setScore(0);

				//reset car positions
				blueCar.setX(100);
				blueCar.setY(60);
				redCar.setX(300);
				redCar.setY(60);
				
				//reset speed and orientation
				blueCar.setSpeed(0);
				redCar.setSpeed(0);
				blueCar.setRotation(0);
				redCar.setRotation(0);
			}
		 }
		public void keyTyped(KeyEvent e) { }
	}
	
	//TimerListener class that is called repeatedly by the timer
	private class TimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

			// Clear the screen by filling the background color
            g.setColor(Color.GRAY); // Change the background color as needed
            g.fillRect(0, 0, WIDTH, HEIGHT);

            // Move and draw the Racecar objects
            
            track.draw(g);
            
            blueCar.move();
            redCar.move();

            blueCar.draw(g);
            redCar.draw(g);

			g.setColor(Color.BLUE);
			g.setFont(new Font("Arial", Font.BOLD, 30)); 
			g.drawString("SCORE: " + blueCar.getScore(), 170, 200);
			g.setColor(Color.RED);
			g.drawString("SCORE: " + redCar.getScore(), 170, 250);

			if(blueCar.getScore() == 5){
				gameOver = true;
				blueWon = true;
			} else if(redCar.getScore() == 5){
				gameOver = true;
				redWon = true;
			}

			//stop the game if its over (score = 5)
			if(gameOver){
				if(blueWon){
					g.setColor(Color.BLUE);
					g.setFont(new Font("Arial", Font.BOLD, 60)); 
					g.drawString("BLUE WINS!", 400, 350);
					g.drawString("Press space to play again", 300, 400);
				} else if(redWon){
					g.setColor(Color.RED);
					g.setFont(new Font("Arial", Font.BOLD, 60)); 
					g.drawString("RED WINS!", 400, 350);
					g.drawString("Press space to play again", 300, 400);
				}
				
				timer.stop();
			}

            repaint();
        }
    }

	//do not modify this
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	//main method with standard graphics code
	public static void main(String[] args) {
		JFrame frame = new JFrame("RacecarAnimation");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new RacecarAnimation()); //TODO: Change this to the name of your class!
		frame.setVisible(true);
	}
}