package ordecha.igor.tiltsnake;

import android.graphics.*;

public class Button {
    private RectF rectangle;
    private Paint rectPaint;

    private Paint txtPaint = new Paint();

    private Utils utils = new Utils();

    private int startY;
    private int height;
    private int offset;

    public int top, bottom, left, right;

    private int colorUnclicked = Color.rgb(0,0,0);
    private int colorClicked = Color.rgb(100, 100, 100);
    private int blend = 0;
    private int dstBlend = 0;

    private String text;


    public Button(int maxX, int height, int offset, String text, int startY){
        this.rectangle = new RectF();
        this.rectPaint = new Paint();

        this.text = text;

        txtPaint.setStyle(Paint.Style.FILL);
        txtPaint.setColor(Color.WHITE);
        txtPaint.setTextSize(80);

        this.height = height;
        this.startY = startY;
        this.offset = offset;

        this.left = offset*2;
        this.top = startY+offset;
        this.right = maxX-(offset*2);
        this.bottom = startY+offset+height;

        rectangle.set(left, top, right, bottom);

        rectPaint.setColor(utils.blendColors(colorClicked, colorUnclicked, (double)blend/1024));
    }

    public void update(boolean isClicked){

        if(isClicked){
            dstBlend = 1024;
        }
        else{
            dstBlend = 0;
        }
    }

    public void update(){
        if(blend != dstBlend){
            if(blend<dstBlend){
                blend+=64;
            }
            else{
                blend-=64;
            }
            rectPaint.setColor(utils.blendColors(colorClicked, colorUnclicked, (double)blend/1024));
            //Log.d("ColorRatio", String.valueOf(blend));
        }
    }

    public void draw(Canvas canvas){
        canvas.drawRoundRect(rectangle, 30, 30, rectPaint);
        utils.drawTextCentered(text, left+((right-left)/2), top+((bottom-top)/2), txtPaint, canvas);
    }

    public int getBottomY(){
        double output = offset + startY + height;
        return (int) output;
    }


    //by Borislav Markov from StackOverFlow


}
