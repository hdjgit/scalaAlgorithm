package com.hdj.algorithm.structure

/**
  * Author: 端吉
  * Date:   2017/5/6.
  *
  * 优先队列：
  *
  * insert(Key v) 插入一个元素
  * max()         返回最大元素
  * delMax()      删除并返回最大元素
  * isEmpty()     队列是否为空
  * size()
  *
  * 基于二叉堆的优先队列
  *
  * 根节点 >= 子节点
  *
  * 13
  * 7          12
  * 3    5    10    9
  * 1  4
  */
class MaxPQ[T <% Ordered[T] : Manifest] {

    var pqData: Array[T] = null

    var N: Int = 1

    def this(maxSize: Int) = {
        this()
        pqData = new Array[T](maxSize)
    }

    /**
      * 上浮操作
      *
      * @param index
      * @return
      */
    def swim(index: Int) = {
        var i = index
        while (i > 1 && pqData(i) > pqData(i / 2)) {
            exchange(i, i / 2)
            i = i / 2
        }
    }

    /**
      * 交换i和j两个元素的位置
      *
      * 假设i和j合法
      *
      * @param i
      * @param j
      */
    def exchange(i: Int, j: Int) = {
        val temp = pqData(i)
        pqData(i) = pqData(j)
        pqData(j) = temp
    }

    def insert(data: T): Unit = {
        pqData(N) = data
        swim(N)
        N += 1
    }

    def max(): T = {
        pqData(1)
    }

    def sink(i: Int): Unit = {
        var index = i
        while (index <= N / 2) {
            if ((2 * index + 1) == N) {
                if (pqData(2 * index) > pqData(index)) {
                    exchange(2 * index, index)
                    index = index * 2
                } else {
                    return;
                }
            } else {
                val bigIndex = if (pqData(2 * index) > pqData(2 * index + 1)) {
                    2 * index
                } else {
                    2 * index + 1
                }
                if (pqData(bigIndex) > pqData(index)) {
                    exchange(bigIndex, index)
                    index = bigIndex
                } else {
                    return;
                }
            }
        }
    }

    def delMax(): T = {
        val max = pqData(1)
        exchange(1, N - 1)
        N -= 1
        sink(1)
        max
    }

    def isEmpty(): Boolean = {
        N == 1
    }

    def size(): Int = {
        N - 1
    }

}

object MaxPQ {
    def main(args: Array[String]): Unit = {
        val maxPQ = new MaxPQ[Int](100)
        maxPQ.insert(5)
        assert(maxPQ.max() == 5)
        maxPQ.insert(7)
        maxPQ.insert(12)
        assert(maxPQ.max() == 12)
        maxPQ.insert(3)
        maxPQ.insert(1)
        maxPQ.insert(10)
        assert(maxPQ.max() == 12)
        maxPQ.insert(13)
        maxPQ.insert(9)
        maxPQ.insert(4)
        assert(maxPQ.max() == 13)
        println(s"maxPQ:${maxPQ.max()}")

        assert(maxPQ.delMax() == 13)
        assert(maxPQ.delMax() == 12)
        //        println(s"max:${maxPQ.delMax()}")
        assert(maxPQ.delMax() == 10)
        assert(maxPQ.delMax() == 9)

        assert(!maxPQ.isEmpty())
        assert(maxPQ.size() == 5)
    }
}
