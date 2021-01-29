package com.tf.smart.community.wechat.common.utils;

import com.tf.smart.community.wechat.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 树形工具类
 */
public class List2Tree {

    /**
     * 构建树
     * @param treeNodes 节点
     * @param root 根节点ID
     * @return java.util.List<T>
     * @Author Leeyoung
     * @Date 2021/1/29
     **/
    public static <T extends TreeNode> List<T> bulidTree(List<T> treeNodes, Object root) {

        List<T> trees = new ArrayList<>();

        for (T treeNode : treeNodes) {

            //根节点
            if (root.equals(treeNode.getPid())) {
                trees.add(treeNode);
            }
            //子节点
            for (T it : treeNodes) {
                if (it.getPid().equals(treeNode.getId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<>());
                    }
                    treeNode.add(it);
                }
            }
        }
        return trees;
    }

}
