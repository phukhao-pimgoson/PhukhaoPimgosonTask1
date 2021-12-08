import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoadTest {
    Road road = new Road("0");

    @Test
    void getId() {
        assertEquals("road_0", road.id);
    }

    @Test
    void getSpeedLimit() {
        assertEquals(1, road.speed_limit);
    }

    @Test
    void getLength() {
        assertEquals(10, road.length);
    }

    @Test
    void getCars() {
        ArrayList<Car> expected = new ArrayList<>();
        assertEquals(expected, road.cars);
    }

    @Test
    void getLights() {
        ArrayList<TrafficLight> expected = new ArrayList<>();
        assertEquals(expected, road.lights);
    }
}