package com.mu.utils.man;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class QueueMan {

    private ArrayList<Runnable> runnables = new ArrayList<>();
    private int delay;
    private boolean async;
    private Thread t;

    public QueueMan() {
        this.t = new Thread(() -> {
            while (true) {
                try {
                    if (this.async) {
                        Thread r = new Thread(() -> {
                            this.runnables.get(0).run();
                            this.runnables.remove(this.runnables.get(0));
                        });
                        r.setDaemon(true);
                        r.start();
                    } else {
                        this.runnables.get(0).run();
                        this.runnables.remove(this.runnables.get(0));
                    }
                    TimeUnit.MILLISECONDS.sleep(this.delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        this.t.start();
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public boolean isAsync() {
        return this.async;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return this.delay;
    }

    public void queueTask(Runnable run) {
        this.runnables.add(run);
    }

    public void shutdown() {
        this.t.stop();
    }
}
