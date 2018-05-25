package com.mygdx.game.Interface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Camera.Camera;
import com.mygdx.game.View.World;

public class Pause extends Interface
{
    public Texture pause;

    public Pause(int x, int y, Texture icon, String id, Camera camera)
    {
        super(x, y, icon, id, camera);
        pause = new Texture("pauseScreen.png");
    }

    @Override
    public void render(SpriteBatch batch)
    {
        batch.draw(icon, (int)camera.mainCamera.position.x - camera.mainCamera.viewportWidth / 2 + position.x, (int)camera.mainCamera.position.y - camera.mainCamera.viewportHeight / 2 + position.y);
    }

    @Override
    public void use(World world)
    {
        Gdx.app.error("PAUSE", "aaaaaaaa");
        world.state = World.State.PAUSE;
    }

}