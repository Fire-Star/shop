package exam.utils;

import java.util.Random;

public class RandomNumber {
	private static Random random=new Random();
	public static int getRandomNumber(int start,int end){
		return random.nextInt(end-start)+start;
	}
}
