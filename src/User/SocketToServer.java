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
import java.rmi.*;
import java.rmi.server.*;
import java.io.*; 

public class SocketToServer {
    public static void main(String[] args)throws IOException { 
        System.setProperty("java.security.policy","client.policy");
        System.setSecurityManager(new SecurityManager());
        String url = "rmi://127.0.0.1:1099/";                  
	try { 
            interfaces.ServerInterface remoteServer = (interfaces.ServerInterface)Naming.lookup(url + "SERVER");
            String hero = remoteServer.createHero();
            interfaces.HeroInterface remoteHero = (interfaces.HeroInterface)Naming.lookup(url + hero);
            Frame f = new Frame(remoteServer, remoteHero);
            remoteHero.removeHero();
        }catch (RemoteException exc) {
            System.out.println("Error in lookup: " + exc.toString()); }
        catch (java.net.MalformedURLException exc) {
            System.out.println("Malformed URL: " + exc.toString());   }
        catch (java.rmi.NotBoundException exc)  {
            System.out.println("NotBound: " + exc.toString());        }
	}
}