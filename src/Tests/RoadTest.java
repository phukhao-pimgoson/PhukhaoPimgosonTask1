package Tests;

import Models.Road;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoadTest {
    Models.Road road = new Models.Road("0", 1, 5, new int[]{0, 0}, Road.Orientation.HORIZONTAL);

    @Test
    void getId() {
        assertEquals(5, road.getLength());
    }

    @Test
    void getSpeedLimit() {
        assertEquals(1, road.getSpeed_limit());
    }

    @Test
    void getLength() {
        assertEquals(5, road.getLength());
    }

    @Test
    void getStartLocationTest() {
        int[] expected1 = {0, 0};
        int[] actual = this.road.getStart_position();
        assertArrayEquals(expected1, actual);
    }

    @Test
    void getEndLocation() {
        int[] expected = {5, 0};
        assertArrayEquals(expected, road.getEnd_position());
    }

    @Test
    void getCars() {
        ArrayList<Models.Car> expected = new ArrayList<>();
        assertEquals(expected, road.getCars());
    }

    @Test
    void getLights() {
        ArrayList<Models.TrafficLight> expected = new ArrayList<>();
        assertEquals(expected, road.getLights());
    }

    @Test
    void getConnectedRoads() {
        ArrayList<Models.Road> expected = new ArrayList<>();
        assertEquals(expected, road.getRoads());
    }
}