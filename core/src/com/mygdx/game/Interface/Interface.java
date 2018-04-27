package com.mygdx.game.Interface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Camera.Camera;
import com.mygdx.game.Position;
import com.mygdx.game.View.World;

public abstract class Interface
{
    //относительно камеры
    public Position position;
    public Camera camera;
    public Texture icon;
    public String id;
    public Rectangle body;

    public Interface(int x, int y, Texture icon, String id, Camera camera)
    {
        this.position = new Position(x, y);
        this.icon = icon;
        this.id = id;
        this.camera = camera;
        this.body = new Rectangle(x, y, icon.getWidth(), icon.getHeight());
    }

    public abstract void render(SpriteBatch batch);
    {

        //batch.setProjectionMatrix();
    }

    public String getID(int x, int y)
    {
        if (position.x <= x && position.x + body.getWidth() >= x && position.y <= y && position.y + body.getHeight() >= y)
            return id;
        else
            return "null";
    }

    public abstract void use (World world);
}
