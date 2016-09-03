package Reusable;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by @Xyelos on 9/2/2016 in Reusable.
 */
public class Sword extends Weapon {

    Rectangle currentHitbox, attackHitbox, idleHitbox; // if you ask
    Vector2 pos, size; // IF you ask..
    Integer damage; // ..then get out. The names are great.
    Sprite sprite; // TODO: MAKE DEFAULT SPRITE YOU LAZY LAZY HUMAN BEING

    /**
     * @param pos - the position
     * @param size - the size (y is length x is width)
     * @param damage - the damage caused on hit
     * @created idleHitbox - created with normal values, the idle sword
     * @created attackHitbox - y (length) is used where width would normally be, to make it logical/working */
    public Sword(Vector2 pos, Vector2 size, Integer damage) {
        this.pos = pos;
        this.size = size;
        this.damage = damage;

        idleHitbox = new Rectangle(pos.x, pos.y, size.x, size.y);
        attackHitbox = new Rectangle(pos.x, pos.y, size.y, size.x); // flip it so it points outward.
    }


    @Override
    public void attack() {

    }

    public void update(float delta, Batch batch) {
        render(batch);
    }

    public void render(Batch batch) {

    }


}
