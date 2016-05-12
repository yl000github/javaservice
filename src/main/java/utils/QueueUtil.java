package utils;

import java.util.Iterator;
import java.util.Queue;

public class QueueUtil {
	public static boolean compare(Queue<String> q1,Queue<String> q2){
		if(q1.size()!=q2.size()) return false;
		Iterator<String> it1=q1.iterator();
		Iterator<String> it2=q2.iterator();
		while(it1.hasNext()){
			if(!it1.next().equals(it2.next())) return false;
		}
		return true;
	}
}
