package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Model.JustObject;

public class Second extends World
{
    public Second()
    {
        super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

   // @Override
    public void getSettings()
    {
        allObject.add(new JustObject("from2to1", 500, 300, new Texture("door.png"),"first"));

        for (int i = 3; i < 8; i++)
        {
            for (int j = 5; j < 10; j++)
            {
                //closeAllTime.add(aStar.getNodeFromMap(i,j));
            }
        }
    }
}
