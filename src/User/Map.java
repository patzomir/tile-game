/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import Server.*;

/**
 *
 * @author Patzo
 */
public class Map {
	private int[][] static_map = 
        { 
        		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        		{1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1},
        		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        		{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}

        };
	public int[][] getMap(){return static_map;};
	
}