package cn.sysmaster.datastruct.linkedlist

import org.junit.Test


class SingleLinkedListTest {

    @Test
    fun testSingleLinkedList() {
        val list = SingleLinkedList<Int>()
        list.add(1)
        list.add(2)
        list.add(3)
        list.add(5, 66)
        list.add(0, 67)
        list.remove()
        list.remove(1)
        list.set(1,55)

        for (i in 0 until list.size) {
            println("${list.get(i)}")
        }
        println("list contains 3   ${list.contains(3)}")
    }
}