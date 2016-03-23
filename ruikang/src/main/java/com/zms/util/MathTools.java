package com.zms.util;

import java.util.ArrayList;
import java.util.List;

public class MathTools {
	
	
	
	//字符串数组转 int数组
	public static int[] strArray2intArray(String...arr){
        int[] intArr = new int[arr.length];
        for (int i=0; i<arr.length; i++) {
            intArr[i] = Integer.parseInt(arr[i]);
           
        }
        return intArr;
    }
	
	//字符串数组转 integer数组
	public static Integer[] strArray2IntegerArray(String...arr){
		Integer[] intArr = new Integer[arr.length];
        for (int i=0; i<arr.length; i++) {
            intArr[i] = Integer.parseInt(arr[i]);
           
        }
        return intArr;
    }
	
	//字符串数组转  list<Integer>
	public static   List<Integer>  strArray2intList(String...arr){
         List<Integer>  ids=new ArrayList<Integer>();
        for (int i=0; i<arr.length; i++) {
        	
             ids.add(Integer.parseInt(arr[i]));
           
        }
        return ids;
    }

}
