package org.liko.study.datastructure;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Objects;

/**
 * TreeTest
 *
 * @author liko
 * @date 2019/12/25
 */
public class TreeTest {

    @Test
    public void biTNodeTest() {
        BiTNode<String> biTree = init();

//        preorderTraversal(biTree);
//        System.out.println("=============================");
//        inTheSequenceTraversal(biTree);
//        System.out.println("=============================");
//        subsequentTraversal(biTree);
//        System.out.println("=============================");
        levelIterator(biTree);

    }

    private void preorderTraversal(BiTNode biTree) {
        if (Objects.isNull(biTree)) {
            return;
        }

        System.out.print(biTree.getData());
        preorderTraversal(biTree.getLeftChild());
        preorderTraversal(biTree.getRightChild());
    }

    private void inTheSequenceTraversal(BiTNode biTree) {
        if (Objects.isNull(biTree)) {
            return;
        }
        inTheSequenceTraversal(biTree.getLeftChild());
        System.out.print(biTree.getData());
        inTheSequenceTraversal(biTree.getRightChild());
    }

    private void subsequentTraversal(BiTNode biTree) {
        if (Objects.isNull(biTree)) {
            return;
        }
        subsequentTraversal(biTree.getLeftChild());
        subsequentTraversal(biTree.getRightChild());
        System.out.print(biTree.getData());
    }

    private void levelIterator(BiTNode biTree) {
        if (Objects.isNull(biTree)) {
            return;
        }

        LinkedList<BiTNode> linkedList = new LinkedList<>();
        linkedList.offer(biTree);
        while (!linkedList.isEmpty()) {
            BiTNode peek = linkedList.pop();

            System.out.println(peek.getData());
            if (Objects.nonNull(peek.getLeftChild())) {
                linkedList.offer(peek.getLeftChild());
            }

            if (Objects.nonNull(peek.getRightChild())) {
                linkedList.offer(peek.getRightChild());
            }
        }
    }



    private BiTNode<String> init() {
        BiTNode<String> nodeA = new BiTNode<>();
        nodeA.setData("A");

        BiTNode<String> nodeB = new BiTNode<>();
        nodeB.setData("B");
        nodeA.setLeftChild(nodeB);

        BiTNode<String> nodeC = new BiTNode<>();
        nodeC.setData("C");
        nodeA.setRightChild(nodeC);

        BiTNode<String> nodeD = new BiTNode<>();
        nodeD.setData("D");
        nodeB.setLeftChild(nodeD);

        BiTNode<String> nodeE = new BiTNode<>();
        nodeE.setData("E");
        nodeB.setRightChild(nodeE);

        BiTNode<String> nodeF = new BiTNode<>();
        nodeF.setData("F");
        nodeC.setLeftChild(nodeF);

        BiTNode<String> nodeG = new BiTNode<>();
        nodeG.setData("G");
        nodeC.setRightChild(nodeG);

        BiTNode<String> nodeH = new BiTNode<>();
        nodeH.setData("H");
        nodeD.setLeftChild(nodeH);

        BiTNode<String> nodeI = new BiTNode<>();
        nodeI.setData("I");
        nodeD.setRightChild(nodeI);

        BiTNode<String> nodeJ = new BiTNode<>();
        nodeJ.setData("J");
        nodeE.setLeftChild(nodeJ);

        return nodeA;
    }
}
