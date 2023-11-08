package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Ехать по указанному маршруту со всеми остановками");
    }

    @Override
    public void passengers(int num) {
        System.out.println("Количество пассажиров: " + num);
    }

    @Override
    public double refill(int volume) {
        return volume * 62.40;
    }
}
