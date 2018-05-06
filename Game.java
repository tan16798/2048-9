
import java.awt.Canvas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JOptionPane;



public class Game extends Canvas  implements Runnable, MouseListener, MouseMotionListener ,KeyListener {
        private static final long serialVersionUID = 1550691097823471818L;      
public static final int WIDTH = 640, HEIGHT = 640;
        private Thread thread;
        private boolean running = false;

       private Cell cell;
	
private Board board;
       private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		
       
		public Game() {
    	 
    	  int x=0, y=0;
    	  new Frame(WIDTH,HEIGHT,"2048",this);
    	
    	  
    	  addMouseMotionListener(this);
    	  addMouseListener(this);
    	  
    	  setFocusable(true);
    	  addKeyListener(this);
    	
    	 board = new Board(0, 0 );
    	
    	
      }
        public synchronized void start(){
                thread = new Thread(this);
                thread.start();
                running = true;
        }
        public synchronized void stop(){
                try{
                        thread.join();
                        running = false;
                }catch(Exception e){
                        e.printStackTrace();
                }
        }
        public void run(){
               
                long lastTime = System.nanoTime();
                double amountOfTicks = 60.0;
                double ns = 1000000000 / amountOfTicks;
                double delta = 0;
                long timer = System.currentTimeMillis();
                int frames = 0;
                while(running){
                        long now = System.nanoTime();
                        delta += (now - lastTime) / ns;
                        lastTime = now;
                        while(delta >= 1){
                                tick();
                                delta--;
                        }
                       if(running)
                               render();
                        frames++;
                        if(System.currentTimeMillis() - timer > 1000){
                                timer += 240;
                                System.out.println("FPS: " + frames);
                                frames = 0;
                        }
                }
                stop();
        }
      
        private void tick(){
       
       
        //if(Keys.pressed[KeyEvent.VK_LEFT]==true ||Keys.pressed[KeyEvent.VK_DOWN]==true ||Keys.pressed[KeyEvent.VK_UP]==true || Keys.pressed[KeyEvent.VK_RIGHT]==true) {
        	//System.out.println("hit");}
       // Keys.update();
		board.update();
		 Keys.update();
		
        	}
        
       private void render(){
    	   Graphics2D g = (Graphics2D) image.getGraphics();
   		g.setColor(Color.black);
   		g.fillRect(0, 0, WIDTH, HEIGHT);
   		board.render(g);
   		
   		g.dispose();

   		
   		Graphics2D g2d = (Graphics2D) getGraphics();
   		g2d.drawImage(image, 0, 0, null);
   		g2d.dispose();
            
           }
         
  
       
		public static void main(String args[]){
		
           new Game();
           
                    
        }
		
            
		@Override
		public void mousePressed(MouseEvent e) {
			
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			
			
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
		

			
		}
		@Override
		public void mouseClicked(MouseEvent e) {
		
			
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			
			
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			
			
			
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			
			
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyReleased(KeyEvent e) {Keys.keyReleased(e);
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyPressed(KeyEvent e) {Keys.keyPressed(e);}
	
}