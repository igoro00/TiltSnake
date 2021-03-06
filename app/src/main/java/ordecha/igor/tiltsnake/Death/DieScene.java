package ordecha.igor.tiltsnake.Death;

import android.content.Context;
import android.graphics.*;
import android.hardware.SensorEvent;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import ordecha.igor.tiltsnake.Button;
import ordecha.igor.tiltsnake.Scene;
import ordecha.igor.tiltsnake.SceneManager;

public class DieScene implements Scene {

    private ImageZdehues image;
    private TextBox textBox;
    private Button resetButton;
    private Button homeButton;

    public DieScene(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int maxX = size.x;
        int maxY = size.y;
        int offset = maxX / 13;
        image = new ImageZdehues(context, maxX, maxY, offset);
        textBox = new TextBox(maxX,300, offset, image.getBottomY());
        resetButton = new Button(maxX, 200, offset, "Spróbuj Ponownie", textBox.getBottomY());
        homeButton = new Button(maxX, 200, offset, "Menu Główne", resetButton.getBottomY());

    }

    @Override
    public void terminate(){

    }
    @Override
    public void draw(Canvas canvas){
        canvas.drawColor(Color.rgb(192, 192, 192));
        image.draw(canvas);
        textBox.draw(canvas);
        resetButton.draw(canvas);
        homeButton.draw(canvas);


    }
    @Override
    public void update(){
        resetButton.update();
        homeButton.update();
        textBox.update();
    }

    @Override
    public void recieveTouch(MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(resetButton.left<event.getX()&& event.getX()<resetButton.right
                && resetButton.top<event.getY() && resetButton.bottom>event.getY()){
                    resetButton.update(true);
                }
                if(homeButton.left<event.getX()&& event.getX()<homeButton.right
                        && homeButton.top<event.getY() && homeButton.bottom>event.getY()){
                    homeButton.update(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                resetButton.update(false);
                homeButton.update(false);

                if(resetButton.left<event.getX()&& event.getX()<resetButton.right
                        && resetButton.top<event.getY() && resetButton.bottom>event.getY()){
                    SceneManager.ACTIVE_SCENE = 1;
                }
                if(homeButton.left<event.getX()&& event.getX()<homeButton.right
                        && homeButton.top<event.getY() && homeButton.bottom>event.getY()){
                    SceneManager.ACTIVE_SCENE = 0;
                }
                break;
        }
        Log.d("xd", "ACTIVE_SCENE: " + String.valueOf(SceneManager.ACTIVE_SCENE));
        Log.d("xd", "action: " + event.getAction());
    }

    @Override
    public void recieveSensor(SensorEvent event){

    }
}

