package Reusable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by @Xyelos on 5/30/2016 in ${PACKAGE_NAME}.
 */
public class Pencil {
    // This class/object will contain methods that automatically draw things like..
    // Text boxes
    // Buttons
    // Quadrilaterals



    // Default Colors -- Goldenrod and Gold.
    public void drawTextBox(ShapeRenderer renderer, Vector2 whereAt, float length, float width, float borderWidth) {
        renderer.set(ShapeRenderer.ShapeType.Filled);
        // Outside of the box.
        renderer.setColor(Color.GOLDENROD);
        //renderer.hitbox(107, 165, 420, 120);
        renderer.rect(whereAt.x, whereAt.y, length, width);
        // / Draw in SALMON, should fill the text box with this color
        renderer.setColor(Color.GOLD);
        renderer.rect(whereAt.x + (.5f * borderWidth), whereAt.y + (.5f * borderWidth), length - borderWidth, width - borderWidth);
    }

    public void drawTextBox(ShapeRenderer renderer, Vector2 whereAt, float length,
                            float width, float borderWidth, Color borderColor, Color innerColor) {
        renderer.set(ShapeRenderer.ShapeType.Filled);
        // Outside of the box, and this is the outer color.
        renderer.setColor(borderColor);
        renderer.rect(whereAt.x, whereAt.y, length, width);
        // / Color two, this is the inner color, should fill the text box with this color
        renderer.setColor(innerColor);
        renderer.rect(whereAt.x + (.5f * borderWidth), whereAt.y + (.5f * borderWidth), length - borderWidth, width - borderWidth);
    }

    // p = first point, sp = second point, tp = third point, cp = fourth point
    public static void drawTrapezoid(ShapeRenderer renderer, Color color, Vector2 p, Vector2 sp, Vector2 tp, Vector2 fp){
        ShapeRenderer.ShapeType prevType = renderer.getCurrentType();
        Color prevColor = renderer.getColor();
        renderer.setColor(color);
        renderer.set(ShapeRenderer.ShapeType.Line); // set to shape type line
        renderer.line(p.x, p.y, sp.x, sp.y); // first point -> second point
        renderer.line(sp.x, sp.y, tp.x, tp.y); // second point -> third point
        renderer.line(tp.x, tp.y, fp.x, fp.y); // third point -> fourth point
        renderer.line(p.x, p.y, fp.x, fp.y); // first point -> fourth point
        renderer.set(prevType); // reset type
        renderer.setColor(prevColor); // reset color
    }



}
