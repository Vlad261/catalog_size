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
public interface ElementModification extends ElementDirectory{

    long getSize();
    
    void remove();
    
    void setPart(double part);
        
    void start();
    
}
