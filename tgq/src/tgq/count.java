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
		sb2=getFile();//StringBuffer�ļ�����
				 
	     //���ʽ�ȡ
		List<String> lists = new ArrayList<String>();  //�洢���˺󵥴ʵ��б�  
		String str2 = sb2.toString();
		String[] wordsArr1 = str2.split("[^a-zA-Z]");  //���˳�ֻ������ĸ��  
		for (String word : wordsArr1) {  
			if(word.length() != 0){  //ȥ������Ϊ0���� 
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
			System.out.println("          P:          ��������result.txt                 ");
			System.out.println("          C:              ��Ƶ�ʼ���                                                            ");
			System.out.println("          L:           �������뵥�ʵĴ�Ƶ                                                    ");
			System.out.println("________________________________________________________");
			System.out.print("������ʾ���ţ�     ");
			String code = in.next();
			switch(code)
			{
			case "P":
			{
				Set<StringBuffer> ks1 = ts.keySet();
				for(StringBuffer s2:ks1) 
				{
			
					try {
						setFile ("���ʣ�"+s2.toString()+"            ���ִ�����"+ts.get(s2)+"\r\n");
					} catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
				System.out.println("�ļ��ɹ����뵽ָ��·����");
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
				System.out.print("����鿴����������  ");
				n = in.nextInt();
				for(int i=0;i<n;i++)
				{
					System.out.println("���� "+(i+1));
					String word = in.next();
					Set<StringBuffer> ks1 = ts.keySet();
					for(StringBuffer s2:ks1) 
					{
						if(word.equals(s2.toString()))
						{
							System.out.println("���ʣ�"+s2.toString()+"            ���ִ�����"+ts.get(s2));
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
		System.out.println("��Ҫ����ĸ�Ƶ�ʸ�����");
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
		 System.out.println("��Ƶ���Լ�����ִ�����"); 
		 for(Entry<String,Integer> entry : aMap2.entrySet()) 
		 { 
			 System.out.println("���ʣ� " +entry.getKey() + "      ����   ��" + entry.getValue()); 
			 count=count+1;
			 if(count==n)
				 break;
		 } 
	} 
	public static StringBuffer getFile () { //�ļ����뺯��
		String str=null;//����һ���ַ������ͱ���str
		StringBuffer sb1=  new  StringBuffer();
		try {
			BufferedReader in = new BufferedReader(new FileReader("file.txt"));
	        while ((str = in.readLine())!= null) 
	        {
	        	str = str.toLowerCase();
	        	sb1.append(str);
	        }
            in.close();
		} catch (IOException e) {//��try��������쳣ʱת��catch�����
	       	System.out.println("��ȡ����");
	       	e.printStackTrace();//printStackTrace()�����Ǵ�ӡ�쳣��Ϣ�ڳ����г����λ�ü�ԭ��
		}
		System.out.println(sb1);//���sb1
			return sb1; 
	}
	public static void setFile (String s) throws IOException { //�ļ�д�뺯��
		byte[] bs = s.getBytes();//�����ֽ�������������������
		FileOutputStream fos=new FileOutputStream("result.txt",true);
		 try {
			 fos.write(bs);
			 
		 } catch (IOException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }//�ӻ������ֽ����������ֽ�����ʽȡ��

		 fos.close();//�رն�ȡ��
		 
		 File file=new File("result.txt");//�����ļ�����
		 if(file.exists()){//if��������������exists�����ж��ļ��Ƿ����
		 }
		 else{//����
			 System.out.println("����ļ�������");//����ļ�������
		 }
	}
	
}

