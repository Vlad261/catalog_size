package Model;

import Presenter.Presenter;

public class ManagerThread extends Thread implements Observable {

    private Node head;
    private int status = 3;
    private Presenter presenter;
    private boolean flag = false;

    public ManagerThread(Presenter presenter) {
        this.presenter = presenter;
        presenter.setManager(this);
    }

    public void setHead(Node head) {
        if (this.head == null) {
            this.head = head;
        }
    }

    public synchronized void setStatus(int status) {
        if (this.status != Observable.STOPED) {
            this.status = status;
            flag = true;
        }
    }

    @Override
    public synchronized int getStatus() {
        if (flag && !head.isOpen()) {
            presenter.updataDisplay(status);
        }
        return status;
    }

    @Override
    public void run() {
        head.start();
        head.setOpen(true);
        if (status == Observable.STOPED) {
            head.remove();
            presenter.endingAnalysis(false);
        } else {
            presenter.endingAnalysis(true);
        }

    }
}
