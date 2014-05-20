package com.lucky4all.andengine_mygame.Scenes;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.app.Activity;

import com.lucky4all.andengine_mygame.ResourceManager.ResourcesManager;


/**
 * @author Ruben Pla Ferrero
 * 
 */
public abstract class BasicScene extends Scene {

	protected Engine engine;
	protected Activity activity;
	protected BoundCamera camera;
	protected VertexBufferObjectManager vbom;
	protected ResourcesManager resourcesManager;

	public BasicScene() {
		this.resourcesManager = ResourcesManager.getInstance();
		this.engine = resourcesManager.engine;
		this.activity = resourcesManager.activity;
		this.camera = resourcesManager.camera;
		this.vbom = resourcesManager.vbom;
		createScene();
	}

	public abstract void createScene();

	public abstract void onBackKeyPressed();

	public abstract SceneType getSceneType();

	public abstract void disposeScene();

}
