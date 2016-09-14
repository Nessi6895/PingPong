package ru.sbt.pingpong;

import static ru.sbt.pingpong.Sound.*;

public class PingPong {
    private final Object lock = new Object();
    private volatile Sound nextSound = PING;

    public void start(){
        new PingPongThread(PING).start();
        new PingPongThread(PONG).start();
    }

    private class PingPongThread extends Thread {
        private final Sound sound;

        public PingPongThread(Sound sound) {
            this.sound = sound;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    while (nextSound != sound) {
                        try {
                            lock.wait();
                        } catch (InterruptedException ignore) {
                        }
                    }
                    sound.printSound();
                    nextSound = sound.nextSound();
                    lock.notify();
                }
            }
        }
    }
}