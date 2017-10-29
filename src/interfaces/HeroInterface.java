/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import Server.*;
import java.rmi.*;
/**
 *
 * @author Patzo
 */
public interface HeroInterface extends Remote {
    public void shootArrow() throws RemoteException;
    public void moveHero(int offsetX, int offsetY) throws RemoteException;
    public void removeHero() throws RemoteException, NotBoundException;
    public boolean checkWhetherAlive() throws RemoteException;
}
