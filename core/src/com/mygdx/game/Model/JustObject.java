package com.mygdx.game.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Position;
import com.mygdx.game.View.World;

public class JustObject
{
    public enum Type
    {
        THING,
        PASSAGE,
        INTERACTION,
        BARRIER
    }

    public Texture texture;
    public Rectangle body;
    public String id;
    public Position center;
    public Type type;
    public String nameLocation;
    public String checkID;
    public String nameMethod;

    //для thing
    public JustObject(String id, int x, int y, Texture texture)
    {
        this.type = Type.THING;
        constructorMethod(id, x, y, texture);
    }

    //для barrier
    public JustObject(int x, int y, Texture texture, String id)
    {
        this.type = Type.BARRIER;
        constructorMethod(id, x, y, texture);
    }

    //для passage
    public JustObject(String id, int x, int y, Texture texture, String nameLocation)
    {
        this.type = Type.PASSAGE;
        this.nameLocation = nameLocation;
        constructorMethod(id, x, y, texture);
    }

    //для interaction
    public JustObject(String id, int x, int y, Texture texture, String checkID, String nameMethod)
    {
        this.type = Type.INTERACTION;
        this.checkID = checkID;
        this.nameMethod = nameMethod;
        constructorMethod(id, x, y, texture);
    }

    private void constructorMethod(String id, int x, int y, Texture texture)
    {
        this.id = id;
        center = new Position(x, y);
        this.texture = texture;
        this.body = new Rectangle(x - texture.getWidth() / 2 - 1, y - texture.getHeight() / 2 + 1, texture.getWidth() + 1, texture.getHeight() + 1);
    }

    public void run(World world)
    {
        switch (type)
        {
            case THING:
                if (World.cloak.addThing(this))
                {
                    world.allObject.remove(this);
                }
                break;

            case PASSAGE:
                world.lastNameLocation = World.nameLocation;
                world.nameLocation = nameLocation;
                world.game.setScreen(World.game.listScreens.get(nameLocation));
                break;

            case INTERACTION:
                break;

            case BARRIER:
                break;
        }
    }

    public boolean isCollision(Player player)
    {
        return player.body.overlaps(this.body);
    }

    public String getIDByClick(int x, int y)
    {
        if (body.contains(x, y))
            return this.id;
        else return "null";
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

    public void renderPanel(SpriteBatch batch, Position center)
    {
        batch.draw(texture, center.x - texture.getWidth() / 2, center.y - texture.getHeight() / 2);
    }
}
