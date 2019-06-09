package ordecha.igor.tiltsnake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.SensorEvent;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private Snake snake;
    private Food food;

    private MainThread thread;
    private int maxX;
    private int maxY;
    private int gridSize;
    private int speed = 2;
    private float rotX;
    private float rotY;
    private float rotZ;
    public GameView(Context context){
        super(context);

        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        maxX = size.x;
        maxY = size.y;
        Log.d("xd", String.valueOf(maxY));
        gridSize = maxY/calculateAspectRatio(maxX, maxY);
        snake = new Snake(Color.rgb(255,255,255), gridSize, maxX/2, maxY/2, maxX, maxY, speed);
        food = new Food(Color.rgb(255,0,0), gridSize, maxX, maxY);
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


    void update(){
        snake.update(rotX, rotY);
        if(snake.eat(food.X, food.Y)){
            food.update();
        }
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        food.draw(canvas);
        snake.draw(canvas);
    }

    void onSensorChanged(SensorEvent event){
        rotY = event.values[1];
        rotX = event.values[0];
        rotX=-rotX;
    }

    void onEat(){
        food.update();
    }

    private int calculateAspectRatio(int X, int Y){
        /*
                  Y                  X
            -------------    =   ----------
            areWeThereYet            i
         */
        float areWeThereYet;
        for(int i=1; i<X; i++){
            areWeThereYet=i*Y/X;
            if(areWeThereYet == (int)areWeThereYet && maxY/ areWeThereYet<100){
                Log.d("calculateAspectRatio", String.valueOf(i) + "YES");
                return (int)areWeThereYet;
            }
            else{
                Log.d("calculateAspectRatio", String.valueOf(i) + "NO");
            }
        }
        return 25;
    }
}
