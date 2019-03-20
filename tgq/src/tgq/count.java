package tgq;

import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.util.Map.Entry;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;


public class count{
	public static void main(String[] args)  {
		Scanner in = new Scanner(System.in);
		// TODO Auto-generated method stub
		TreeMap<StringBuffer,String > ts = new TreeMap<StringBuffer,String>
		(new Comparator<StringBuffer>(){
			@Override
			public int compare(StringBuffer sb1,StringBuffer sb2 ) {
			
				if(sb1==null&&sb2==null) {
					return 1;
				}
				String str1 = sb1.toString();
				String str2 = sb2.toString();
				if (str1.compareTo(str2)>0) {
					return 1;		
				}
				else if (str1.compareTo(str2)<0) {
					return -1;
				}
				else
				{
					return 0;
				}
			}
		});
		StringBuffer sb2=  new  StringBuffer();
		sb2=getFile();//StringBuffer文件读入
				 
	     //单词截取
		List<String> lists = new ArrayList<String>();  //存储过滤后单词的列表  
		String str2 = sb2.toString();
		String[] wordsArr1 = str2.split("[^a-zA-Z]");  //过滤出只含有字母的  
		for (String word : wordsArr1) {  
			if(word.length() != 0){  //去除长度为0的行 
				//System.out.println(word);
				lists.add(word);  
			} 
		}  
		int count=1; 
		Map<String, Integer> hashMap = new HashMap<String, Integer>();
		for(String str: lists) 
		{
			StringBuffer s2 = new StringBuffer( str);
			
			if (ts.containsKey(s2)) {
				count=Integer.parseInt(Integer.toString(count));;
				count++;
				ts.put(s2,Integer.toString(count));
				hashMap.put(str,count);
			}
			else {
				count=1;
				ts.put(s2, Integer.toString(count));
				hashMap.put(str,count);
			}	 
		}
		
			
		while(true)
		{
			System.out.println("________________________________________________________");
			System.out.println("          P:          结果输出到result.txt                 ");
			System.out.println("          C:              高频词计数                                                            ");
			System.out.println("          L:           查找输入单词的词频                                                    ");
			System.out.println("________________________________________________________");
			System.out.print("输入提示符号：     ");
			String code = in.next();
			switch(code)
			{
			case "P":
			{
				Set<StringBuffer> ks1 = ts.keySet();
				for(StringBuffer s2:ks1) 
				{
			
					try {
						setFile ("单词："+s2.toString()+"            出现次数："+ts.get(s2)+"\r\n");
					} catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
				System.out.println("文件成功读入到指定路径下");
			}
			break;
			case "C":
			{
				sort(hashMap);
				
			}
			break;
			case "L":
			{
				int n=0;
				int num=0;
				System.out.print("输入查看单词数量：  ");
				n = in.nextInt();
				for(int i=0;i<n;i++)
				{
					System.out.println("单词 "+(i+1));
					String word = in.next();
					Set<StringBuffer> ks1 = ts.keySet();
					for(StringBuffer s2:ks1) 
					{
						if(word.equals(s2.toString()))
						{
							System.out.println("单词："+s2.toString()+"            出现次数："+ts.get(s2));
							num++;
							if(num==n)
								break;
						}	
					}
				}
			}
			break;
			}
		}
		
	}
		

	private static void sort(Map<String, Integer>hashMap)
	{ 
		Scanner in = new Scanner(System.in);
		int n,count=0;
		System.out.println("需要输出的高频词个数：");
		n=in.nextInt();
		 Set<java.util.Map.Entry<String, Integer>> mapEntries = hashMap.entrySet();  
		 List<Entry<String,Integer>> aList = new LinkedList<Entry<String,Integer>>(mapEntries); 

		 Collections.sort(aList, new Comparator<Entry<String,Integer>>() { 

		 public int compare(Entry<String, Integer> ele1, 
		 Entry<String, Integer> ele2) { 

		 return ele2.getValue().compareTo(ele1.getValue()); 
		 } 
		 });  
		 Map<String,Integer> aMap2 = new LinkedHashMap<String, Integer>(); 
		 for(Entry<String,Integer> entry: aList) { 
		 aMap2.put(entry.getKey(), entry.getValue()); 
		 } 
		 System.out.println("高频词以及其出现次数："); 
		 for(Entry<String,Integer> entry : aMap2.entrySet()) 
		 { 
			 System.out.println("单词： " +entry.getKey() + "      次数   ：" + entry.getValue()); 
			 count=count+1;
			 if(count==n)
				 break;
		 } 
	} 
	public static StringBuffer getFile () { //文件读入函数
		String str=null;//定义一个字符串类型变量str
		StringBuffer sb1=  new  StringBuffer();
		try {
			BufferedReader in = new BufferedReader(new FileReader("file.txt"));
	        while ((str = in.readLine())!= null) 
	        {
	        	str = str.toLowerCase();
	        	sb1.append(str);
	        }
            in.close();
		} catch (IOException e) {//当try代码块有异常时转到catch代码块
	       	System.out.println("读取有误");
	       	e.printStackTrace();//printStackTrace()方法是打印异常信息在程序中出错的位置及原因
		}
		System.out.println(sb1);//输出sb1
			return sb1; 
	}
	public static void setFile (String s) throws IOException { //文件写入函数
		byte[] bs = s.getBytes();//定义字节数组用来当作缓冲区
		FileOutputStream fos=new FileOutputStream("result.txt",true);
		 try {
			 fos.write(bs);
			 
		 } catch (IOException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }//从缓冲区字节数组中以字节流形式取出

		 fos.close();//关闭读取流
		 
		 File file=new File("result.txt");//创建文件对象
		 if(file.exists()){//if语句的条件，调用exists方法判断文件是否存在
		 }
		 else{//否则
			 System.out.println("输出文件不存在");//输出文件不存在
		 }
	}
	
}

