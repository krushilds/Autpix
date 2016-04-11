package helloandroid.example.com.autpix;

import android.app.Activity;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;



public class Splashscreen extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread splashThread = new Thread(){

            public void run (){
            try {
                sleep(5000);
                Intent openHomeScreen = new Intent(getApplicationContext(),HomeScreen.class);
                startActivity(openHomeScreen);
                finish();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            }
        };

        splashThread.start();

    }
}
