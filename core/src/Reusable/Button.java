package Reusable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by @Xyelos on 8/2/2016 in Reusable.
 */
public class Button implements Utility, SpritelessUtility {

        Texture text;
        Vector2 position;
        float height, width;
        Rectangle buttonRect;

        public Button(Vector2 position, float height, float width, Texture text) {
            this.position = position;
            this.height = height;
            this.width = width;
            buttonRect = new Rectangle(position.x, position.y, width, height);
            this.text = text;
        }

        public Button (Vector2 position, float height, float width) {
            this.position = position;
            this.height = height;
            this.width = width;
            buttonRect = new Rectangle(position.x, position.y, width, height);
        }


        public void render(ShapeRenderer renderer, Color color, String label) {
            renderer.set(ShapeRenderer.ShapeType.Filled);
            renderer.setColor(color);
            renderer.rect(buttonRect.x, buttonRect.y, buttonRect.width, buttonRect.height);
        }

        public void render(SpriteBatch batch) {
            batch.draw(text, buttonRect.getX(), buttonRect.getY());
        }

        public boolean isPressed() {
            if (Gdx.input.isKeyPressed(Input.Buttons.LEFT)) {
                if (Gdx.input.getX() < buttonRect.x + this.width && //
                        Gdx.input.getX() > buttonRect.x && //
                        Gdx.input.getY() > buttonRect.y - this.height && //
                        Gdx.input.getY() < buttonRect.y) { //
                    return true;
                }
            }
            return false;
        }

}

