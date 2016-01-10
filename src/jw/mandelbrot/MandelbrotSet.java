package jw.mandelbrot;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

// The class MandelbrotSet creates methods that allow for the generation of a fractal 
// using the Mandelbrot Set for coordinates in the complex plane. 

public class MandelbrotSet implements ActionListener{
	private JFrame window;	
	
	private int xAxis = 600, yAxis = 400;
	private float scale = 2; // Scaling variable 
	
	private int colorShift = 45; 
	private boolean pan = false;
	private int xMouse = 0, yMouse = 0;
	
	private int maxCount = 1000;
	private BufferedImage image;
	private imagePanel iPane;
	
	private JPanel jPane;
	private JButton button;
	
	public MandelbrotSet () {
		window = new JFrame ("Mandelbrot Set");
		window.setVisible (true);
		window.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		window.setLayout(new BorderLayout());
		
		iPane = new imagePanel();
		image = new BufferedImage (2 * xAxis, 2 * yAxis, BufferedImage.TYPE_INT_RGB);
		createMandelbrot();	
		window.add(iPane, BorderLayout.CENTER);
		
		jPane = new JPanel ();
		jPane.setLayout(new FlowLayout());
		button = new JButton ("Change it up!");
		button.addActionListener(this);
		jPane.add(button);
		window.add(jPane, BorderLayout.SOUTH);
		
		window.pack();
		
	}	
		
	public void createMandelbrot () {
		float distance; 
		int count;
		ComplexNum initial, term;
		
		for (int x = -xAxis; x < xAxis; x++) {
			for (int y = -yAxis; y < yAxis; y++ ) {
				initial = new ComplexNum ((x - xAxis/4) * scale / xAxis, y * scale / xAxis);
				term = new ComplexNum (0,0); 
				distance = 0;
				count = 0;
				
				//find the term of the sequence (if it's before maxCount) at which the magnitude exceeds 2
				do {
					distance += ComplexNum.mag(term);
					term = ComplexNum.plus(ComplexNum.times(term, term), initial);
					count += 1;
				} while (ComplexNum.mag(term) <= 2 && count < maxCount);
				
				//set pixel color based on distance and count
				image.setRGB(x + xAxis, yAxis - y - 1, (int) distance * count << colorShift); 
			}
		}
	}
	
	private static int randInt (int min, int max) {
		return (int) (Math.random() * (max - min + 1)) + min;
	}
	
	public void actionPerformed (ActionEvent evt) {
		colorShift = randInt (1, 200);
		System.out.printf ("New colorShift: %d\n", colorShift);
		createMandelbrot();
		window.repaint();
	}
	
	private class imagePanel extends JPanel {
		private static final long serialVersionUID = 1L;

		@Override
		public Dimension getPreferredSize () {
			return new Dimension (image.getWidth(), image.getHeight());
		}
		
		@Override
		public void paintComponent (Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, null);
		}
		
	}
	
	public static void main (String[] args) {
		new MandelbrotSet();	
	}

}
