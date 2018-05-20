package com.mygdx.game.Control;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Interface.Interface;
import com.mygdx.game.Model.JustObject;
import com.mygdx.game.Player.Player;
import com.mygdx.game.SearchPath.Node;
import com.mygdx.game.View.World;

public class MainSystem
{
    public World world;
    public Logic logic;
    public InputController controller;
    public String idObject = "null";
    public String idInterface = "null";
    public MainSystem(World world)
    {
        this.world = world;
        logic = new Logic();
        controller = new InputController();
    }

    public void update()
    {
        if (Gdx.input.justTouched())
        {
            controller.render(Gdx.input.getX(), world.camera.cameraHeight - Gdx.input.getY());
        }
        logic.render();
    }

    public class Logic
    {
        public void render()
        {
            //Gdx.app.error("LogicRender" , " " + (world.chosenObject && world.player.body.overlaps(world.door.body)));
            //Gdx.app.error("id", "" + idInterface);
            //Gdx.app.error("size: ", "" + Gdx.graphics.getWidth() + " " + Gdx.graphics.getHeight());
            world.player.state = Player.State.STAND;
            if (world.player.path.size() > 0 && world.player.countArr != world.player.path.size())
            {
                world.player.state = Player.State.WALK;
                world.player.move();
            }
            if (!idInterface.equals("null"))
            {
                interfaceAction();
            }
            if (!idObject.equals("null"))
            {
                objectAction();
            }
            world.camera.positionUpdate(world.player);
        }

        public void interfaceAction()
        {
            for (Interface justInterface : world.interfaces)
            {
                if (justInterface.id.equals(idInterface))
                {
                    idInterface = "null";
                    justInterface.use(world);
                    break;
                }
            }
        }

        public void objectAction()
        {
            for (JustObject object : world.allObject)
            {
                if (object.id.equals(idObject) && object.isCollision(world.player))
                {
                    idObject = "null";
                    object.run(world);
                    break;
                }
            }
        }
    }

    public class InputController
    {
        public void render(int x, int y)
        {
            int targetX = (((int) (world.camera.mainCamera.position.x) - world.camera.cameraWidth / 2) + x);
            int targetY = (((int) (world.camera.mainCamera.position.y) - world.camera.cameraHeight / 2) + y);
            Gdx.app.error("koord", "" + targetX +" "+ targetY);
            getIDInterface(x, y);

            if (idInterface.equals("null"))
            {
                //проверка на пренодлежность к интерфейсу
                //Gdx.app.error("корды", "" + targetX +" "+ targetY);
                //для А*
                {
                    Node st = world.aStar.getNodeFromMap(world.player.position.x / Node.nodeSize, world.player.position.y / Node.nodeSize);
                    if (!world.aStar.closeAllTime.contains(st))
                    {
                        world.player.setPath(world.aStar.getPath(world.player.position.x, world.player.position.y, targetX, targetY));
                    }

                    getIDObject(targetX, targetY);

                    if (world.player.path.size() <= world.player.countArr)
                        world.player.countArr = 0;
                    if (world.player.path.size() > 0 && world.player.countArr != 0)
                    {
                        if (world.player.position.x / Node.nodeSize != world.player.path.get(world.player.countArr - 1).position.x &&
                                world.player.position.y / Node.nodeSize != world.player.path.get(world.player.countArr - 1).position.y)
                        {
                            world.player.countArr = 0;
                        }
                    }

                }
            }
        }

        public void getIDObject(int x, int y)
        {
            for (JustObject object : world.allObject)
            {
                idObject = object.getIDByClick(x, y);
                if (!idObject.equals("null"))
                    break;
            }
        }

        public void getIDInterface(int x, int y)
        {
            for (Interface path : world.interfaces)
            {
                idInterface = path.getID(x, y);
                if (!idInterface.equals("null"))
                    return;
            }
        }
    }
}
