import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Stack;











public class Board{
public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;

	public static final int ROWS = 4;
	public static final int COLS = 4;
    public static Stack T;
	private final int startingCells = 2;
	private Cell[][] board;
	private boolean dead;
	private boolean won;
	private BufferedImage gameBoard;
	private int x;
	private int y;
private Random r;
private int i=0;
private boolean undo=true;
	private static int SPACING = 10;
	public static int BOARD_WIDTH = (COLS + 1) * SPACING + COLS * Cell.CELL_X;
	public static int BOARD_HEIGHT = (ROWS + 1) * SPACING + ROWS * Cell.CELL_Y;

	

	

	public Board(int x, int y) {
		 T=new Stack ();
		this.x = x;
		this.y = y;
		board = new Cell[ROWS][COLS];
		gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		createBoardImage();
		for(int i=0;i<3;i++) {

			spawn();
		}
		
	}
	private void createBoardImage() {
		Graphics2D g = (Graphics2D) gameBoard.getGraphics();
		g.setColor(Color.darkGray);
		g.fillRect(0,0, BOARD_WIDTH, BOARD_HEIGHT);
		g.setColor(Color.lightGray);

		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				int x = SPACING + SPACING * col + Cell.CELL_X * col;
				int y = SPACING + SPACING * row + Cell.CELL_Y * row;
				g.fillRoundRect(x, y, Cell.CELL_X, Cell.CELL_Y, Cell.arcx, Cell.arcy);
			}
		}
	}
	public void render(Graphics2D g) {
		BufferedImage finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D) finalBoard.getGraphics();
		g2d.setColor(new Color(0, 0, 0, 0));
		g2d.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
		g2d.drawImage(gameBoard, 0, 0, null);

		
				for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				Cell current = board[row][col];
				
				
				if (current == null) continue;
				else current.render(g2d);
				//System.out.println("board["+row+"]["+col+"]="+current);
				
			}
	}

		g.drawImage(finalBoard, x, y, null);
		g2d.dispose();

		
	}
	public void spawn() {
		r=new Random();
		boolean notvalid = true;
		/*int location = r.nextInt(16);
		int row = location / ROWS;
		int col = location % COLS;*/
		while(notvalid==true) {
		int row = r.nextInt(ROWS);
		int col = r.nextInt(COLS);
		Cell current=board[row][col];

		int x=SPACING+col*Cell.CELL_Y+SPACING*col;
		int y=SPACING+row*Cell.CELL_X+SPACING*row;
		if(current==null) {
		int value = r.nextInt(10) < 9 ? 2 : 4;
		//System.out.println(value+"l");
		Cell cell =new Cell(value,x,y );
		board[row][col]=cell;
		}
		notvalid=false;
		}
	}
	
public void keys() {
	 boolean cu=false;

	if (Keys.pressed[KeyEvent.VK_LEFT]!=true && Keys.prev[KeyEvent.VK_LEFT]==true) {
		if(undo==true) {
		loadmatrix(T); undo=false;
		System.out.println(undo);}
		
		
		for (int row=ROWS-1; row>=0;row--) {
			for(int col=COLS-1;col>=0; col--) {
			  Cell current = board[row][col];
			  
			  System.out.println("board["+row+"]["+col+"]="+current);
			  if(col!=0) {
				if(current!=null&&board[row][col-1]==null) {
					
					board[row][col-1]=current;
					board[row][col]=null;
					if(board[row][col-1]!=null&&col-1==0) //spawn just when go to the end of board
						 cu=true;
					}
				
				else if(current!=null&&board[row][col-1].getValue() == current.getValue()&&board[row][col-1].Move()==true&&board[row][col].Move()==true ) {
					board[row][col-1].setMove(false);
					board[row][col-1].setValue(current.getValue()*2);
					board[row][col]=null;
					spawn();
				}
				
			  }
					
			}
		}
		
	}
	
	
	else if (Keys.pressed[KeyEvent.VK_DOWN]!=true && Keys.prev[KeyEvent.VK_DOWN]==true) {
		if(undo==true) {
			loadmatrix(T); undo=false;
			System.out.println(undo);}
		
		for (int row=ROWS-1; row>=0;row--) {
			for(int col=0;col<COLS; col++) {
			  Cell current = board[row][col];
			  
			  System.out.println("board["+row+"]["+col+"]="+current);
			  if(row!=ROWS-1) {
				if(current!=null&&board[row+1][col]==null) {
					
					board[row+1][col]=current;
					board[row][col]=null;
					if(board[row+1][col]!=null&&row+1==ROWS-1) //spawn just when go to the end of board
						 cu=true;
					}
				
				else if(current!=null&&board[row+1][col].getValue() == current.getValue()&&board[row+1][col].Move()==true&&board[row][col].Move()==true) {
					board[row+1][col].setMove(false);
					board[row+1][col].setValue(current.getValue()*2);
					board[row][col]=null;
					
					spawn();
				}
				
			  }
					
			}
		}
		
		
	}
	
	
	
	
	else if (Keys.pressed[KeyEvent.VK_RIGHT]!=true && Keys.prev[KeyEvent.VK_RIGHT]==true) {
		if(undo==true) {
			loadmatrix(T); undo=false;
			System.out.println(undo);}
	
	
		for (int row=0; row<ROWS;row++) {
			for(int col=COLS-1;col>=0; col--) {
			  Cell current = board[row][col];
			  
			  System.out.println("board["+row+"]["+col+"]="+current);
			  if(col!=COLS-1) {
				if(current!=null&&board[row][col+1]==null) {
					

						
					
					board[row][col+1]=current;
					board[row][col]=null;
					if(board[row][col+1]!=null&&col+1==COLS-1)
						//spawn just when go to the end of board
					 cu=true;
					}
				
				else if(current!=null&&board[row][col+1].getValue() == current.getValue()&&board[row][col+1].Move()==true&&board[row][col].Move()==true) {
					
					board[row][col+1].setMove(false);
					
					board[row][col+1].setValue(current.getValue()*2);
					board[row][col]=null;
					
						spawn();//spawn when combine
					
					
				}
				
			  }
			 
					
			}
		}
		 
		
	}




	else if (Keys.pressed[KeyEvent.VK_UP]!=true && Keys.prev[KeyEvent.VK_UP]==true) {
		if(undo==true) {
			loadmatrix(T); undo=false;
			System.out.println(undo);}
	
	for (int row=ROWS-1; row>=0;row--) {
		for(int col=COLS-1;col>=0; col--) {
		  Cell current = board[row][col];
		  
		  System.out.println("board["+row+"]["+col+"]="+current);
		  if(row!=0) {
			if(current!=null&&board[row-1][col]==null) {
				
				board[row-1][col]=current;
				board[row][col]=null;
				if(board[row-1][col]!=null&&row-1==0) //spawn just when go to the end of board
					 cu=true;
				}
			
			else if(current!=null&&board[row-1][col].getValue() == current.getValue()&&board[row-1][col].Move()==true&&board[row][col].Move()==true) {
				board[row-1][col].setMove(false);
				board[row-1][col].setValue(current.getValue()*2);
				board[row][col]=null;
				spawn();
				
			}
			
		  }
				
		}
	}
	
 }
	else if (Keys.pressed[KeyEvent.VK_SPACE]!=true && Keys.prev[KeyEvent.VK_SPACE]==true)
	          {
		      undo(T);
			  }
	
	else {resetaction();}

 if(cu==true) {spawn();}
}
	
	


public void update() {
	
	for (int row = 0; row < ROWS; row++) {
	for	 (int col = 0; col < COLS; col++) {
			Cell current = board[row][col];
			if (current == null) continue;
			//current.update();
			{
				
				resetPosition(current, row, col);
				//board[row][col].setMove(true);
			} 
	keys();
	

	
}}}
private void resetPosition(Cell tile, int row, int col) {
	if (tile == null) return;

	int x = SPACING+col*Cell.CELL_Y+SPACING*col;;
	int y = SPACING+row*Cell.CELL_X+SPACING*row;

	int distX = tile.getX() - x;
	int distY = tile.getY() - y;

	if (Math.abs(distX) < Cell.SLIDE_SPEED) {
		tile.setX(tile.getX() - distX);
	}

	if (Math.abs(distY) < Cell.SLIDE_SPEED) {
		tile.setY(tile.getY() - distY);
	}

	if (distX < 0) {
		tile.setX(tile.getX() + Cell.SLIDE_SPEED);
	}
	if (distY < 0) {
		tile.setY(tile.getY() + Cell.SLIDE_SPEED);
	}
	if (distX > 0) {
		tile.setX(tile.getX() -Cell.SLIDE_SPEED);
	}
	if (distY > 0) {
		tile.setY(tile.getY() - Cell.SLIDE_SPEED);
	}
	
}

public void loadmatrix(Stack T) {
	for (int row = 0; row < ROWS; row++) {
		for	 (int col = 0; col < COLS; col++) {
				Cell current = board[row][col];
				/*if (current == null) continue;
				//current.update();
				{
					
					board[row][col].setMove(true);
				} */
				T.push(current);
				
	   }}
	System.out.println(" stack "+T);
}
public void undo(Stack T) {
	for (int row=ROWS-1; row>=0;row--) {
		for(int col=COLS-1;col>=0; col--) {
			if(T.isEmpty()==true) break;
			else if(i<16) {board[row][col]=(Cell) T.pop(); i++;}
			if(board[row][col]!=null)
				System.out.println("board"+row+col+":"+board[row][col].getValue());
			   
			
		}}
}
public void resetaction() {
	for (int row = 0; row < ROWS; row++) {//stop the tile just spawn combine and stop loamatrix many time
	     for	 (int col = 0; col < COLS; col++) {
			Cell current = board[row][col];
			if (current == null) continue;
			
			{
				
				board[row][col].setMove(true);
			} 
         }
	     }
      undo=true;
      i=0;
}

		}