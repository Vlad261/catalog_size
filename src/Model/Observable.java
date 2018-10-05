/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author Lenovo
 */
public interface Observable {
    
    int ACTIVE = 1;

    int PAUSE = 2;
    
    int STOPED = 9;
    
    int getStatus();
    
}
