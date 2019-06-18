package ordecha.igor.tiltsnake.Death;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Button {
    private Rect rectangle;

    private int top;
    private int height;

    private int purpose;

    String restartTxt = "";
    String homeTxt = "";
    String myTxt;

    String[] txts = {
        "Spróbuj\nponownie",
        "Menu\ngłówne"
    };

    Button(Context context, int maxX, int height, int offset, int purpose, int startY){
        this.rectangle = new Rect();
        this.purpose = purpose;
        this.height = height;
        top = startY+offset;
        rectangle.set(offset*2, top, maxX-(offset*2), top+height);
    }

    public void update(boolean isClicked){

    }

    public void draw(Canvas canvas){

    }

    public int getBottomY(){
        return top+height;
    }

    public void reshufle(){

    }
}
