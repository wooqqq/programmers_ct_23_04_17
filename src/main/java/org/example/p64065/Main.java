package org.example.p64065;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "{{2,1,3,4},{2},{2,1,3},{2,1}}";
        int[] answer = solution.solution(s);

        System.out.println(Arrays.toString(answer));
    }
}

class Solution {
    public int[] solution(String s) {
        s = s.substring(2, s.length() - 2); // 가장 바깥쪽 {{ 와 }} 제거
        String[] sBits = s.split("},\\{"); // },{ 로 나누기

        Map<Integer, String[]> map = Arrays.stream(sBits)
                .map(sBit -> sBit.split(","))
                .collect(Collectors.toMap(arr -> arr.length, arr -> arr));

        int[] answer = new int[map.size()];

        for (int i = 1; i <= map.size(); i++) {
            String[] arr = map.get(i);
            int lastE1 = Integer.parseInt(arr[arr.length - 1]);

            answer[i - 1] = lastE1;
        }

        return answer;
    }
}