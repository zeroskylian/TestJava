
class TestBM {
  private static final int SIZE = 256; // 全局变量或成员变量

  private static void generateBC(char[] b, int m, int[] bc) {
    for (int i = 0; i < SIZE; ++i) {
      bc[i] = -1; // 初始化bc
    }
    for (int i = 0; i < m; ++i) {
      int ascii = (int) b[i]; // 计算b[i]的ASCII值
      bc[ascii] = i;
    }
  }

  public static int bm(char[] a, int n, char[] b, int m) {
    int[] bc = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
    generateBC(b, m, bc); // 构建坏字符哈希表
    int i = 0; // i表示主串与模式串对齐的第一个字符
    while (i <= n - m) {
      int j;
      for (j = m - 1; j >= 0; --j) { // 模式串从后往前匹配
        if (a[i + j] != b[j])
          break; // 坏字符对应模式串中的下标是j
      }
      if (j < 0) {
        return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
      }
      // i+j 是坏字符在主串中的位置；
      // (int)a[i+j]是将坏字符转换为ASCII码
      // 在bc散列中查找位置k，若找到了就滑动j - k,找不到就 滑动j - (-1) 也就是整个字符串的长度
      int k = j - bc[(int) a[i + j]];
      System.out.printf("%d===%s===%s\n", k, b[j], a[k]);
      // 这里等同于将模式串往后滑动j-bc[(int)a[i+j]]位
      i = i + (j - bc[(int) a[i + j]]);
      System.out.printf("%d\n", i);
    }
    return -1;
  }

  public static void main(String[] args) {
    char[] s = { 'a', 'b', 'c', 'a', 'b', 'w', 'd' };
    char[] b = { 'd', 'c', 'a', 'b' };
    int i = bm(s, s.length, b, b.length);
  }
}