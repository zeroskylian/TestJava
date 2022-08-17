class TestBuildHeap {
  static void buildHeap(int[] a, int n) {
    for (int i = n / 2; i >= 1; --i) {
      heapify(a, n, i);
    }
  }

  static void heapify(int[] a, int n, int i) {
    while (true) {
      int maxPos = i;
      if (i * 2 <= n && a[i] < a[i * 2])
        maxPos = i * 2;
      if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1])
        maxPos = i * 2 + 1;
      if (maxPos == i)
        break;
      swap(a, i, maxPos);
      i = maxPos;
    }
  }

  static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static void main(String[] args) {
    int[] a = { 1, 3, 2, 4, 5, 7 };
    buildHeap(a, 5);
    System.out.println(a);
    for (int i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
  }
}