import java.awt.Color;
import java.awt.Graphics;

public class RacetrackWalls {
	
	public int width;
	public int height;
	
	private Color color;
	
	public RacetrackWalls() {
		this.color = Color.BLACK;
		width = RacecarAnimation.WIDTH; //1000
		height = RacecarAnimation.HEIGHT; //700
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		
		//outer walls
		g.fillRect(0,  0, width, 50);
		g.fillRect(width - 50, 50, 50, height);
		g.fillRect(0,  height-50, width, 50);
		g.fillRect(0,  50, 50, height);
		
		//inner walls
		g.fillRect(150,  150,  width - 300, 150); //r1
		g.fillRect(150,  200, 400, 150); //r2
		g.fillRect(300,  350, 250, 100); //r3
		g.fillRect(width -300, 200, 150, 100); //r4
		g.fillRect(width -400, 400, 250, 100); //r5
		g.fillRect(150,  height - 200, width - 300, 50); //r6

		//finish line
		g.setColor(Color.WHITE);
		g.fillRect(50, 150, 100, 20); //f1

	}

}
