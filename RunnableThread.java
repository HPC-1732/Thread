class NewThread implements Runnable {
  Thread t;
  int ThreadNo;

  NewThread(int ThreadNo) {
    t = new Thread(this, "Runnable Thread");
    this.ThreadNo = ThreadNo;
    t.start();
  }

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println(ThreadNo + " Child Thread " + i);
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        System.out.println(ThreadNo + " Child Thraed is interrupted");
      }
    }
    System.out.println("Exiting Child Thread " + ThreadNo);
  }
}

public class RunnableThread {
  public static void main(String[] args) {
    System.out.println("Main Thread Started...");
    new NewThread(1);
    new NewThread(2);
    System.out.println("Main Thread exited...");
  }
}
