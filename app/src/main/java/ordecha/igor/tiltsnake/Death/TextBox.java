package ordecha.igor.tiltsnake.Death;

import android.graphics.*;
import android.util.Log;
import ordecha.igor.tiltsnake.SceneManager;
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
            "Nawet nie próbuj!",
            "Nie martw się,",
            "Marnujesz tylko czas!"
    };
    private String[] texts2 = {
            "Znowu zginiesz.",
            "tak miało być",
            ""
    };

    TextBox(int maxX, int height, int offset, int startY){
        if(chckTexts()) {
            generator.setSeed(System.currentTimeMillis()/10000);
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
        if(!texts2[currentTXT].equals("")) {
            utils.drawTextCentered(texts1[currentTXT], left + ((right - left) / 2), top + (int) ((bottom - top) * 0.3), txtPaint, canvas);
            utils.drawTextCentered(texts2[currentTXT], left + ((right - left) / 2), top + (int) ((bottom - top) * 0.7), txtPaint, canvas);
        }
        else{
            utils.drawTextCentered(texts1[currentTXT], left + ((right - left) / 2), top + (int) ((bottom - top) * 0.5), txtPaint, canvas);
        }
    }

    int getBottomY(){
        double output = (offset*1.8) + startY + height;
        return (int) output;
    }

    void update(){
        if(SceneManager.JUST_DIED) {
            currentTXT = generator.nextInt(texts1.length);
            SceneManager.JUST_DIED = false;
            Log.d("xd", "reshuffled. new number is:  " + String.valueOf(currentTXT) + "  out of:  " + String.valueOf(texts1.length-1));
        }
    }

    private boolean chckTexts(){
        return texts1.length==texts2.length;
    }



}
