package builder;

import java.util.concurrent.Semaphore;

class WaterBuilder {


    private final Semaphore hSemaphore;
    private final Semaphore oSemaphore;

    public WaterBuilder() {
        hSemaphore = new Semaphore(2);
        oSemaphore = new Semaphore(0);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hSemaphore.acquire(1);
        releaseHydrogen.run();
        oSemaphore.release(1);
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oSemaphore.acquire(2);
        releaseOxygen.run();
        hSemaphore.release(2);
    }
}
