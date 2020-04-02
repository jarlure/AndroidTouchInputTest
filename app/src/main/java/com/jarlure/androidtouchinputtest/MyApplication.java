package com.jarlure.androidtouchinputtest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Gravity;
import android.widget.Toast;
import com.jme3.app.SimpleApplication;
import com.jme3.app.StatsAppState;
import com.jme3.input.RawInputListener;
import com.jme3.input.event.*;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.system.AppSettings;
import com.jme3.system.JmeSystem;
import com.jme3.system.android.JmeAndroidSystem;
import com.jme3.ui.Picture;

public class MyApplication extends SimpleApplication {

    private Geometry button;
    private RawInputListener listener = new RawInputListener() {
        @Override
        public void beginInput() {

        }

        @Override
        public void endInput() {

        }

        @Override
        public void onJoyAxisEvent(JoyAxisEvent evt) {

        }

        @Override
        public void onJoyButtonEvent(JoyButtonEvent evt) {

        }

        @Override
        public void onMouseMotionEvent(MouseMotionEvent evt) {

        }

        @Override
        public void onMouseButtonEvent(MouseButtonEvent evt) {

        }

        @Override
        public void onKeyEvent(KeyInputEvent evt) {
            //TODO: onKeyEvent() should be called many times when "Hello" was typed once.(It works on windows7 correctly)
            System.out.println("KeyChar="+evt.getKeyChar());
        }

        @Override
        public void onTouchEvent(TouchEvent evt) {
            if (TouchEvent.Type.DOWN==evt.getType()){
                if (evt.getY()>button.getLocalTranslation().getY()){
                    JmeSystem.showSoftKeyboard(true);
                }
            }
            else if (TouchEvent.Type.KEY_DOWN==evt.getType()){
                Handler mainThread = new Handler(Looper.getMainLooper());
                mainThread.post(() -> {
                    //TODO: Charactors field should have value when "Hello" was typed.
                    Toast toast=Toast.makeText(JmeAndroidSystem.getView().getContext(),"KeyCode="+evt.getKeyCode()+",Charactors="+evt.getCharacters(),Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                });
            }
        }
    };

    public MyApplication(){
        super(new StatsAppState());
    }

    @Override
    public void simpleInitApp() {
        viewPort.setBackgroundColor(ColorRGBA.DarkGray);
        getInputManager().addRawInputListener(listener);
        JmeSystem.showSoftKeyboard(true);

        Material mat = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.LightGray);
        button =new Picture("button");
        button.setMaterial(mat);
        AppSettings settings = getContext().getSettings();
        float height = 0.1f*settings.getHeight();
        button.scale(settings.getWidth(), height,0);
        button.move(0,settings.getHeight()-height,0);
        guiNode.attachChild(button);
    }

}
