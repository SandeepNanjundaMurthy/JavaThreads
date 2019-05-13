import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Async {
	
	public void PrintHello(){
		System.out.println("Hello");
	}
	
	public void PrintWorld(){
		System.out.println("World");
	}
	
	public void method() throws InterruptedException {
		CompletableFuture.runAsync(() -> PrintHello());
		CompletableFuture.runAsync(() -> PrintWorld());
	}
	
	public static void main(String[] args) throws InterruptedException{
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		try{
			executor.scheduleAtFixedRate(() -> {
				try {
					new Async().method();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}, 10, 10, TimeUnit.SECONDS);
			TimeUnit.MINUTES.sleep(1);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}

}
