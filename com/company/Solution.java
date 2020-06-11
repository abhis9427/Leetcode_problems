package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    ArrayList<ArrayList<Integer>> graph;
    Map<Integer, Boolean> m_visit = new HashMap<>();
    int[] color;
    int flag = 0;


    void constructGraph(int ver) {
        graph = new ArrayList<>(ver);
        for (int i = 0; i < ver; i++) {
            graph.add(new ArrayList<Integer>());
        }
    }

    void colorCodingNode(int n) {
        color = new int[n];
        //0 is coded as white
        for (int c = 0; c < n; c++) {
            color[c] = 0;
        }
    }


    void addDirectedEdge(int v, int u) {
        graph.get(v).add(u);
    }

    /*boolean visited(int source) {
        return m_visit.containsKey(source);
    }*/

    boolean colorTraversing(int source) {
        int clrOfSrc = color[source];
        //vist only white vertices
        if (clrOfSrc == 0) {
            //Making it grey
            color[source] = 1;
            System.out.println(source);
            List<Integer> neigh = graph.get(source);
            for (Integer nei : neigh) {
                if (color[nei] == 1) {
                    flag = 1;
                    System.out.println("flag: " + flag);

                } else {
                    colorTraversing(nei);
                }
            }
            //all the nodes are visited so colouring it black
            color[source] = 2;
        } else {
            return false;
        }
        return true;
    }

    /*boolean traversal(int source) {
        if (!visited(source)) {
            m_visit.put(source, true);
            List<Integer> neighbours = graph.get(source);
            for (Integer neighbour : neighbours) {
                boolean notCycle = traversal(neighbour);
                if (!notCycle) {
                    return false;
                }
            }
        } else {
            //System.out.println("is cycle");
            return false;
        }

        return true;
    }*/

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            return true;
        }
        boolean ans = true;
        int[] getSrc=new int[numCourses];
        int src=prerequisites[0][0];
        if(prerequisites.length>3){
            int value = 0;
            for (int i = 0; i < numCourses; i++) {

                getSrc[i] = 0;
            }
            for (int i = 0; i < prerequisites.length; i++) {
                getSrc[prerequisites[i][0]] += 1;
            }
            for (int i = 0; i < numCourses; i++) {
                if (value <= getSrc[i]) {
                    src = i;
                }
            }
        }


        colorCodingNode(numCourses);
        constructGraph(numCourses);
        for (int i = 0; i < prerequisites.length; i++) {
            for (int j = 1
                 ; j < prerequisites[0].length; j++) {
                addDirectedEdge(prerequisites[i][0], prerequisites[i][j]);
            }
        }
        System.out.println("sorce : "+src);
        colorTraversing(src);
        if (flag == 1) {
            ans = false;
        }
        System.out.println("ans:");
        return ans;
    }
}