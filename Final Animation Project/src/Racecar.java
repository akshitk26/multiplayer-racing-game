import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Racecar {
	
	private double x;
	private double y;
	private Color color;
	private double speed;
	private double vx;
	private double vy;
	private double rotation;
	private int width;
	private int height;
	private int score;
	private boolean hasCrossedFinish;
	
	private RacetrackWalls track;

	ArrayList<Rectangle> innerWalls = new ArrayList<>();
	//inner walls
	Rectangle r1 = new Rectangle(150, 150, RacecarAnimation.WIDTH - 300, 150);
	Rectangle r2 = new Rectangle(150, 200, 400, 150);
	Rectangle r3 = new Rectangle(300, 350, 250, 100);
	Rectangle r4 = new Rectangle(RacecarAnimation.WIDTH - 300, 200, 150, 100);
	Rectangle r5 = new Rectangle(RacecarAnimation.WIDTH - 400, 400, 250, 100);
	Rectangle r6 = new Rectangle(150, RacecarAnimation.HEIGHT - 200, RacecarAnimation.WIDTH - 300, 50);

	//finish line
	private static Rectangle finishLine = new Rectangle(50, 150, 100, 20);

	public Racecar() {
		this.x = 0;
		this.y = 0;
		this.color = Color.WHITE;
		this.vx = 0;
		this.vy = 0;
		this.speed = 0;
		width = 50;
		score = 0;
		this.hasCrossedFinish = false;
	}

	public Racecar(double x, double y, Color color, RacetrackWalls track) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.vx = 0;
		this.vy = 0;
		this.speed = 0;
		width = 50;
		height = 25;
		this.track = track;
		hasCrossedFinish = false;
	}
	
	public void draw(Graphics g) {

		//create new 2d graphics object to draw with
	    Graphics2D g2d = (Graphics2D) g.create();
	    g2d.setColor(this.color);

		//saves current form before rotating
	    AffineTransform old = g2d.getTransform();

		//rotate
	    g2d.rotate(Math.toRadians(rotation), x + width / 2, y + height / 2);

		//draw car
	    g2d.fillRect((int) x, (int) y, width, height);

		//resets form so rotations dont stack on each other
	    g2d.setTransform(old);
	}
	
	public void increaseSpeed() {
		speed += 0.5;
	}
	
	public void decreaseSpeed() {
		speed -= 0.5;
	}
	
	public void rotateRight() {
		rotation += 10;
	}
	
	public void rotateLeft() {
		rotation -= 10;
	}

	public void move() {
		vx = Math.cos(Math.toRadians(rotation));
		vy = Math.sin(Math.toRadians(rotation));
		
		x += vx * speed;
		y += vy * speed;
	
		//Create a Rectangle object for car
		Rectangle car = new Rectangle((int) x, (int) y, width, height);
	
		//Check for collision with outer walls
		if (x < 50 || x + width > RacecarAnimation.WIDTH - 50 || y < 50 || y + height > RacecarAnimation.HEIGHT - 50) {
			handleCollision();
		}

		//add barriers to arraylist
		innerWalls.add(r1);
		innerWalls.add(r2);
		innerWalls.add(r3);
		innerWalls.add(r4);
		innerWalls.add(r5);
		innerWalls.add(r6);
	
		//Check for collision with inner walls
		for (Rectangle wall : innerWalls) {
			if (car.intersects(wall)) {
				handleCollision();
				break;
			}
		}

		//finish line intersection
		if (car.intersects(finishLine)) {
			if (!hasCrossedFinish) {
				score++;
				hasCrossedFinish = true;
			}
		} else {
			hasCrossedFinish = false;
		}
	}

	private void handleCollision() {
		// Handle collision: You can reset the position of the racecar, change its direction, or take any other appropriate action
		// For example, you can reset the position to the previous location:
		x -= speed * Math.cos(Math.toRadians(rotation));
		y -= speed * Math.sin(Math.toRadians(rotation));
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getX() {
		return x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getY() {
		return y;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return speed;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public double getRotation() {
		return rotation;
	}

}
