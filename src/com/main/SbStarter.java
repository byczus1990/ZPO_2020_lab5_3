package com.main;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class SbStarter {

	public static void main(String[] args) throws ExecutionException {
		// TODO Auto-generated method stub

		int numberOfViewers = 0;
		ExecutorService executorService = Executors.newFixedThreadPool(100);
		CompletionService<Boolean> taskCompletionService = new ExecutorCompletionService<>(executorService);
		
		for (int i = 0; i < 100; i++)
		{	
			taskCompletionService.submit(new Osoba());
		}
		
		int odebranych = 0;
		boolean error = false;
		while(odebranych < 100 && !error)
		{
			try {
				Future<Boolean> fut = taskCompletionService.take();
//				System.out.println("::" + fut.get());
				if(fut.get() == true)
				{
					numberOfViewers++;
					System.out.println(numberOfViewers);
				}
				odebranych++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		executorService.shutdown();
		
		if(numberOfViewers < 5)
		{
			System.out.println("PRZEPRASZAMY FILM NIE BÊDZIE WYŒWIETLANY\nDLA MNIEJ NI¯ 5 OSÓB");
		}else
		{
			ExecutorService executorService2 = Executors.newFixedThreadPool(numberOfViewers);
			CompletionService<Boolean> taskCompletionService2 = new ExecutorCompletionService<>(executorService2);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (int i = 0; i < numberOfViewers; i++)
			{
				taskCompletionService2.submit(new Widz());
			}
			
			int odebranych2 = 0;
			boolean error2 = false;
			int numberOfLeavers = 0;
			while(odebranych2 < numberOfViewers && !error2)
			{
				try {
					Future<Boolean> fut = taskCompletionService2.take();
//					System.out.println("::" + fut.get());
					if(fut.get() == true)
					{
						numberOfLeavers++;
						System.out.println(numberOfViewers);
					}
					odebranych2++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				if(numberOfViewers - numberOfLeavers < 5)
				{
					System.out.println("FRAJERZY PRZERYWAMY FILM.\nKASY NIE ODDAJEMY...");
				}else
				{
					System.out.println("ZOSTA£O WIDZÓW: " + (numberOfViewers - numberOfLeavers));
					System.out.println("THE END. FAJNY FILM?");
				}
				
				executorService2.shutdown();
		}

//		System.out.println(taskCompletionService);
		
		}
	}

