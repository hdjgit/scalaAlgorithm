package com.hdj.algorithm.sort

import scala.runtime.RichInt

/**
  * Author: 端吉
  * Date:   2017/5/3.
  *
  * 选择排序
  *
  * 通过不断的选择数组中的最小值，并与数组的第一个元素互换实现排序
  */
object Select {

    /**
      * 此处如果使用 T<:Comparable[T] 直接传入1，2，3会报错，因为Int并没有实现Comparable
      *
      * 但是RichInt实现了Comparable[Int]
      * 使用<% 意味着T 可以隐式转换成Comparable[T]
      *
      * 此处使用Ordered特质，它在Comparable基础上额外提供了关系操作符 <
      * @param arr
      * @tparam T
      */
    def sort[T <% Ordered[T]](arr: Array[T]) = {
        //选择排序，从数组中所有元素中选出最小的元素，与首个元素交换
        for (i <- 0 until arr.length) {
            var minIndex = i
            for (j <- i until arr.length) {
                if (arr(j) < arr(minIndex)) {
                    minIndex = j
                }
            }
            swap(arr, i, minIndex)
        }
    }

    def swap[T](arr: Array[T], i: Int, j: Int) = {
        val temp = arr(i)
        arr(i) = arr(j)
        arr(j) = temp
    }

    def main(args: Array[String]): Unit = {
        val arr = Array(0, 34, 12, 444, 21, 34, 52)
        //        val arr = new Array[RichInt](10)
        sort(arr)
        println(s"array:  ${arr.mkString(",")}")
    }
}
