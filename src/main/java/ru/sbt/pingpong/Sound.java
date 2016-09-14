package ru.sbt.pingpong;

public enum Sound {
    PING {
        void printSound() {
            System.out.println("Ping");
        }

        Sound nextSound() {
            return PONG;
        }
    },
    PONG {
        void printSound() {
            System.out.println("Pong");
        }

        Sound nextSound() {
            return PING;
        }
    };
    abstract void printSound();
    abstract Sound nextSound();
}
