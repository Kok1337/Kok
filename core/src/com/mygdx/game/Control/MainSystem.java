package com.mygdx.game.Control;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Interface.Interface;
import com.mygdx.game.Model.Passage;
import com.mygdx.game.Model.Thing;
import com.mygdx.game.SearchPath.Node;
import com.mygdx.game.View.World;

public class MainSystem
{
    public World world;
    public Logic logic;
    public InputController controller;
    public int idObject = -1;
    public String idInterface = "null";
    public MainSystem(World world)
    {
        this.world = world;
        logic = new Logic();
        controller = new InputController();
    }

    public void update()
    {
        if (Gdx.input.isTouched())
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
            if (world.player.path.size() > 0 && world.player.countArr != world.player.path.size())
            {
                world.player.move();
            }
            Gdx.app.error("Logic", "" + idObject);
            if (!idInterface.equals("null"))
            {
                interfaceProcess(getInterfaceByID());
            }
            if (idObject != -1)
            {
                passageProcess(getPassageByID());
                thingProcess(getThingByID());
            }
            world.camera.positionUpdate(world.player);
        }

        public Passage getPassageByID()
        {
            for (Passage passage : world.passages)
            {
                if (passage.id == idObject)
                    return passage;
            }

            return null;
        }

        public void passageProcess(Passage passage)
        {
            if (passage != null)
            {
                if (passage.isCollision(world.player))
                {
                    idObject = -1;
                    passage.openLocation();
                }
            }
        }

        public Thing getThingByID()
        {
            for (Thing thing : world.things)
            {
                if (thing.id == idObject)
                    return thing;
            }

            return null;
        }

        public void thingProcess(Thing thing)
        {
            if (thing != null)
            {
                if (thing.isCollision(world.player))
                {
                    if (World.cloak.addThing(thing))
                    {
                        world.things.remove(thing);
                    }
                }
            }
        }

        public Interface getInterfaceByID()
        {
            for (Interface justInterface : world.interfaces)
            {
                if (justInterface.id.equals(idInterface))
                {
                    return justInterface;
                }
            }

            return null;
        }

        public void interfaceProcess(Interface justInterface)
        {
            if (justInterface != null)
            {
                justInterface.use(world);
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
                    Node st = world.aStar.getNodeFromMap(world.player.position.x / world.aStar.nodeSize, world.player.position.y / world.aStar.nodeSize);
                    if (!world.aStar.closeAllTime.contains(st))
                    {
                        world.player.setPath(world.aStar.getPath(world.player.position.x, world.player.position.y, targetX, targetY));
                    }

                    getIDObject(targetX, targetY);

                    if (world.player.path.size() <= world.player.countArr)
                        world.player.countArr = 0;
                    if (world.player.path.size() > 0 && world.player.countArr != 0)
                    {
                        if (world.player.position.x / world.aStar.nodeSize != world.player.path.get(world.player.countArr - 1).position.x &&
                                world.player.position.y / world.aStar.nodeSize != world.player.path.get(world.player.countArr - 1).position.y)
                        {
                            world.player.countArr = 0;
                        }
                    }

                }
            }
        }

        public void getIDObject(int x, int y)
        {
            for (Passage pas : world.passages)
            {
                idObject = pas.getIDByClick(x, y);
                if (idObject != -1)
                    break;
            }

            if (idObject == -1)
            {
                for (Thing thing : world.things)
                {
                    idObject = thing.getIDByClick(x, y);
                    if (idObject != -1)
                        break;
                }
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
