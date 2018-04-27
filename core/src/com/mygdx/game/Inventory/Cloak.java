package com.mygdx.game.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Camera.Camera;
import com.mygdx.game.Interface.Bag;
import com.mygdx.game.Model.Thing;
import com.mygdx.game.View.World;

public class Cloak
{
    Sprite backgroung;
    Panel [] panels;
    public int selectPanel;
    public Cloak()
    {
        backgroung = new Sprite(new Texture("backIn.png"));
        backgroung.scale(10);
        panels = new Panel[12];
        panels[0] = new Panel(80, 260, 0);
        panels[1] = new Panel(200, 260, 1);
        panels[2] = new Panel(500, 260, 2);
        panels[3] = new Panel(620, 260, 3);

        panels[4] = new Panel(80, 140, 4);
        panels[5] = new Panel(200, 140, 5);
        panels[6] = new Panel(500, 140, 6);
        panels[7] = new Panel(620, 140, 7);

        panels[8] = new Panel(80, 20, 8);
        panels[9] = new Panel(200, 20, 9);
        panels[10] = new Panel(500, 20, 10);
        panels[11] = new Panel(620, 20, 11);
        selectPanel = -1;
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(backgroung, 0, 0);
        for (Panel panel : panels)
        {
            panel.render(batch);
        }
    }

    public boolean addThing(Thing thing)
    {
        for (Panel panel : panels)
        {
            if (panel.thing == null)
            {
                panel.addThing(thing);
                return true;
            }
        }
        return false;
    }

    public void deleteThing()
    {
        panels[selectPanel].deleteThing();
    }

    public void selectPanel(int x, int y)
    {
        for (Panel panel : panels)
        {
            selectPanel = panel.getIDByClick(x, y);
            if (selectPanel != -1 && panel.thing != null)
            {
                Bag.setThing(panel.thing);
                break;
            }
        }
        if (selectPanel == -1)
            Bag.setThing(null);
    }
}

