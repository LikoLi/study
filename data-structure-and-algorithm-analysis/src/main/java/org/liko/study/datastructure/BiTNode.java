package org.liko.study.datastructure;

import lombok.Data;
import lombok.ToString;

/**
 * BiTNode
 *
 * @author liko
 * @date 2019/12/25
 */
@Data
@ToString
public class BiTNode<T> {
    private T data;

    private BiTNode<T> leftChild;

    private BiTNode<T> rightChild;
}
