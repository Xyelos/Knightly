package Reusable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by @Xyelos on 8/21/2016 in Reusable.
 */
public class BouncePad {
    private Rectangle hitbox;
    private Float frictionCoef; // ensure infinite bouncing isn't a thing
    private Vector2 pos,
            size;
    private Sprite sprite = new Sprite(new Texture(Gdx.files.internal("bouncePad.png"))); // gotta make a default for this
    private NinePatch ninePatch = new NinePatch(sprite, 17, 28, 17, 5);

    public BouncePad(float x, float y, float width, float height) {
        this.pos = new Vector2(x, y);
        this.size = new Vector2(width, height);
        frictionCoef = .9f; // .9f is a default value

        this.hitbox = new Rectangle(pos.x, pos.y, size.x, size.y);
    }

    public BouncePad(float x, float y, float width, float height, float frictionCoef) {
        this.pos = new Vector2(x, y);
        this.size = new Vector2(width, height);
        this.frictionCoef = frictionCoef;
        this.hitbox = new Rectangle(pos.x, pos.y, size.x, size.y);
    }

    public BouncePad(float x, float y, float width, float height, NinePatch patch) {
        this.pos = new Vector2(x, y);
        this.size = new Vector2(width, height);
        frictionCoef = .9f; // .9f is a default value
        ninePatch = patch;
        this.hitbox = new Rectangle(pos.x, pos.y, size.x, size.y);
    }

    public BouncePad(float x, float y, float width, float height, float frictionCoef, NinePatch patch) {
        this.pos = new Vector2(x, y);
        this.size = new Vector2(width, height);
        this.frictionCoef = frictionCoef; // fine-tuned bouncing lol
        ninePatch = patch;
        this.hitbox = new Rectangle(pos.x, pos.y, size.x, size.y);
    }

    // update methods
    public void update(float delta, ShapeRenderer renderer) {
        render(renderer);
    }
    public void update(float delta, Batch batch) {
        render(batch);
    }
    // render methods
    public void render(ShapeRenderer renderer) {
        renderer.setColor(Color.OLIVE);
        renderer.set(ShapeRenderer.ShapeType.Filled);
        renderer.rect(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight());
    }
    public void render(Batch batch) {
        ninePatch.draw(batch, hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight()); // I used the wrong sprite for the wrong class and all, but meh.

    }


    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public Float getFrictionCoef() {
        return frictionCoef;
    }

    public void setFrictionCoef(Float frictionCoef) {
        this.frictionCoef = frictionCoef;
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

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public NinePatch getNinePatch() {
        return ninePatch;
    }

    public void setNinePatch(NinePatch ninePatch) {
        this.ninePatch = ninePatch;
    }
}
