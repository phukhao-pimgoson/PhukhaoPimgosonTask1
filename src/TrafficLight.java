import java.util.Random;

public class TrafficLight {
    public String id;
    public boolean state; // False for red, True for green
    public int position;
    public Road road;


    public TrafficLight(String id, Road road) {
        this.id = "light_" + id;
        Random random = new Random();
        state = random.nextBoolean(); // assign a random starting state

        this.road = road;
        position = this.road.length / 2; // places the light midway of the road
        this.road.lights.add(this); // add the light on the road
    }

    public void light(int time) { // toggles the light every 5 simulation ticks
        boolean starting_state = state; // for use of alternating states
        if (time % 10 == 0) {
            state = starting_state;
        } else if (time % 5 == 0) {
            state = !starting_state;
        }
    }

    public void status() {
        if (state) {
            System.out.printf("%s is green on %s at position:%s%n", this.id, this.road.id, this.position);
        }
        else  {
            System.out.printf("%s is red on %s at position:%s%n", this.id, this.road.id, this.position);
        }
    }
}
