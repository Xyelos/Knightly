package com.xyelos.knightly;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import Reusable.BouncePad;
import Reusable.Player;

/**
 * Created by @Xyelos on 8/8/2016 in com.xyelos.knightly.
 */

public class gameTest implements Screen {
    Texture img;
    Camera camera;
    Viewport viewport;
    Game game;
    ArrayList<BouncePad> bouncePads;
    ArrayList<Platform> hitboxes; // should be hitbox;
    ShapeRenderer renderer;
    Batch batch;
    Player player;
    public gameTest(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        // create and update camera
        camera = new OrthographicCamera();
        viewport = new FitViewport(width, height);
        viewport.setCamera(camera);
        // array list of platforms and bouncePads, used to control where they are.
        hitboxes = new ArrayList<Platform>();
        bouncePads = new ArrayList<BouncePad>();
        renderer = new ShapeRenderer();
        batch = new SpriteBatch();
        player = new Player(new Vector2(0, 400));
        Gdx.input.setInputProcessor(player);
        // create platforms
        addPlatforms();
        addBouncePads();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.45f, .45f, .75f, 1); // r g b alpha
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        

        camera.update();
        // project your viewport or you are overrated.
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        batch.setProjectionMatrix(viewport.getCamera().combined);

        // render stuffs that need ShapeRenderer-rendered.
        renderer.setAutoShapeType(true);
        renderer.begin();
        renderer.set(ShapeRenderer.ShapeType.Filled);

        for (BouncePad bouncePad : bouncePads) {
            bouncePad.update(delta, renderer); // update also renders
        }

        for (Platform platform : hitboxes) {
            platform.update(delta, renderer); // read previous comment
        }
        renderer.end();
        batch.begin();

        for (BouncePad bouncePad : bouncePads) {
            bouncePad.update(delta, batch);
        }

        for (Platform platform : hitboxes) {
            platform.update(delta, batch);
        }

        player.update(delta, batch, hitboxes, bouncePads);

        batch.end();
    }



    private void addPlatforms() {
        float width = viewport.getWorldWidth(),
            height = viewport.getWorldHeight();

        hitboxes.add(new Platform(0, 0, width, 10)); // cover the bottom area, better NOT to fall off..
        hitboxes.add(new Platform(width * .25f, 150, width * .5f, 10));
        hitboxes.add(new Platform(width * .1f, 100, width * .1f, 10));
        hitboxes.add(new Platform(width - width * .2f, 100, width * .1f, 10));
        hitboxes.add(new Platform(width * .275f, 250, width * .1f, 10));
        hitboxes.add(new Platform(width * .625f, 250, width * .1f, 10));
    }

    private void addBouncePads() {
        float width = viewport.getWorldWidth(),
                height = viewport.getWorldHeight();

        bouncePads.add(new BouncePad(width * .375f, 250, width * .25f, 10));
    }

    @Override
    public void resize (int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        renderer.dispose();
        batch.dispose();
    }
}

