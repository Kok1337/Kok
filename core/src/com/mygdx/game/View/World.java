package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.Camera.Camera;
import com.mygdx.game.Control.MainSystem;
import com.mygdx.game.Interface.Bag;
import com.mygdx.game.Interface.Interface;
import com.mygdx.game.Interface.Pause;
import com.mygdx.game.Inventory.Cloak;
import com.mygdx.game.Model.JustObject;
import com.mygdx.game.Model.Passage;
import com.mygdx.game.Model.Thing;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Position;
import com.mygdx.game.SearchPath.AStar;
import com.mygdx.game.SearchPath.Node;
import com.mygdx.game.Start;

import java.util.ArrayList;

public abstract class World implements Screen
{
    public enum State
    {
        PAUSE,
        RUN,
        INVENTORY
    }

    public static Start game;
    public static State state = State.RUN;
    public static Cloak cloak = new Cloak();
    public static String nameLocation;
    public static String lastNameLocation;
    public static SpriteBatch batch = new SpriteBatch();

    public Pause pause;

    public AStar aStar;
    public Player player;
    public Camera camera;
    public MainSystem system;
    public Camera IR;

    public int nodeSize;
    public int worldWidth;
    public int worldHeight;

    public ArrayList<Thing> things;
    public ArrayList<Passage> passages;
    public ArrayList<Node> closeAllTime;
    public ArrayList<Interface> interfaces;



    public World(int worldWidth, int worldHeight, int nodeSize)
    {
        //потом вместо размера мира надо будет кидать текстуру и вычислять через нее размеры
        this.worldWidth = worldWidth;
        this.worldHeight =worldHeight;
        this.nodeSize = nodeSize;
        player = new Player(0, 0, nodeSize);
        aStar = new AStar(worldWidth, worldHeight, nodeSize);
        camera = new Camera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), worldWidth, worldHeight);
        interfaces = new ArrayList<Interface>();
        closeAllTime = new ArrayList<Node>();
        passages = new ArrayList<Passage>();
        things = new ArrayList<Thing>();
        getSettings();
        aStar.setCloseAllTime(closeAllTime);
        system = new MainSystem(this);

        pause = new Pause(740, 420, new Texture("buttonPause.png"),"pause", camera);
        getInterface();
        IR = new Camera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        IR.mainCamera.position.set(camera.cameraWidth / 2, camera.cameraHeight /2, 0);
    }

    @Override
    public void show()
    {

        //изменение позиции персонажа
        //player.setPosition(player.getPosition(nameLocation, lastNameLocation));
        player.setPosition(new Position(0, 0));
        player.path = aStar.getPath(player.position.x, player.position.y, player.position.x, player.position.y);
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //рендер локации

        batch.begin();

        switch (state)
        {
            case RUN:
                runRender();
                Gdx.app.error("render", delta + "");
                break;
            case INVENTORY:
                IR.render(batch);
                cloak.render(batch);
                if (Gdx.input.justTouched())
                {
                    cloak.selectPanel(Gdx.input.getX(),  Gdx.graphics.getHeight() - Gdx.input.getY());
                    this.state = State.RUN;
                }
                break;
            case PAUSE:
                IR.render(batch);
                batch.draw(pause.pause, IR.cameraWidth / 2 - pause.pause.getWidth() / 2, IR.cameraHeight / 2 - pause.pause.getHeight() / 2);
                if (Gdx.input.justTouched())
                    this.state = State.RUN;
                break;
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {
        this.state = State.PAUSE;
        //batch.draw(new Texture("pauseScreen.png"), 0, 0);
    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {
        JustObject.counter = 0;
    }

    @Override
    public void dispose()
    {
        batch.dispose();
    }

    void getInterface()
    {
        interfaces.add(pause);
        interfaces.add(new Bag(703, 0, new Texture("panelSuper.png"),"bag", camera));
    }

    void runRender()
    {
        system.update();
        aStar.render(batch);
        for (Passage passage : passages)
        {
            passage.render(batch);
        }
        for (Thing thing : things)
        {
            thing.render(batch);
        }
        player.render(batch);
        camera.render(batch);
        for (Interface part : interfaces)
        {
            part.render(batch);
        }
    }

    public abstract void getSettings();
}
