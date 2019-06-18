package ordecha.igor.tiltsnake.Death;

import android.content.Context;
import android.graphics.*;
import android.hardware.SensorEvent;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import ordecha.igor.tiltsnake.Scene;
import ordecha.igor.tiltsnake.SceneManager;

public class DieScene implements Scene {

    private ImageZdehues image;
    private int maxX;
    private int maxY;
    private int offset;
    public DieScene(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        maxX = size.x;
        maxY = size.y;
        offset = maxX / 13;
        image = new ImageZdehues(context, maxX, maxY, offset);

    }

    @Override
    public void terminate(){

    }
    @Override
    public void draw(Canvas canvas){
        canvas.drawColor(Color.rgb(192, 192, 192));
        image.draw(canvas);


    }
    @Override
    public void update(){

    }

    @Override
    public void recieveTouch(MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                SceneManager.ACTIVE_SCENE = 0;
                break;
        }
        Log.d("xd", "ACTIVE_SCENE: " + String.valueOf(SceneManager.ACTIVE_SCENE));
        Log.d("xd", "action: " + event.getAction());
    }

    @Override
    public void recieveSensor(SensorEvent event){

    }
}

