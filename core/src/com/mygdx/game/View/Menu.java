package com.mygdx.game.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.mygdx.game.Button;
import com.mygdx.game.Start;

import java.util.ArrayList;

public class Menu implements Screen {

    Start st;
    SpriteBatch batch = new SpriteBatch();
    public Menu(Start st)
    {
        this.st = st;
    }

    ArrayList<Button> buttons;
    Texture texture = new Texture("menu.png");

    @Override
    public void show()
    {
        buttons = new ArrayList<Button>();
        buttons.add(new Button(700, 400, new Texture("ng.png")) {
            @Override
            public void run()
            {
                st.setScreen(st.listScreens.get("first"));
            }
        });
        buttons.add(new Button(700, 340, new Texture("zagr.png")) {
            @Override
            public void run()
            {

            }
        });
        buttons.add(new Button(700, 280, new Texture("setting.png"))
        {
            @Override
            public void run() {

            }
        });
        buttons.add(new Button(700, 220, new Texture("exit.png"))
        {
            @Override
            public void run() {

            }
        });
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //рендер локации

        batch.begin();
        batch.draw(texture, 0, 0);
        for (Button button : buttons)
        {
            button.render(batch);
        }

        if (Gdx.input.justTouched())
        {
            for (Button button : buttons)
            {
                if (button.isClick(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()))
                    button.run();
                break;
            }
        }

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
