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
import interfaces.HeroInterface;
import interfaces.ServerInterface;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class Frame extends JFrame {
	public Frame(ServerInterface remoteServer, HeroInterface remoteHero){
        
        Layers j = new Layers(remoteServer, remoteHero);
        
        this.add(j);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(660, 520);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        //j.Listen();
        j.ReceiveCoordinates();
        this.dispose();
	}


}