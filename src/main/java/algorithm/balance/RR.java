package algorithm.balance;

/**
 * @program: jmm
 * @description: 轮询
 * @Author: xiang
 * @create: 2023/7/25 14:01
 * @Version 1.0
 */
public class RR {

    class Server{
        //上节点连接
        Server prev;
        //下节点连接
        Server next;
        //节点名称
        String name;

        public Server(String name){
            this.name = name;
        }
    }

    /**
     * 当前服务节点
     */
    Server current;

    /**
     * 初始化 轮询类   多个服务节点ip用逗号隔开
     * @param servername
     */
    public RR(String servername){
        System.out.println("init server list:"+servername);
        String[] split = servername.split(",");
        for(String str : split){
            Server server = new Server(str);
            //当前服务阶段为null,说明第一台机器，current就指向新创建的server
            if(null==current){
                this.current = server;
                //同时 server的前后均指向自己
                current.prev=current;
                current.next=current;
            }else addServer(str);  //否则说明已经有机器了，按新加处理
        }

    }

    /**
     * 添加机器
     * @param servername 服务地址
     */
    private void addServer(String servername) {
        System.out.println("add server:"+servername);
        Server server = new Server(servername);
        Server next = this.current.next;

        //在当前节点后插入新的节点
        this.current.next =server;
        server.prev=this.current;
        //修改下一节点的prev指针
        server.next=next;
        next.prev=server;
    }

    /**
     * 将当前服务器移除，同时修改前后节点的指针，让其直接关联
     * 移除的current会被回收器回收掉
     */
    public void  remove(){
        System.out.println("remove current="+current.name);
        this.current.prev.next=this.current.next;
        this.current.next.prev=this.current.prev;
        this.current=current.next;
    }

    /**
     * 请求由当前节点处理即可
     * 注意 处理完成后 current指针后遗
     */
    public void request(){
        System.out.println(this.current.name);
        this.current=current.next;
    }

    public static void main(String[] args) throws InterruptedException {
        //初始化两台机器
        RR rr = new RR("192.168.1.21,192.168.1.22");

        //启动额外线程，模拟不断请求
        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(100);
                }catch(Exception e){e.printStackTrace();}
                rr.request();
            }
        }).start();
        //3s后，3号机器加入清单
        Thread.currentThread().sleep(3000);
        rr.addServer("192.168.1.23");

        //3s后，当前服务节点移除
        Thread.currentThread().sleep(3000);
        rr.remove();
    }


}
