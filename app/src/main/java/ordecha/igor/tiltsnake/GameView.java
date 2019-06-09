package ordecha.igor.tiltsnake;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static ordecha.igor.tiltsnake.MainThread.canvas;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    public GameView(Context context){
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
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
        super.draw(canvas);
    }
}
