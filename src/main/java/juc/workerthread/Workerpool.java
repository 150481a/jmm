package juc.workerthread;

import java.util.concurrent.*;

/**
 *
 * 注意在初始化 ThreadPoolExecutor 时，我们保持初始池大小为 2，最大池大小为 4 而工作队列大小为 2。因此如果已经有四个正在执行的任务而此时分配来更多任务的话，工作队列将仅仅保留他们(新任务)中的两个，其他的将会被 RejectedExecutionHandlerImpl 处理。
 * 性质不同的任务可用使用不同规模的线程池分开处理:
 *  CPU密集型: 尽可能少的线程，Ncpu+1
 *  IO密集型: 尽可能多的线程, Ncpu*2，比如数据库连接池
 *  混合型: CPU密集型的任务与IO密集型任务的执行时间差别较小，拆分为两个线程池；否则没有必要拆分。
 *  为什么很多公司不允许使用Executors去创建线程池? 那么推荐怎么使用呢?
 *
 * 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明：Executors各个方法的弊端：
 *  newFixedThreadPool和newSingleThreadExecutor: 主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
 *  newCachedThreadPool和newScheduledThreadPool: 主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。
 * 推荐方式 1 首先引入：commons-lang3包
 *  ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
 *         new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
 * 推荐方式 2 首先引入：com.google.guava包
 *  ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
 *
 * //Common Thread Pool
 * ExecutorService pool = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
 *
 * // excute
 * pool.execute(()-> System.out.println(Thread.currentThread().getName()));
 *
 *  //gracefully shutdown
 * pool.shutdown();
 *
 * 推荐方式3 spring配置线程池方式：自定义线程工厂bean需要实现ThreadFactory，可参考该接口的其它默认实现类，使用方式直接注入bean调用execute(Runnable task)方法即可
 *  <bean id="userThreadPool" class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
 *         <property name="corePoolSize" value="10" />
 *         <property name="maxPoolSize" value="100" />
 *         <property name="queueCapacity" value="2000" />
 *
 *     <property name="threadFactory" value= threadFactory />
 *         <property name="rejectedExecutionHandler">
 *             <ref local="rejectedExecutionHandler" />
 *         </property>
 *     </bean>
 */
public class Workerpool {

    public static void main(String[] args) throws InterruptedException {
        //实现 RejectedExecutionHandler  拒绝策略
        RejectedExecutionHandlerImpl rejectedExecutionHandler = new RejectedExecutionHandlerImpl();

        // 创建线程的工厂，通过自定义的线程工厂可以给每个新建的线程设置一个具有识别度的线程名。默认为DefaultThreadFactory
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        //创建线程池
        /**
         * corePoolSize 线程池中的核心线程数，当提交一个任务时，线程池创建一个新线程执行任务，直到当前线程数等于corePoolSize, 即使有其他空闲线程能够执行新来的任务, 也会继续创建线程；如果当前线程数为corePoolSize，继续提交的任务被保存到阻塞队列中，等待被执行；如果执行了线程池的prestartAllCoreThreads()方法，线程池会提前创建并启动所有核心线程。
         * workQueue 用来保存等待被执行的任务的阻塞队列. 在JDK中提供了如下阻塞队列: 具体可以参考JUC
         *      ArrayBlockingQueue: 基于数组结构的有界阻塞队列，按FIFO排序任务；
         *      LinkedBlockingQueue: 基于链表结构的阻塞队列，按FIFO排序任务，吞吐量通常要高于ArrayBlockingQueue；
         *      SynchronousQueue: 一个不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue；
         *      PriorityBlockingQueue: 具有优先级的无界阻塞队列；
         *  LinkedBlockingQueue比ArrayBlockingQueue在插入删除节点性能方面更优，但是二者在put(), take()任务的时均需要加锁，SynchronousQueue使用无锁算法，根据节点的状态判断执行，而不需要用到锁，其核心是Transfer.transfer().
         * maximumPoolSize  线程池中允许的最大线程数。如果当前阻塞队列满了，且继续提交任务，则创建新的线程执行任务，前提是当前线程数小于maximumPoolSize；当阻塞队列是无界队列, 则maximumPoolSize则不起作用, 因为无法提交至核心线程池的线程会一直持续地放入workQueue.
         * keepAliveTime  线程空闲时的存活时间，即当线程没有任务执行时，该线程继续存活的时间；默认情况下，该参数只在线程数大于corePoolSize时才有用, 超过这个时间的空闲线程将被终止；
         * unit  keepAliveTime的单位
         * threadFactory  创建线程的工厂，通过自定义的线程工厂可以给每个新建的线程设置一个具有识别度的线程名。默认为DefaultThreadFactory
         * handler  线程池的饱和策略，当阻塞队列满了，且没有空闲的工作线程，如果继续提交任务，必须采取一种策略处理该任务，线程池提供了4种策略:
         *      AbortPolicy: 直接抛出异常，默认策略；
         *      CallerRunsPolicy: 用调用者所在的线程来执行任务；
         *      DiscardOldestPolicy: 丢弃阻塞队列中靠最前的任务，并执行当前任务；
         *      DiscardPolicy: 直接丢弃任务；当然也可以根据应用场景实现RejectedExecutionHandler接口，自定义饱和策略，如记录日志或持久化存储不能处理的任务
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2
                , 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectedExecutionHandler);
        //启动监控线程
        MyMonitorThread myMonitorThread = new MyMonitorThread(threadPoolExecutor, 3);
        Thread thread = new Thread(myMonitorThread);
        thread.start();
        //提交线程任务
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(new WorkerThreadDemo("cmd" + i));
        }

        Thread.sleep(3000);

        //关闭线程池
        threadPoolExecutor.shutdown();

        Thread.sleep(5000);
        //关闭线程任务
        myMonitorThread.shutdown();
    }
}
