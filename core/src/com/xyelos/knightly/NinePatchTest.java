package com.xyelos.knightly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by @Xyelos on 8/22/2016 in com.xyelos.knightly.
 */
public class NinePatchTest implements Screen {
    NinePatch ninePatch;
    SpriteBatch batch;
    @Override
    public void show() {
        ninePatch = new NinePatch(new Texture("platform.png"), 17, 28, 17, 5);
        ninePatch = new NinePatch(new Texture("bouncePad.png"), 1, 1, 32, 0);
        ninePatch = new NinePatch(new Texture("bouncePad.png"), 17, 28, 17, 5);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear the screen
        batch.begin();
        ninePatch.draw(batch, 100, 100, 200, 25);
        batch.draw(new Sprite(new Texture("platform.png")), 400.0f, 100.0f, 200.0f, 25);

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
