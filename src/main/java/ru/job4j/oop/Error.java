package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Активность: " + active);
        System.out.println("Статус: " + status);
        System.out.println("Сообщение: " + message);
    }

    public static void main(String[] args) {
        Error error = new Error();
        Error err1 = new Error(false, 23, "\"6, 18\"");
        Error err2 = new Error(true, 12, "\"4, 7\"");
        error.printInfo();
        err1.printInfo();
        err2.printInfo();
    }
}
