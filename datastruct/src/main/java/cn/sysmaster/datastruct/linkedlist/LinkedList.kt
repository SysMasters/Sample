package cn.sysmaster.datastruct.linkedlist

import java.util.LinkedList

class LinkedList<E> {
    data class Node<E>(
        var prev: Node<E>? = null,
        var item: E,
        var next: Node<E>? = null
    )

    var first: Node<E>? = null
    var last: Node<E>? = null
    var size = 0


    fun add(e: E) {
        linkLast(e)
    }

    fun add(index: Int, e: E) {
        if (index < 0 || index >= size) {
            return
        }
        when (index) {
            0 -> {
                linkFirst(e)
            }

            size - 1 -> {
                linkLast(e)
            }

            else -> {
                val target = node(index)
                val prev = target?.prev
                val newNode = Node(prev, e, target)
                target?.prev = newNode
                prev?.next = newNode
                size++
            }
        }
    }

    fun remove() {
        unlinkLast()
    }

    fun remove(index: Int) {
        if (index < 0 || index >= size) {
            return
        }
        when (index) {
            0 -> {
                unlinkFirst()
            }

            size - 1 -> {
                unlinkLast()
            }

            else -> {
                val target = node(index)
                val prev = target?.prev
                prev?.next = target?.next
                size--
            }
        }
    }

    /**
     * 根据索引 index 取得节点值
     */
    fun get(index: Int): E? {
        return node(index)?.item
    }

    /**
     * 判断链表中是否包含指定值
     */
    fun contains(e: E): Boolean {
        var node = first
        for (i in 0 until size) {
            if (node?.item == e) {
                return true
            }
            node = node?.next
        }
        return false
    }

    /**
     * 修改操作
     */
    fun set(index: Int, e: E) {
        if (index < 0 || index >= size) {
            return
        }
        val target = node(index)
        target?.item = e
    }

    /**
     * 删除头部节点
     */
    private fun unlinkFirst() {
        val next = first?.next
        first = next
        if (next == null) {
            last = null
        } else {
            first?.prev = null
        }
        size--
    }

    /**
     * 删除尾部节点
     */
    private fun unlinkLast() {
        val prev = last?.prev
        last = prev
        if (prev == null) {
            first = null
        } else {
            last?.next = null
        }
        size--
    }

    /**
     * 查找指定索引处的节点
     */
    private fun node(index: Int): Node<E>? {
        var node: Node<E>?
        // 右移1位 >> 相当于size/2
        if (index < size shr 1) {
            // 从前向后遍历
            node = first
            for (i in 0 until index) {
                node = node?.next
            }
        } else {
            // 从后向前遍历
            node = last
            for (i in size - 1 downTo index + 1) {
                node = node?.prev
            }
        }
        return node
    }

    /**
     * 从头部添加
     */
    private fun linkFirst(e: E) {
        val f = first
        val newNode = Node(null, e, f)
        first = newNode
        if (f == null) {
            last = newNode
        } else {
            f.prev = newNode
        }
        size++
    }

    /**
     * 从尾部添加
     */
    private fun linkLast(e: E) {
        val l = last
        val newNode = Node(l, e, null)
        last = newNode
        if (l == null) {
            first = newNode
        } else {
            l.next = newNode
        }
        size++
    }
}