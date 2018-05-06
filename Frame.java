

	import java.awt.Canvas;
	import java.awt.Color;
	import java.awt.Dimension;
	import java.awt.Graphics;

	import javax.swing.JButton;
	import javax.swing.JFrame;
	public class Frame extends Canvas {
	        private static final long serialVersionUID = -240840600533728354L;

	public Frame(int width,int height, String title, Game game){
	                JFrame frame = new JFrame(title);
	                frame.setPreferredSize(new Dimension(width,height));
	                frame.setMaximumSize(new Dimension(width,height));
	                frame.setMinimumSize(new Dimension(width,height));             
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.setResizable(false);
	               frame.setLocationRelativeTo(null);
	                JButton button = new JButton("Play");
	                button.setBounds(30, 2, 100, 50);
	                button.setVisible(true);
	                                 JButton button2 = new JButton("Credit");
	                button2.setBounds(30, 50, 100, 50);
	                button2.setVisible(true);
	                
	               
	               frame.add(game);
	                frame.setVisible(true);
	                
	                
	                game.start();
	                
	        }


	}

