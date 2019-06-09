package ordecha.igor.tiltsnake;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import java.util.Random;

class Food {
    private Rect rectangle;
    Random random;
    private int color;
    private int size;
    private int maxX;
    private int maxY;
    int X;
    int Y;
    Food(int color, int size, int maxX, int maxY){
        this.rectangle = new Rect();
        this.color = color;
        this.size = size;
        this.maxX = maxX;
        this.maxY = maxY;
        random = new Random();
        update();
    }

    void update(){
        randomize();
        rectangle.set(X, Y, X+size, Y+size);
    }

    void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    void randomize(){
        X = random.nextInt(maxX);
        Y = random.nextInt(maxY);
        X = X/size*size;
        Y = Y/size*size;
    }
}
