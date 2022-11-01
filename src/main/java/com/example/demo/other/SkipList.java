package com.example.demo.other;

import java.util.Random;
import java.util.Stack;

/**
 * @Author zhoushenghua
 * @Description 跳表  https://www.cnblogs.com/bigsai/p/14193225.html
 * @Date 1.11.22
 */
public class SkipList<T> {
    SkipNode headNode; //头节点，入口
    int highLevel; //层数
    Random random; //随机函数
    final int MAX_LEVEL = 32; //最大的层数

    public SkipList() {
        random = new Random();
        headNode = new SkipNode(Integer.MIN_VALUE, null);
        highLevel = 0;
    }

    /**
     * 查询操作
     * (1) 从team节点出发，如果当前节点的key与查询的key相等，那么返回当前节点(如果是修改操作那么一直向下进行修改值即可)。
     * (2) 如果key不相等，且右侧为null，那么证明只能向下(结果可能出现在下右方向)，此时team=team.down
     * (3) 如果key不相等，且右侧不为null，且右侧节点key小于待查询的key。那么说明同级还可向右，此时team=team.right
     * (4)（否则的情况）如果key不相等，且右侧不为null，且右侧节点key大于待查询的key 。那么说明如果有结果的话就在这个索引和下个索引之间，此时team=team.down。
     *
     * @param key
     * @return
     */
    public SkipNode search(int key) {
        SkipNode team = headNode;
        while (team != null) {
            if (team.key == key) {
                return team;
            } else if (team.right == null) {
                team = team.down;
            } else if (team.right.key > key) {
                team = team.down;
            } else {
                team = team.right;
            }
        }
        return null;
    }

    /**
     * 删除操作
     * (1)如果team右侧为null，那么team=team.down(之所以敢直接这么判断是因为左侧有头结点在左侧，不用担心特殊情况)
     * (2)如果team右侧不 为null，并且右侧的key等于待删除的key，那么先删除节点，再team向下team=team.down为了删除下层节点。
     * (3)如果team右侧不 为null，并且右侧key小于待删除的key，那么team向右team=team.right。
     * (4)如果team右侧不 为null，并且右侧key大于待删除的key，那么team向下team=team.down，在下层继续查找删除节点。
     *
     * @param key
     */
    public void delete(int key) {
        SkipNode team = headNode;
        while (team != null) {
            if (team.right == null) {
                team = team.down;
            } else if (team.right.key == key) {
                team.right = team.right.right;
                team = team.down;
            } else if (team.right.key > key) {
                team = team.down;
            } else {
                team = team.right;
            }
        }
    }

    /**
     * 插入操作
     * (1)首先通过查找的方式，找到待插入的左节点。插入的话最底层肯定是需要插入的，所以通过链表插入节点(需要考虑是否为末尾节点)
     * (2)插入完这一层，需要考虑上一层是否插入，首先判断当前索引层级，如果大于最大值那么就停止(比如已经到最高索引层了)。否则设置一个随机数1/2的概率向上插入一层索引(因为理想状态下的就是每2个向上建一个索引节点)。
     * (3)继续(2)的操作，直到概率退出或者索引层数大于最大索引层。
     *
     * @param node
     */
    public void add(SkipNode node) {
        int key = node.key;
        SkipNode existNode = search(key);
        if (existNode != null) {
            existNode.value = node.value;
            return;
        }

        Stack<SkipNode> stack = new Stack<>(); //存储向下的节点，这些节点可能在右侧插入节点
        SkipNode team = headNode;
        while (team != null) {
            if (team.right == null) {
                stack.add(team);
                team = team.down;
            } else if (team.right.key > key) {
                stack.add(team);
                team = team.down;
            } else {
                team = team.right;
            }
        }

        int level = 1;
        SkipNode downNode = null;
        while (!stack.isEmpty()) {
            //在该层插入node
            team = stack.pop();
            SkipNode nodeTeam = new SkipNode(node.key, node.value);
            nodeTeam.down = downNode;
            downNode = nodeTeam;
            if (team.right == null) {
                team.right = nodeTeam;
            } else {
                nodeTeam.right = team.right;
                team.right = nodeTeam;
            }

            if (level > MAX_LEVEL) {
                break;
            }

            double num = random.nextDouble();
            if (num > 0.5) {
                break;
            }
            level++;
            if (level > highLevel) {
                highLevel = level;
                SkipNode highHeadNode = new SkipNode(Integer.MIN_VALUE, null);
                highHeadNode.down = headNode;
                headNode = highHeadNode;
                stack.add(headNode);
            }
        }
    }

    /**
     * 打印数据
     */
    public void printNode() {
        SkipNode teamNode = headNode;
        int index = 0; //层数
        SkipNode last = teamNode;
        while (last.down != null) {
            last = last.down;
        }

        while (teamNode != null) {
            SkipNode enumNode = teamNode.right;
            SkipNode enumLast = last.right;
            System.out.printf("%-8s", "head->");

            while (enumNode != null && enumLast != null) {
                if (enumLast.key == enumNode.key) {
                    System.out.printf("%-5s", enumLast.key + "->");
                    enumLast = enumLast.right;
                    enumNode = enumNode.right;
                } else {
                    enumLast = enumLast.right;
                    System.out.printf("%-5s", "");
                }
            }
            teamNode = teamNode.down;
            index++;
            System.out.println();
        }
        System.out.println("================================================层数：" + index + "================================================");
    }

    public static void main(String[] args) {
        SkipList<Integer> list = new SkipList<>();
        for (int i = 1; i < 20; i++) {
            list.add(new SkipNode(i, "值"));
        }
        System.out.println("原所有节点数据:");
        list.printNode();
        System.out.println();
        list.delete(7);
        list.delete(14);
        System.out.println("删除节点后数据:");
        list.printNode();
    }
}

/**
 * 节点模型定义
 *
 * @param <T>
 */
class SkipNode<T> {
    int key;
    T value;
    SkipNode right; //右节点
    SkipNode down; //下节点

    public SkipNode(int key, T value) {
        this.key = key;
        this.value = value;
    }
}
