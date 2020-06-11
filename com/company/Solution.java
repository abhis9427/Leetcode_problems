package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    ArrayList<ArrayList<Integer>> graph;
    Map<Integer, Boolean> m_visit ;
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

    boolean colorTraversing(int source) {
        int clrOfSrc = color[source];
        //vist only white vertices
        if (clrOfSrc == 0) {
            m_visit.put(source,true);
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


    public boolean canFinish(int numCourses, int[][] prerequisites) {
        m_visit=new HashMap<>();
        for(int i=0;i<numCourses;i++){
            m_visit.put(i,false);
            System.out.println("false 1 ckpt :  "+i);
        }
        if (prerequisites.length == 0) {
            return true;
        }
        boolean ans = true;

        colorCodingNode(numCourses);
        constructGraph(numCourses);
        for (int i = 0; i < prerequisites.length; i++) {
            for (int j = 1
                 ; j < prerequisites[0].length; j++) {
                addDirectedEdge(prerequisites[i][0], prerequisites[i][j]);
            }
        }
        System.out.println("sorce : " + prerequisites[0][0]);
        colorTraversing(prerequisites[0][0]);
        for(int i=0;i<numCourses;i++){
            if(m_visit.get(i)==null|| !m_visit.get(i)){
                System.out.println("false"+i);
                colorTraversing(i);
            }
        }
        if (flag == 1) {
            ans = false;
        }
        System.out.println("ans:");
        return ans;
    }
}