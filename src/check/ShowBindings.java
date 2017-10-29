/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package check;

/**
 *
 * @author Patzo
 */

import java.rmi.*;
import java.rmi.server.*;

public class ShowBindings{
   public static void main (String args[]){
      try{
         String[] bindings = Naming.list("");
         for (int i=0; i< bindings.length; i++)
            System.out.println(bindings[i]);
      }
      catch(Exception e){
         e.printStackTrace();
      }
   }
}
