package nieuzywane;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class AnimatedSprite extends Group
{
    private FrameSprite frameSprite;
 
    /**
     * Create sprite.
     *
     * @param texture
     *            The animation texture.
     * @param rows
     *            The animation texture rows.
     * @param cols
     *            The animation texture rows.
     * @param frameDuration
     *            The animation frame duration.
     */
    public AnimatedSprite(TextureRegion textureRegion, int rows, int cols, float frameDuration)
    {
        frameSprite = new FrameSprite(textureRegion, rows, cols, frameDuration, true);
 
        setWidth(frameSprite.getWidth());
        setWidth(frameSprite.getHeight());
 
        addActor(frameSprite);
    }
 
    @Override
    public Actor hit(float x, float y, boolean z)
    {
        return super.hit(x, y, z);
    }
}
