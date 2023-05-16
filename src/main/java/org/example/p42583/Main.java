package org.example.p42583;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.StreamHandler;

public class Main {
}

class Solution {
    public int solution(int bridge_length, int weight, int[] truch_weights) {
        // 다리 만들기
        // - 흐른 시간(초)
        // - 최대 하중
        // - 길이
        Bridge bridge = new Bridge(bridge_length, weight);

        // 트럭들 만들기
        // 출발여부
        // 패스여부
        // 다리에 진입한 후 흐른 시간
        List<Truck> trucks = makeTrucks(bridge, truch_weights);

        // 모든 트록이 통과할 때까지 반복
        while (!trucks.stream().allMatch(Truck::isPassed)) {
            bridge.increaseSeconds(); // 초 증가
            trucks.forEach(Truck::tick); // 모든 트럭이 초 증가에 따른 행동을 하도록
        }

        return bridge.getSeconds();
    }

    private List<Truck> makeTrucks(Bridge bridge, int[] truck_weights) {
        List<Truck> trucks = new ArrayList<>();

        Truck oldTruck = null;

        for (int truck_weight : truck_weights) {
            Truck truck = new Truck(bridge, truck_weight, oldTruck);
            trucks.add(truck);
            oldTruck = truck;
        }

        return trucks;
    }
}

class Bridge {
    private final int length;
    // 다리가 버틸 수 있는 하중
    private final int allowableWeight;
    private int truckWeight = 0;
    private int seconds = 0;

    public Bridge(int length, int allowableWeight) {
        this.length = length;
        this.allowableWeight = allowableWeight;
    }

    public void increaseSeconds() {
        seconds++;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getLength() {
        return length;
    }

    public void increaseTruckWeight(int weight) {
        truckWeight += weight;
    }

    public void decreaseTruckWeight(int weight) {
        truckWeight -= weight;
    }

    public int getAllowableWeight() {
        return allowableWeight;
    }

    public int getTruckWeight() {
        return truckWeight;
    }
}


class Truck {
    // 다리 길이
    // 이 트럭의 무게
    private final int weight;
    private final Bridge bridge;
    // 이 트럭이 다리에 진입한 후 지난 시간
    private int seconds = 0;
    // 이 트럭이 다리에 진입했는지 여부
    private boolean started = false;
    // 내 바로 앞의 트럭
    private final Truck prev;

    public Truck(Bridge bridge, int weight, Truck prev) {
        this.bridge = bridge;
        this.weight = weight;
        this.prev = prev;
    }

    public boolean isPassed() {
        if (!started) return false;
        return seconds == bridge.getLength() + 1;
    }

    // 매 초마다 수행해야 하는 일을 처리
    public void tick() {
        // 이미 통과한 트럭은 무시
        if (isPassed()) return;

        // 다리에 진입할 차례인지 확인
        if (isTurnToStart()) {
            started = true;
            bridge.increaseTruckWeight(weight);
        }

        // 다리 위에 있는 시간을 증가
        if (started) {
            seconds++;

            // 다리를 벗어났다면 무게를 감소, 즉 패스처리
            if (seconds == bridge.getLength() + 1) {
                bridge.decreaseTruckWeight(weight);
            }
        }
    }

    private boolean isTurnToStart() {
        if (isWaitingNumberZero() && canIIn()) {
            return true;
        }

        return false;
    }

    private boolean canIIn() {
        return bridge.getAllowableWeight() >= bridge.getTruckWeight() + weight;
    }

    private boolean isWaitingNumberZero() {
        if (started) return false;
        return prev == null || (prev.started && prev.seconds > 1);
    }
}