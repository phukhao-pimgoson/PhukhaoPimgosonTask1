import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarTest {
    Road road = new Road("0");
    Car car = new Car("0", road);

    @Test
    void testMove() {
        car.move();
        assertEquals(2, car.position);
    }

    @Test
    void getSpeed() {
        assertEquals(0, car.speed);
    }

    @Test
    void getPosition() {
        assertEquals(0, car.position);
    }

    @Test
    void getRoad() {
        assertEquals(road, car.road);
    }

    @Test
    void getId() {
        assertEquals("car_0", car.id);
    }

}