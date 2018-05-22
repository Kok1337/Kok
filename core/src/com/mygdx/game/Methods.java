package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Model.JustObject;
import com.mygdx.game.View.World;

public class Methods
{
    public static Start start;

    public Methods(Start start)
    {
        this.start = start;
    }

    public static void MethodManader(JustObject object)
    {
        if (object.nameMethod.equals("bern"))
            candlestick(object);
    }

    public static void candlestick(JustObject object)
    {
        start.listScreens.get("second").allObject.add(new JustObject("sqr", 100, 100, new Texture("obj.png")));
        object.texture = new Texture("candel.png");
        World.cloak.deleteThing();
    }

}
