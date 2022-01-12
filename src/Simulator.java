import Models.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Simulator extends JPanel {

    public enum State {
        STOPPED, RUNNING, FINISHED
    }

    private State state = State.STOPPED;
    private int scale;
    private ArrayList<Road> roads;
    private ArrayList<Car> cars = new ArrayList<>();
    private ArrayList<TrafficLight> lights;
    private Timer timer;
    private Boolean stop = true;
    private Random random = new Random();
    private static int cycle = 0;
    private int cars_spawning = 4;
    private int cars_spawned = 0;
    private int cars_gone = 0;
    private int cycles = 30;
    private int speed = 1000;

    public void loadMap(ArrayList<Road> roads, ArrayList<TrafficLight> lights) {
        this.roads = roads;
        this.lights = lights;
    }

    public void setVehicleSpawn(int spawns) {
        this.cars_spawning = spawns - 1;
        createVehicle();
    }

    public void setVehicleSpawnRate(int rate) {
        this.cycles = rate;
    }

    private void createVehicle() {
        cars.add(new Car(Integer.toString(cycle), roads.get(0)));
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void simulate() {
        setLayout(new BorderLayout());
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(1, 0));
        infoPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        JLabel vehicleLabel = new JLabel("Vehicles: ");
        infoPanel.add(vehicleLabel);
        JLabel averageSpeedLabel = new JLabel("Average Speed: ");
        infoPanel.add(averageSpeedLabel);
        JLabel stateLabel = new JLabel("State: " + state);
        infoPanel.add(stateLabel);
        add(infoPanel, BorderLayout.SOUTH);

        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(speed / 60, e -> {
            cycle++;
            if (cars.size() == 0) {
                state = State.FINISHED;
            } else if (stop) {
                state = State.STOPPED;
            } else {
                state = State.RUNNING;
            }
            stateLabel.setText("State: " + state);
            vehicleLabel.setText("Vehicles: " + getTotalVehicles());
            averageSpeedLabel.setText("Average Speed:" + getAverageSpeed());
            if (cars.size() == 0 || stop) {
                timer.stop();
            }
            if (cycle % 30 == 0) { //light operates every x tics
                for (TrafficLight light : lights) {
                    light.light(random.nextInt());
                    light.status();
                }
            }
            for (Iterator<Car> iterator = cars.iterator(); iterator.hasNext(); ) {
                Car car = iterator.next();
                car.move();
                car.status();
                if (car.getPosition() + car.getLength() + car.getSpeed() >= car.getRoad().getLength() && car.getRoad().getRoads().isEmpty() && (car.getSpeed() == 0)) {
                    car.getRoad().getCars().remove(car);
                    iterator.remove();
                    cars_gone++;
                }
            }

            if (cycle % cycles == 0 && cars_spawned < cars_spawning) {
                createVehicle();
                cars_spawned++;
            }
            repaint();
        });
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Road road : roads
        ) {
            road.draw(g, scale);
        }

        if (!cars.isEmpty()) {
            for (Car car : cars
            ) {
                car.draw(g, scale);
            }
        }

        for (TrafficLight light : lights
        ) {
            light.draw(g, scale);
        }
    }

    private int getTotalVehicles() {
        return cars_spawned + 1 - cars_gone;
    }

    private String getAverageSpeed() {
        int totalSpeed = 0;
        for (Car car : cars) {
            totalSpeed = totalSpeed + car.getSpeed();
        }
        if (!cars.isEmpty()) {
            int average = totalSpeed / cars.size();
            return average * 3.6 + "km/h";
        } else {
            return "0";
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public void setStopSim(Boolean stop) {
        this.stop = stop;
    }
}
