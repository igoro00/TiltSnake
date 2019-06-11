package ordecha.igor.tiltsnake;

import android.graphics.Color;

class Utils {
    int blendColors(int color1, int color2, double ratio) {
        final double inverseRation = 1f - ratio;
        double r = (Color.red(color1) * ratio) + (Color.red(color2) * inverseRation);
        double g = (Color.green(color1) * ratio) + (Color.green(color2) * inverseRation);
        double b = (Color.blue(color1) * ratio) + (Color.blue(color2) * inverseRation);
        return Color.rgb((int) r, (int) g, (int) b);
    }
}
