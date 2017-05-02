package com.hdj.algorithm.structure


/**
  * Author: 端吉
  * Date:   2017/4/9.
  *
  * 写一个简单的栈，实现如下方法:
  * push()
  * pop()
  * size()
  * isEmpty()
  * 内部使用数组方式实现
  *
  * 由于泛型在运行时擦除，所以为了new Array[T] 需要使用这个Manifest
  */
class ArrayStack[T >: Null : Manifest] {

  /**
    * 使用var因为扩容时候可能会使用新的数组
    */
  private var stackDatas = new Array[T](5)

  /**
    * 指向下一个要插入的元素
    */
  var currentIndex = 0

  /**
    * 往栈中添加数据
    *
    * @param data
    */
  def push(data: T) = {
    if (currentIndex > stackDatas.length - 1) {
      //数组扩容
      resize(stackDatas.length * 2)
      println(s"数组扩容：${stackDatas.length}")
    }
    stackDatas(currentIndex) = data
    currentIndex += 1
    //    println(s"push and currentIndex:$currentIndex")
  }

  /**
    * 弹出栈顶元素
    *
    * @return
    */
  def pop(): Option[T] = {

    if (currentIndex < stackDatas.length / 4) {
      //数组扩容
      resize(stackDatas.length / 2);

      println(s"数组缩容：${stackDatas.length}")
    }

    if (currentIndex > 0) {
      val result = stackDatas(currentIndex - 1)
      stackDatas(currentIndex - 1) = null
      currentIndex -= 1
      //      println(s"pop and currentIndex:$currentIndex")
      Some(result)
    } else {
      None
    }

  }

  def resize(size: Int): Unit = {
    val oldArrayDatas = stackDatas;
    stackDatas = new Array[T](size)
    Array.copy(oldArrayDatas, 0, stackDatas, 0, currentIndex)
  }

  def isEmpty(): Boolean = {
    stackDatas.length == 0
  }

  def size(): Int = {
    //    print(s"size currentIndex:$currentIndex")
    currentIndex

  }
}

object ArrayStack {
  def main(args: Array[String]): Unit = {
    val stack = new ArrayStack[String]
    stack.push("1")
    stack.push("2")
    stack.push("3")
    stack.push("4")
    stack.push("5")
    stack.push("6")
    stack.push("7")
    stack.push("8")

    println(s"result: pop1:${stack.pop()},pop2:${stack.pop()},pop3:${stack.pop()},pop4:${stack.pop()}，pop5:${stack.pop()}")

    println(s"stack.isEmpty:${stack.isEmpty()}, stack.size: ${stack.size()}")
  }
}