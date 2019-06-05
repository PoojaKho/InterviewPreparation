package model;

public class Menuitem {
    private String onclick;

    private String value;

    public String getOnclick ()
    {
        return onclick;
    }

    public void setOnclick (String onclick)
    {
        this.onclick = onclick;
    }

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "Menuitem [onclick = "+onclick+", value = "+value+"]";
    }
}
