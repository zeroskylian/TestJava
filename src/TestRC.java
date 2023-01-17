public class TestRC {
    int[] result = new int[8];//全局或成员变量,下标表示行,值表示queen存储在哪一列
    public void cal8queens(int row) { // 调用方式：cal8queens(0);
        if (row == 8) { // 8个棋子都放置好了，打印结果
            printQueens(result);
            return; // 8行棋子都放好了，已经没法再往下递归了，所以就return
        }
        for (int column = 0; column < 8; ++column) { // 每一行都有8中放法
            if (isOk(row, column)) { // 有些放法不满足要求
                result[row] = column; // 第row行的棋子放到了column列
                cal8queens(row+1); // 考察下一行
            }
        }
    }

    /*八皇后我一开始一直没想明白一个问题，就是每当print一种结果之后，并没有重置result数组，那再穷举下一种结果时，result中的脏数据会影响到isOK()函数的判断吗？
经过一番调试思考后，发现我的担心多余了。每次穷举下一种结果时，都会在上一种结果的基础上进行穷举，所以result中<row之前的结果都是有用的，不是脏数据, 也不应该清掉它。
而isOK()每次都寻找正上，左上，右上的占用结果进行判断，因此result中>=row的结果也不会影响到isOK()的判断，很快就可能被后续的穷举重新设置，遂而形成新的穷举结果。
有这些疑问只能说明我对递归回溯理解得还不到位，只能通过一步步调试来寻找答案
    * */
    private boolean isOk(int row, int column) {//判断row行column列放置是否合适
        int leftup = column - 1, rightup = column + 1;
        for (int i = row-1; i >= 0; --i) { // 逐行往上考察每一行
            if (result[i] == column) return false; // 第i行的column列有棋子吗？
            if (leftup >= 0) { // 考察左上对角线：第i行leftup列有棋子吗？
                if (result[i] == leftup) return false;
            }
            if (rightup < 8) { // 考察右上对角线：第i行rightup列有棋子吗？
                if (result[i] == rightup) return false;
            }
            --leftup; ++rightup;
        }
        return true;
    }
    private void printQueens(int[] result) { // 打印出一个二维矩阵
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                if (result[row] == column) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();
    }

    int step(int param) {
        if (param == 1) {
            return 1;
        }
        if (param == 2) {
            return 2;
        }
        return  step(param - 1) + step(param - 2);
    }

    public static void main(String[] args) {
//        TestRC t = new TestRC();
//        t.cal8queens(0);
//        System.out.println(t.step(5));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 6) {
                    break;
                    // 在执行i==6时强制终止循环，i==6不会被执行
                }
                System.out.println(j + "===");
            }
            System.out.println(i);
        }
    }
}
