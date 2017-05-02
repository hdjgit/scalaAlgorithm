package com.hdj.algorithm.structure

/**
  * Author: 端吉
  * Date:   2017/5/2.
  * 链表实现的Stack，实现了
  * push 入栈
  * pop  出栈
  * size 栈大小
  * isEmpty 栈是否为空
  */
class LinkedStack[T] {

    var currentNode: Node[T] = _
    var size: Int = 0

    def push(data: T) = {
        val newNode = new Node[T](data)
        newNode.pre = currentNode
        currentNode = newNode
        size += 1
    }

    def pop(): Option[T] = {
        if (currentNode != null) {
            val result = currentNode.value
            currentNode = currentNode.pre
            size -= 1
            Some(result)
        } else {
            None
        }
    }

    def isEmpty(): Boolean = {
        currentNode == null
    }


}

class Node[T](val value: T) {
    var pre: Node[T] = _ //初始化为默认值
}

object LinkedStack {
    def main(args: Array[String]): Unit = {
        val stack = new LinkedStack[String]
        println(s"stack.isEmpty:${stack.isEmpty()},stack.size:${stack.size}")
        stack.push("1")
        println(s"stack.isEmpty:${stack.isEmpty()},stack.size:${stack.size}")
        stack.push("2")
        stack.push("3")
        println(s"stack.isEmpty:${stack.isEmpty()},stack.size:${stack.size}")

        println(s"pop1:${stack.pop()},size:${stack.size}")
        println(s"pop2:${stack.pop()},size:${stack.size}")
        println(s"pop3:${stack.pop()},size:${stack.size}")
        println(s"pop4:${stack.pop()},size:${stack.size}")

    }
}