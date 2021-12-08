public class Car {
    public String id;
    public int speed;
    public int position;
    public Road road;

    public Car(String id, Road road) {
        this.id = "car_" + id;
        this.road = road;
        this.speed = 0;
        this.position = 0; // start at the beginning of the single road for this program version
        this.road.cars.add(this); // add the car on the road
    }

    public void move() {
        if (!this.road.lights.isEmpty() && this.position == this.road.lights.get(0).position && this.road.lights.get(0).state) {
            this.speed = 0;
        }
        else {
            this.speed = this.road.speed_limit;
            if (this.position >= this.road.length) {
                this.road.cars.remove(this);
            }
            else {
                this.position = this.position + this.speed;
            }
        }
    }

    public void status() {
        System.out.printf("%s going:%dm/s on %s at position:%s%n", this.id, this.speed, this.road.id, this.position);
    }
}
