package cn.sysmaster.datastruct.linkedlist

class SingleLinkedList<E> {

    data class Node<E>(
        var item: E,
        var next: Node<E>?
    )

    var first: Node<E>? = null
    var last: Node<E>? = null
    var size = 0

    private fun linkFirst(e: E) {
        val f = first
        val newNode = Node(e, f)
        first = newNode
        if (f == null) {
            last = newNode
        }
        size++
    }

    private fun linkLast(e: E) {
        val l = last
        val newNode = Node(e, null)
        last = newNode
        if (l == null) {
            first = newNode
        } else {
            l.next = newNode
        }
        size++
    }

    fun add(e: E) {
        linkLast(e)
    }

    fun add(index: Int, e: E) {
        if (index < 0 || index >= size) {
            return
        }
        when (index) {
            0 -> linkFirst(e)
            size - 1 -> linkLast(e)
            else -> {
                val prev = node(index - 1)
                val newNode = Node(e, prev?.next)
                prev?.next = newNode
                size++
            }
        }
    }

    private fun unlinkFirst() {
        val f = first
        first = f?.next
        if (f == null) {
            last = null
        }
        size--
    }

    private fun unlinkLast() {
        val prev = node(size - 1)
        last = prev
        if (prev == null) {
            first = null
        }
        size--
    }

    fun remove() {
        unlinkLast()
    }

    fun remove(index: Int) {
        if (index < 0 || index >= size) {
            return
        }
        when (index) {
            0 -> unlinkFirst()
            size - 1 -> unlinkLast()
            else -> {
                val prev = node(index - 1)
                val target = prev?.next
                prev?.next = target?.next
                size--
            }
        }
    }

    fun set(index: Int, e: E) {
        if (index < 0 || index >= size) {
            return
        }
        val target = node(index)
        target?.item = e
    }

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


    fun get(index: Int): E? {
        return node(index)?.item
    }


    private fun node(index: Int): Node<E>? {
        var node = first
        for (i in 0 until index) {
            node = node?.next
        }
        return node
    }

}