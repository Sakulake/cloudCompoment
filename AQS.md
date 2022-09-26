# aqs
AbstractQueenedSynchronizer


```plantUml

@startuml
interface Lock{

}

class class{}
 
@enduml
```


### lock（ReentrantLock）
1. sync.lock()
2. fairlock
    1. acquire()
        1.if(!tryAcquire() && acquireQueened(addWaiter(Node.EXCLUSIVE),arg)){
            selfIntercept();
        }




##### tryAcquire 尝试设置状态==获得锁（aqs保护方法默认报错，ReentrantLock实现）
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

##### addWaiter(Node.EXCLUSIVE) 创建线程对应的等待队列节点并放到队列中（aqs）
1. 创建队列节点 new Node(Thread.currentThread());
2. CAS插入队列（如果队列为空，new Node() 当做队首=队尾）

##### acquireQueued 尝试把队列中的节点进行获取锁，失败有挂起（aqs）
failed = true
intecepted = flase
for (;;) {
1. 获取当前节点的前一个节点为predecessor
   1. 如果predecessor就是head &&  tryAcquire()
      1. 设置当前节点为head
      2. predecessor.next=null
      3. failed =true
      4. return intecepted
   2. 如果shouldParkAfterFailedAcquire(predecessor,node) && parkAndCheckInterrupt()
      1. interrupted =true
 }
   3. 如果 failed 
      1. cancelAcquire

##### 如果shouldParkAfterFailedAcquire（pred，node）（aqs）
1. 获取pred 的 waitStatus
2. 如果 waitStatus = -1 SIGNAL return true
3. else 如果 waitStatus >0  先前遍历， 找到第一个waitStatus <=0 的节点 设置node的pred
4. else i.e. waitStatus= 0 cas 设置 waitStatus =-1
5. return false

##### parkAndCheckInterrupt（aqs）
1.  LockSupport.park(this);  
    1.  设置Thread.parkBlocker = 当前aqs对象
    2.  阻塞当前线程
    3.  (被唤起后) 设置Thread.parkBlocker = null 
2.  return Thread.interrupted()


##### cancelAcquire（aqs）
1. 如果 node == null 直接return 
2. 将node.thread = null;
3. 将node.prev设置链表中向前遍历的第一个waitState<=0 的节点 pred
4. 如果节点==tail
    1. cas设置tail = pred 冰鞋 pred.next = null
5. else
    // 如果pred 不等于head && （pred的订单状态== signal || pred的订单状态<0 『condition|propagate』&& cas操作pred.waitState =SIGNAL) && pred.thread != null
    //判断node.next != null && node.next.waitStatus <=0;
    // cas 操作 pred.next = next;
    if (pred != head &&
                    ((ws = pred.waitStatus) == Node.SIGNAL ||
                     (ws <= 0 && compareAndSetWaitStatus(pred, ws, Node.SIGNAL))) &&
                    pred.thread != null) {
                    Node next = node.next;
                    if (next != null && next.waitStatus <= 0)
                        compareAndSetNext(pred, predNext, next);
    }else{
        unparkSuccessor(node);
    }
                    
                    
                    
##### unparkSuccessor（aqs）
1. 如果node.waitState <0 
    cas 操作  node.waitState = 0
2. if node.next==null || node.next.waitState>0
    从tail开始向前遍历，找到最后那个waitStatus<=0的节点
        LockSupport.unpark 唤起该节点线程






### unlock（reentrantLock）
1. sync.release(1);
2.  if (tryRelease(arg)) {
               // 唤起head后第一个 node.next.waitState<=0的节点
               Node h = head;
               if (h != null && h.waitStatus != 0)
                   unparkSuccessor(h);
               return true;
           }
           return false;
##### tryRelease（aqs 保护方法默认报错 reentrantLock实现）
 if (Thread.currentThread() != getExclusiveOwnerThread())
                throw new IllegalMonitorStateException();
            boolean free = false;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;


### lockInterruptibly(ReentrantLock)
1.  sync.acquireInterruptibly(1);

 if (Thread.interrupted())
    throw new InterruptedException();
 if (!tryAcquire(arg))
    doAcquireInterruptibly(arg);
    
##### doAcquireInterruptibly(aqs)
1. node= addWaiter(Node.EXCLUSIVE); // 加入链表末端
2. failed =true
for(;;){
    1. 获取node前节点
}
final Node node = addWaiter(Node.EXCLUSIVE);
        boolean failed = true;
        try {
            for (;;) {
                final Node p = node.predecessor();
                if (p == head && tryAcquire(arg)) {
                    setHead(node);
                    p.next = null; // help GC
                    failed = false;
                    return;
                }
                if (shouldParkAfterFailedAcquire(p, node) &&
                    parkAndCheckInterrupt())
                    throw new InterruptedException();
            }
        } finally {
            if (failed)
                cancelAcquire(node);
        }

### lock 和 lockInterruptibly 区别
lockInterruptibly在线程唤起后，如果线程被中断，不再参与锁获取
lock在线程唤起后，如果线程被中断，继续参与锁获取

### tryLock(ReentrantLock) // 非公平锁
1. sync.nonfairTryAcquire(1);
2.  final Thread current = Thread.currentThread();
               int c = getState();
               if (c == 0) {
                   if (compareAndSetState(0, acquires)) {
                       setExclusiveOwnerThread(current);
                       return true;
                   }
               }
               else if (current == getExclusiveOwnerThread()) {
                   int nextc = c + acquires;
                   if (nextc < 0) // overflow
                       throw new Error("Maximum lock count exceeded");
                   setState(nextc);
                   return true;
               }
               return false;
               
               
               
               
 // 如果锁标识是0，就cas操作state，如果成功，return true ，
 // 如果不是，判断拥有锁线程是否是当前线程，如果是 直接state+，返回true;如果不是，返回false
 
 
 ### Condition 
 一个aqs 可以创建多个condition对象，该对象维护一个单向链表node。如果有线程调用await，将当前线程放入链表尾部，node.waitState = -2(CONDITION)
 
 ##### await（aqs 中ConditionObject 实现）
 1. 如果线程被中断，抛出异常
 2. addConditionWaiter 将当前线程变成node加入当前condition对象的单向链表尾部，如果之前链表尾部节点状态不为CONDITION，清理链表所有状态不是CONDITION的节点
 3. fullyRelease完全释放锁
 4. while (!isOnSyncQueue(node)) {如果当前node不在aqs等待队列中，
    1. 阻塞当前线程
    2. 当线程被唤起后， if ((interruptMode = checkInterruptWhileWaiting(node)) != 0)
                                   break;
    }
 5.  if (acquireQueued(node, savedState) && interruptMode != THROW_IE)  // 将节点放入aqs队列并将前置节点设置为sigal -1
                    interruptMode = REINTERRUPT;
 6.  if (node.nextWaiter != null) // clean up if cancelled  // 如果当前condition对象的单向节点后续不为空，将取消状态的节点移出队列
                    unlinkCancelledWaiters();
 7.  if (interruptMode != 0)
                    reportInterruptAfterWait(interruptMode); // 抛出中断异常或者重新中断



##### awaitNanos（aqs 中ConditionObject 实现）


##### signal
1. 如果没有锁，抛出异常
2. 获取当前conditionObject.firstNode
3. doSignal()
    do {
                    if ( (firstWaiter = first.nextWaiter) == null)
                        lastWaiter = null;
                    first.nextWaiter = null;
                } while (!transferForSignal(first) &&
                         (first = firstWaiter) != null);
    // 将头节点的node放入aqs队列中

##### signalall
1. 如果没有锁，抛出异常
2. 获取当前conditionObject.firstNode
3. doSignalAll()
    1. 设置头节点、尾结点为 null；
    2. do {
                       Node next = first.nextWaiter;
                       first.nextWaiter = null;
                       transferForSignal(first);
                       first = next;
                   } while (first != null);
                   
##### transferForSignal
1. 设置节点状态为condition -> 0
2. 节点进入aqs队列
3. 若果节点的状态>0或者 设置节点状态为-1（signal）失败，阻塞节点线程

        /*
         * If cannot change waitStatus, the node has been cancelled.
         */
        if (!compareAndSetWaitStatus(node, Node.CONDITION, 0))
            return false;

        /*
         * Splice onto queue and try to set waitStatus of predecessor to
         * indicate that thread is (probably) waiting. If cancelled or
         * attempt to set waitStatus fails, wake up to resync (in which
         * case the waitStatus can be transiently and harmlessly wrong).
         */
        Node p = enq(node);
        int ws = p.waitStatus;
        if (ws > 0 || !compareAndSetWaitStatus(p, ws, Node.SIGNAL))
            LockSupport.unpark(node.thread);
        return true;

# 常用工具使用
1. CountDownLatch
   countdown 减少state -1，如果减到0,unpark所有队列里线程
   await = 在state=0时获取锁，如果不等于0,则park当前线程
    用完以后不能重复使用
2. CyclicBarrier
    利用一个condition对象trip存储阻塞线程，利用一个Generation对象标志一次等待
    await() 线程进入等待，当index==0 时，执行申明CyclicBarrier是传入的barrierCommand
    并且调用nextGeneration(signalAll、创建下一代 generation)
3. Semaphore