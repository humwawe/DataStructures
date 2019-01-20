package hum.linkedlist;

import java.util.Random;

/**
 * @author hum
 */
public class SkipList {
    private final static byte HEAD_BIT = (byte) -1;
    private final static byte DATA_BIT = (byte) 0;
    private final static byte TAIL_BIT = (byte) 1;


    private Node head;
    private Node tail;
    private int size;
    private int height;
    public Random random;

    public SkipList() {
        this.head = new Node(null, HEAD_BIT);
        this.tail = new Node(null, TAIL_BIT);
        head.right = tail;
        tail.left = head;
        this.random = new Random(System.currentTimeMillis());
    }

    private Node find(Integer element) {
        Node current = head;
        for (; ; ) {
            while (current.right.bit != TAIL_BIT && current.right.value <= element) {
                current = current.right;
            }
            if (current.down != null) {
                current = current.down;
            } else {
                break;
            }
        }
        //current<=element<current.right
        return current;
    }

    private Node find2(Integer element) {
        return findHelper(element, head);
    }

    private Node findHelper(Integer element, Node current) {
        if (current.right.bit != TAIL_BIT && current.right.value <= element) {
            return findHelper(element, current.right);
        } else if (current.down != null) {
            return findHelper(element, current.down);
        }
        return current;
    }


    public boolean contains(Integer element) {
        Node node = this.find(element);
        return node.value.equals(element);
    }

    public Integer get(Integer element) {
        Node node = this.find(element);
        return node.value.equals(element) ? node.value : null;
    }

    public void add(Integer element) {
        Node preNode = this.find(element);
        Node newNode = new Node(element);
        newNode.left = preNode;
        newNode.right = preNode.right;
        preNode.right.left = newNode;
        preNode.right = newNode;

        int currentLevel = 0;
        while (random.nextDouble() < 0.3d) {
            if (currentLevel >= height) {
                height++;
                Node dummyHead = new Node(null, HEAD_BIT);
                Node dummyTail = new Node(null, TAIL_BIT);

                dummyHead.right = dummyTail;
                dummyHead.down = head;
                head.up = dummyHead;

                dummyTail.left = dummyHead;
                dummyTail.down = tail;
                tail.up = dummyTail;

                head = dummyHead;
                tail = dummyTail;
            }

            while (preNode != null && preNode.up == null) {
                preNode = preNode.left;
            }
            preNode = preNode.up;
            Node upNode = new Node(element);
            upNode.left = preNode;
            upNode.right = preNode.right;
            upNode.down = newNode;

            preNode.right.left = upNode;
            preNode.right = upNode;

            newNode.up = upNode;
            newNode = upNode;
            currentLevel++;
        }

        size++;
    }

    public void dumpSkipList() {
        Node temp = head;
        int i = height + 1;
        while (temp != null) {
            System.out.printf("Total [%d] height. [%d] ", height + 1, i--);
            Node node = temp.right;
            while (node.bit == DATA_BIT) {
                System.out.printf("->%d ", node.value);
                node = node.right;
            }
            System.out.println();
            temp = temp.down;
        }
        System.out.println("=============================");
    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    private static class Node {
        private Integer value;
        private Node up, down, left, right;
        private byte bit;

        public Node(Integer value, byte bit) {
            this.value = value;
            this.bit = bit;
        }

        public Node(Integer value) {
            this(value, DATA_BIT);
        }
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.add(10);
        skipList.dumpSkipList();
        skipList.add(1);
        skipList.dumpSkipList();

        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 50; i++) {
            skipList.add(random.nextInt(100));
        }
        skipList.dumpSkipList();
        System.out.println(skipList.find(0).bit);
        System.out.println(skipList.find2(0).bit);
        System.out.println(skipList.find(20).value);
        System.out.println(skipList.find2(20).value);


    }
}
