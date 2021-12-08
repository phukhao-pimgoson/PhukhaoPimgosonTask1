import java.util.ArrayList;

public class Road {
    public String id;
    public int length;
    public int speed_limit;
    public ArrayList<Car> cars = new ArrayList<>();
    public ArrayList<TrafficLight> lights = new ArrayList<>();

    public Road(String id) {
        this.id = "road_" + id;
        // program version with static road length and speed limit
        length = 10;
        speed_limit = 1;
    }
}
