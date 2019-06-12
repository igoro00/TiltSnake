package ordecha.igor.tiltsnake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.SensorEvent;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

public class GameplayScene implements Scene{

    private Snake snake;
    private Food food;

    private int points = 0;

    private int maxX;
    private int maxY;
    private int gridSize;
    private int speed = 1;

    private float rotX;
    private float rotY;
    private float rotZ;

    GameplayScene(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        maxX = size.x;
        maxY = size.y;
        Log.d("xd", String.valueOf(maxY));
        gridSize = maxY/calculateAspectRatio(maxX, maxY);
        snake = new Snake(Color.rgb(55,0,55),Color.rgb(200,0,255), gridSize, maxX/2, maxY/2, maxX, maxY, speed);
        food = new Food(Color.rgb(255,0,0), gridSize, maxX, maxY, snake);

    }
    @Override
    public void terminate(){
        SceneManager.ACTIVE_SCENE = 0;
    }
    @Override
    public void draw(Canvas canvas){
        canvas.drawColor(Color.rgb(192, 192, 192));
        food.draw(canvas);
        snake.draw(canvas);
    }
    @Override
    public void update(){
        snake.update(rotX, rotY);
        if(snake.eat(food.X, food.Y)){
            food.update();
            points++;
        }
        if(snake.die()){

        }
    }
    @Override
    public void recieveTouch(MotionEvent event){

    }

    @Override
    public void recieveSensor(SensorEvent event){
        rotY = event.values[1];
        rotX = event.values[0];
        rotX=-rotX;
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
