package eu.andmemasin.tonis.egggame3;

import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public Integer initial_value = 50;
    private Chronometer chronometer;

    private Integer counterValue;
    private boolean isWinnPlayed = false;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        chronometer = (Chronometer) findViewById(R.id.chronometer);

        final ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        Button reloadButton = (Button) findViewById(R.id.reloadButton);
        TextView valueBox = (TextView) findViewById(R.id.counter);
        reset();

        valueBox.setText(this.initial_value.toString());

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (getCounterValue() == initial_value) {
                    startTimer();
                }

                if (getCounterValue() > 0) {
                    setCounterValue(getCounterValue() - 1);
                }
                if (getCounterValue() <= 0) {
                    winGame();
                }
            }
        });

        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void reset() {
        isWinnPlayed = false;

        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.jump);
        mp.start();

        resetTimer();
        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
        Button reloadButton = (Button) findViewById(R.id.reloadButton);
        TextView valueBox = (TextView) findViewById(R.id.counter);
        this.setCounterValue(this.initial_value);
        imageButton.setBackgroundResource(R.drawable.muna2);

    }

    public Integer getCounterValue() {
        return counterValue;
    }

    public void setCounterValue(Integer counterValue) {
        TextView valueBox = (TextView) findViewById(R.id.counter);
        valueBox.setText(counterValue.toString());
        this.counterValue = counterValue;
    }

    private void startTimer() {
        resetTimer();
        chronometer.start();
    }

    private void resetTimer(){
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.setTextSize(20);
        chronometer.setTextColor(Color.BLACK);

    }

    private void stopTimer() {
        chronometer.stop();
        chronometer.setTextSize(30);
        chronometer.setTextColor(Color.RED);
    }

    private void winGame(){
        if(!isWinnPlayed){
            stopTimer();
            ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton);
            imageButton.setBackgroundResource(R.drawable.mats6);

            MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.tadaa);
            mp.start();
            isWinnPlayed = true;
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://eu.andmemasin.tonis.egggame3/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://eu.andmemasin.tonis.egggame3/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

