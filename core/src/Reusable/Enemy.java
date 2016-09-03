package Reusable;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.xyelos.knightly.Platform;

import java.util.ArrayList;

/**
 * Created by @Xyelos on 8/9/2016 in Reusable.
 */
public class Enemy implements Entity {

    public Vector2 pos; // where is it?

    // all of these are generated by extensions to Enemy.
    public Sprite sprite; // what is the sprite?
    public Rectangle hitbox;
    public int health; // how much health?
    public int damage; // how much damage dealt? (flat damage instead of scaling due to laziness.)
    public int speed;

    @Override
    public void update(float delta, Batch batch, ArrayList<Platform> hitboxes) {

    }

    @Override
    public void render(Batch batch) {

    }

    public void hit(Player player) {
        this.health -= player.getDamage();
    }

    // Getters
    public Rectangle getHitbox() { return hitbox;}
    public Sprite getSprite() { return sprite; }
    public int getHealth() { return health; }
    public int getDamage() { return damage; }
    public Vector2 getPos() { return pos; }
    public int speed() { return speed; }
    // the setters
    public void setPos(Vector2 pos) { this.pos = pos; }
    public void setSprite (Sprite newSprite) { this.sprite = newSprite; } // this may be redundant.
}