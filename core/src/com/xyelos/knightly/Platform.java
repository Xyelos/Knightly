package com.xyelos.knightly;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


/**
 * Created by @Xyelos on 8/19/2016 in com.xyelos.knightly.
 */
public class Platform {
    Rectangle hitbox;

    NinePatch ninePatch = new NinePatch(new Texture("platform.png"), 17, 28, 17, 5);
    Vector2 pos, size;

    public Platform(float x, float y, float width, float height) {
        pos = new Vector2(x, y);
        size = new Vector2(width, height);

        hitbox = new Rectangle(pos.x, pos.y, size.x, size.y);

    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.WHITE);
        renderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height); // accurate representation of reality
    }

    public void render(Batch batch) {
        ninePatch.draw(batch, pos.x, pos.y, size.x, size.y);
    }

    public void update(float delta, ShapeRenderer renderer) {
        render(renderer);
    }

    public void update(float delta, Batch batch) {
        render(batch);
    }


    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public NinePatch getNinePatch() {
        return ninePatch;
    }

    public void setNinePatch(NinePatch ninePatch) {
        this.ninePatch = ninePatch;
    }

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
}
