package com.lucky4all.andengine_mygame.ResourceManager;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.debug.Debug;

import com.lucky4all.andengine_mygame.GameActivity;

/**
 * @author Ruben Pla Ferrero
 * 
 */
public class ResourcesManager {

	public static final ResourcesManager INSTANCE = new ResourcesManager();

	public Engine engine;
	public GameActivity activity;
	public BoundCamera camera;
	public VertexBufferObjectManager vbom;

	public ITextureRegion splash_region;
	private BitmapTextureAtlas splashTextureAtlas;
	public ITextureRegion menu_background_region;

	public ITextureRegion play_region;
	public ITextureRegion options_region;
	private BuildableBitmapTextureAtlas menuTextureAtlas;

	public BuildableBitmapTextureAtlas gameTextureAtlas;
	public ITextureRegion platform1_region;
	public ITextureRegion platform2_region;
	public ITextureRegion platform3_region;
	public ITextureRegion coin_region;
	public ITiledTextureRegion player_region;

	public Font font;

	public ResourcesManager() {
	}

	public void loadMenuResources() {
		loadMenuGraphics();
		loadMenuAudio();
		loadMenuFonts();
	}

	private void loadMenuAudio() {

	}

	private void loadMenuGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
		menuTextureAtlas = new BuildableBitmapTextureAtlas(
				activity.getTextureManager(), 1024, 1024,
				TextureOptions.BILINEAR);
		menu_background_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(menuTextureAtlas, activity,
						"menu_background.png");
		play_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				menuTextureAtlas, activity, "play.png");
		options_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(menuTextureAtlas, activity, "options.png");

		try {
			this.menuTextureAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 1, 0));
			this.menuTextureAtlas.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e("Exception: " + e);
		}
	}

	public void loadMenuTextures() {
		menuTextureAtlas.load();
	}

	public void unloadMenuTextures() {
		menuTextureAtlas.unload();
	}

	public void loadMenuFonts() {
		FontFactory.setAssetBasePath("gfx/font/");
		final ITexture mainFontTexture = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		font = FontFactory.createStrokeFromAsset(activity.getFontManager(),
				mainFontTexture, activity.getAssets(), "font.ttf", 50, true,
				android.graphics.Color.WHITE, 2, android.graphics.Color.BLACK);
		font.load();

	}

	public void loadGameResources() {
		loadGameGraphics();
		loadGameFonts();
		loadGameAudio();
	}

	private void loadGameGraphics() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
		gameTextureAtlas = new BuildableBitmapTextureAtlas(
				activity.getTextureManager(), 1024, 1024,
				TextureOptions.BILINEAR);
		platform1_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(gameTextureAtlas, activity, "platform1.png");
		platform2_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(gameTextureAtlas, activity, "platform2.png");
		platform3_region = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(gameTextureAtlas, activity, "platform3.png");
		coin_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				gameTextureAtlas, activity, "coin.png");
		player_region = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(gameTextureAtlas, activity, "player.png",
						3, 1);

		try {
			this.gameTextureAtlas
					.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(
							0, 1, 0));
			this.gameTextureAtlas.load();
		} catch (final TextureAtlasBuilderException e) {
			Debug.e("Exception: " + e);
		}
	}

	private void loadGameFonts() {

	}

	private void loadGameAudio() {

	}

	public void unloadGameTextures() {

	}

	public void loadSplashScreen() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		splashTextureAtlas = new BitmapTextureAtlas(
				activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
		splash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				splashTextureAtlas, activity, "splash.png", 0, 0);
		splashTextureAtlas.load();
	}

	public void unloadSplashScreen() {
		splashTextureAtlas.unload();
		splash_region = null;
	}

	/**
	 * WE USE THIS METHOD AT THE BEGINNING OF GAME LOADING, TO PREPARE RESOURCES
	 * MANAGER PROPERLY WE CAN ACCES THEM FROM DIFFERENT SCENES.
	 */

	public static void prepareManager(Engine engine, GameActivity activity,
			BoundCamera camera, VertexBufferObjectManager vbom) {
		getInstance().engine = engine;
		getInstance().activity = activity;
		getInstance().camera = camera;
		getInstance().vbom = vbom;
	}

	public static ResourcesManager getInstance() {

		return INSTANCE;
	}

}
