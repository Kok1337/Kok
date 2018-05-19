package com.mygdx.game.Model;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Player.Player;
import com.mygdx.game.View.World;

public abstract class Interaction extends JustObject
{
    public boolean used = false;
    public Thing thing;
    public Interaction(int x, int y, Texture img, Thing thing)
    {
        super(x, y, img);
        this.thing = thing;
    }

    public abstract void doIt();

    public void check(Player player)
    {
        if (World.cloak.selectPanel != -1)
        if (thing == World.cloak.panels[World.cloak.selectPanel].thing)
            doIt();
    }

}
