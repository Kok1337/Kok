package com.mygdx.game.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Position;

import java.util.ArrayList;

public class Thing extends JustObject
{
    public Thing(int x, int y, Texture img)
    {
        super(x, y, img);
    }

    public void renderPanel(SpriteBatch batch, Position center)
    {
        batch.draw(texture, center.x - texture.getWidth() / 2, center.y - texture.getHeight() / 2);
    }
}
