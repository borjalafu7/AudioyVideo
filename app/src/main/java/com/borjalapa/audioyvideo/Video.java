package com.borjalapa.audioyvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Video extends AppCompatActivity {
    VideoView videoView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = (VideoView) findViewById(R.id.videoView);

        //meter el video en el videoView
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video));

        /*
        videoView.start();
        videoView.resume();
        videoView.stopPlayback();

         */

        progressDialog.show(Video.this,"","");
        progressDialog.setCancelable(true);

    }

    private void PlayVideo(){
        try{
            getWindow().setFormat(PixelFormat.TRANSLUCENT);
            MediaController mediaController = new MediaController(Video.this);
            mediaController.setAnchorView(videoView);

            Uri videoUri = Uri.parse("android.resource://"+ getPackageName()+ "/" + R.raw.video);
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(videoUri);
            videoView.requestFocus();

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    progressDialog.dismiss();
                    videoView.start();
                }
            });
        }catch(Exception e){

            progressDialog.dismiss();
            System.out.println("Video Play Error :"+e.toString());
            finish();
        }
    }

}