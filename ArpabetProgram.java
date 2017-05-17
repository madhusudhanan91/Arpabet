import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class ArpabetProgram 
{
	static boolean flag=false;
	private static String FILE_PATH = "/afs/cad.njit.edu/courses/ccs/s17/cs/610/002/mr488/";
	public static String DICT_FILE = "DictFile.txt";
	LinkedHashMap<String, String> lhmap;
	HashMap<String,ArrayList<String>> arphaValue=new HashMap<String,ArrayList<String>>();
	String[] arphabets;
	int noOfArphabets;
	
	public int getLength(String s){
		String arpts=this.lhmap.get(s);
		String[] temp=arpts.split(" ");
//		System.out.println("TEXT:"+s+" ARPhabets :"+arpts+" Length: "+temp.length);
		return temp.length;
	}
	
	public String getArphabets(int start,int end){
		ArrayList<String> arph=new ArrayList<String>();
	
		StringBuffer arphabets=new StringBuffer();
		for(;start<end;start++){
			arph.add(this.arphabets[start]);
		}
		
		String temp=String.join(" ", arph);
//		System.out.println("GET ARPHABETS CODE REACHED :"+temp);
		return temp;
	}
	
	public ArrayList<String> getText(String arphabet){
		ArrayList<String> temp=arphaValue.get(arphabet);
//		System.out.println("ARPHABET GET TEXT *********"+temp.toString());
		return temp;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> wordBreak(String s, Set<String> wordDict) {
		ArrayList<String> [] pos = new ArrayList[this.noOfArphabets+1];
	    pos[0]=new ArrayList<String>();
//	 System.out.println("WORD BREAK TEST 1");
	    for(int i=0; i<this.noOfArphabets; i++){
	    	
//	   	 System.out.println("WORD BREAK TEST 2 i:"+i);
	   	 try{
	        if(pos[i]!=null){
//	       	 System.out.println("WORD BREAK TEST 3");
	            try{
	            	Boolean flag=false;
	        	for(int j=i+1; (j<=(i+31))&&(j<=this.noOfArphabets); j++){
//	        		 System.out.println("WORD BREAK TEST 4 i :" +i+" j:"+j);
	               // String sub = s.substring(i,j);
	                String sub=this.getArphabets(i,j);
	                if(wordDict.contains(sub.trim())){
	                    if(pos[j]==null){
//	                    	System.out.println("CODE REACHED 1");
	                        ArrayList<String> list = new ArrayList<String>();
//	                        System.out.println("CODE REACHED 2");
	                        list.addAll(this.getText(sub));
//	                        System.out.println("CODE REACHED 3");
	                        pos[j]=list;
//	                        System.out.println("CODE REACHED 4");
	                    }else{
	                        pos[j].addAll(this.getText(sub));
	                    }
	 
	                }
	            }
	        	if (pos[i+1]==null){
	        		ArrayList<String> list = new ArrayList<String>();
	        		pos[i+1]=list;
	        	}
	            }
	            catch(Exception e){
	            	System.out.println("Exception Handled for Second loop"+e);
	            }
	        }
	   	 }
	   	 catch(Exception e){
	   		 System.out.println("Exception caught at WORD BREAK "+e +"\ni:"+i);
	   	 }
	    }
//		System.out.println("The contents stored in Position Table are\n");
//	    for(ArrayList<String> tt:pos)
//	    System.out.println(tt.toString());
	    if(pos[this.noOfArphabets]==null){
//	    	System.out.println("NULL OCCURRED");
	        return new ArrayList<String>();
	    }else{
	        ArrayList<String> result = new ArrayList<String>();
	        dfs(pos, result, "", this.noOfArphabets);
//	        System.out.println("SIZE OF RESULT : "+result.size());
	        return result;
	    }
	}
	 
	public void dfs(ArrayList<String> [] pos, ArrayList<String> result, String curr, int i){
//		System.out.println("DFS EXECUTION");
	    if(i==0){
	    	flag=true;
//	        result.add(curr.trim());
//	    	System.out.println("0th index ENCOUNTERED");
	    	//result.add(this.arphaValue.get(curr).get(0).trim());
	        result.add(curr.trim());
	        
	    	return;
	    }
//	    result.add(curr.trim());
	    for(String s: pos[i]){
	        String combined = s + " "+ curr;
//	        System.out.println("I VALUE : "+i);
	        if (flag)
	        	return;
	        dfs(pos, result, combined, i-this.getLength(s));
	        
	    }
	}
	
	
	public static void main(String[] args) throws FileNotFoundException,IOException 
	{
		ArpabetProgram object1=new ArpabetProgram();
		@SuppressWarnings("unused")
		long startTime = System.currentTimeMillis();
		object1.lhmap = new LinkedHashMap<String, String>();
		//ArrayList<String> arphabets=new ArrayList<String>();
	    //String[] arphabets;
		String str,out="";
	    BufferedReader buf = new BufferedReader(new FileReader(FILE_PATH+DICT_FILE));
	    while ((str = buf.readLine()) != null)
	    {
	        String[] strsplit = str.split("  ", 2);
	        if (strsplit.length >= 2)
	        {
	            String key = strsplit[0];
	            String value = strsplit[1];
	            object1.lhmap.put(key, value);
	            if (object1.arphaValue.containsKey(value)){
	            	ArrayList<String> temp=object1.arphaValue.get(value);
	            	temp.add(key);
	            	object1.arphaValue.put(value,temp);
	            }
	            else{
	            	ArrayList<String> temp=new ArrayList<String>();
	            	temp.add(key);
	            	object1.arphaValue.put(value,temp);
	            }
	        } 
	        else 
	        {
		         System.out.println("End of Line: " + str);
		    }
	        
	        
	    }
//	    System.out.println("The contents of Hash Map are\n");
	    @SuppressWarnings("unused")
		Set<String> set = object1.lhmap.keySet();
//		System.out.println("S EY1 M %%%%%" + object1.arphaValue.get("S EY1 M").toString());
    
	    for (String hashkey : object1.lhmap.keySet())
	    {
	    	if(hashkey.equals("SAME"))
	    	{
//	    	System.out.println(hashkey + "  $$$$$$$$$$$$$$$$$$$$  " + object1.lhmap.get(hashkey));
//	    	System.out.println(object1.arphaValue.get(object1.lhmap.get(hashkey)) + "  $$$$$$$$$$$$$$$$$$$$  " + object1.lhmap.get(hashkey));
	    	}
	    }
	    buf.close();
		System.out.println("Please enter the Input File Name with extension located in " + FILE_PATH);
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		String input = s.nextLine();
		//Scanner scan = new Scanner(new File(filePath + input));
		ArrayList<String> fileList = new ArrayList<String>();
		/*
		 * Test Code
		 * */
		BufferedReader r = new BufferedReader( new FileReader( FILE_PATH + input ) );
		String ss = "";
		String line2 = null;
		int count=0;
		while ((line2 = r.readLine()) != null) {
			if(!line2.endsWith(" "))
				line2=line2+" ";
		    ss += line2;
//		    System.out.println("line no: "+count+++"**"+line2);
		}
		
//		System.out.println(ss);
		object1.arphabets=ss.split(" ");
		object1.noOfArphabets=object1.arphabets.length;
	
//		for(String key:object1.arphaValue.keySet()){
//	    	System.out.println(key+"::"+object1.arphaValue.get(key).toString());
//	    }
//		for(String arp:object1.arphabets){
//			System.out.println(arp);
//		}
//		int maxLength=0;
//		String maxValue="";
//		for(String k:object1.arphaValue.keySet()){
//			String[] temp=k.split(" ");
//			if (temp.length>maxLength){
//				maxLength=temp.length;
//				maxValue=k;
//			}
//		}
//		System.out.println("MAX LENGTH **********"+maxLength);
//		System.out.println("MAX Value **********"+maxValue);
		try{
		List<String> output=new ArrayList<String>();
//		System.out.println("Test 1");
		output= object1.wordBreak(ss, object1.arphaValue.keySet());
//		System.out.println("Test 2");
		System.out.println("\nOne possible translation is\n");
//		for(String sentence:output){
////			System.out.println("Test 3");
//			System.out.println(sentence);
//		}
		System.out.println(output.get(0));
		}
		catch(Exception e){
			System.out.println("Exception Handled "+e);
		}
		//System.out.println(arphabets.toString());
//		while(scan.hasNext())
//		{
//			fileList.add(scan.next());
//			System.out.println("SCAN.NEXT*********"+scan.next());
//		
//		}
		//System.exit(0);
		//scan.close();
//		System.out.println("The contents of the Array List are:\n");
//		for(int i=0;i<fileList.size();i++)
//		{
//			System.out.println(fileList.get(i));
//		}
//		System.out.println("\nExtracted Key Values are\n");
//		
//		for(int i=0;i<fileList.size();i++){
//			for(@SuppressWarnings("rawtypes") Map.Entry m : lhmap.entrySet()){
//				if(fileList.get(i).contains((CharSequence) m.getValue())){
//				System.out.println(m.getKey());
//					out = m.getKey().toString();
//					System.out.println(out);
//				}
//			}
//			
//		}
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.println("\nThe total running time of this program is " + formatter.format(totalTime / 1000d) + " seconds");
	}
}
