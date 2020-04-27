package com.main;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class Osoba implements Callable<Boolean>{
	
	private boolean go;

	@Override
	public Boolean call() throws Exception {
		// TODO Auto-generated method stub
		int randomnumer = ThreadLocalRandom.current().nextInt(0, 4) + 1;
		
		try {
			Thread.sleep(randomnumer * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		randomnumer = ThreadLocalRandom.current().nextInt(0, 100) + 1;
		
		if(randomnumer > 5)
		{
			go = false;
		}else
		{
			go = true;
		}
		return go;
	}

}
