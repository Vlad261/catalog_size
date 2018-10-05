/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presenter;

import Model.ElementDisplay;
import Model.ManagerThread;
import Model.Model;
import View.DisplayProcess;
import View.View;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Lenovo
 */
public class Presenter {

    private View view;
    private Model model;
    private DisplayProcess display;
    private ManagerThread manager;

    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setPresenter(this);
        model.setPresenter(this);
    }

    public void analysis(File f, DisplayProcess process) {
        this.display = process;
        view.clearTable();
        process.updataStatus(DisplayProcess.ACTIVE);
        model.putMainDir(f.getAbsolutePath());
        /*
         model.setHead(f.getAbsolutePath());        
         fillingTable();
         */
    }

    public void endingAnalysis(boolean flag) {
        manager = null;
        display.updataStatus(DisplayProcess.STOPED);
        if (flag) {
            fillingTable();
        }
    }

    private String shiftName(String name, int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append("     ");
        }
        builder.append(name);
        return builder.toString();
    }

    private void fillingTable() {
        view.clearTable();
        ArrayList<ElementDisplay> list = model.getList();
        ArrayList<Vector> rows = new ArrayList<>();
        for (ElementDisplay line : list) {
            Vector<String> row = new Vector<>();
            row.add(shiftName(line.getName(), line.getLavel()));
            row.add(line.getSizeToString());
            row.add(String.valueOf(line.getFils()));
            row.add(String.valueOf(line.getFolders()));
            row.add(String.valueOf(line.getPart()));
            row.add(line.getPath());
            rows.add(row);
        }
        view.updateTable(rows);
    }

    public void openDir(int row) {
        boolean flag = model.openFolder(row);
        if (flag) {
            fillingTable();
        }
    }

    public void updataDisplay(int status) {
        display.updataStatus(status);
    }

    public void setStatus(int status) {
        manager.setStatus(status);
    }
    
    public void setManager(ManagerThread manager){
        if(this.manager==null){
            this.manager=manager;
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        Model client = new Model();
        View view = new View();
        new Presenter(view, client);

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                view.setVisible(true);
            }
        });
    }
}
