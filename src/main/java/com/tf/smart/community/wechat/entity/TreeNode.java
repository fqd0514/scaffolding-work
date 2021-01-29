package com.tf.smart.community.wechat.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形结构
 */
@Data
public abstract class TreeNode {

    /**
     * 节点id
     */
    protected String id;
    /**
     * 父节点id
     */
    protected String pid;
    /**
     * 子节点集合
     */
    protected List<TreeNode> children = new ArrayList<>();


    public void add(TreeNode node) {
        children.add(node);
    }
}
