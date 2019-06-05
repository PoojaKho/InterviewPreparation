package model;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by poojakhot on 6/4/19.
 */
public class Popup {

    public List<Menuitem> getMenuitem() {
        return menuitem;
    }

    public void setMenuitem(List<Menuitem> menuitem) {
        this.menuitem = menuitem;
    }

    private List<Menuitem> menuitem=new ArrayList<>();


    @Override
    public String toString()
    {
        return "Popup [menuitem = "+menuitem+"]";
    }
}
