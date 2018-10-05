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
public abstract interface ElementDirectory {
    
    int FILE=1;
            
    int FOLDER=2;
    
    long getFils();
    
    long getFolders();
    
}
