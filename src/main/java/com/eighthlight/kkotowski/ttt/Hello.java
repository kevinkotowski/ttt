package com.eighthlight.kkotowski.ttt;

public class Hello {
    private String name = "";

    public String getName()
    {
        return name;
    }

    public String getMessage()
    {
        if (name == "")
        {
            return "Hello!";
        }
        else
        {
            return "Hello " + name + "!";
        }
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
