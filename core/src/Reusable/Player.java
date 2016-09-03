package Reusable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import com.xyelos.knightly.Constants;
import com.xyelos.knightly.Platform;

import java.util.ArrayList;

/**
 * Created by @Xyelos on 8/7/2016 in Reusable.
 */

public class Player implements Entity, InputProcessor {
    public Vector2 pos,
            velocity = new Vector2(Gdx.graphics.getWidth() * .45f, 10),
            size = new Vector2((Gdx.graphics.getWidth() * .1f), Gdx.graphics.getHeight() * .1f),
            speed = new Vector2(Gdx.graphics.getWidth() * 2f, Gdx.graphics.getHeight() * .2f);
    Intersector intersector;
    boolean isFalling = false; // isFalling -> Am I falling?
    int damage;
    Rectangle hitbox,
            feetHitbox,
            headHitbox,
            leftSideHitbox,
            rightSideHitbox, // hitbox for the head
            swordHitbox; // hitbox for the sword, used when attacking
    float Delta, elapsedTime;
    // all my sprites, works with the State enum. :D
    Sprite standing = new Sprite(new Texture(Gdx.files.internal("standing.png"), true)),
            attackRight = new Sprite(new Texture(Gdx.files.internal("attackRight.png"), true)),
            walkRight = new Sprite(new Texture(Gdx.files.internal("walk 1.png"), true)),
            walkRight2 = new Sprite(new Texture(Gdx.files.internal("walk 2.png"), true)),
            attackLeft = new Sprite(new Texture(Gdx.files.internal("attackRight.png"), true)),
            walkLeft = new Sprite(new Texture(Gdx.files.internal("walk 1.png"), true)),
            walkLeft2 = new Sprite(new Texture(Gdx.files.internal("walk 2.png"), true)),
            standingLeft = new Sprite(standing.getTexture()); // will be flipped
    Animation walkingRight;
    Animation walkingLeft;
    Constants constant = new Constants();
    // flip attackLeft/walkLeft/walkLeft2. (!!!MAXIMUM LAZINESS!!!)
    public enum State {
        WALKINGRIGHT, // if you're walkingRight
        IDLERIGHT, // if you'ren't walkingRight
        ATTACKINGRIGHT,
        ATTACKINGLEFT,
        WALKINGLEFT,// if you're attacking
        IDLELEFT
    }

    State state, prevState; // if you don't know what this is, get out.


    public void initAnimations() {
        attackLeft.flip(true, false);
        walkLeft.flip(true, false);
        walkLeft2.flip(true, false);
        standingLeft.flip(true, false);
        elapsedTime = 0;
        walkingRight = new Animation(.2f, walkRight, walkRight2);
        walkingRight.setPlayMode(Animation.PlayMode.LOOP);
        walkingLeft = new Animation(.2f, walkLeft, walkLeft2);
        walkingLeft.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void initHitboxes(Vector2 pos) {
        hitbox = new Rectangle(pos.x, pos.y, size.x * .5f, size.y);
        feetHitbox = new Rectangle(pos.x, pos.y, size.x * .5f, size.y * .01f);
        headHitbox = new Rectangle(pos.x, pos.y + size.y, size.x * .5f, size.y * .01f);
        leftSideHitbox = new Rectangle(pos.x, pos.y+1, size.x * .01f, size.y-1);
        rightSideHitbox = new Rectangle(pos.x + size.x, pos.y+1, size.x * .01f, size.y-1);
    }

    public Player(Vector2 Position) {
        // init/create all animations and player-related art
        initAnimations();

        pos = Position;
        intersector = new Intersector();
        // init/create all hitboxes
        initHitboxes(pos);
    }

    public void bouncing (ArrayList<BouncePad> bouncePads) {
        for (BouncePad bouncePad : bouncePads) {
            if (intersector.overlaps(bouncePad.getHitbox(), feetHitbox)) {
                if (velocity.y < -.1f) {
                    isFalling = false; // makes it TECHNICALLY possible to jump on bounce pad hit, but makes it REALLY hard.
                    velocity.y *= -1 * bouncePad.getFrictionCoef(); // get the BP friction
                    isFalling = true; // this interval is very short.
                } else {
                    isFalling = false;
                    feetHitbox.y = hitbox.y = pos.y = bouncePad.getHitbox().y + bouncePad.getSize().y -1;
                }
            } else if (intersector.overlaps(bouncePad.getHitbox(), headHitbox)) {
                if(velocity.y > 0)
                    velocity.y *= -1;
            } else if (intersector.overlaps(bouncePad.getHitbox(), rightSideHitbox)) {
                pos.x = bouncePad.getPos().x - hitbox.width - 1;
            } else if (intersector.overlaps(bouncePad.getHitbox(), leftSideHitbox)) {
                pos.x = bouncePad.getPos().x + bouncePad.getHitbox().width + 1;
            }
        }
    }

    public boolean isFalling (ArrayList<Platform> hitboxes) {
            for (Platform plat : hitboxes) {
                if(intersector.overlaps(plat.getHitbox(), feetHitbox)) {
                    if(velocity.y <= 0)
                        feetHitbox.y = hitbox.y = pos.y = plat.getHitbox().y + plat.getSize().y-1;
                    return false;
                }
            }
        return true;
    }


    @Override
    public void update(float delta, Batch batch, ArrayList<Platform> hitboxes) {
        isFalling = isFalling(hitboxes);

        Delta = delta;
        elapsedTime = elapsedTime + delta;

        prevState = state;
        moveOrAttack(delta);
        Gdx.app.log("IS FALLING", String.valueOf(isFalling));
        if (isFalling && velocity.y > -15)
            velocity.y -= 7.5f * delta;
        else if (!isFalling && velocity.y < 0)
            velocity.y -= velocity.y; // set it to zero by subtracting it from self
        Gdx.app.log("IS FALLING", String.valueOf(isFalling));

        pos.y += velocity.y;

        // move hitboxes with me
        updateHitboxes();
        Gdx.app.log("Velocity Y", "" + velocity.y);
        render(batch);
    }

    public void update(float delta, Batch batch, ArrayList<Platform> hitboxes, ArrayList<BouncePad> bouncePads) {
        isFalling = isFalling(hitboxes);
        bouncing(bouncePads);
        prevState = state;
        Delta = delta;
        elapsedTime = elapsedTime + delta;
        moveOrAttack(delta);

        Gdx.app.log("IS FALLING", String.valueOf(isFalling));
        if (isFalling && velocity.y > -15)
            velocity.y -= 7.5f * delta;
        else if (!isFalling && velocity.y < 0)
            velocity.y -= velocity.y; // set it to zero;
        Gdx.app.log("IS FALLING", String.valueOf(isFalling));

        pos.y += velocity.y;

        updateHitboxes();
        Gdx.app.log("Velocity Y", "" + velocity.y); // no y velocity
        render(batch); // negative y velocity?
    }

    @Override
    public void render(Batch batch) {
        if (state == state.WALKINGRIGHT) {
            Gdx.app.log("Player Status", "Walking : Position (" + pos.x + ", " + pos.y + ")");
            // draw walkingRight ANIMATION! WOOT WOOT
            batch.draw(walkingRight.getKeyFrame(elapsedTime, true), pos.x, pos.y, size.x, size.y);
        } else if (state == state.ATTACKINGRIGHT) {
            Gdx.app.log("Player Status", "Attacking");
            // draw attacking sprite
            batch.draw(attackRight, pos.x, pos.y, size.x, size.y);
        } else if (state == state.IDLERIGHT) {
            Gdx.app.log("Player Status", "Idle");
            // draw idle sprite
            batch.draw(standing, pos.x, pos.y, size.x, size.y);
        } else if (state == state.WALKINGLEFT) {
            Gdx.app.log("Player Status", "Walking : Position (" + pos.x + ", " + pos.y + ")");
            // draw left animation keyframe
            // I subtract the size from the position to fix drawing issues. Flipping actually mirrors the image, so meh.
            batch.draw(walkingLeft.getKeyFrame(elapsedTime, true), pos.x - size.x * .5f, pos.y, size.x, size.y);
        } else if (state == State.ATTACKINGLEFT) {
            Gdx.app.log("Player Status", "Attacking-left");
            batch.draw(attackLeft, pos.x - size.x * .5f, pos.y, size.x, size.y);
        } else if (state == State.IDLELEFT) {
            batch.draw(standingLeft, pos.x - size.x * .5f, pos.y, size.x, size.y);
        }
        // DEBUGGING //
        batch.end();
        debugRender(constant.getDebugging(), batch);
        batch.begin();
        // END DEBUGGING //
        Gdx.app.log("Position: ", pos.x + ", " + pos.y);
        Gdx.app.log("Velocity: ", velocity.x + ", " + velocity.y);

    }

    public void debugRender(boolean render, Batch batch) {
        if(render) {
            ShapeRenderer renderer = new ShapeRenderer();

            renderer.setProjectionMatrix(batch.getProjectionMatrix());
            renderer.setColor(Color.ROYAL);
            renderer.begin(ShapeRenderer.ShapeType.Line);
            renderer.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height); // normal
            renderer.setColor(Color.GOLDENROD); // COLOUR GET: GOLDRENROD
            renderer.rect(feetHitbox.x, feetHitbox.y, feetHitbox.width, feetHitbox.height); // feet
            renderer.rect(headHitbox.x, headHitbox.y, headHitbox.width, headHitbox.height); // head
            renderer.rect(rightSideHitbox.x, rightSideHitbox.y, rightSideHitbox.width, rightSideHitbox.height);
            renderer.rect(leftSideHitbox.x, leftSideHitbox.y, leftSideHitbox.width, leftSideHitbox.height);

            renderer.end();
        }
    }

    public void moveOrAttack(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            // it should spawn an attackRight hitbox for a second or two.
            //state = state.ATTACKINGRIGHT; // you're attacking
            if (prevState == State.WALKINGLEFT || prevState == State.ATTACKINGLEFT || prevState == State.IDLELEFT)
                state = State.ATTACKINGLEFT;
             else
                state = State.ATTACKINGRIGHT;

        } else  if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            pos.x -= velocity.x * delta; // move left
            state = state.WALKINGLEFT; // you're walkingRight
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            pos.x += velocity.x * delta; // move right
            state = state.WALKINGRIGHT; // you're walkingRight
        } else {
            if (prevState == State.WALKINGLEFT || prevState == State.ATTACKINGLEFT || prevState == State.IDLELEFT)
                state = State.IDLELEFT;
            else
                state = State.IDLERIGHT;


        }
    }

    public void updateHitboxes() {
        hitbox.x = pos.x;
        hitbox.y = pos.y;
        feetHitbox.x = pos.x;
        feetHitbox.y = pos.y;
        headHitbox.x = pos.x;
        headHitbox.y = pos.y + size.y;
        rightSideHitbox.x = pos.x + size.x * .5f;
        rightSideHitbox.y = pos.y;
        leftSideHitbox.x = pos.x;
        leftSideHitbox.y = pos.y;
    }

    public int getDamage () { return damage; } // return the damage value

    public Vector2 getPosition() {
        return this.pos;
    }

    public Rectangle getHitbox() {
        return this.hitbox;
    }

    @Override
    public boolean keyDown(int keycode) {
        if ((Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) && !isFalling) {
            velocity.y += 5; // once and only once //
            isFalling = true;
        }
        return false;
    }

    @Override
    public boolean keyUp (int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped (char character) {
        return false;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved (int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled (int amount) {
        return false;
    }
}