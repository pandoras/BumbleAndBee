package screens;

import com.badlogic.gdx.Gdx;

/**
 * It defines a virtual area where the game stuff is contained 
 * and provides a way to get the real width and height 
 * to use with a camera in order to always show the virtual area.
 * @author 
 *
 */
public class WirtualnyEkran {

	float virtualWidth;
	float virtualHeight;

	public float getVirtualWidth() {
		return virtualWidth;
	}

	public float getVirtualHeight() {
		return virtualHeight;
	}

	public WirtualnyEkran(float virtualWidth, float virtualHeight) {
		this(virtualWidth, virtualHeight, false);
	}

	public WirtualnyEkran(float virtualWidth, float virtualHeight, boolean shrink) {
		this.virtualWidth = virtualWidth;
		this.virtualHeight = virtualHeight;
	}

	public float getWidth() {
		return getWidth(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	public float getHeight() {
		return getHeight(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	/**
	 * Returns the view port width to let all the virtual view port to be shown on the screen.
	 * 
	 * @param screenWidth
	 *            The screen width.
	 * @param screenHeight
	 *            The screen Height.
	 */
	public float getWidth(float screenWidth, float screenHeight) {
		float virtualAspect = virtualWidth / virtualHeight;
		float aspect = screenWidth / screenHeight;
		if (aspect > virtualAspect || (Math.abs(aspect - virtualAspect) < 0.01f)) {
			return virtualHeight * aspect;
		} else {
			return virtualWidth;
		}
	}

	/**
	 * Returns the view port height to let all the virtual view port to be shown on the screen.
	 * 
	 * @param screenWidth
	 *            The screen width.
	 * @param screenHeight
	 *            The screen Height.
	 */
	public float getHeight(float screenWidth, float screenHeight) {
		float virtualAspect = virtualWidth / virtualHeight;
		float aspect = screenWidth / screenHeight;
		if (aspect > virtualAspect || (Math.abs(aspect - virtualAspect) < 0.01f)) {
			return virtualHeight;
		} else {
			return virtualWidth / aspect;
		}
	}

}
