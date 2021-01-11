package com.borjalapa.audioyvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    ImageButton playPause,stop;
    Boolean playing = false;
    SeekBar seekBar;

    Handler handler;

    final int DELAY_TIME = 750;

    //crear carpeta raw y meter los archivos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playPause = (ImageButton) findViewById(R.id.btnPlayPause);
        stop = (ImageButton) findViewById(R.id.btnStop);
        seekBar = (SeekBar)findViewById(R.id.seekBar);

        //meter el audio en la actividad
        mediaPlayer = MediaPlayer.create(this,R.raw.musica);
        //mediaPlayer.setVolume(3,3);

        //ajustar el maximo de la seekbar con la duracion del audio
        seekBar.setMax(mediaPlayer.getDuration());

        //Creamos manejador de la seekbar
        handler = new Handler();

        actualizarBarra();

        //listeners para iniciar y parar la musica
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(playing){
                    mediaPlayer.pause();
                    playPause.setImageDrawable(getDrawable(R.drawable.ic_play));
                }else{
                    mediaPlayer.start();
                    playPause.setImageDrawable(getDrawable(R.drawable.ic_pause));
                }

                playing = !playing;
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
                playPause.setImageDrawable(getDrawable(R.drawable.ic_play));
                mediaPlayer.seekTo(0);
                playing = false;
            }
        });



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mediaPlayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



    }

    //hacer que la barra avance con el audio
    private void actualizarBarra() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,DELAY_TIME);
            }
        }, DELAY_TIME);
    }

    public void irVideo(View view) {
        Intent i = new Intent(this,Video.class);
        startActivity(i);
    }
}