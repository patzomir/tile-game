/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.rmi.RemoteException;


/**
 *
 * @author Patzo
 */
public class StartServer {
    public static void main(String args[]) {
           try{

               //create a local instance of the object
               Server server = new Server("SERVER");

               System.out.println("Server waiting.....");
           }
           catch (RemoteException re)  {
               System.out.println("Remote exception: " + re.toString());  }
    }
}
