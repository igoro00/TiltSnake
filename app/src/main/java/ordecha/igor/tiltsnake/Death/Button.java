package ordecha.igor.tiltsnake.Death;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import ordecha.igor.tiltsnake.Utils;

public class Button {
    private Rect rectangle;
    private Paint paint;
    private Utils utils;

    private int startY;
    private int height;
    private int offset;

    int top, bottom, left, right;

    private int colorUnclicked = Color.rgb(0,0,0);
    private int colorClicked = Color.rgb(100, 100, 100);
    private int blend = 0;
    private int dstBlend = 0;

    private int purpose;

    String[] txts = {
        "Spróbuj\nponownie",
        "Menu\ngłówne"
    };

    Button(int maxX, int height, int offset, int purpose, int startY){
        this.rectangle = new Rect();
        this.paint = new Paint();
        this.utils = new Utils();
        this.purpose = purpose;
        this.height = height;
        this.startY = startY;
        this.offset = offset;


        this.left = offset*2;
        this.top = startY+offset;
        this.right = maxX-(offset*2);
        this.bottom = startY+offset+height;

        rectangle.set(left, top, right, bottom);

        paint.setColor(utils.blendColors(colorClicked, colorUnclicked, (double)blend/1024));
    }

    void update(boolean isClicked){
        if(isClicked){
            dstBlend = 1024;
        }
        else{
            dstBlend = 0;
        }
    }

    void update(){
        if(blend != dstBlend){
            if(blend<dstBlend){
                blend+=64;
            }
            else{
                blend-=64;
            }
            paint.setColor(utils.blendColors(colorClicked, colorUnclicked, (double)blend/1024));
            //Log.d("ColorRatio", String.valueOf(blend));
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
