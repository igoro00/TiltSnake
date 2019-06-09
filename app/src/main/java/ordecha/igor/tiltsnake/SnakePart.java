package ordecha.igor.tiltsnake;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class SnakePart {
    private Rect rectangle;
    private int color;
    private int size;

    public SnakePart(int color, int size){
        this.rectangle = new Rect();
        this.color = color;
        this.size = size;
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    public void update(){

    }

    public void update(Point point){
        //left, top, right, bottom
        rectangle.set(point.x, point.y, point.x+size, point.y+size);
    }
}
