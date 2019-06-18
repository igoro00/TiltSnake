package ordecha.igor.tiltsnake.Gameplay;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;

class Food {
    private Rect rectangle;
    private Snake snake;
    private Random random;
    private int points = 0;
    private int color;
    private int size;
    private int maxX;
    private int maxY;
    int X;
    int Y;
    Food(int color, int size, int maxX, int maxY, Snake snake){
        this.rectangle = new Rect();
        this.color = color;
        this.size = size;
        this.maxX = maxX;
        this.maxY = maxY;
        this.snake = snake;
        random = new Random();
        update();
    }

    void update(){
        randomize();
        if(chckPos(X, Y)) {
            update();
            return;
        }
        rectangle.set(X, Y, X + size, Y + size);
    }

    void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    private void randomize(){
        X = random.nextInt(maxX);
        Y = random.nextInt(maxY-size);
        X = X/size*size;
        Y = Y/size*size;
    }

    private boolean chckPos(int X, int Y){
        for(int i= 0; i<=snake.total; i++){
            if( X == snake.tail[i].left && Y == snake.tail[i].top){
                return true;
            }
        }
        return false;
    }

    private void updatePoints(int pnts){points = pnts;}
}
