package com.mygdx.game.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Interface.Pause;
import com.mygdx.game.Model.Interaction;
import com.mygdx.game.Model.Passage;
import com.mygdx.game.Model.Thing;
import com.mygdx.game.Position;

public class First extends World
{

    public First()
    {
        super(1000, 1000);
    }

   // @Override
    public void getSettings()
    {
        for (int i = 5; i < 10; i++)
        {
            for (int j = 5; j < 10; j++)
            {
                closeAllTime.add(aStar.getNodeFromMap(i,j));
            }
        }
        things.add(new Thing(25, 25, new Texture("matches.png")));
        things.add(new Thing(700, 400, new Texture("mandragora.png")));
        things.add(new Thing(500, 500, new Texture("obj.png")));

        passages.add(new Passage(400, 100, new Texture("door.png"), "second"));
        used.add(new Interaction(40, 400, new Texture("candelfalse.png"), things.get(0)) {
            @Override
            public void doIt()
            {
                this.texture = new Texture("candel.png");
                this.used = true;
                World.cloak.deleteThing();
            }
        });
    }
}
