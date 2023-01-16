package pl.edu.pb.videoplayerapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholder {
    @GET("zadanko-api")
    Call<List<VideoObject>> getVideo();
}
