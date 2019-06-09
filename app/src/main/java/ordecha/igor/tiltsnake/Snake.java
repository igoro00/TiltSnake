package ordecha.igor.tiltsnake;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

//ToDo: Death Detection
//Todo: Anti-Go-Back feature

class Snake {
    private Rect[] tail;
    private int speed;
    private int color;
    private int headColor;
    private int size;
    private int maxX;
    private int maxY;
    private int realX;
    private int realY;
    private int shownX;
    private int shownY;
    private int oldShownX=0;
    private int oldShownY=0;
    private int total = 0;


    Snake(int color, int headColor, int size, int startX, int startY, int maX, int maxY, int speed){
        this.speed = speed;
        this.color = color;
        this.headColor = headColor;
        this.size = size;
        this.maxX = maX;
        this.maxY = maxY;
        this.realX = startX;
        this.realY = startY;
        tail = new Rect[255];
        tail[0]=new Rect();
        tail[0].set(startX, startY, startX+size, startY+size);
    }

    void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        Paint headPaint = new Paint();
        headPaint.setColor(headColor);
        for(int i = 0; i<=total;i++) {
            if(i==total) {
                canvas.drawRect(tail[i], headPaint);
            }
            else {
                canvas.drawRect(tail[i], paint);
            }
        }
    }

    void update(float rotX, float rotY){
        realX+=rotX*speed;
        realY+=rotY*speed;
        matchToGrid();
        if(oldShownX!=shownX || oldShownY!=shownY) {
            for (int i = 0; i < total; i++) {
                tail[i].set(tail[i + 1].left, tail[i + 1].top, tail[i + 1].right, tail[i + 1].bottom);
            }
            tail[total].set(shownX, shownY, shownX + size, shownY + size);
            oldShownX = shownX;
            oldShownY = shownY;
        }
    }


    private void matchToGrid(){
        if(realX>maxX){
            realX=0;
        }
        if(realY>maxY){
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
            tail[total]=new Rect();
            tail[total].set(shownX, shownY, shownX+size, shownY+size);
            Log.d("xd", String.valueOf(total));
            return true;
        }
        else{
            return false;
        }
    }
}