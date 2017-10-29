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
import java.io.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameStart extends JPanel {
	int TotalWidth=640;
	int TotalHeight=480;
	Image wall, floor;
	int static_map[][], i, j;
	BufferedImage im = new BufferedImage(TotalWidth,TotalHeight,BufferedImage.TRANSLUCENT);
	public GameStart(){
		init();
	}
	public void init(){
		setOpaque(false);
		setSize(TotalWidth, TotalHeight);
		Graphics g = im.getGraphics();
		try {
			floor = ImageIO.read(new File("Resources\\Ground01.png"));
			wall = ImageIO.read(new File("Resources\\Column.png"));
			Map map = new Map();
			static_map=map.getMap();		
			DrawMap(g);
		} catch (IOException e){ System.out.println("EXCEPTION");}
	};
	private void DrawMap(Graphics g){
		for (i=0;i<12;i++){
			for (j=0;j<16;j++){
				if(static_map[i][j]==1) g.drawImage(wall,40*j,40*i,40,40,null);
				else  g.drawImage(floor,40*j,40*i,40,40,null);
			};
		};
	};
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        g.drawImage(im,0,0,null);
	};
}
