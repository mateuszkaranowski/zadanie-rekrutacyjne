package pl.edu.pb.videoplayerapplication;

public class VideoObject {
    private String id, title, manifest;
    private Integer counter;
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getManifest() {
        return manifest;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Integer getCounter() {
        if(this.counter==null){
            this.counter =0;
        }
        return counter;
    }
}
