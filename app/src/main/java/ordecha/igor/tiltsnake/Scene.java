package ordecha.igor.tiltsnake;

import android.graphics.Canvas;
import android.hardware.SensorEvent;
import android.view.MotionEvent;

public interface Scene {
    void update();
    void draw(Canvas canvas);
    void terminate();
    void recieveTouch(MotionEvent event);
    void recieveSensor(SensorEvent event);
}
