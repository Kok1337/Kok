package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Position;
import com.mygdx.game.SearchPath.Node;

import java.util.ArrayList;

public class Player
{
    public int speed;
    public int countArr;
    public int nodeSize;
    public Texture player;
    public Position position;
    public Rectangle body;
    public ArrayList<Node> path;

    public Player(int x, int y, int nodeSize)
    {
        this.nodeSize = nodeSize;
        position = new Position(x, y);
        player = new Texture("player.png");
        body = new Rectangle(x - 20, y, player.getWidth(), 30);
        path = new ArrayList<Node>();
        countArr = 0;
        speed = 2;
    }

    public void setPath(ArrayList<Node> path)
    {
        this.path = path;
    }

    public void render(SpriteBatch batch)
    {
        //move();
        body.setPosition(position.x - 20, position.y);
        batch.draw(player, position.x - 20, position.y);
    }

    public void move()
    {
        if (position.x == path.get(countArr).position.x * nodeSize && position.y == path.get(countArr).position.y * nodeSize)
        {
            countArr++;
        }
        //Gdx.app.error("move" , countArr +" "+path.get(path.size() - 1).position.x +" "+path.get(path.size() - 1).position.y);
        if (path.size() > countArr)
        {
            if ((position.x - path.get(countArr).position.x * nodeSize) < 0)
                position.x += speed;
            else if ((position.x - path.get(countArr).position.x * nodeSize) > 0)
                position.x -= speed;
            if ((position.y - path.get(countArr).position.y * nodeSize) < 0)
                position.y += speed;
            else if ((position.y - path.get(countArr).position.y * nodeSize) > 0)
                position.y -= speed;
        }
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }
/*
    public Position getPosition(String name, String lastName)
    {
        switch (name)
        {
            case "first":
                switch (lastName)
                {
                    case "start":
                        return new Position(300, 400);

                    case "second":
                        return new Position(380, 100);
                }

            case "second":
                switch (lastName)
                {
                    case "first":
                        return new Position(650, 100);
                }
        }
        return new Position(0, 0);
    }
*/
}
