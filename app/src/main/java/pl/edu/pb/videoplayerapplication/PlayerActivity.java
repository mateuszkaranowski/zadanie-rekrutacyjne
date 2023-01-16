package pl.edu.pb.videoplayerapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerView;

public class PlayerActivity extends AppCompatActivity {

    TextView titleInfo, counter;
    ExoPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            titleInfo = findViewById(R.id.titleInfoVideo);
            titleInfo.setText(bundle.getString("titleVideo"));
            counter = findViewById(R.id.counter);
            Integer in = bundle.getInt("counter");
            String str = "Number of views: " + in.toString();
            counter.setText(str);
        }
        String url = bundle.getString("manifestVideo");
        player = new ExoPlayer.Builder(PlayerActivity.this)
                .setSeekBackIncrementMs(10000)
                .setSeekForwardIncrementMs(10000)
                .build();
        PlayerView playerView = findViewById(R.id.playerView);
        playerView.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(url);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.setPlayWhenReady(true);
        player.addListener(new Player.Listener() {
            @Override
            public void onPlayerError(PlaybackException error) {
                Toast.makeText(PlayerActivity.this, "SORRY! Something went wrong! :( ", Toast.LENGTH_LONG).show();
                Toast.makeText(PlayerActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
        }
    }
}
