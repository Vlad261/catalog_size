/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public interface ElementDisplay extends ElementDirectory{
      
    int getLavel();
    
    String getName();
    
    double getPart();
    
    String getPath();
    
    int rights();
    
    String getSizeToString();
    
    void getOpenNode(ArrayList<ElementDisplay> resultList);
    
}
