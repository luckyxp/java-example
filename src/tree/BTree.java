package tree;

import java.util.Arrays;

/**
 * <li>m为B树的阶,n为节点的度
 * <li>m阶B树的每个节点最多有m个子节点
 * <li>若根节点不是叶子节点,则至少有两个子节点
 * <li>除根节点和叶子节点,每个节点至少有ceil(m/2)个节点
 * <li>所有非叶子节点都包含下列数据(children[n],keys[n-1]),即n个子节点,以及n-1个key
 * <li>children[]和keys[]都是有顺序的,keys[i]大于children[i]中的所有key,keys[i]小于children[i+1]中的所有key
 */
public class BTree {
    static class Node {
        //是否为叶子节点
        boolean leaf = true;
        //有效key
        int keyNumber;
        //最小度
        int t;
        int[] keys;
        Node[] children;

        public Node(int t) {
            this.t = t;
            this.keys = new int[2 * t - 1];
            this.children = new Node[2 * t];
        }

        @Override
        public String toString() {
            return Arrays.toString(Arrays.copyOfRange(keys, 0, keyNumber));
        }

        Node get(int key) {
            int i = 0;
            while (i < keyNumber) {
                if (keys[i] == key) {
                    return this;
                }
                if (keys[i] > key) {
                    break;
                }
                i++;
            }
            if (leaf) {
                return null;
            }
            //当keys[i]>key keys[i-1]<key时,key可能在children[i]中
            return children[i].get(key);
        }
    }
}
