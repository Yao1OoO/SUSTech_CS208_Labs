import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class ClosestPair {
    public static Long closestPair(Point[] points){
        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return (p1.x > p2.x) ? 1 : (p1.x == p2.x) ? 0 : -1;
            }

        });
        return divide(0,points.length-1,points);
    }

    public static long divide(int left, int right, Point[] points) {
        long curMinDis = Long.MAX_VALUE;
        if (left == right) {
            return curMinDis;
        }
        if (left + 1 == right) {
            return distance(points[left], points[right]);
        }
        int middle = (left + right) >> 1;
        long leftMinDis = divide(left, middle, points);
        long rightMinDis = divide(middle, right, points);

        curMinDis = (leftMinDis <= rightMinDis) ? leftMinDis : rightMinDis;

        List<Integer> validPointIndex = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[middle].x - points[i].x) <= curMinDis) {
                validPointIndex.add(i);
            }
        }
        for (int i = 0; i < validPointIndex.size() - 1; i++) {
            for (int j = i + 1; j < validPointIndex.size(); j++) {
                if (Math.abs(points[validPointIndex.get(i)].y
                        - points[validPointIndex.get(j)].y) > curMinDis) {
                    continue;
                }
                long tempDis = distance(points[validPointIndex.get(i)],
                        points[validPointIndex.get(j)]);

                curMinDis = (tempDis < curMinDis) ? tempDis : curMinDis;
            }
        }

        return curMinDis;
    }

    public static long distance(Point p1, Point p2) {
        return (long) (p2.y - p1.y) * (p2.y - p1.y) + (long) (p2.x - p1.x) * (p2.x - p1.x);
    }
}

