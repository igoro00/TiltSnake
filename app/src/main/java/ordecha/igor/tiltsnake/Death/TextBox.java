package ordecha.igor.tiltsnake.Death;

import android.graphics.*;
import ordecha.igor.tiltsnake.Utils;

import java.util.Random;

public class TextBox {
    private RectF rectangle;
    private Paint rectPaint;
    private Paint txtPaint;
    private Utils utils = new Utils();
    private Random generator = new Random();

    private int startY;
    private int height;
    private int offset;
    private int currentTXT;

    private int top, bottom, left, right;

    private String[] texts1 = {
      "Nawet nie próbuj!"
    };
    private String[] texts2 = {
       "Znowu zginiesz."
    };

    TextBox(int maxX, int height, int offset, int startY){
        if(chckTexts()) {
            this.rectangle = new RectF();
            this.rectPaint = new Paint();
            this.txtPaint = new Paint();
            this.height = height;
            this.startY = startY;
            this.offset = offset;

            this.left = offset;
            this.top = startY+offset;
            this.right = maxX-offset;
            this.bottom = startY+offset+height;

            rectPaint.setColor(Color.BLACK);
            rectangle.set(left, top, right, bottom);

            txtPaint.setStyle(Paint.Style.FILL);
            txtPaint.setColor(Color.WHITE);
            txtPaint.setTextSize(90);
        }
    }

    public void draw(Canvas canvas){
        canvas.drawRoundRect(rectangle, 10, 10, rectPaint);
        //canvas.drawText(texts1[currentTXT], offset+20, startY+offset+txtPaint.getTextSize()+20, txtPaint);
        //canvas.drawText(texts2[currentTXT], offset+20, startY+offset+height-60, txtPaint);
        utils.drawTextCentered(texts1[currentTXT], left+((right-left)/2)-30, top+(int)((bottom-top)*0.3), txtPaint, canvas);
        utils.drawTextCentered(texts2[currentTXT], left+((right-left)/2)+50, top+(int)((bottom-top)*0.7), txtPaint, canvas);
    }

    int getBottomY(){
        double output = (offset*1.8) + startY + height;
        return (int) output;
    }

    void update(){
        currentTXT = generator.nextInt(currentTXT+1);
    }

    private boolean chckTexts(){
        return texts1.length==texts2.length;
    }



}
