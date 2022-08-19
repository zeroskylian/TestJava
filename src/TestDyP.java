public class TestDyP {

    public static int maxW = Integer.MAX_VALUE; //存储背包中物品总重量的最大值

    // cw表示当前已经装进去的物品的重量和；i表示考察到哪个物品了；
    // w背包重量；items表示每个物品的重量；n表示物品个数
    // 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，那可以这样调用函数：
    // f(0, 0, a, 10, 100)
    public static void f(int i, int cw, int[] items, int n, int w) {
        System.out.println(String.format("enter %d",i));
        if (cw == w || i == n) { // cw==w表示装满了;i==n表示已经考察完所有的物品
            System.out.println(String.format("exit %d",i));
            if (cw > maxW) maxW = cw;
            return;
        }
        f(i + 1, cw, items, n, w);
        if (cw + items[i] <= w) { // 已经超过可以背包承受的重量的时候，就不要再装了
            f(i + 1, cw + items[i], items, n, w);
        }
        System.out.println(String.format("leave %d",i));
    }
    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w+1]; // 默认值false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w-items[i]; j >= 0; --j) {//把第i个物品放入背包
                if (states[j]==true)
                    states[j+items[i]] = true;
            }
            //  int[] a =  {2, 2, 5, 9, 13}; knapsack2(a, 5, 10); 可用这组数据尝试为什么由小到大不对
            //  j需要从大到小计算，因为从小到大，当前阶段刚刚因为决策放入而加上去的重量，在后续遍历时会被当做上个阶段的true。
//            for (int j = 0; j <= w-items[i]; ++j) {//把第i个物品放入背包
//                if (states[j]==true)
//                    if (j+items[i] == 10) {
//                        System.out.println("error");
//                    }
//                    states[j+items[i]] = true;
//            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true)
                return i;
        }
        return 0;
    }

    static int[][] matrix = {{5},{7,8},{2,3,4},{4,9,6,1},{2,7,9,4,5}};

    static int[][] cache = new int[5][6];
    /* 回溯算法
    * matrix 矩阵
    * level 第几层
    * index 当前处于层级的第几个元素
    * value 当前总值
    * */
    static void yanghuiTriangleRyc(int[][] matrix, int level, int index, int value) {
        System.out.println(String.format("%d===%d===%d",level, index, value));
        if (level >= 4) {
            if (value < maxW) {
                maxW = value;
            }
            return;
        }
        int next = level + 1;
        for (int i = 0; i <= 1; i++) {
            int idx = index + i;
            yanghuiTriangleRyc(matrix, next, idx, value + matrix[next][idx]);
        }

//        yanghuiTriangleRyc(matrix, next, index, value + matrix[next][index]);
//        index++;
//        yanghuiTriangleRyc(matrix, next, index, value + matrix[next][index]);
    }

    ///
    static int yanghuiTriangleDy(int[][] matrix) {
        int length = matrix.length;
        int[][] dy = new int[length][length];
        for (int i = length - 1; i >= 0; i--) {
            int[] rawNums = matrix[i];
            int rowLength = rawNums.length;
            for (int j = 0; j < rowLength; j++) {
                if (i == length - 1) {
                    dy[i][j] = rawNums[j];
                } else {
                    int[] next = dy[i + 1];
                    dy[i][j] = Math.min(next[j], next[j + 1]) + rawNums[j];
                }
            }
        }
        return dy[0][0];
    }

    /// 大牛写的
    public static int yanghuiTriangleByBoss(int[][] matrix) {
        int length = matrix.length;
        // 用于存储每一层的状态
        int[] min = new int[length + 1];
        for (int i = length - 1; i >= 0; i--) {
            int[] rawNums = matrix[i];
            int rowLength = rawNums.length;
            for (int j = 0; j < rowLength; j++) {
                min[j] = Math.min(min[j], min[j + 1]) + rawNums[j];
            }
        }
        return min[0];
    }

    public static void main(String[] args) {
//        yanghuiTriangleRyc(matrix, 0, 0, 5);
//        System.out.println(maxW);
//        System.out.println(yanghuiTriangleByBoss(matrix));
        System.out.println(yanghuiTriangleDy(matrix));
    }
}
