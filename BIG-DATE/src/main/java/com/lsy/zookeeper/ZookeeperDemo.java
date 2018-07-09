package com.lsy.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class ZookeeperDemo {
	public static void main(String[] args) throws Exception {
		ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 30000, new Watcher() {
			
			public void process(WatchedEvent event) {
				System.out.println("监听到事件"+event);
				
			}
		});
		String create = zk.create("/test", "hello word".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		System.out.println(create);
		Thread.sleep(10000);
	}
}
