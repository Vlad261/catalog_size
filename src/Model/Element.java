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
public class Element implements ElementDisplay, ElementModification {

    protected String name = "‘‡ÈÎ˚";
    protected long size=0;
    protected String path;
    protected int level;
    protected long numberFils = 0;
    public double part;

    public Element(String path, int laval) {
        this.path = path;
        this.level = laval;
    }

    @Override
    public int getLavel() {
        return level;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPart() {
        return part;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public int rights() {
        return FILE;
    }

    @Override
    public String getSizeToString() {
        int value = 0;
        StringBuilder builder = new StringBuilder();
        if (size > 1024) {
            double kb = size / 1024;
            if (kb > 1024) {
                double mb = kb / 1024;
                if (mb > 1024) {
                    double gb = mb / 1024;
                    value = (int) (gb * 10);
                    gb = (double) value / 10;
                    builder.append(gb);
                    builder.append(" √¡");
                } else {
                    value = (int) (mb * 10);
                    mb = (double) value / 10;
                    builder.append(mb);
                    builder.append(" Ã¡");
                }
            } else {
                value = (int) (kb * 10);
                kb = (double) value / 10;
                builder.append(kb);
                builder.append(" K¡");
            }
        } else {
            builder.append(size);
            builder.append(" ·");
        }
        return builder.toString();
    }

    public void getOpenNode(ArrayList<ElementDisplay> resultList){ 
        resultList.add(this);
    }
    
    @Override
    public long getFils() {
        return numberFils;
    }

    @Override
    public long getFolders() {
        return 0;
    }

    @Override
    public long getSize() {
        return size;
    }

    @Override
    public void remove() {
        
    }

    @Override
    public void setPart(double part) {
        this.part = part;
    }

    @Override
    public void start() {
        
    }
    
    public void addElement(long size) {
        this.size += size;
        numberFils++;
    }

}
