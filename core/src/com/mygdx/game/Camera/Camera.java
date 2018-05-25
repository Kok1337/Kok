package com.mygdx.game.Camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Player.Player;

public class Camera implements GestureDetector.GestureListener
{
    public int worldWidth;
    public int worldHeight;

    public int normalCameraHeight  = Gdx.graphics.getHeight();
    public int normalCameraWidth = Gdx.graphics.getWidth();
    public int minimalCameraHeight = Gdx.graphics.getHeight()/2;
    public int minimalCameraWidth = Gdx.graphics.getWidth()/2;
    public float coefficientX;
    public float coefficientY;
    public OrthographicCamera mainCamera;

    public Camera(int width, int height, int worldWidth, int worldHeight)
    {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.normalCameraWidth = width;
        this.normalCameraHeight = height;
        mainCamera = new OrthographicCamera(normalCameraWidth, normalCameraHeight);
    }

    public void render(SpriteBatch batch)
    {
        coefficientX = normalCameraWidth/mainCamera.viewportWidth;
        coefficientY = normalCameraHeight/mainCamera.viewportHeight;
        mainCamera.update();
        batch.setProjectionMatrix(mainCamera.combined);
    }

    public void positionUpdate(Player player)
    {
        mainCamera.position.set(getPositionX(player), getPositionY(player), 0);
    }

    public int getPositionX(Player player)
    {
        if(player.position.x > mainCamera.viewportWidth / 2 && player.position.x < worldWidth - mainCamera.viewportWidth / 2)
            return player.position.x;
        else if(player.position.x < worldWidth / 2)
            return (int)mainCamera.viewportWidth / 2;
        else
            return worldWidth - (int)mainCamera.viewportWidth / 2;
    }

    public int getPositionY(Player player)
    {
        if(player.position.y > mainCamera.viewportHeight / 2 && player.position.y < worldHeight - mainCamera.viewportHeight / 2)
            return player.position.y;
        else if(player.position.y < worldHeight / 2)
            return (int)mainCamera.viewportHeight / 2;
        else
            return worldHeight - (int)mainCamera.viewportHeight / 2;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        if (initialDistance - distance > 0 && mainCamera.viewportHeight < worldHeight && mainCamera.viewportWidth < worldWidth){
            Gdx.app.log("zoom", mainCamera.viewportHeight +" "+ mainCamera.viewportWidth);
            mainCamera.viewportWidth += mainCamera.viewportWidth * 0.01;
            mainCamera.viewportHeight += mainCamera.viewportHeight * 0.01;
        } else if(mainCamera.viewportHeight-mainCamera.viewportHeight*0.01 >= minimalCameraHeight){
            mainCamera.viewportWidth -= mainCamera.viewportWidth * 0.01;
            mainCamera.viewportHeight -= mainCamera.viewportHeight * 0.01;
        }
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}

