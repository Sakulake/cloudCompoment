# aqs
AbstractQueenedSynchronizer


```plantUml

@startuml
interface Lock{

}

class class{}
 
@enduml
```


### lock
1. sync.lock()
2. fairlock
    1. acquire()
        1.if(!tryAcquire() && acquireQueened(addWaiter(Node.EXCLUSIVE),arg)){
            selfIntercept();
        }




##### tryAcquire 尝试设置状态==获得锁
1. getState() 
2. 判断当前锁状态
3. 如果==0
   1. 如果（！队列里有节点 ） && cas 更新状态 结果
   2. true : 设置拥有锁线程
   3. false : 返回false
4. else 
   1. 是否是占有该锁的线程
   2. true ： setState（+=arg）
   3. false： 返回false

##### addWaiter(Node.EXCLUSIVE) 创建线程对应的等待队列节点并放到队列中
1. 创建队列节点 new Node(Thread.currentThread());
2. CAS插入队列（如果队列为空，new Node() 当做队首=队尾）

##### acquireQueued 尝试把队列中的节点进行获取锁，失败有挂起
failed = true
intecepted = flase
自旋
1. 获取当前节点的前一个节点为predecessor
   1. 如果predecessor就是head &&  tryAcquire()
      1. 设置当前节点为head
      2. predecessor.next=null
      3. failed =true
      4. return intecepted
   2. 如果shouldParkAfterFailedAcquire(predecessor,node) && parkAndCheckInterrupt()
      1. interrupted =true
   3. 如果 failed 
      1. cancelAcquire

##### 如果shouldParkAfterFailedAcquire（pred，node）
1. 获取pred 的 waitStatus
2. 如果 waitStatus = -1 SIGNAL return true
3. else 如果 waitStatus >0  先前遍历， 找到第一个waitStatus <=0 的节点 设置node的pred
4. else i.e. waitStatus= 0 cas 设置 waitStatus =-1
5. return false

##### parkAndCheckInterrupt
1.  LockSupport.park(this);  
    1.  设置Thread.parkBlocker = 当前aqs 
    2.  阻塞当前线程
    3.  (被唤起后) 设置Thread.parkBlocker = null 
2.  return Thread.interrupted()