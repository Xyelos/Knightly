package Reusable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by @Xyelos on 8/2/2016 in Reusable.
 */

// this utility type does not allow for custom sprites
public interface SpritelessUtility {

    void render(ShapeRenderer renderer, Color color, String label);

}
