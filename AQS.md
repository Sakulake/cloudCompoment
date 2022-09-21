# AQS
## 概念
AbstractQueenedSychronizer的简写。

利用一个原子变量 + 先进先出的链表队列 + unsafe 类库方法 

一个实现同步锁的基础框架。

子类需要实现protected的方法来操作原子变量，来完成最终的同步器。

子类应该分为两种模式 shared  exclusive

还有个内部类 Condition Object

##  结构

