package rbtree;

import java.util.ArrayList;
import java.util.List;

/**
 * <li>所有节点都有红黑两种颜色,根节点是黑色
 * <li>所有null视为黑色
 * <li>红色节点不能相邻(红色节点的子节点是黑色)
 * <li>从根到任意叶子节点,路径中的黑色节点数一样
 */
public class RBTree {
    Node root;

    enum Color {RED, BLACK}

    static class Node {
        int key;
        Object value;
        Node left;
        Node right;
        Node parent;
        Color color = Color.RED;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public Node(int key, Color color) {
            this.key = key;
            this.color = color;
        }

        public Node(int key, Color color, Node left, Node right) {
            this.key = key;
            this.color = color;
            this.left = left;
            this.right = right;
            if (left != null) {
                left.parent = this;
            }
            if (right != null) {
                right.parent = this;
            }
        }

        boolean isLeftChild() {
            return parent != null && parent.left == this;
        }

        //叔叔节点
        Node uncle() {
            if (parent == null || parent.parent == null) {
                return null;
            }
            return parent.parent.left == parent ? parent.parent.right : parent.parent.left;
        }

        //兄弟节点
        Node sibling() {
            if (parent == null) {
                return null;
            }
            return parent.left == this ? parent.right : parent.left;
        }

        @Override
        public String toString() {
            return key+"";
        }
    }

    public int doCheck(Node parent, Node node, int curBn) {
        if (isRed(parent) && isRed(node)) {
            // System.out.println("RR");
            return -1;
        }
        if (node == null) {
            //叶子节点,结束
            System.out.println(curBn);
            return curBn;
        }
        if (node.color == Color.BLACK) {
            curBn++;
        }
        int lBn = doCheck(node, node.left, curBn);
        int rBn = doCheck(node, node.right, curBn);
        if (lBn == rBn) {
            return lBn;
        } else {
            return -1;
        }
    }

    public List<Node> mOrder(Node node) {
        List<Node> nodes = new ArrayList<>();
        getNode(nodes, node);
        return nodes;
    }

    public void getNode(List<Node> nodes, Node node) {
        if (node == null) {
            return;
        }
        getNode(nodes, node.left);
        nodes.add(node);
        getNode(nodes, node.right);
    }

    public static void main(String[] args) {
        RBTree rbTree = new RBTree();
        rbTree.put(2, null);
        rbTree.put(4, null);
        rbTree.put(7, null);
        rbTree.put(5, null);
        rbTree.put(8, null);
        rbTree.put(1, null);
        rbTree.remove(4);
        rbTree.remove(2);
        rbTree.remove(1);
        rbTree.remove(7);
        rbTree.remove(8);
        rbTree.remove(5);
        int i = rbTree.doCheck(null, rbTree.root, 0);
        System.out.println(i);
        List<Node> nodes = rbTree.mOrder(rbTree.root);
        System.out.println(nodes);
    }

    //右旋
    private void rightRotate(Node node) {
        //需要旋转节点的父节点
        Node parent = node.parent;
        //新上任的节点(原节点的左孩子)
        Node newNode = node.left;
        //将新上任节点的右孩子过继给退休节点做左孩子
        Node rightChild = newNode.right;
        if (rightChild != null) {
            rightChild.parent = node;
        }
        node.left = rightChild;

        //退休节点沦为新上任节点的右孩子
        newNode.right = node;
        node.parent = newNode;

        //新上任节点继承退休节点的父亲
        newNode.parent = parent;
        if (parent == null) {
            //没有父节点则成为根节点
            root = newNode;
        } else {
            if (parent.left == node) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
    }

    //左旋
    private void leftRotate(Node node) {
        Node parent = node.parent;
        Node newNode = node.right;
        Node leftChild = newNode.left;
        if (leftChild != null) {
            leftChild.parent = node;
        }
        node.right = leftChild;
        newNode.left = node;
        newNode.parent = parent;
        node.parent = newNode;
        if (parent == null) {
            root = newNode;
        } else if (parent.left == node) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    public void put(int key, Object value) {
        //遍历指针
        Node p = root;
        //需要插入节点的父节点,此节点肯定是叶子节点
        Node parent = null;
        while (p != null) {
            parent = p;
            if (p.key == key) {
                //key存在,更新即可
                p.value = value;
                return;
            } else if (p.key < key) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        //判断key,决定插到左边还是右边
        Node inserted = new Node(key, value);
        inserted.parent = parent;
        if (parent == null) {
            root = inserted;
        } else if (parent.key > inserted.key) {
            parent.left = inserted;
        } else {
            parent.right = inserted;
        }
        //修正树
        fixup(inserted);
    }

    public Node find(int key) {
        Node p = root;
        while (p != null) {
            if (p.key == key) {
                return p;
            } else if (p.key > key) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    /**
     * BST删除操作:
     *  1若删除节点没有子节点,直接删除
     *  2若删除节点只有一个子节点,删除节点后,子节点顶替被删除的节点
     *  3若删除节点有两个子节点,找到删除节点的前驱或后继节点,与其进行交换再删除
     * RBT删除操作:
     *      1无非空子节点
     *          1.1红色 直接删除
     *          1.2黑色 如果删除,那这条路径上就少了一个黑色(双黑),它下面又没有红色可以用来补齐,只能把矛盾往上推,让所有树都少一个黑色达到平衡,双黑又分为下面几种情况
     *              1.2.1被删除节点的兄弟节点是红色 对父节点进行旋转,转化为1.2.2(由于兄弟节点是红色,旋转之后成为祖父节点,就会导致父树多一个黑,所以需要进行颜色调整)
     *                  被删除节点是左孩子 左旋父节点,原父节点变红,原兄弟节点变黑(新上任的祖父节点)
     *                  被删除节点是右孩子 右旋父节点,原父节点变红,原兄弟节点变黑
     *              1.2.2被删除节点的兄弟节点是黑色
     *                  1.2.2.1两个侄子都为黑
     *                      1.2.2.1.1被删除节点的父节点是红色 删掉目标节点,为了平衡,把兄弟节点变红,把父节点变黑
     *                      1.2.2.1.2被删除节点的父节点是黑色 删除目标节点,为了平衡,把兄弟节点变红,由于父节点本来就是黑色,所以此路径少了一个黑,即触发双黑,把父节点当作目标节点重新调整
     *                  1.2.2.2至少有一个侄子为红(这里只讨论,只有一个红侄节点的情况,两个红侄节点,直接忽略一个,让其接近LL或RR)
     *                      LL 右旋父节点,原侄节点变黑,原父节点变黑,原兄弟节点变为原父节点的颜色(新上任的父节点为了维持平衡,需要继续保持原来的颜色),删除目标节点
     *                      LR 左旋兄弟节点,原兄弟节点变红,原侄节点变黑,兄弟节点和侄节点身份对调,从而转换成LL情况
     *                      RR 左旋父节点,原侄节点变黑,原父节点变黑,原兄弟节点变为原父节点的颜色,删除目标节点
     *                      RL 右旋兄弟节点,原兄弟节点变红,原侄节点变黑,兄弟节点和侄节点身份对调,从而转换成RR情况
     *      2只有一个非空子节点(同BST2操作)
     *          红黑和黑黑都违背红黑树平衡,所以只有黑红
     *          而这种情况下的红也不可能再有子节点
     *          直接把删除节点与其仅一个的子节点进行值交换,再删除子节点
     *      3有两个非空子节点(同BST3操作)
     *          找到前驱或后继节点,交换值,再删除,前驱或后继只可能是1或2,所以将3转换成1或,2
     */
    public void remove(int key) {
        Node deleted = find(key);
        if (deleted == null) {
            return;
        }
        doRemove(deleted);
    }

    private void doRemove(Node deleted) {
        if (deleted.left != null && deleted.right != null) {
            Node replaced = postCursor(deleted);
            int key = replaced.key;
            Object value = replaced.value;
            replaced.key = deleted.key;
            replaced.value = deleted.value;
            deleted.key = key;
            deleted.value = value;
            recovery(replaced);
        } else if (deleted.left != null) {
            deleted.key = deleted.left.key;
            deleted.value = deleted.left.value;
            recovery(deleted.left);
        } else if (deleted.right != null) {
            deleted.key = deleted.right.key;
            deleted.value = deleted.right.value;
            recovery(deleted.right);
        } else {
            //触发双黑,修复双黑再删除
            if (!isRed(deleted)) {
                fixDoubleBlack(deleted);
            }
            recovery(deleted);
        }

    }

    private void fixDoubleBlack(Node node) {
        Node parent = node.parent;
        Node sibling = node.sibling();
        if (sibling == null) {
            //黑色叶子节点兄弟为null,只可能是root节点
            return;
        }
        if (isRed(sibling)) {
            parent.color = Color.RED;
            sibling.color = Color.BLACK;
            if (node.isLeftChild()) {
                leftRotate(parent);
            } else {
                rightRotate(parent);
            }
            sibling = node.sibling();
            parent = node.parent;
        }
        Node sLeft = sibling.left;
        Node sRight = sibling.right;
        if (isRed(sLeft) || isRed(sRight)) {
            if (parent.isLeftChild()) {
                if (isRed(sLeft)) {
                    //LL
                    rightRotate(parent);
                    sLeft.color = Color.BLACK;
                    sibling.color = parent.color;
                    parent.color = Color.BLACK;
                } else {
                    //LR
                    sibling.color = Color.RED;
                    sRight.color = Color.BLACK;
                    leftRotate(sibling);
                    fixDoubleBlack(node);
                }
            } else {
                if (isRed(sRight)) {
                    //RR
                    leftRotate(parent);
                    sRight.color = Color.BLACK;
                    sibling.color = parent.color;
                    parent.color = Color.BLACK;
                } else {
                    //RL
                    sibling.color = Color.RED;
                    sLeft.color = Color.BLACK;
                    rightRotate(sibling);
                    fixDoubleBlack(node);
                }
            }
        } else {
            if (isRed(parent)) {
                sibling.color = Color.RED;
                parent.color = Color.BLACK;
            } else {
                sibling.color = Color.RED;
                fixDoubleBlack(parent);
            }
        }
    }

    private void recovery(Node deleted) {
        if (deleted.left != null || deleted.right != null) {
            return;
        }
        if (deleted.parent == null) {
            if (deleted == root) {
                root = null;
            }
        } else if (deleted.isLeftChild()) {
            deleted.parent.left = null;
        } else {
            deleted.parent.right = null;
        }
    }

    //前驱
    private Node preCursor(Node deleted) {
        Node p = deleted.left;
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }
    //后继
    private Node postCursor(Node deleted) {
        Node p = deleted.right;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == Color.RED;
    }

    /**
     * 首先所有插入节点均视为红色
     * case1:插入节点是根节点,直接变黑
     * case2:插入节点的父节点是黑色,则不影响红黑性质,无需调整
     * 插入节点的父节点是红色,触发红红不相邻,又分为case3,case4两种情况
     * case3:叔叔节点为红色,则父节点变黑,为了保证平衡,叔叔节点也变黑,由于子树多了一个黑节点,所以祖父节点变红,如果祖父变红导致红红相邻,则进行递归调整
     * case4:叔叔节点为黑色
     *  LL: 父节点变黑,祖父节点变红,右旋父节点(由于父节点变黑,祖父节点变红,那么父比叔多一个黑,所以右旋,把父黑提升至祖父进行共享)
     *  LR: 左旋父节点,此时父子调换,再按LL调整
     *  RR: 左旋(同LL)
     *  RL: 右旋父节点,此时父子调换,再按RR调整
     */
    private void fixup(Node inserted) {
        if (inserted == root) {
            inserted.color = Color.BLACK;
        } else if (isRed(inserted.parent)) {
            if (isRed(inserted.uncle())) {
                inserted.parent.color = Color.BLACK;
                inserted.uncle().color = Color.BLACK;
                inserted.parent.parent.color = Color.RED;
                fixup(inserted.parent.parent);
            } else {
                boolean insertedIsLeftChild = inserted.isLeftChild();
                if (inserted.parent.isLeftChild()) {
                    if (insertedIsLeftChild) {
                        //LL
                        inserted.parent.color = Color.BLACK;
                        inserted.parent.parent.color = Color.RED;//祖父节点可能是root节点,此操作可能会把跟节点变成红色,递归到case1 插入节点为根节点,直接变黑
                        rightRotate(inserted.parent.parent);
                    } else {
                        //LR
                        leftRotate(inserted.parent);
                        //变为LL情况,但是父子调换了,这里inserted是LL情况中的父节点,那么inserted.parent就是祖父节点
                        inserted.color = Color.BLACK;
                        inserted.parent.color = Color.RED;
                        rightRotate(inserted.parent);
                    }
                } else {
                    if (insertedIsLeftChild) {
                        //RL
                        rightRotate(inserted.parent);
                        inserted.color = Color.BLACK;
                        inserted.parent.color = Color.RED;
                        leftRotate(inserted.parent);
                    } else {
                        //RR
                        inserted.parent.color = Color.BLACK;
                        inserted.parent.parent.color = Color.RED;
                        leftRotate(inserted.parent.parent);
                    }
                }
            }
        }
    }
}
