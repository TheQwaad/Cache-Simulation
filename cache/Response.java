package cache;

public class Response {
    private final boolean hit;
    private final int time;

    public Response(boolean hit, int time) {
        this.hit = hit;
        this.time = time;
    }

    public boolean isHit() {
        return hit;
    }

    public int getTime() {
        return time;
    }
}
