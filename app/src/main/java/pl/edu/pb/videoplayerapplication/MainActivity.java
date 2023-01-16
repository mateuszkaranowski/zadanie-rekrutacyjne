package pl.edu.pb.videoplayerapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VideoObjectAdapter videoObjectAdapter;
    private List<VideoObject> videoObjectList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.listOfVideos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://zryjto.linuxpl.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);
        Call<List<VideoObject>> call = jsonPlaceholder.getVideo();
        call.enqueue(new Callback<List<VideoObject>>() {
            @Override
            public void onResponse(Call<List<VideoObject>> call, Response<List<VideoObject>> response) {
                if(!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                videoObjectList = response.body();
                videoObjectAdapter = new VideoObjectAdapter(MainActivity.this, videoObjectList);
                SpaceBetweenItems spaceBetweenItems = new SpaceBetweenItems(20);
                videoObjectList.sort(new Comparator<VideoObject>() {
                    @Override
                    public int compare(VideoObject o1, VideoObject o2) {
                        //Toast.makeText(MainActivity.this, "FIRST: " + o1.getCounter().toString() + "SECOND: " + o2.getCounter(), Toast.LENGTH_LONG).show();
                        return o1.getCounter().compareTo(o2.getCounter());
                    }
                });
                recyclerView.addItemDecoration(spaceBetweenItems);
                recyclerView.swapAdapter(videoObjectAdapter, false);
                //videoObjectAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<VideoObject>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Opss, sorry! Looks like you are offline right now. Please check your Internet connection!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}