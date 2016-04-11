package helloandroid.example.com.autpix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by krushilbhavsar on 06/02/2016.
 */
public class HomeScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);




    }

    public void openActivityOne(View view)
    {
        Intent intent = new Intent(HomeScreen.this, navigationMenu.class);
        startActivity(intent);
    }
}
