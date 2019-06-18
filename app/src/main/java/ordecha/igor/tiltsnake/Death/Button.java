package ordecha.igor.tiltsnake.Death;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Button {
    private Rect rectangle;
    private Paint paint;

    private int startY;
    private int height;
    private int offset;

    int top, bottom, left, right;

    private int colorUnclicked = Color.BLACK;
    private int colorClicked = Color.rgb(47, 47, 47);

    private int purpose;

    String[] txts = {
        "Spróbuj\nponownie",
        "Menu\ngłówne"
    };

    Button(int maxX, int height, int offset, int purpose, int startY){
        this.rectangle = new Rect();
        this.paint = new Paint();
        this.purpose = purpose;
        this.height = height;
        this.startY = startY;
        this.offset = offset;


        this.left = offset*2;
        this.top = startY+offset;
        this.right = maxX-(offset*2);
        this.bottom = startY+offset+height;

        rectangle.set(left, top, right, bottom);
    }

    public void update(boolean isClicked){
        if(isClicked){
            paint.setColor(colorClicked);
        }
        else{
            paint.setColor(colorUnclicked);
        }
    }

    public void draw(Canvas canvas){
        canvas.drawRect(rectangle, paint);
    }

    int getBottomY(){
        double output = offset + startY + height;
        return (int) output;
    }

}
