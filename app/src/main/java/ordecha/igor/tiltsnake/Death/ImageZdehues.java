package ordecha.igor.tiltsnake.Death;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import ordecha.igor.tiltsnake.R;

class ImageZdehues {
    private int maxX;
    private int maxY;
    private int offset;
    private float scaledY;

    private Bitmap image;
    private Bitmap scaledImage;

    ImageZdehues(Context context, int maxX, int maxY, int offset){
        this.offset = offset;
        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.zdehues);

        float scaledX = maxX-(offset*2);
        scaledY = scaledX/image.getWidth();
        scaledY *= image.getHeight();
        scaledImage = Bitmap.createScaledBitmap(image, (int)scaledX, (int)scaledY, true);
    }

    void draw(Canvas canvas){
        canvas.drawBitmap(scaledImage, offset, offset * 2, null);
    }

    int getBottomY(){
        return (int)scaledY+(3* offset);
    }

}
