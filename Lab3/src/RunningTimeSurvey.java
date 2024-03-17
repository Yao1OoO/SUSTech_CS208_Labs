//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Random;
//import java.io.File;
//import java.lang.reflect.Method;
//
//import jxl.Workbook;
//import jxl.write.Label;
//import jxl.write.WritableSheet;
//import jxl.write.WritableWorkbook;
//
//public class RunningTimeSurvey {
//    //             task name            function name             run times upper
//    static String[][] taskList = {
//            {"LinearTimeTest", "linearTime", "10000000"},
//            {"LinearTimeTest", "linearTimeCollections", "10000000"},
//            { "NlognTimeTest",       "NlognTime",           "1000000"},
//            { "QuadraticTimeTest",   "QuadraticTime",       "10000"},
//            { "CubicTimeTest",       "CubicTime",           "1000"},
//            { "ExponentialTimeTest", "ExponentialTime",     "29"},
//            { "FactorialTimeTest",   "FactorialTime",       "12"}
//
//    };
//
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        String osName = System.getProperty("os.name");
//        System.out.println(osName);
//        try {
//            File xlsFile = new File("RunningTimeSurvey.xls");
//            // Create a workbook
//            WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
//            // Create a worksheet
//            WritableSheet sheet = workbook.createSheet("RunningTime", 0);
//            // the first row
//            int max_upper = 0, max_giant_upper = 0;
//            for (String[] taskInfo : taskList) {
//                max_upper = Math.max(max_upper, Integer.parseInt(taskInfo[2]));
//                if (taskInfo[0].equals("ExponentialTimeTest") || taskInfo[0].equals("FactorialTimeTest")) {
//                    max_giant_upper = Math.max(max_giant_upper, Integer.parseInt(taskInfo[2]));
//                }
//            }
//            for (int j = 1, n = 10; n <= max_upper; j++, n *= 10) {
//                sheet.addCell(new Label(j + 1, 0, "n = " + n));
//            }
//            int next_row = 1;
//            for (int i = 0; i < taskList.length; i++) {
//                String[] taskInfo = taskList[i];
//                if (taskInfo[0].equals("ExponentialTimeTest") || taskInfo[0].equals("FactorialTimeTest")) {
//                    continue;
//                }
//                Class<?> me = Class.forName(Thread.currentThread().getStackTrace()[1].getClassName());
//                Method method = me.getMethod(taskInfo[1], int.class);
//                int upper = Integer.parseInt(taskInfo[2]);
//                sheet.addCell(new Label(0, i + 1, taskInfo[0]));
//                sheet.addCell(new Label(1, i + 1, taskInfo[1]));
//                next_row = i + 1;
//                for (int j = 1, n = 1; Math.pow(10, j) <= upper; j++) {
//                    n = 10 * n;
//                    Long time = (Long) method.invoke(null, n);
//                    // add data to sheet
//                    sheet.addCell(new Label(j + 1, i + 1, time.toString()));
//                }
//            }
//            ++next_row;
//            for (int j = 1, n = 10; n <= max_giant_upper; j++, ++n) {
//                sheet.addCell(new Label(j + 1, next_row, "n = " + n));
//            }
//            ++next_row;
//            for (String[] taskInfo : taskList) {
//                if (!(taskInfo[0].equals("ExponentialTimeTest") || taskInfo[0].equals("FactorialTimeTest"))) {
//                    continue;
//                }
//                Class<?> me = Class.forName(Thread.currentThread().getStackTrace()[1].getClassName());
//                Method method = me.getMethod(taskInfo[1], int.class);
//                int upper = Integer.parseInt(taskInfo[2]);
//                sheet.addCell(new Label(0, next_row, taskInfo[0]));
//                sheet.addCell(new Label(1, next_row, taskInfo[1]));
//                for (int j = 1, n = 10; n <= upper; j++) {
//                    ++n;
//                    Long time = (Long) method.invoke(null, n);
//                    // add data to sheet
//                    sheet.addCell(new Label(j + 1, next_row, time.toString()));
//                }
//                ++next_row;
//            }
//            workbook.write();
//            workbook.close();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    public static void mergeSort(long[] list) {
//        if (list.length > 1) {
//            long[] firstHalf = new long[list.length / 2];
//            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
//            mergeSort(firstHalf);
//            long[] secondHalf = new long[list.length - list.length / 2];
//            System.arraycopy(list, list.length / 2, secondHalf, 0, list.length - list.length / 2);
//            mergeSort(secondHalf);
//            merge(firstHalf, secondHalf, list);
//        }
//    }
//    public static void merge(long[] list1, long[] list2, long[] temp) {
//        int current1 = 0;
//        int current2 = 0;
//        int current3 = 0;
//        while (current1 < list1.length && current2 < list2.length) {
//            if (list1[current1] < list2[current2]) {
//                temp[current3++] = list1[current1++];
//            } else {
//                temp[current3++] = list2[current2++];
//            }
//        }
//        while (current1 < list1.length) {
//            temp[current3++] = list1[current1++];
//        }
//        while (current2 < list2.length) {
//            temp[current3++] = list2[current2++];
//        }
//    }
//    public static long linearTimeCollections(int n) {
//        ArrayList<Long> arrayList = new ArrayList<Long>(n);
//        generateArrayList(n, arrayList);
//        long timeStart = System.currentTimeMillis();
//        getMax(n, arrayList);
//        long timeEnd = System.currentTimeMillis();
//        long timeCost = timeEnd - timeStart;
//        return timeCost;
//    }
//
//    public static long linearTime(int n) {
//        long[] list = new long[n];
//        generateList(n, list);
//        long timeStart = System.currentTimeMillis();
//        getMax(n, list);
//        long timeEnd = System.currentTimeMillis();
//        long timeCost = timeEnd - timeStart;
//        return timeCost;
//    }
//
//    public static long getMax(long n, long[] list) {
//        long max = list[0];
//        for (int i = 1; i < n; i++) {
//            if (list[i] > max) {
//                max = list[i];
//            }
//        }
//        return max;
//    }
//
//    public static void generateList(int n, long[] list) {
//        for (int i = 0; i < n; i++) {
//            list[i] = i;
//        }
//        // shuffle
//        Random rnd = new Random();
//        for (int i = list.length; i > 1; i--) {
//            int j = rnd.nextInt(i);
//            long temp = list[j];
//            list[j] = list[i - 1];
//            list[i - 1] = temp;
//        }
//    }
//
//    public static void generateArrayList(int n, ArrayList<Long> arrayList) {
//        for (long i = 0; i < n; i++) {
//            arrayList.add(i);
//        }
//        // shuffle
//        Collections.shuffle(arrayList);
//    }
//
//    public static long getMax(long n, ArrayList<Long> arrayList) {
//        long max = arrayList.get(0);
//        for (int i = 1; i < n; i++) {
//            if (arrayList.get(i) > max) {
//                max = arrayList.get(i);
//            }
//        }
//        return max;
//    }
//
//    public static long NlognTime(int n) {
//        long[] list = new long[n];
//        generateList(n,list);
//        long timeStart = System.currentTimeMillis();
//        mergeSort(list);
//        long timeEnd = System.currentTimeMillis();
//        long timeCost = timeEnd - timeStart;
//        return timeCost;
//    }
//
//    public static long QuadraticTime(int n) {
//        long[] list1 = new long[n];
//        long[] list2 = new long[n];
//        generateList(n,list1);
//        generateList(n,list2);
//        long[][] list3 =new long[n][n];
//        long timeStart = System.currentTimeMillis();
//        list3 = q(list1,list2);
//        long timeEnd = System.currentTimeMillis();
//        long timeCost = timeEnd - timeStart;
//        return timeCost;
//    }
//    public static long[][] q(long[] list1, long[] list2){
//        long[][] list3 = new long[list1.length][list2.length];
//        for (int i = 0; i < list1.length; i++) {
//            for (int j = 0; j <list2.length; j++) {
//                list3[i][j] = list1[i]*list2[j];
//            }
//        }
//        return list3;
//    }
//    public static long CubicTime(int n) {
//        long[] list1 = new long[n];
//        long[] list2 = new long[n];
//        long[] list3 = new long[n];
//        generateList(n,list1);
//        generateList(n,list2);
//        generateList(n, list3);
//        long sum  = 0;
//        long timeStart = System.currentTimeMillis();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                for (int k = 0; k < n; k++) {
//                    sum = sum + list1[i]+list2[j]+list3[k];
//                }
//            }
//        }
//        long timeEnd = System.currentTimeMillis();
//        long timeCost = timeEnd - timeStart;
//        return timeCost;
//    }
//
//    public static long ExponentialTime(int n) {
//        long timeStart = System.currentTimeMillis();
//        long sum = 0;
//        for (int i = 0; i < Math.pow(2,n); i++) {
//            sum+= i;
//        }
//        long timeEnd = System.currentTimeMillis();
//        long timeCost = timeEnd - timeStart;
//        return timeCost;
//    }
//
//    public static long FactorialTime(int n) {
//        long timeStart = System.currentTimeMillis();
//        long l = f(n);
//        long timeEnd = System.currentTimeMillis();
//        long timeCost = timeEnd - timeStart;
//        System.out.println(l);
//        return timeCost;
//    }
//
//
//    public  static  long f (int n){
//        long sum = 1;
//        for (int i = 1; i <=n; i++) {
//            sum *= i;
//        }
//        long re = 1;
//        for (long i = 0; i < sum; i++) {
//           re += re;
//        }
//        return  re;
//    }
//}