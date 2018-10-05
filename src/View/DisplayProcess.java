/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author Lenovo
 */
public interface DisplayProcess {

    int ACTIVE = 1;

    int PAUSE = 2;
    
    int STOPED = 9;

    void updataStatus(int status);

}
