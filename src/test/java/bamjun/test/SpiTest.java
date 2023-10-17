package bamjun.test;

import spi.LoggerSpi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpiTest {

    public static void main(String[] args) {
        ServiceLoader<LoggerSpi> load = ServiceLoader.load(LoggerSpi.class);

        Iterator<LoggerSpi> iterator = load.iterator();
        while (iterator.hasNext()){
            LoggerSpi next = iterator.next();
            System.out.println("class:" + next.getClass().getName());
            next.info("kaishili");
        }
    }
}
