
// A5 Part 2: Code needs to be added to the run() method
//		of class Circular_Buffer

import java.util.*;

import Scheduler.Scheduler;
import Scheduler.Process;
import Scheduler.Guard;
class A5_Part2 {

	public static void main(String[] args) {

		Producer p = new Producer();
		Consumer c  = new Consumer();
		Circular_Buffer b = new Circular_Buffer();
		
		Process[] arr = {p, c, b};
		Scheduler s = new Scheduler(arr, true);
				// true => show the scheduler's log
				// false => don't shown any log
		s.start();
		 
	}
}

class Producer extends Process   {    

	public void run() {
		for (int v = 1; v < 11; v++) {
			send_data("put", v);
		}
	}
	}

class Consumer extends Process   {    
	
	public void run() {
		while (true) {
			try { Thread.sleep(new Random().nextInt(100));
			} 
			catch (Exception e) {}
			receive("get");
			int v = (int) get_data();
			// no need for above line, strictly speaking,
			// as the printing was already done 
		}
	}	
}

public class Circular_Buffer extends Process  {
	
	private final int n = 5;
	private int[] data = new int[n];
	private int p = 0;
	private int g = 0;
	private int count = 0;
	private String state; // needed for property checking
	
	private void put(int v) {
		data[p] = v;
		p = (p + 1) % n;
		state = "Put:" + v;
		System.out.println(state);
		count++;
	}
	
	private int get() {
		int v = data[g];
		g = (g + 1) % n;
		count--;
		state = "Get:" + v;
		System.out.println(state);
		return v;
	}
	public void run() {	
		while (true) {
			
			if(count == 0)
			{
				receive("put");
				int x = (int) get_data();
				put(x);	

			}
			else if(count == n)
			{
				int x = get();
				send_data("get", x);
			}
			else
			{
				send_or_receive_data("get", data[g], "put");
				switch (chosen)
				{
					case("put"):
					{
						int x = (int) get_data();
						put(x);
						break;
					}
					case("get"):
					{
						get();
						break;
					}
				}
			}
			
		}

	}
}

