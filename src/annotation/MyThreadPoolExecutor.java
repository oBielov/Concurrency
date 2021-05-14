package annotation;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolExecutor extends ThreadPoolExecutor {


    //init
    public MyThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize, corePoolSize, 0l, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>());
    }

    /**
     * Check if runnable command has Repeat annotation, if so
     * executes command as many times as pointed in Repeat argument.
     * @param command Runnable object
     * @see Runnable
     */
    @Override
    public void execute(Runnable command){
        if(command.getClass().isAnnotationPresent(Repeat.class)){
            int repeat = command.getClass().getAnnotation(Repeat.class).value();
            while (--repeat > 0){
                super.execute(command);
            }
        }
        super.execute(command);
    }

}
