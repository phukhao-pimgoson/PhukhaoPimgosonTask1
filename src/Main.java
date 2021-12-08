import java.util.ArrayList;

public class Main {
    public static void main() {
        ArrayList<Road> roads = new ArrayList<>();
        ArrayList<Car> cars = new ArrayList<>();
        ArrayList<TrafficLight> lights = new ArrayList<>();
        roads.add(new Road("0"));
        cars.add(new Car("0", roads.get(0)));
        lights.add(new TrafficLight("0", roads.get(0)));
        int time = 0;
        int cars_finished = 0;
        while (cars_finished < cars.size()) {
            for (TrafficLight light : lights) {
                light.light(time);
                light.status();
            }
            for (Car car : cars) {
                car.move();
                car.status();
                if (car.road.cars.isEmpty() && (car.speed == 0)) {
                    cars_finished = cars_finished + 1;
                }
            }
            time = time + 1;
            System.out.println(time + " Seconds have passed.\n");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException sim) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
