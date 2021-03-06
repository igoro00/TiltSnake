package ordecha.igor.tiltsnake;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    Sensor accelerometer;
    GameView gmV;
    private long backPressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        gmV = new GameView(MainActivity.this);
        setContentView(gmV);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onResume(){
        if(SceneManager.ACTIVE_SCENE == 1) {
            SceneManager.ACTIVE_SCENE = 0;
        }
        super.onResume();
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        gmV.recieveSensor(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onBackPressed () {
        if(SceneManager.ACTIVE_SCENE==1){
            if(backPressedTime + 2000 > System.currentTimeMillis()){
                super.onBackPressed();
                return;
            } else {
                Toast.makeText(getBaseContext(), "Naciśnij ponownie by wyjść z gry", Toast.LENGTH_SHORT).show();
            }

            backPressedTime = System.currentTimeMillis();
        }
        else if(SceneManager.ACTIVE_SCENE==2){
            SceneManager.ACTIVE_SCENE=0;
        }
        else{
            super.onBackPressed();
        }

    }

}