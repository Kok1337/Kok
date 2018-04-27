package com.mygdx.game.Inventory;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Model.Thing;
import com.mygdx.game.Position;

public class Panel
{
    public Position center;
    public Rectangle body;
    public Texture panel = new Texture("panel.png");
    public Thing thing;
    public int id;
    public Panel(int x, int y, int number)
    {
        this.id = number;
        center = new Position(x + panel.getWidth() /2  + 1, y + panel.getHeight() / 2 + 1);
        body = new Rectangle(x, y, panel.getWidth(), panel.getHeight());
    }

    public void addThing(Thing thing)
    {
        this.thing = thing;
    }

    public void deleteThing()
    {
        this.thing = null;
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(panel, body.x, body.y);
        if (thing != null)
            thing.renderPanel(batch, center);
    }

    public int getIDByClick(int x, int y)
    {
        if (body.contains(x, y))
            return id;
        else return -1;
    }
}
