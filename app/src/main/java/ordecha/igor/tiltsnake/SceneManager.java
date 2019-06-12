package ordecha.igor.tiltsnake;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.SensorEvent;
import android.view.MotionEvent;

import java.util.ArrayList;

class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    static int ACTIVE_SCENE;

    SceneManager(Context context){
        ACTIVE_SCENE = 0;
        scenes.add(new GameplayScene(context));
    }

    void recieveTouch(MotionEvent event){
        scenes.get(ACTIVE_SCENE).recieveTouch(event);
    }

    void recieveSensor(SensorEvent event){
        scenes.get(ACTIVE_SCENE).recieveSensor(event);
    }

    void update(){
        scenes.get(ACTIVE_SCENE).update();
    }

    void draw(Canvas canvas){
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }

}
