package ordecha.igor.tiltsnake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.SensorEvent;
import android.util.Log;
import android.view.*;


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;

    SceneManager manager;

    public GameView(Context context){
        super(context);

        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);

        manager = new SceneManager(context);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        manager.recieveTouch(event);
        return true;
    }

    void update(){
        manager.update();
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        manager.draw(canvas);
    }

    void recieveSensor(SensorEvent event){
        manager.recieveSensor(event);
    }

}
