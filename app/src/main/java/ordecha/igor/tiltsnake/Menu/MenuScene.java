package ordecha.igor.tiltsnake.Menu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.SensorEvent;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import ordecha.igor.tiltsnake.Button;
import ordecha.igor.tiltsnake.Scene;
import ordecha.igor.tiltsnake.SceneManager;

public class MenuScene implements Scene {
    
    private Button playButton;
    
    public MenuScene(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int maxX = size.x;
        int maxY = size.y;
        int offset = maxX / 13;
        playButton = new Button(maxX, 200, offset, "Zagraj", (int)(maxY/3));
    }
    
    
    @Override
    public void update() {
        playButton.update();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.rgb(192, 192, 192));
        playButton.draw(canvas);
    }

    @Override
    public void terminate() {

    }

    @Override
    public void recieveTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(playButton.left<event.getX()&& event.getX()<playButton.right
                        && playButton.top<event.getY() && playButton.bottom>event.getY()){
                    playButton.update(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                playButton.update(false);

                if(playButton.left<event.getX()&& event.getX()<playButton.right
                        && playButton.top<event.getY() && playButton.bottom>event.getY()){
                    SceneManager.ACTIVE_SCENE = 1;
                }
                break;
        }
        Log.d("xd", "ACTIVE_SCENE: " + String.valueOf(SceneManager.ACTIVE_SCENE));
        Log.d("xd", "action: " + event.getAction());
    }

    @Override
    public void recieveSensor(SensorEvent event) {

    }
}
