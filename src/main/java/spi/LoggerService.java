package spi;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.logging.Logger;

public class LoggerService  implements  LoggerSpi{




        @Override
        public void info(String msg) {
                System.out.println("info 中没有发现 Logger 服务提供者");
        }

        @Override
        public void debug(String msg) {
                System.out.println("debug 中没有发现 Logger 服务提供者");
        }

}
