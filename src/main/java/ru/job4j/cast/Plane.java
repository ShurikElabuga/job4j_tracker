package ru.job4j.cast;

public class Plane implements Vehicle {
    @Override
    public void move() {
        System.out.println("Самолет летит благодоря подЪемной силе крыла и реактивной тяге двигателей.");
    }
}
