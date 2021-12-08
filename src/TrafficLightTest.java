import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrafficLightTest {
    Road road = new Road("0");
    TrafficLight light = new TrafficLight("0", road);

    @Test
    void testLight() {
        light.light(0);
        assertTrue(light.state);
        assertFalse(light.state);
    }

    @Test
    void getRoad() {
        assertEquals(road, light.road);
    }

    @Test
    void getPosition() {
        assertEquals(5, light.position);
    }

    @Test
    void getId() {
        assertEquals("light_0", light.id);
    }
}