package model;

import java.util.ArrayList;

/**
 * Created by poojakhot on 6/4/19.
 */
public class Employee {
    private String id;
    private String name;
    private Popup popup =new Popup();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Popup getpopup() {
        return popup;
    }

    public void setpopup(Popup popup) {
        popup = popup;
    }



    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", popup=" + popup +
                '}';
    }
}
