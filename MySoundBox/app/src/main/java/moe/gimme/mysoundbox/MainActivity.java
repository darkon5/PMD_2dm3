package moe.gimme.mysoundbox;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity {

    //private ImageButton btnKick, btnSnare;

    //private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //btnKick = (ImageButton) findViewById(R.id.btnKick);
        //btnSnare = (ImageButton) findViewById(R.id.btnSnare);

        //AudioManager am=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        //am.setMode(AudioManager.MODE_NORMAL);


    }

    public void playKick(View vista){

        //mediaPlayer = MediaPlayer.create(this, R.raw.kick);
        //mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
        //FileInputStream fis = new FileInputStream(R.raw.kick);
        //mediaPlayer.setDataSource(getApplicationContext(), R.raw.kick);
        //mediaPlayer.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);

        try {

            MediaPlayer mp = new MediaPlayer();
            mp.reset();
            mp.release();
            mp=MediaPlayer.create(this,R.raw.kick);
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.seekTo(0);
            mp.start();

            /*Uri uri = Uri.parse("android.resource://moe.gimme.mysoundbox/" + R.raw.kick);

            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(this, uri);
            mediaPlayer.prepare();
            mediaPlayer.start();
*/
        }catch(Exception e){
            e.printStackTrace();

        }
    }
}
