package study.java.jdks.version1_5.introspector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * 内省
 * @author hadoop2
 *
 */
public class MyIntrospector {
    public static void setProperty(UserInfo userInfo,String propertyName)throws Exception{
        PropertyDescriptor propDesc=new PropertyDescriptor(propertyName,UserInfo.class);
        Method methodSetProperty=propDesc.getWriteMethod();
        System.out.println("获取的方法名为:" + methodSetProperty.getName());
        methodSetProperty.invoke(userInfo, "wong@126.com");
        getProperty(userInfo,propertyName);
        
    }  
    public static void getProperty(UserInfo userInfo,String propertyName)throws Exception{
        PropertyDescriptor proDescriptor =new PropertyDescriptor(propertyName,UserInfo.class);
        Method methodGetProperty=proDescriptor.getReadMethod();
        Object objPropertyValue=methodGetProperty.invoke(userInfo);
        System.out.println("get "  + propertyName + ":"+objPropertyValue.toString());
    }
    
    
    public static void setPropertyByIntrospector(UserInfo userInfo,String propertyName)throws Exception{
    	
        BeanInfo beanInfo=Introspector.getBeanInfo(UserInfo.class);
        PropertyDescriptor[] proDescrtptors=beanInfo.getPropertyDescriptors();
        if(proDescrtptors!=null&&proDescrtptors.length>0){
            for(PropertyDescriptor propDesc:proDescrtptors){
                if(propDesc.getName().equals(propertyName)){
                    Method methodSetProperty=propDesc.getWriteMethod();
                    methodSetProperty.invoke(userInfo, "alan");
                    break;
                }
            }
        }
    }

    
    public static void getPropertyByIntrospector(UserInfo userInfo,String propertyName)throws Exception{
        BeanInfo beanInfo=Introspector.getBeanInfo(UserInfo.class);
        PropertyDescriptor[] proDescrtptors=beanInfo.getPropertyDescriptors();
        if(proDescrtptors!=null&&proDescrtptors.length>0){
            for(PropertyDescriptor propDesc:proDescrtptors){
                if(propDesc.getName().equals(propertyName)){
                    Method methodGetProperty=propDesc.getReadMethod();
                    Object objPropertyValue=methodGetProperty.invoke(userInfo);
                    System.out.println("get " + propertyName + ":"+objPropertyValue.toString());
                    break;
                }
            }
        }
    }

    
    
    
    
    
    
    
    public static void main(String[] args) {
    	UserInfo userInfo = new UserInfo();
    	try {
			setProperty(userInfo,"emailAddress");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}


class UserInfo {
    private long userId;
    private String userName;
    private int age;
    private String emailAddress;
    
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
}


