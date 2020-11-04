package pers.qwr.core.demo;


import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

public class CoreAction {
    private static String connectString = "node1:2181,node2:2181,node3:2181";
    private static int sessionTimeout = 2000;
    private static ZooKeeper zkClient = null;

    /**
     * 初始化客户端
     * @ throws IOException
     */
    static {
        try {
            zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
                public void process(WatchedEvent event) {
                    // 收到事件通知后的回调函数
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        getChildren();
//        create();
//        exist();
//        getData();
//        setData();
    }

    // 获取子节点
    public static void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }
        // 延时阻塞
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 　定义创建节点方法createZnode，调用客户端create方法，设置路径、编辑内容并转化为byte类型、应答类型、节点类型，然后将路径赋值给字符串变量path，输出path；
     * 参数1：要创建的节点的路径； 参数2：节点数据 ； 参数3：节点权限 ；参数4：节点的类型
     */
    public static void create() throws Exception {
        String nodeCreated = zkClient.create("/atguigu2", "jinlian".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("node create result:" + nodeCreated);
    }

    //判断路径是否存在
    public static void exist() throws Exception {
        Stat stat = zkClient.exists("/qwrZK", false);
        System.out.println(stat == null ? "node /qwrZK exists " : stat.toString());
    }

    //获取节点数据
    public static void getData() throws KeeperException, InterruptedException {
        byte[] data = zkClient.getData("/qwrZK", false, null);
        System.out.println(new String(data));
    }

    /**
     * 定义方法deleteZnode，调用delete方法，设置目标节点、版本值（-1为删除），然后可以遍历目标删除节点的父节点；
     */
    public void deleteZnode() throws KeeperException, InterruptedException {
        String path = "/java6";
        for (String str : zkClient.getChildren(path, false)) {
            System.out.println(str);
            String newPath = path + "/" + str;
            zkClient.delete(newPath, -1);
        }
        zkClient.delete(path, -1);
    }

    /**
     * 定义方法setZnode，调用setData方法，设置目标节点并转化为byte类型、版本值（-1），调用getData方法，设置查看节点、是否监听、状态（new Stat()），
     * 将得到的值赋值给字节集合data，将data强转为string类型数据进行查看。
     */
    public static void setData() throws KeeperException, InterruptedException {
        zkClient.setData("/qwrZK", "dou ni wan!".getBytes(), -1);
    }

}
