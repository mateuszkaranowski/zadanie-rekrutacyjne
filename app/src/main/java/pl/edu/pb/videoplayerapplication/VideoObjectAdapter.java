package pl.edu.pb.videoplayerapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoObjectAdapter extends RecyclerView.Adapter<VideoObjectAdapter.VideoObjectHolder> {
    List<VideoObject> videoObjectList;
    Context context;
    Context con;
    Integer counter;

    public VideoObjectAdapter(Context context, List<VideoObject> videos) {
        this.context = context;
        videoObjectList = videos;
    }

    @NonNull
    @Override
    public VideoObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.on_click_list_element, parent, false);
        con = view.getContext();
        return new VideoObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoObjectHolder holder, int position) {
        VideoObject videoObject = videoObjectList.get(position);
        holder.title.setText(videoObject.getTitle());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = videoObject.getCounter();
                counter++;
                videoObject.setCounter(counter++);
                Intent intent = new Intent(con, PlayerActivity.class);
                intent.putExtra("titleVideo", videoObject.getTitle());
                intent.putExtra("manifestVideo", videoObject.getManifest());
                intent.putExtra("counter", videoObject.getCounter());
                con.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoObjectList.size();
    }

    public static class VideoObjectHolder extends RecyclerView.ViewHolder{
        TextView title, clicker;
        public VideoObjectHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleVideo);
        }
    }
}
