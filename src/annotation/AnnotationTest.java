package annotation;

@Repeat(5)
public class AnnotationTest implements Runnable {

    @Override
    public void run() {
        System.out.println("This message will be repeated");
    }
}
