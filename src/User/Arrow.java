/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

/**
 *
 * @author Patzo
 */
import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Arrow extends JPanel {
    int angle, x, y;
    Image arrow;
    public Arrow(){
	setOpaque(false);
	try {
            arrow = ImageIO.read(new File("Resources\\arrowBrown.png"));
	} catch (IOException e){ System.out.println("EXCEPTION");}
	angle =0;
    }

    public void paintComponent(Graphics gg)
    {
	Graphics2D g = (Graphics2D) gg;
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	AffineTransform matrix = g.getTransform(); // Backup
	g.rotate(Math.toRadians(angle),15,15);
	/* Begin */
	g.drawImage(arrow,0,0,30,30,null);
	/* End */
	g.setTransform(matrix); // Restore
	
    }
    public void render(int X, int Y, int angle){
	x=X;
	y=Y;
	this.setBounds(x, y, 30, 30);
        this.angle=angle;
	this.repaint();
    }
}