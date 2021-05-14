package builder;

import java.util.concurrent.Semaphore;

/**
 * Class made for building water molecule from two hydrogen
 * and one oxygen atoms. Based on Semaphore class to be sure
 * if molecule consists exactly from two "H" and one "O".
 * @see Semaphore
 */
class WaterBuilder {


    private final Semaphore hSemaphore;
    private final Semaphore oSemaphore;
    Runnable releaseHydrogen = ()-> System.out.print("H");
    Runnable releaseOxygen = ()-> System.out.print("O");
    int molecules;

    //init

    /**
     * Semaphores called with "true" arg. to build exact FIFO chain HHO
     * @param molecules number of chains needed to produce
     */
    public WaterBuilder(int molecules) {
        hSemaphore = new Semaphore(2, true);
        oSemaphore = new Semaphore(0, true);
        this.molecules = molecules;
    }

    //logic

    /**
     * Semaphore for "H" element.
     * @param releaseHydrogen will return "H" string
     * @throws InterruptedException
     */
    private void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        hSemaphore.acquire(1);
        releaseHydrogen.run();
        oSemaphore.release(1);
    }

    /**
     * Semaphore for "O" element
     * @param releaseOxygen will return "O" string
     * @throws InterruptedException
     */
    private void oxygen(Runnable releaseOxygen) throws InterruptedException {
        oSemaphore.acquire(2);
        releaseOxygen.run();
        hSemaphore.release(2);
    }

    /**
     * Main method to form a chain of hydrogen and oxygen elements
     * Uses InputTank class that provides string of "O" and "H" characters
     * Outputs formed "HHO" chains with given {@link #molecules} param.
     * @see InputTank
     */
    public void build(){
        String input = InputTank.buildChain(molecules);

        System.out.println("Output: ");

        for(int i = 0; i < input.length(); i++ ){

            switch (input.charAt(i)) {
                case 'O' -> new Thread(() -> {
                    try {
                        oxygen(releaseOxygen);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
                case 'H' -> new Thread(() -> {
                    try {
                        hydrogen(releaseHydrogen);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }


}
