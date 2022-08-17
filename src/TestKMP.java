import java.io.*;
class TestKMP {
  // b表示模式串，m表示模式串的长度
  private static int[] getNexts(char[] b, int m) {
    int[] next = new int[m];
    next[0] = -1;
    int k = -1;
    for (int i = 1; i < m; ++i) {
      while (k != -1 && b[k + 1] != b[i]) {
        k = next[k];
      }
      if (b[k + 1] == b[i]) {
        ++k;
      }
      next[i] = k;
      System.out.println(k);
    }
    return next;
  }

  public static void main(String[] args) {
    char[] s = { 'a', 'b', 'a', 'b', 'a', 'c', 'd' };
    int[] a = getNexts(s, s.length);
    for (int i = 0; i < s.length; i++) {
      // System.out.println(a[i]);
    }
  }
}