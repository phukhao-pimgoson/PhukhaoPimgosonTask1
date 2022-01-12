package Models;

import java.awt.*;
import java.util.ArrayList;

public class Road {

    public enum Orientation {
        HORIZONTAL, VERTICAL
    }

    private Orientation orientation;
    private String id;
    private int speed_limit;
    private int length;
    private int width;
    private int[] start_position;
    private int[] end_position;
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<TrafficLight> lights = new ArrayList<>();
    private ArrayList<Road> roads = new ArrayList<>();


    public Road(String id, int speedLimit, int length, int[] start_position, Orientation orientation) {
        this.id = "road_" + id;
        this.speed_limit = speedLimit;
        this.length = length;
        width = 8;
        this.orientation = orientation;
        this.start_position = start_position;
        setEndLocation();
    }

    public void draw(Graphics g, int scale) {
        if (orientation == Orientation.HORIZONTAL) {
            int[] startLocation = this.start_position;
            int x = startLocation[0] * scale;
            int y = startLocation[1] * scale;
            int width = length * scale;
            int height = this.width * scale;
            g.setColor(Color.darkGray);
            g.fillRect(x, y, width, height);
            //Center Lines
            g.setColor(Color.white);
            g.fillRect(x, y + (height / 2) - scale / 6, width, scale / 6);
            g.fillRect(x, y + (height / 2) + scale / 6, width, scale / 6);
        } else if (orientation == Orientation.VERTICAL) {
            int[] startLocation = this.start_position;
            int x = startLocation[0] * scale;
            int y = startLocation[1] * scale;
            int width = this.width * scale;
            int height = length * scale;
            g.setColor(Color.darkGray);
            g.fillRect(x, y, width, height);
            //Center Lines
            g.setColor(Color.white);
            g.fillRect(x + (width / 2) - scale / 6, y, scale / 6, height);
            g.fillRect(x + (width / 2) + scale / 6, y, scale / 6, height);
        }
    }


    String getId() {
        return id;
    }

    public int getSpeed_limit() {
        return speed_limit;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    private void setEndLocation() {
        if (orientation == Orientation.HORIZONTAL) {
            this.end_position = new int[]{this.length + this.start_position[0], this.start_position[1]}; //only works for horizontal roads;
        } else if (orientation == Orientation.VERTICAL) {
            this.end_position = new int[]{this.start_position[1], this.length + this.start_position[0]};
        }
    }

    public int[] getStart_position() {
        return start_position;
    }

    public int[] getEnd_position() {
        return end_position;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public ArrayList<TrafficLight> getLights() {
        return lights;
    }

    public ArrayList<Road> getRoads() {
        return roads;
    }

    Orientation getOrientation() {
        return orientation;
    }

}
