package com.mygdx.game.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Position;

public class JustObject
{
    public Texture texture;
    public Rectangle body;
    public int id;
    public Position center;
    public static int counter = 0;

    public JustObject(int x, int y, Texture img)
    {
        counter++;
        center = new Position(x, y);
        this.texture = img;
        this.body = new Rectangle(x - texture.getWidth() / 2 - 1, y - texture.getHeight() / 2 + 1, texture.getWidth() + 1, texture.getHeight() + 1);
        this.id = counter;
    }

    public boolean isCollision(Player player)
    {
        return player.body.overlaps(this.body);
    }

    public int getIDByClick(int x, int y)
    {
        if (body.contains(x, y))
            return this.id;
        else return -1;
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(texture, body.x, body.y);
    }

    public void setPosition(Position position)
    {
        this.center = position;
        body.setCenter(position.x, position.y);
    }

}
