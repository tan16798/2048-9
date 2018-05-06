import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;




public class Cell {
public static int CELL_X=80;
public static int CELL_Y=80;
public static int arcx=15;
public static int arcy=15;
public static int speed=20;
private  BufferedImage cellimage;
private int value;
private boolean move=false;

private Color background;
private Color text;
private  int x;
private  int y;
public static final int SLIDE_SPEED = 30;
public  Cell(int value ,int x , int y ) {
	this.x=x;
	this.y=y;
	this.value=value;
	
	cellimage= new BufferedImage(CELL_X,CELL_Y, BufferedImage.TYPE_INT_ARGB);

	drawImage();

	
	//value =2;
}
public void render(Graphics2D g) {
	int drawX, drawY;
	g.drawImage(cellimage, x, y, null);
	Font fnt2=new Font ("algerian", Font.PLAIN, 30);
	g.setFont(fnt2);
	if(value<10) {
	drawX = x + 30;
	drawY = y+50;
	}
	else if (value<100 && value>=10) {
		drawX = x+23;
		drawY = y+50;
	}
	else if (value<1000 && value>=100) {
		drawX = x+16;
		drawY = y+50;
	}
	else {
		drawX = x+5;
		drawY = y+50;
	}
	g.setColor(text);
	g.drawString(""+value ,drawX, drawY);
	//System.out.println(x+" "+y);
}

public void drawImage() {
	
	Graphics2D g = (Graphics2D) cellimage.getGraphics();
	if (value == 2) {
		background = new Color(151,202,239);
		text = new Color(25);
	}
	else if (value == 4) {
		background = new Color(217,189,172);//(128,189,158);
		text = new Color(25);
	}
	else if (value == 8) {
		background = new Color(76,123,149);
		text = new Color(25);
	}
	else if (value == 16) {
		background = new Color(217,189,172);
		text = new Color(25);
	}
	else if (value == 32) {
		background = new Color(249,32,30);
		text = new Color(25);
	}
	else if (value == 64) {
		background = new Color(186,218,85);
		text = new Color(25);
	}
	else if (value == 128) {
		background = new Color(25);
		text = new Color(25);
	}
	else if (value == 256) {
		background = new Color(25);
		text = new Color(25);
	}
	else if (value == 512) {
		background = new Color(25);
		text = new Color(25);
	}
	else if (value == 1024) {
		background = new Color(25);
		text = new Color(25);
	}
	else if (value == 2048) {
		background = new Color(25);
		text = new Color(25);
	}
	else if(value == 0){
		background = Color.lightGray;
		text = Color.black;
	}
	
	else{
		background = new Color(25);
		text = new Color(25);
	}
	g.setColor(new Color(0, 0, 0, 0));
	g.fillRect(0, 0, CELL_X,CELL_Y );
	g.setColor(background);
	g.fillRoundRect(0, 0, Cell.CELL_X, Cell.CELL_Y, Cell.arcx, Cell.arcy);
	//System.out.println(x+" "+y);
	

	//
	//g.setColor(text);
	
	//g.drawString(""+value ,CELL_X/2 ,CELL_Y/2);
	//System.out.println("yes");
	g.dispose();
	}
public int getValue() {
	return value;
}
public void setValue(int value) {
	this.value = value;
	drawImage();
}
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
public boolean Move() {
	return move;
}
public void setMove(boolean move) {
	this.move = move;
}

  
}
