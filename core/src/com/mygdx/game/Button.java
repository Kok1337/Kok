package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Button
{
    Texture texture;
    Position centerPos;
    Rectangle body;

    public Button(int x, int y, Texture texture)
    {
        centerPos = new Position(x, y);
        this.texture = texture;
        body = new Rectangle(x - texture.getHeight() / 2 - 1, y - texture.getHeight() / 2 - 1, texture.getWidth() + 2, texture.getHeight() + 2);
    }

    public boolean isClick(int x, int y)
    {
        return body.contains(x, y);
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(texture, body.x, body.y);
    }

    //тип, что делает если на нее нажали
    public abstract void run();
}
