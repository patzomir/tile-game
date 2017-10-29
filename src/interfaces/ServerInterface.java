/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import Server.*;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Patzo
 */
public interface ServerInterface extends Remote {
    public String createHero() throws RemoteException;
    public String ReturnLayout() throws RemoteException;
    public String ReturnArrowLayout() throws RemoteException;
}
