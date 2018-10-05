/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class Node extends Element {

    private long numberFolders = 0;
    private ArrayList<ElementModification> list;
    private boolean open = false;
    private Observable manager;

    public Node(Observable manager, String path, int laval) {
        super(path, laval);
        this.manager=manager;        
        if (laval == 0) {
            part = 100;
        }
        list = new ArrayList<>();
    }


    @Override
    public int rights() {
        return FOLDER;
    }

    @Override
    public long getFolders() {
        return numberFolders;
    }

    private void setPart() {
        double part;
        for (ElementModification element : list) {            
            part = (double) element.getSize();
            part = (long) (part / size * 1000);
            part /= 10;
            element.setPart(part);
        }
    }

    private void setSize() {
        for (ElementModification element : list) {
            size += element.getSize();
        }
    }

    private void sort() {
        int k = 0;
        while (k < list.size()) {   //30 20 10         
            if (k != 0) {
                if (list.get(k - 1).getSize() >= list.get(k).getSize()) {
                    k++;
                } else {
                    ElementModification temp = list.get(k);
                    list.set(k, list.get(k - 1));
                    list.set(k - 1, temp);
                    k--;
                }
            } else {
                k++;
            }
        }
    }
    

    @Override
    public void start() {
        setInfo();
        fillingList();
        for (ElementModification element : list) {
            if(checkToStatus()){
                return;
            }
            element.start();
            numberFils += element.getFils();
            numberFolders += element.getFolders();
        }
        setSize();
        setPart();
        sort();
    }

    @Override
    public void remove() {
        for (ElementModification element : list) {
            element.remove();
        }
        list.clear();
    }

    private void setInfo() {
        File head = new File(path);
        name = head.getName();
    }

    private void fillingList() {
        File head = new File(path);
        File element[] = head.listFiles();
        try {
            Element fils = new Element(path, level + 1);
            for (File f : element) {
                checkToStatus();
                if (f.isDirectory()) {
                    list.add(new Node(manager, f.getPath(), level + 1));
                    numberFolders++;
                } else if (f.isFile()) {
                    fils.addElement(f.length());
                    numberFils++;
                }
            }
            if (numberFils > 0) {
                list.add(fils);
            }
        } catch (Exception e) {
            System.out.println(path+ ": "+e.getMessage());            
        }
    }

    @Override
    public void getOpenNode(ArrayList<ElementDisplay> resultList) {
        resultList.add(this);
        if (open) {
            for (ElementModification element : list) {
                ((ElementDisplay) element).getOpenNode(resultList);
            }
        }
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isOpen() {
        return open;
    }
    
    private boolean checkToStatus(){
        while (manager.getStatus()==Observable.PAUSE) {            
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                System.out.println("Node->"+ex.getMessage());
            }
        }
        return manager.getStatus()==Observable.STOPED;
    }


}
