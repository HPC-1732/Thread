import java.util.Scanner;

public class ThreadedArraySum {

  public static void main(String[] args) throws InterruptedException {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int numbers[] = new int[n];
    int t = Math.min(n, 4);
    for (int i = 0; i < n; i++) {
      numbers[i] = sc.nextInt();
    }
    int chunkSize = n / t;

    Thread[] threads = new Thread[t];
    PartialSum[] partialSums = new PartialSum[t];
    for (int i = 0; i < t; i++) {
      int start = i * chunkSize;
      int end = (i == t - 1 ? n : Math.min((i + 1) * chunkSize, n));
      partialSums[i] = new PartialSum(numbers, start, end, chunkSize);
      threads[i] = new Thread(partialSums[i]);
      threads[i].start();
    }

    for (Thread thread : threads) {
      thread.join();
    }

    int totalSum = 0;
    for (PartialSum partialSum : partialSums) {
      totalSum += partialSum.getSum();
    }

    System.out.println("Total sum: " + totalSum);
  }
}

class PartialSum implements Runnable {

  private final int[] numbers;
  private final int start;
  private final int end;
  private int sum;
  private int chunkSize;

  public PartialSum(int[] numbers, int start, int end, int chunkSize) {
    this.numbers = numbers;
    this.start = start;
    this.end = end;
    this.chunkSize = chunkSize;
  }

  @Override
  public void run() {
    System.out.println("Current thread no: " + (start / chunkSize + 1));
    sum = 0;
    for (int i = start; i < end; i++) {
      sum += numbers[i];
    }
  }

  public int getSum() {
    return sum;
  }
}
