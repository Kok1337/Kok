package com.mygdx.game.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Position;
import com.mygdx.game.SearchPath.Node;

import java.util.ArrayList;

public class Player
{
    public enum State
    {
        WALK,
        STAND
    }
    /**/
    private static final int WALK_COLS = 10;
    private static final int WALK_ROWS = 1;

    private static final int STAND_COLS = 21;
    private static final int STAND_ROWS = 1;

    Animation walkAnimation;
    Animation standAnimation;
    Texture walkSheet;
    Texture standSheet;
    TextureRegion[] walkFrames;
    TextureRegion[] standFrames;
    TextureRegion currentFrame;
    public State state = State.STAND;

    float stateTime;
    /**/

    public int speed;
    public int countArr;
    public int nodeSize;
    public Texture player;
    public Position position;
    public Rectangle body;
    public ArrayList<Node> path;
    public boolean isFlip;
    boolean left = true;

    public Player(int x, int y, int nodeSize) {
        this.nodeSize = nodeSize;
        position = new Position(x, y);
        player = new Texture("player.png");
        body = new Rectangle(x - 20, y, player.getWidth(), 30);
        path = new ArrayList<Node>();
        countArr = 0;
        speed = 2;
        isFlip = false;
        /**/

        walkSheet = new Texture(Gdx.files.internal("walk.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/WALK_COLS, walkSheet.getHeight()/WALK_ROWS);
        walkFrames = new TextureRegion[WALK_COLS * WALK_ROWS];
        int index = 0;
        for (int i = 0; i < WALK_ROWS; i++) {
            for (int j = 0; j < WALK_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.05f, walkFrames);

        standSheet = new Texture(Gdx.files.internal("stand.png"));
        tmp = TextureRegion.split(standSheet, standSheet.getWidth()/STAND_COLS, standSheet.getHeight()/STAND_ROWS);
        standFrames = new TextureRegion[STAND_COLS * STAND_ROWS];
        index = 0;
        for (int i = 0; i < STAND_ROWS; i++) {
            for (int j = 0; j < STAND_COLS; j++) {
                standFrames[index++] = tmp[i][j];
            }
        }
        standAnimation = new Animation(0.15f, standFrames);

        stateTime = 0f;
        /**/
    }

    public void setPath(ArrayList<Node> path) {
        this.path = path;
    }

    public void render(SpriteBatch batch)
    {
        if (stateTime > 1000)
            stateTime = 0;
        switch (state)
        {
            case WALK:
                stateTime += Gdx.graphics.getDeltaTime();
                currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true);
                break;
            case STAND:
                stateTime += Gdx.graphics.getDeltaTime();
                currentFrame = (TextureRegion) standAnimation.getKeyFrame(stateTime, true);
                break;
        }

        body.setPosition(position.x - 20, position.y);
        currentFrame.flip(isFlip != currentFrame.isFlipX(), false);
        batch.draw(currentFrame, position.x - 20, position.y);
    }

    public void move() {
        if (position.x == path.get(countArr).position.x * nodeSize && position.y == path.get(countArr).position.y * nodeSize)
        {
            countArr++;
        }
        //Gdx.app.error("move" , countArr +" "+path.get(path.size() - 1).position.x +" "+path.get(path.size() - 1).position.y);
        if (path.size() > countArr) {
            if ((position.x - path.get(countArr).position.x * nodeSize) < 0)
            {
                isFlip = false;
                position.x += speed;
            }
            else if ((position.x - path.get(countArr).position.x * nodeSize) > 0)
            {
                isFlip = true;
                position.x -= speed;
            }
            if ((position.y - path.get(countArr).position.y * nodeSize) < 0)
                position.y += speed;
            else if ((position.y - path.get(countArr).position.y * nodeSize) > 0)
                position.y -= speed;
        }
    }

    public void setPosition(Position position) {
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
