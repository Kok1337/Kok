package com.mygdx.game.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Interface.Pause;
import com.mygdx.game.Model.Passage;
import com.mygdx.game.Position;

public class Second extends World
{
    public Second()
    {
        super(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 20);
    }

   // @Override
    public void getSettings()
    {
        passages.add(new Passage(700, 100, new Texture("door.png"), "first"));

        for (int i = 3; i < 8; i++)
        {
            for (int j = 5; j < 10; j++)
            {
                closeAllTime.add(aStar.getNodeFromMap(i,j));
            }
        }
    }
}
