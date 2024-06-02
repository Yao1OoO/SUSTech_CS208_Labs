class BF {

    public static long Bf(Point[] points){
        long min = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                long res = (long) (points[i].x - points[j].x) *(points[i].x-points[j].x) + (long) (points[i].y-points[j].y)*(points[i].y-points[j].y);
                min = Math.min(res,min);
            }
        }
        return min;
    }
}
