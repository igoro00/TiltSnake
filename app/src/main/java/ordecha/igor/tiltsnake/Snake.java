package ordecha.igor.tiltsnake;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

class Snake {
    private Rect rectangle;
    private int speed;
    private int color;
    private int size;
    private int maxX;
    private int maxY;
    private int realX;
    private int realY;
    private int shownX;
    private int shownY;
    private int total = 0;
    Snake(int color, int size, int startX, int startY, int maX, int maxY, int speed){
        this.rectangle = new Rect();
        this.speed = speed;
        this.color = color;
        this.size = size;
        this.maxX = maX;
        this.maxY = maxY;
        this.realX = startX;
        this.realY = startY;

        rectangle.set(startX, startY, startX+size, startY+size);
    }

    void draw(Canvas canvas){
        rectangle.set(shownX, shownY, shownX+size, shownY+size);
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    void update(float rotX, float rotY){
        for(int i = 0; i<total; i++){

        }
        realX+=rotX*speed;
        realY+=rotY*speed;
        matchToGrid();
    }


    private void matchToGrid(){
        if(realX>maxX){
            realX=0;
        }
        if(realY>maxY-10){
            realY=0;
        }
        if(realY<0){
            realY=maxY;
        }
        if(realX<0){
            realX = maxX;
        }
        //shownX = realX;
        //shownY = realY;
        shownX = realX/size*size;
        shownY = realY/size*size;
    }

    boolean eat(int foodX, int foodY){
        if(shownX==foodX && shownY==foodY){
            total++;
            return true;
        }
        else{
            return false;
        }
    }
}
