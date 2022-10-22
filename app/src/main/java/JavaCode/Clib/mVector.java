package JavaCode.Clib;

import java.util.Vector;

public class mVector {
    private Vector a;
    String name;

    public mVector(String name2) {
        this.name = name2;
        this.name = name2;
        this.a = new Vector();
    }

    public mVector() {
        this.name = "BuildConfig.FLAVOR";
        this.a = new Vector();
    }

    public mVector(Vector a2) {
        this.name = "BuildConfig.FLAVOR";
        this.a = a2;
    }

    public void addElement(Object o) {
        this.a.addElement(o);
    }

    public boolean contains(Object o) {
        if (this.a.contains(o)) {
            return true;
        }
        return false;
    }

    public int size() {
        if (this.a == null) {
            return 0;
        }
        return this.a.size();
    }

    public Object elementAt(int index) {
        if (index <= -1 || index >= this.a.size()) {
            return null;
        }
        return this.a.elementAt(index);
    }

    public void setElementAt(Object obj, int index) {
        if (index > -1 && index < this.a.size()) {
            this.a.setElementAt(obj, index);
        }
    }

    public int indexOf(Object o) {
        return this.a.indexOf(o);
    }

    public void removeElementAt(int index) {
        if (index > -1 && index < this.a.size()) {
            this.a.removeElementAt(index);
        }
    }

    public void removeElement(Object o) {
        this.a.removeElement(o);
    }

    public void removeAllElements() {
        this.a.removeAllElements();
    }

    public void insertElementAt(Object o, int i) {
        this.a.insertElementAt(o, i);
    }

    public Object firstElement() {
        return this.a.firstElement();
    }

    public Object lastElement() {
        return this.a.lastElement();
    }
}
