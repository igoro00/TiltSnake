package ordecha.igor.tiltsnake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Toast;

public class Utils {
    public int blendColors(int color1, int color2, double ratio) {
        final double inverseRation = 1f - ratio;
        double r = (Color.red(color1) * ratio) + (Color.red(color2) * inverseRation);
        double g = (Color.green(color1) * ratio) + (Color.green(color2) * inverseRation);
        double b = (Color.blue(color1) * ratio) + (Color.blue(color2) * inverseRation);
        return Color.rgb((int) r, (int) g, (int) b);
    }

    public int naturalNumber(int input){
        int output;
        if(input>=0){
            output = input;
        }
        else{
            output = -input;
        }
        return output;
    }

    public void drawTextCentered(String text, int x, int y, Paint paint, Canvas canvas) {
        int xPos = x - (int)(paint.measureText(text)/2);
        int yPos = (int) (y - ((paint.descent() + paint.ascent()) / 2)) ;

        canvas.drawText(text, xPos, yPos, paint);
    }
}
