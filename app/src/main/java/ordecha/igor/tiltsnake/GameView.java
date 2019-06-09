package ordecha.igor.tiltsnake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static ordecha.igor.tiltsnake.MainThread.canvas;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private SnakePart snake;
    private Point snakePoint;
    private MainThread thread;
    public GameView(Context context){
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);


        snakePoint = new Point(0,0);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
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
            } catch (InterruptedException e) {
                Log.d("xd", e.toString());
            }
            retry = false;
        }
    }


    public void update(){
        snakePoint.set(300, 500);
        snake.update(snakePoint);
    }

    @Override
    public void draw(Canvas canvas){
        int i=90;
        snake =new SnakePart(Color.rgb(255,0,255), i/9);
        super.draw(canvas);
        snake.draw(canvas);
    }
}
