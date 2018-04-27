package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.View.First;
import com.mygdx.game.View.Second;
import com.mygdx.game.View.World;

import java.util.ArrayList;
import java.util.HashMap;

public class Start extends Game {

	public HashMap<String, World> listScreens;

	@Override
	public void create()
	{
		World.game = this;
		listScreens = new HashMap<String, World>();
		listScreens.put("first", new First());
		listScreens.put("second", new Second());

		World.lastNameLocation = "start";
		World.nameLocation = "first";
		setScreen(listScreens.get("first"));
	}
}
