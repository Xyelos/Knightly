package Reusable;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.xyelos.knightly.Platform;

import java.util.ArrayList;

/**
 * Created by @Xyelos on 8/7/2016 in Reusable.
 */
public interface Entity {
    // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ //
    void update(float delta, Batch batch, ArrayList<Platform> hitboxes); // every entity should get updated \\
    // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ //
    void render(Batch batch); // every entity should get drawn \\ // \\ // \\ // \\ // \\ // \\ // \\
    // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ // \\ //
}
