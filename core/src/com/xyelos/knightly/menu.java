package com.xyelos.knightly;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import Reusable.Pencil;
import Reusable.Player;

/**
 * Created by @Xyelos on 8/1/2016 in com.xyelos.knightly.
 */

// This should display the main menu of 'knightly'

public class menu implements Screen {
    Skin skin;
    Stage stage;
    ShapeRenderer renderer;
    Batch batch;
    Game game;
    TextButton newGameButton, LoadGameButton; // the load button may be redundant, but meh.
    TextButton.TextButtonStyle defaultStyle;
    Texture title;

    Sprite cloud1 = new Sprite(new Texture(Gdx.files.internal("Clouds\\clouds1.png"))),  // AL
        cloud2 = new Sprite(new Texture(Gdx.files.internal("Clouds\\clouds2.png"))),  // THE
        cloud3 = new Sprite(new Texture(Gdx.files.internal("Clouds\\clouds3.png"))),  // CLOUDS
        cloud4 = new Sprite(new Texture(Gdx.files.internal("Clouds\\clouds0.png")));  // !!!!!



    Boolean startNewGame = false;
    BitmapFont font;


    public menu(Game game) {
        this.game = game; // allows switching between screens
    }

    @Override
    public void show() {

        font = new BitmapFont();
        skin = new Skin();

        renderer = new ShapeRenderer(); // for basic shapes
        batch = new SpriteBatch(); // for sprites/images

        // create a Stage (stage) and set it as the input processor
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);
        // project the batch
        batch.setProjectionMatrix(stage.getViewport().getCamera().combined);
        renderer.setProjectionMatrix(stage.getViewport().getCamera().combined);

        addPixmapsToSkin(); // this method creats pixmaps (for the buttons) and adds them to Skin

        // create the default TextButtonStyle
        defaultStyle = new TextButton.TextButtonStyle();
        defaultStyle.font = font;
        defaultStyle.up = skin.newDrawable("lightGray", Color.DARK_GRAY);
        defaultStyle.down = skin.newDrawable("darkGray", Color.DARK_GRAY);
        defaultStyle.checked = skin.newDrawable("lightGray", Color.DARK_GRAY);
        defaultStyle.over = skin.newDrawable("gray", Color.DARK_GRAY);
        // add the default style to skin as "default".
        skin.add("default", defaultStyle);

        // add the new g
        newGameButton = new TextButton("New Game", defaultStyle); // create the newgamebutton
        newGameButton.addListener(new ChangeListener() { // give the newgamebutton a listener
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new gameTest(game));
            }
        });
        newGameButton.setPosition(250 + 5, 100); // set the position
        newGameButton.setSize(165, 75); // set the size
        newGameButton.setSkin(new Skin()); // set the skin
        stage.addActor(newGameButton); // add it to the stage

        // add the load game button
        LoadGameButton = new TextButton("Load Game", defaultStyle); // create it   \\
        LoadGameButton.addListener(new ChangeListener() { // give it a listener
            public void changed(ChangeEvent event, Actor actor) {
                LoadGameButton.setText("Loading Game");
                Gdx.app.log("Joking", "I'm not really starting a new game trololol"); // trololololol
            }
        });
        LoadGameButton.setPosition(250, 200); // set the position
        LoadGameButton.setSize(175, 75); // set the size
        LoadGameButton.setSkin(new Skin()); // set the skin
        stage.addActor(LoadGameButton); // add it to stage

        title = new Texture(Gdx.files.internal("Knightly-Background2.png"));

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(103 / 255f, 219 / 255f, 215 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // clear the screen

        batch.begin();
        drawClouds(batch, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();

        batch.begin();
        batch.draw(title, 170, 300, 360, 150);  // 300 / 125 = 2.4
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
        skin.dispose();
        batch.dispose();
        renderer.dispose();
    }

    public void addPixmapsToSkin() {
        // Generate a 1x1 gray texture and store it in the skin named "white".
        Pixmap pixmap = new Pixmap(500, 500, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.LIGHT_GRAY);
        pixmap.fill();

        skin.add("lightGray", new Texture(pixmap));

        Pixmap pix = new Pixmap(500, 500, Pixmap.Format.RGBA8888);
        pix.setColor(Color.DARK_GRAY);
        pix.fill();

        skin.add("darkGray", new Texture(pix));

        Pixmap map = new Pixmap(500, 500, Pixmap.Format.RGBA8888);
        map.setColor(Color.GRAY);
        map.fill();

        skin.add("gray", new Texture(map));
        // create font, add to skin.
        font = new BitmapFont();
        font.setColor(Color.WHITE);

        skin.add("white", font);
    }

    public void drawClouds(Batch batch, float height, float width) {
        // draw clouds in appropriate places.
        batch.draw(cloud1, width * .2f, height * .25f);
        batch.draw(cloud2, width * .125f, height * .55f);
        batch.draw(cloud4, width * .9f, height * .133f);
        batch.draw(cloud3, width * .342f, height * .9f);
        batch.draw(cloud1, width * .5f, height * .05f);
        batch.draw(cloud4, width * .953f, height * .39f);
    }



}
