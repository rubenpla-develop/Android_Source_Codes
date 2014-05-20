package com.lucky4all.andengine_hanoi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

public class MainActivity extends SimpleBaseGameActivity {

	private static int CAMERA_WIDTH = 800;
	private static int CAMERA_HEIGHT = 480;
	private ITextureRegion mBackgroundTextureRegion, mTowerTextureRegion,
			mRing1, mRing2, mRing3;
	private Stack mStack1, mStack2, mStack3;
	Sprite mTower1, mTower2, mTower3;

	@Override
	public EngineOptions onCreateEngineOptions() {
		final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
				new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
	}

	@Override
	protected void onCreateResources() {
		try {
			ITexture backgroundTexture = new BitmapTexture(
					this.getTextureManager(), new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {

							return getAssets().open("gfx/background.png");
						}

					});

			ITexture towerTexture = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {

							return getAssets().open("gfx/tower.png");
						}

					});

			ITexture ring1 = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {

							return getAssets().open("gfx/ring1.png");
						}

					});

			ITexture ring2 = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {

							return getAssets().open("gfx/ring2.png");
						}

					});

			ITexture ring3 = new BitmapTexture(this.getTextureManager(),
					new IInputStreamOpener() {

						@Override
						public InputStream open() throws IOException {

							return getAssets().open("gfx/ring3.png");
						}

					});

			// load resources into VRAM
			backgroundTexture.load();
			towerTexture.load();
			ring1.load();
			ring2.load();
			ring3.load();

			// Set up texture region
			mBackgroundTextureRegion = TextureRegionFactory
					.extractFromTexture(backgroundTexture);
			mTowerTextureRegion = TextureRegionFactory
					.extractFromTexture(towerTexture);
			mRing1 = TextureRegionFactory.extractFromTexture(ring1);
			mRing2 = TextureRegionFactory.extractFromTexture(ring2);
			mRing3 = TextureRegionFactory.extractFromTexture(ring3);

		} catch (Exception e) {
			Debug.e("Exception: " + e);
		}

	}

	@Override
	protected Scene onCreateScene() {
		// Create the new scene
		final Scene scene = new Scene();
		Sprite backgroundSprite = new Sprite(0, 0, mBackgroundTextureRegion,
				this.getVertexBufferObjectManager());
		scene.attachChild(backgroundSprite);

		// add the towers

		mTower1 = new Sprite(192, 63, this.mTowerTextureRegion,
				getVertexBufferObjectManager());
		mTower2 = new Sprite(400, 63, this.mTowerTextureRegion,
				getVertexBufferObjectManager());
		mTower3 = new Sprite(604, 63, this.mTowerTextureRegion,
				getVertexBufferObjectManager());
		scene.attachChild(mTower1);
		scene.attachChild(mTower2);
		scene.attachChild(mTower3);

		Ring ring1 = new Ring(1, 139, 174, this.mRing1,
				getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (((Ring) this.getmStack().peek()).getmWeight() != this
						.getmWeight())
					return false;

				this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2,
						pSceneTouchEvent.getY() - this.getHeight() / 2);
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					checkForCollisionWithTowers(this);
				}

				return true;
			}
		};
		Ring ring2 = new Ring(2, 118, 212, this.mRing2,
				getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (((Ring) this.getmStack().peek()).getmWeight() != this
						.getmWeight())
					return false;

				this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2,
						pSceneTouchEvent.getY() - this.getHeight() / 2);
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					checkForCollisionWithTowers(this);
				}
				return true;
			}

		};
		Ring ring3 = new Ring(3, 97, 255, this.mRing3,
				getVertexBufferObjectManager()) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				if (((Ring) this.getmStack().peek()).getmWeight() != this
						.getmWeight())
					return false;

				this.setPosition(pSceneTouchEvent.getX() - this.getWidth() / 2,
						pSceneTouchEvent.getY() - this.getHeight() / 2);
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_UP) {
					checkForCollisionWithTowers(this);
				}
				return true;
			}
		};
		scene.attachChild(ring1);
		scene.attachChild(ring2);
		scene.attachChild(ring3);

		// create the stacks
		mStack1 = new Stack();
		mStack2 = new Stack();
		mStack3 = new Stack();

		// add all rings to stack1
		mStack1.add(ring3);
		mStack1.add(ring2);
		mStack1.add(ring1);

		// initialize starting pos for each ring
		ring1.setmStack(mStack1);
		ring2.setmStack(mStack1);
		ring3.setmStack(mStack1);
		ring1.setmTower(mTower1);
		ring2.setmTower(mTower1);
		ring3.setmTower(mTower1);

		// add touch handlers
		scene.registerTouchArea(ring1);
		scene.registerTouchArea(ring2);
		scene.registerTouchArea(ring3);
		scene.setTouchAreaBindingOnActionDownEnabled(true);

		return scene;
	}

	private void checkForCollisionWithTowers(Ring ring) {
		Stack stack = null;
		Sprite tower = null;

		if (ring.collidesWith(mTower1)
				&& (mStack1.size() == 0 || ring.getmWeight() < ((Ring) mStack1
						.peek()).getmWeight())) {
			stack = mStack1;
			tower = mTower1;
		} else if (ring.collidesWith(mTower2)
				&& (mStack2.size() == 0 || ring.getmWeight() < ((Ring) mStack2
						.peek()).getmWeight())) {
			stack = mStack2;
			tower = mTower2;
		} else if (ring.collidesWith(mTower3)
				&& (mStack3.size() == 0 || ring.getmWeight() < ((Ring) mStack3
						.peek()).getmWeight())) {
			stack = mStack3;
			tower = mTower3;
		} else {
			stack = ring.getmStack();
			tower = ring.getmTower();
		}

		ring.getmStack().remove(ring);
		if (stack != null && tower != null && stack.size() == 0) {
			ring.setPosition(
					tower.getX() + tower.getWidth() / 2 - ring.getWidth() / 2,
					tower.getY() + tower.getHeight() - ring.getHeight());
		} else if (stack != null && tower != null && stack.size() > 0) {
			ring.setPosition(
					tower.getX() + tower.getWidth() / 2 - ring.getWidth() / 2,
					((Ring) stack.peek()).getY() - ring.getHeight());
		}
		stack.add(ring);
		ring.setmStack(stack);
		ring.setmTower(tower);

	}

	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.activity_main);
	//
	// // if (savedInstanceState == null) {
	// // getFragmentManager().beginTransaction()
	// // .add(R.id.container, new PlaceholderFragment()).commit();
	// // }
	// }
	//
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	//
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }
	//
	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // Handle action bar item clicks here. The action bar will
	// // automatically handle clicks on the Home/Up button, so long
	// // as you specify a parent activity in AndroidManifest.xml.
	// int id = item.getItemId();
	// if (id == R.id.action_settings) {
	// return true;
	// }
	// return super.onOptionsItemSelected(item);
	// }

	/**
	 * A placeholder fragment containing a simple view.
	 */
	/*
	 * public static class PlaceholderFragment extends Fragment {
	 * 
	 * public PlaceholderFragment() { }
	 * 
	 * @Override public View onCreateView(LayoutInflater inflater, ViewGroup
	 * container, Bundle savedInstanceState) { View rootView =
	 * inflater.inflate(R.layout.fragment_main, container, false); return
	 * rootView; } }
	 */

}
