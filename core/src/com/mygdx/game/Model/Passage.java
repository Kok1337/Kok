package com.mygdx.game.Model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Player.Player;
import com.mygdx.game.Position;
import com.mygdx.game.View.World;

//Данный класс создает переход от одной локации на другую
public class Passage extends JustObject
{
    public String nameLocation;

    public Passage(int x, int y, Texture img, String nameLocation)
    {
        super(x, y, img);
        this.nameLocation = nameLocation;
    }

    public void openLocation()
    {
        World.lastNameLocation = World.nameLocation;
        World.nameLocation = nameLocation;
        World.game.setScreen(World.game.listScreens.get(nameLocation));
    }
}
