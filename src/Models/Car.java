package Models;

import java.awt.*;
import java.util.Random;

public class Car {

    private static final int STOPPED = 0;
    private static final int START_POSITION = 0;
    int length; // number of segments occupied
    int breadth;
    String id; // unique identifier
    private int speed; //segments moved per turn
    private Road road; // current Model.Road object
    int position; // position on current road
    private Color colour;
    private Random random = new Random();


    public Car(String id, Road currentRoad) {
        this.id = "car_" + id;
        length = 4;
        breadth = 2;
        speed = 0;
        this.road = currentRoad;
        currentRoad.getCars().add(this); //add this car to the road its on.
        colour = randomColour();
    }

    public void move() {
        Random random = new Random();
        int nextPosition = position + length + speed;
        //vehicle in front check:
        for (Car nextCar : road.getCars()) {
            if (nextCar.position > position && nextCar.position <= nextPosition + 4) {
                speed = STOPPED;
                break;
            } else {
                speed = road.getSpeed_limit();
            }
        }
        //red light check:
        if (speed != STOPPED) {
            if (!road.getLights().isEmpty() &&
                    nextPosition + 1 >= road.getLights().get(0).getPosition() &&
                    this.road.getLights().get(0).getState().equals("red")) {
                speed = STOPPED;
            } else {
                speed = road.getSpeed_limit();
                if (road.getLength() <= nextPosition && !road.getRoads().isEmpty()) {
                    road.getCars().remove(this);
                    int nextRoadIndex = random.nextInt(2);
                    road = road.getRoads().get(nextRoadIndex);
                    road.getCars().add(this);
                    position = START_POSITION;
                } else if (road.getLength() >= nextPosition) {
                    position = (position + speed);
                } else {
                    speed = STOPPED;
                }
            }
        }
    }

    public void draw(Graphics g, int scale) {
        int xValue = 0;
        int yValue = 1;
        if (road.getOrientation() == Road.Orientation.HORIZONTAL) {
            int[] startLocation = getRoad().getStart_position();
            int width = getLength() * scale;
            int height = getBreadth() * scale;
            int x = (getPosition() + startLocation[xValue]) * scale;
            int y = (startLocation[yValue] * scale) + scale;
            g.setColor(colour);
            g.fillRect(x, y, width, height);
        } else if (road.getOrientation() == Road.Orientation.VERTICAL) {
            int[] startLocation = getRoad().getStart_position();
            int width = getBreadth() * scale;
            int height = getLength() * scale;
            int x = (startLocation[xValue] * scale) + ((road.getWidth() * scale) - (width + scale));
            int y = (getPosition() + startLocation[yValue]) * scale;
            g.setColor(colour);
            g.fillRect(x, y, width, height);
        }
    }

    private Color randomColour() {
        int r = random.nextInt(245 + 1) + 10;
        int g = random.nextInt(245 + 1) + 10;
        int b = random.nextInt(245 + 1) + 10;
        return new Color(r, g, b);
    }


    public void status() {
        System.out.printf("%s going:%dm/s on %s at position:%s%n", this.getId(), this.getSpeed(),
                this.getRoad().getId(), this.getPosition());
    }

    public int getLength() {
        return length;
    }

    void setLength(int length) {
        this.length = length;
    }

    public int getBreadth() {
        return breadth;
    }

    public int getSpeed() {
        return speed;
    }

    public int getPosition() {
        return position;
    }

    public Road getRoad() {
        return road;
    }

    public String getId() {
        return id;
    }

}
