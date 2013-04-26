package nieuzywane;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FrameSprite extends Actor
{
    private TextureRegion[] frames;
 
    private Animation animation;
 
    private TextureRegion currentFrame;
 
    private float stateTime;
    private boolean looping;
 
    public FrameSprite(TextureRegion texture, int rows, int cols, float frameDuration, boolean looping)
    {
        this.looping = looping;
 
        int tileWidth = texture.getRegionWidth() / cols;
        int tileHeight = texture.getRegionHeight() / rows;
        TextureRegion[][] tmp = texture.split(tileWidth, tileHeight);
        frames = new TextureRegion[cols * rows];
 
        int index = 0;
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                frames[index++] = tmp[i][j];
            }
        }
 
        setWidth(tileWidth);
        setHeight(tileHeight);
 
        animation = new Animation(frameDuration, frames);
        stateTime = 0f;
 
    }
 
    /**
     * Reset animation.
     *
     * You can use this to ensure the animation plays from the start again. It's
     * handy if you have one-shot animations like explosions but you are using
     * re-usable Sprites. You must reset the animation to ensure the animation
     * plays back again.
     */
    public void resetAnimation()
    {
        stateTime = 0;
    }
 
    /**
     * Check to see if animation finished.
     *
     * @param stateTime
     *
     * @return True if finished.
     */
    public boolean isAnimationFinished()
    {
        return animation.isAnimationFinished(stateTime);
    }
 
}