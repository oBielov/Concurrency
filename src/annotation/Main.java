package annotation;

public class Main {

    public static void main(String[] args) {
        MyThreadPoolExecutor poolExecutor = new MyThreadPoolExecutor(3);
        poolExecutor.execute(new AnnotationTest());
        poolExecutor.shutdown();
    }

}
