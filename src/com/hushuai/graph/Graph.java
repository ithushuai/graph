package com.hushuai.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * created by it_hushuai
 * 2020/2/29 21:50
 */
public class Graph {
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的邻结矩阵
    private int numOfEdges;//表示边的数目
    private boolean[] isSearched;//记录结点是否被访问
    private LinkedList<Integer> queue = new LinkedList<>();//广度优先中记录节点顺序

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        isSearched = new boolean[n];
    }

    /**
     * 返回节点的个数
     *
     * @return
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 返回边的数目
     *
     * @return
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回节点下标对应的数据
     *
     * @param i
     * @return
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回两个节点之间的权值
     *
     * @param i1
     * @param i2
     * @return
     */
    public int getWeight(int i1, int i2) {
        return edges[i1][i2];
    }

    /**
     * 插入节点
     *
     * @param vertex
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边，也就是确定两个节点之间的关系
     *
     * @param i1
     * @param i2
     * @param weight
     */
    public void addEdges(int i1, int i2, int weight) {
        edges[i1][i2] = weight;
        edges[i2][i1] = weight;
        numOfEdges++;
    }

    /**
     * 显示图对应的邻结矩阵
     */
    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /**
     * 图的深度优先遍历
     *
     * @param i
     */
    public void dfs(int i) {
        System.out.print(vertexList.get(i) + "->");
        isSearched[i] = true;
        for (int j = 0; j < edges.length; j++) {//一个结点遍历之后，从5个结点找到接下来要遍历的结点
            if (edges[i][j] > 0 && !isSearched[j]) {//接下来要出现的结点必须满足：1.与当前节点i要相连；2.未被遍历过
                dfs(j);//找到下一个节点以后，继续以下一个节点作为当前节点来进行递归遍历
            }
        }
    }

    /**
     * 图的广度优先遍历
     *
     * @param i
     */
    public void bfs(int i) {
        queue.addLast(i);//将第一个结点的索引记录到队列中
        while (!queue.isEmpty()) {
            bfs_search(queue.getFirst());
            queue.removeFirst();
        }
    }

    public void bfs_search(int i) {
        //加上这个判断的原因：上一个结点将邻接节点索引放入队列之后，下一个邻接结点有可能会将相同的邻接节点入队列
        //例如：A的邻接结点BC，入队列后queue为C->B->A,后面轮到B时，B的邻接结点为CDE，即队列可能变为D->C->C->B
        if(!isSearched[i]){
            System.out.print(vertexList.get(i) + "->");
            isSearched[i] = true;
        }
        //遍历第一个结点的邻接节点
        for (int j = 0; j < edges.length; j++) {
            if (edges[i][j] > 0 && !isSearched[j]) {
                queue.addLast(j);
            }
        }
    }


    public static void main(String[] args) {
        Graph graph = new Graph(5);

        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");

        graph.addEdges(0, 1, 1);
        graph.addEdges(0, 2, 1);
        graph.addEdges(1, 3, 1);
        graph.addEdges(1, 4, 1);
        graph.addEdges(1, 2, 1);

        graph.showGraph();

//        graph.dfs(0);
        graph.bfs(0);
    }
}
