package com.blueapps.thoth.render;

import com.blueapps.thoth.cache.Storage;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class RenderThread extends Thread {

    private final AtomicReference<Callable<Storage>> task = new AtomicReference<>();
    private volatile boolean running = true;
    private Result result;

    @Override
    public void run() {
        while (running){
            Callable<Storage> callable = task.getAndSet(null);

            if (callable != null){
                try {
                    Storage storage = callable.call();
                    if (result != null) result.result(storage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Let Thread sleep until there is work to do
                LockSupport.park();
            }
        }
    }

    public void submit(Callable<Storage> newTask, Result result){
        if (newTask == null) return;

        this.result = result;
        task.set(newTask);

        // wake Thread up, if it is sleeping
        LockSupport.unpark(this);
    }

    public void stopThread() {
        running = false;

        // wake Thread up to exit loop
        LockSupport.unpark(this);
    }

    public interface Result {

        void result(Storage storage);

    }
}
