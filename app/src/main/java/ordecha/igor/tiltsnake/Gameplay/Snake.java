package ordecha.igor.tiltsnake.Gameplay;

import android.graphics.*;
import android.util.Log;
import ordecha.igor.tiltsnake.Utils;


class Snake {
    Rect[] tail;
    private boolean justEaten = false;
    private Utils utils;
    private int[] lastDir={0,0};
    private int speed;
    private int color;
    private int headColor;
    private int size;
    private int maxX;
    private int maxY;
    private float realX;
    private float realY;
    private int shownX;
    private int shownY;
    private int oldShownX=-1;
    private int oldShownY=-1;
    int total = 0;





    Snake(int color, int headColor, int size, int startX, int startY, int maX, int maxY, int speed){
        this.speed = speed;
        this.color = color;
        this.headColor = headColor;
        this.size = size;
        this.maxX = maX;
        this.maxY = maxY;
        this.realX = startX;
        this.realY = startY;
        utils = new Utils();
        tail = new Rect[255];
        tail[0]=new Rect();
        tail[0].set(startX, startY, startX+size, startY+size);
    }

    void draw(Canvas canvas){
        float oneStep = (float)1/(total+1);
        float realRatio;
        Paint paint = new Paint();
        paint.setColor(color);
        for(int i = 0; i<=total;i++) {
            realRatio = (float)1-(i*oneStep);
            //Log.d("colorRatio", String.valueOf(realRatio));
            paint.setColor(utils.blendColors(headColor, color, realRatio));
            canvas.drawRect(tail[i], paint);
        }
        drawPoint(canvas);
    }

    void update(float rotX, float rotY){
        realX+=rotX*speed;
        realY+=rotY*speed;
        matchToGrid();
        if(oldShownX!=shownX || oldShownY!=shownY) {
            if(!isGoingBackwards(lastDir, chckChange()) || total == 0) {
                lastDir = chckChange();

                //tail
                for (int i = 0; i < total; i++) {
                    tail[i].set(tail[i + 1].left, tail[i + 1].top, tail[i + 1].right, tail[i + 1].bottom);
                }

                //head
                tail[total].set(shownX, shownY, shownX + size, shownY + size);

                oldShownX = shownX;
                oldShownY = shownY;
                justEaten = false;
            }
            else{
                shownX = oldShownX;
                realX = oldShownX + (size/2);
                shownY = oldShownY;
                realY = oldShownY + (size/2);
            }
        }
    }


    private void matchToGrid(){
        if(realX>maxX){
            realX=maxX;
        }
        if(realY>maxY-size){
            realY=maxY-size;
        }
        if(realY<0){
            realY=0;
        }
        if(realX<0){
            realX = 0;
        }
        //pixel style
        shownX = (int)realX/size*size;
        shownY = (int)realY/size*size;

        //non-pixel style
        //shownX = (int)realX;
        //shownY = (int)realY;

    }

    boolean eat(int foodX, int foodY){
        //pixel style
        if(shownX==foodX && shownY==foodY){
            total++;
            justEaten = true;
            tail[total]=new Rect();
            tail[total].set(shownX, shownY, shownX+size, shownY+size);
            Log.d("xd", String.valueOf(total));
            return true;
        }
        else{
            return false;
        }

        //non-pixel style
        /*
        if(utils.naturalNumber(shownX-foodX)<10 && utils.naturalNumber(shownY-foodY)<10){
            total++;
            tail[total]=new Rect();
            tail[total].set(shownX, shownY, shownX+size, shownY+size);
            Log.d("xd", String.valueOf(total));
            return true;
        }
        else{
            return false;
        }*/
    }

    boolean die(){
        for(int i = 0; i<=total-1;i++){
            if(shownY == tail[i].top  && shownX == tail[i].left ){
                if(!justEaten) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[] chckChange(){
        int[] output = {0, 0};
        if(oldShownX>shownX){
            output[0] = -1;
        }
        if(oldShownX<shownX){
            output[0] = 1;
        }
        if(oldShownY>shownY){
            output[1] = -1;
        }
        if(oldShownY<shownY){
            output[1] = 1;
        }
        return output;
    }

    private boolean isGoingBackwards(int[] oldDir, int[] newDir){
        return oldDir[0]== -newDir[0] && oldDir[1]==-newDir[1];
    }

    void cheat(){
        total++;
        justEaten = true;
        tail[total]=new Rect();
        tail[total].set(shownX, shownY, shownX+size, shownY+size);
    }

    private void drawPoint(Canvas canvas){
        Paint pointPaint = new Paint(Color.rgb(255,255,255));
        canvas.drawCircle(this.realX, this.realY, 10, pointPaint);
    }

}
