package org.example.p92343;

import java.util.ArrayList;
import java.util.List;

public class Main {
}

class Solution {
    public int solution(int[] info, int[][] edges) {
        return 3;
    }
}

class PathCalculator {
    private final int[] info;
    private final int[][] edges;
    private final boolean[][] tree;

    public PathCalculator(int[] info, int[][] edges) {
        this.info = info;
        this.edges = edges;

        tree = new boolean[info.length][info.length];

        for (int[] edge : edges) {
            tree[edge[0]][edge[1]] = true;
            tree[edge[1]][edge[0]] = true;
        }
    }

    // 특정 노드를 기준으로 이동할 수 있는 인접 노드들의 번호를 리턴하는 함수
    public List<Integer> getNextNodes(int currentNode) {
        List<Integer> nextNodes = new ArrayList<>();

        for (int i = 0; i < tree[currentNode].length; i++) {
            if (tree[currentNode][i]) {
                nextNodes.add(i);
            }
        }

        return nextNodes;
    }

    public List<Integer> getNextNodes(int currentNode, List<Integer> history) {
        return getNextNodes(currentNode, history, new ArrayList<>());
    }

    // 특정 노드를 기준으로 이동할 수 있는 인접 노드들의 번호를 리턴하는 함수
    // 단, 이미 방문한 곳은 지나친다.
    public List<Integer> getNextNodes(int currentNode, List<Integer> history, List<Integer> prevent) {
        // 현재 노드에 방문했다는건, 더 이상 이 노드에서 탐색할 필요가 없다는 뜻
        // 이 작업을 안하면 같은 노드를 계속 여러번 탐색하게 된다.
        prevent.add(currentNode);

        // 최종적인 데이터가 담길 곳
        List<Integer> nextNodes = new ArrayList<>();

        // 일단 단순하게 현재 노드 기준으로 이동할 수 있는 노드들을 구한다.
        List<Integer> _nextNodes = getNextNodes(currentNode);

        for (int nextNode : _nextNodes) {
            // 이미 탐색한 곳이면 패스
            if (prevent.contains(nextNode)) {
                continue;
            }

            // 이미 방문한 곳이면
            if (history.contains(nextNode)) {
                // 그곳을 기준으로 새로 탐색
                nextNodes.addAll(getNextNodes(nextNode, history, prevent));
            } else {
                // 방문할 리스트에 넣는다
                nextNodes.add(nextNode);
            }
        }

        return nextNodes;
    }
}
