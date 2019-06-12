package ordecha.igor.tiltsnake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;


class Snake {
    Rect[] tail;
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
    }

    void update(float rotX, float rotY){
        realX+=rotX*speed;
        realY+=rotY*speed;
        matchToGrid();
        if(oldShownX!=shownX || oldShownY!=shownY) {
            if(!isGoingBackwards(lastDir, chckChange()) || total == 0) {
                lastDir = chckChange();
                for (int i = 0; i < total; i++) {
                    tail[i].set(tail[i + 1].left, tail[i + 1].top, tail[i + 1].right, tail[i + 1].bottom);
                }
                tail[total].set(shownX, shownY, shownX + size, shownY + size);
                oldShownX = shownX;
                oldShownY = shownY;
            }
            else{
                shownX = oldShownX;
                realX = oldShownX;
                shownY = oldShownY;
                realY = oldShownY;
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
        for(int i = 1; i<=total;i++){
            if(shownY == tail[i].top  && shownX == tail[i].left ){
                return false;
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
        if(oldDir[0]==-1 && newDir[0]==1){
            return true;
        }
        else if(oldDir[0]==1 && newDir[0]==-1){
            return true;
        }
        else if(oldDir[1]==-1 && newDir[1]==1){
            return true;
        }
        else if(oldDir[1]==1 && newDir[1]==-1){
            return true;
        }
        return false;
    }

}
