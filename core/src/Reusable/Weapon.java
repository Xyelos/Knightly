package Reusable;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by @Xyelos on 8/28/2016 in Reusable.
 */
public abstract class Weapon {
    // this is weapon
    // it should be able to attack
    // and it should be equipable.
    // otherwise, why would you call it a weapon?


    Vector2 pos, size;
    Rectangle hitbox,
    idleHitbox,
    attackHitbox;
    Entity wielder; // where is my hitbox? Who causes me to attack?


    public void dispose() {}

    // do your attacking lol -- also, called by sword during update call (which is called by entity)
    public abstract void attack();
    // should get called by entity
    public abstract void update(float delta, Batch batch);
    public abstract void render(Batch batch);

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
}
