package com.mygdx.game.Interface;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Camera.Camera;
import com.mygdx.game.Inventory.Panel;
import com.mygdx.game.Model.Thing;
import com.mygdx.game.Position;
import com.mygdx.game.View.World;

public class Bag extends Interface
{
    public static Panel panel;
    public Bag(int x, int y, Texture icon, String id, Camera camera)
    {
        super(x, y, icon, id, camera);
        panel = new Panel(x, y, 228);
        panel.panel = icon;
    }

    @Override
    public void render(SpriteBatch batch)
    {
        batch.draw(panel.panel, (int)camera.mainCamera.position.x - camera.cameraWidth / 2 + position.x, (int)camera.mainCamera.position.y - camera.cameraHeight / 2 + position.y);
        if (panel.thing != null)
            panel.thing.renderPanel(batch, new Position((int)camera.mainCamera.position.x - camera.cameraWidth / 2 + panel.center.x, (int)camera.mainCamera.position.y - camera.cameraHeight / 2 + panel.center.y));
    }

    @Override
    public void use(World world)
    {
        world.state = World.State.INVENTORY;
    }

    public static void setThing(Thing thing)
    {
        panel.thing = thing;
    }
}
