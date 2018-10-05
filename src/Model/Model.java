/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Presenter.Presenter;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Model {

    private Presenter presenter;
    private Node head;
    private ArrayList<ElementDisplay> list;
    private ManagerThread manager;

    public void setPresenter(Presenter presenter) {
        if (this.presenter == null) {
            this.presenter = presenter;
        }
    }

    public void putMainDir(String path) {//setHead(String path){
        if (list !=null) {
            list.clear();
        }
        if (head != null) {
            head.remove();
        }
        manager = new ManagerThread(presenter);
        head = new Node((Observable) manager, path, 0);
        manager.setHead(head);
        manager.start();
    }

    private void updateList() {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.clear();
        head.getOpenNode(list);
    }

    public ArrayList<ElementDisplay> getList() {
        updateList();
        return list;
    }

    public boolean openFolder(int row) {
        if (list.get(row).rights() == ElementDirectory.FOLDER) {
            Node node = (Node) list.get(row);
            node.setOpen(!node.isOpen());
            updateList();
            return true;
        }
        return false;
    }

}
