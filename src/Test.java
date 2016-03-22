import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import sun.net.www.http.HttpClient;

//1F887CAAECCD54099D7D194BF4096D58	
public class Test {
	public static void main(String[] args) {
		String s = getURLContent("http://localhost:8081/mup-app-web-mall/home/index;JSESSIONID=1F887CAAECCD54099D7D194BF4096D58");
		System.out.println(s);
	}
	 /**  
     * 程序中访问http数据接口  
     */  
    public static String getURLContent(String urlStr) {             
        /** 网络的url地址 */      
     URL url = null;            
        /** http连接 */  
     HttpURLConnection httpConn = null;          
         /**//** 输入流 */ 
     BufferedReader in = null; 
     StringBuffer sb = new StringBuffer(); 
     try{   
      url = new URL(urlStr);   
      in = new BufferedReader( new InputStreamReader(url.openStream(),"UTF-8") ); 
      String str = null;  
      while((str = in.readLine()) != null) {  
       sb.append( str );   
             }   
         } catch (Exception ex) { 
           
         } finally{  
          try{           
           if(in!=null) {
            in.close();   
                 }   
             }catch(IOException ex) {    
             }   
         }   
         String result =sb.toString();   
         return result;  
         }  


/**
     * post方式请求http服务
     * @param urlStr
     * @param params   name=yxd&age=25
     * @return
     * @throws Exception
     */
    public static String getURLByPost(String urlStr,String params)throws Exception{
        URL url=new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        PrintWriter printWriter = new PrintWriter(conn.getOutputStream());
        printWriter.write(params);
        printWriter.flush();        
        BufferedReader in = null; 
        StringBuilder sb = new StringBuilder(); 
        try{   
            in = new BufferedReader( new InputStreamReader(conn.getInputStream(),"UTF-8") ); 
            String str = null;  
            while((str = in.readLine()) != null) {  
                sb.append( str );   
            }   
         } catch (Exception ex) { 
            throw ex; 
         } finally{  
          try{ 
              conn.disconnect();
              if(in!=null){
                  in.close();
              }
              if(printWriter!=null){
                  printWriter.close();
              }
          }catch(IOException ex) {   
              throw ex; 
          }   
         }   
         return sb.toString(); 
    }


 
}
