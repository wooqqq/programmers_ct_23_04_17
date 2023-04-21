package org.example.p42860;


public class Main {
//    public static void main(String[] args) {
//        String str = "Z";
//        int num = str.charAt(0);
//        System.out.println(num);
//    }
}

class Solution {
    public int solution(String name) {
        int answer = 0;
        int index;
        int move = name.length() - 1;

        for (int i = 0; i < name.length(); i++) {
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

            index = i + 1;

            while (index < name.length() && name.charAt(index) == 'A') {
                index++;
            }

            // 정방향과 역방향 중 어느 방법이 더 최소인지 구해서 move에 저장
            move = Math.min(move, i * 2 + name.length() - index);

            // 역방향 갔다가 돌아와서 정방향으로 가는 경우가 더 빠른 경우도 존재
            move = Math.min(move, (name.length() - index) * 2 + i);
        }

        // 알파벳 조정 위해서 상하로 움직인 횟수 + 좌우로 움직인 횟수
        return answer + move;
    }
}
