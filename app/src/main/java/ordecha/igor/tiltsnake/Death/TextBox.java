package ordecha.igor.tiltsnake.Death;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class TextBox {
    private Rect rectangle;
    private Paint paint;

    private int startY;
    private int height;
    private int offset;

    TextBox(int maxX, int height, int offset, int startY){
        this.rectangle = new Rect();
        this.paint = new Paint();
        this.height = height;
        this.startY = startY;
        this.offset = offset;
        paint.setColor(Color.BLACK);
        rectangle.set(offset, startY+offset, maxX-offset, startY+offset+height);
    }

    public void draw(Canvas canvas){
        canvas.drawRect(rectangle, paint);
    }

    int getBottomY(){
        double output = offset + startY + height;
        return (int) output;
    }

    void reshuffle(){

    }

}
