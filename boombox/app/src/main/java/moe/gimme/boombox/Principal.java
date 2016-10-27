package moe.gimme.boombox;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    MediaPlayer mp = new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void playKick(View vista){
        mp.reset();
        mp.release();
        mp=MediaPlayer.create(this,R.raw.kick);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.seekTo(0);
        mp.start();
    }
    public void playSnare(View vista){
        mp.reset();
        mp.release();
        mp=MediaPlayer.create(this,R.raw.snare);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.seekTo(0);
        mp.start();
    }
    public void playHH(View vista){
        mp.reset();
        mp.release();
        mp=MediaPlayer.create(this,R.raw.hh);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.seekTo(0);
        mp.start();
    }
    public void playTom(View vista){
        mp.reset();
        mp.release();
        mp=MediaPlayer.create(this,R.raw.tom);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.seekTo(0);
        mp.start();
    }
    public void playCrash(View vista){
        mp.reset();
        mp.release();
        mp=MediaPlayer.create(this,R.raw.crash);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.seekTo(0);
        mp.start();
    }
    public void playSplash(View vista){
        mp.reset();
        mp.release();
        mp=MediaPlayer.create(this,R.raw.splash);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.seekTo(0);
        mp.start();
    }
    public void playClap(View vista){
        mp.reset();
        mp.release();
        mp=MediaPlayer.create(this,R.raw.clap);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.seekTo(0);
        mp.start();
    }
    public void playRide(View vista){
        mp.reset();
        mp.release();
        mp=MediaPlayer.create(this,R.raw.ride);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.seekTo(0);
        mp.start();
    }
    public void playCow(View vista){
        mp.reset();
        mp.release();
        mp=MediaPlayer.create(this,R.raw.cow);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mp.seekTo(0);
        mp.start();
    }
}
