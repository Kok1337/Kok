package com.mygdx.game.View;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Model.JustObject;

public class First extends World
{

    public First()
    {
        super(1000, 1000);
    }

   // @Override
    public void getSettings()
    {
        allObject.add(new JustObject(100, 100, new Texture("barrier.png"), "barrier1"));
        allObject.add(new JustObject(200, 250, new Texture("barrier.png"), "barrier2"));
        allObject.add(new JustObject("matches",25, 25, new Texture("matches.png")));
        allObject.add(new JustObject("mandragora",700, 400, new Texture("mandragora.png")));
        allObject.add(new JustObject("object",500, 500, new Texture("obj.png")));
        allObject.add(new JustObject("from1to2", 400, 100, new Texture("door.png"), "second"));
        allObject.add(new JustObject("candlestick", 50, 400, new Texture("candelfalse.png"), "matches", "bern"));

        /*used.add(new Interaction(40, 400, new Texture("candelfalse.png"), things.get(0)) {
            @Override
            public void doIt()
            {
                this.texture = new Texture("candel.png");
                this.used = true;
                World.cloak.deleteThing();
            }
        });*/
    }
}
