package ordecha.igor.tiltsnake;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.SensorEvent;
import android.view.MotionEvent;
import ordecha.igor.tiltsnake.Death.DieScene;
import ordecha.igor.tiltsnake.Gameplay.GameplayScene;
import ordecha.igor.tiltsnake.Menu.MenuScene;

import java.util.ArrayList;

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    public static int ACTIVE_SCENE;
    public static boolean JUST_DIED = false;

    SceneManager(Context context){
        ACTIVE_SCENE = 0;
        scenes.add(new MenuScene(context));
        scenes.add(new GameplayScene(context));
        scenes.add(new DieScene(context));
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
