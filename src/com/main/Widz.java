package com.main;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class Widz implements Callable<Boolean>{

	private boolean go;
	
	@Override
	public Boolean call() throws Exception {
		// TODO Auto-generated method stub

		int randomnumer = ThreadLocalRandom.current().nextInt(0, 100) + 1;
		
		if(randomnumer <= 3)
		{
			go = true;
		}else
		{
			go = false;
		}
		return go;
	}

	

}
