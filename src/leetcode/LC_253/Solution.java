package leetcode.LC_253;

import base.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by naco_siren on 9/18/17.
 */
class Solution {
    static int minMeetingRooms1(Interval[] intervals) {
        // Sort intervals in ascending order based on their start times
        Arrays.sort(intervals, (Interval i1, Interval i2) -> i1.start - i2.start);

        int rooms = 0;
        PriorityQueue<Integer> ends = new PriorityQueue<>();
        for (Interval interval : intervals) {
            if (ends.isEmpty() || interval.start < ends.peek()) {
                rooms++;
                ends.offer(interval.end);
            } else {
                ends.poll();
                ends.offer(interval.end);
            }
        }
        return rooms;
    }

    static int minMeetingRooms2(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return 0;

        /* Sort the times into an array with labels */
        Time[] times = new Time[intervals.length * 2];
        for (int i = 0; i < intervals.length; i++) {
            times[i * 2] = new Time(intervals[i].start, true);
            times[i * 2 + 1] = new Time(intervals[i].end, false);
        }
        Arrays.sort(times, new Comparator<Time>(){
            @Override
            public int compare(Time t1, Time t2) {
                int diff = t1.time - t2.time;
                if (diff == 0) {
                    if (t1.isStart && t2.isStart)
                        return 0;
                    else if (!t1.isStart && t2.isStart)
                        return -1;
                    else if (t1.isStart && !t2.isStart)
                        return 1;
                    else
                        return 0;
                } else {
                    return diff;
                }
            }
        });

        /* Process the times one by one */
        int maxRooms = 0;
        int rooms = 0;
        for (int i = 0; i < times.length; i++) {
            if (times[i].isStart) {
                rooms++;
                if (rooms > maxRooms) maxRooms = rooms;
            } else {
                rooms--;
            }
        }

        return maxRooms;
    }

}
