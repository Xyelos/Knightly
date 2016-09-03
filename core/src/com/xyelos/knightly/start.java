package com.xyelos.knightly;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class start extends Game {

    // this class sets the game state (screen) to the menu


	@Override
	public void create () {

        this.setScreen(new menu(this)); // set the screen to menu.

	}
}
