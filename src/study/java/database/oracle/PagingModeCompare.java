package study.java.database.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PagingModeCompare{
	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@10.1.193.120:1521:DB1", "md",	"md");
		long a = System.currentTimeMillis();
		testPageQuery1(conn,100);  	
		//testPageQuery2(conn,100);  	
		//[{1,000,000:110535,	100,000:10915,	10,000:1345,	1,000:301,	100:180}]
		//[{1,000,000:660,		100,000:121,	10,000:94,		1,000:91,	100:88}]
		long b = System.currentTimeMillis();
		System.out.println(b-a);
		
		/*
			对比结论：
			testPageQuery1方法是用ibatis采用的分页方法查询，
			testPageQuery2是用Hibernate采用的分页方法查询，
			 发现testPageQuery1需要执行十几秒，而testPageQuery2仅需要执行零点几秒，差异很大。
			 而如果改成从1000条开始取100条，甚至更靠前，则2者的差别是非常小的。


			如果系统中查询的数据量很大，并且用户会选择查询非常靠后的数据，那么我们就应该替换iBatis的分页实现，
			如果不存在这种情况，那我们就不需要替换iBatis的分页实现，一般情况下，用户不可能去查询那么靠后的页，
			这也是iBatis一直不修改分页实现的原因吧。


		 */
	}

	
	/**
	 * ibatis采用的分页方式，
	 * @param conn
	 * @throws Exception
	 */
	public static void testPageQuery1(Connection conn,long startNum) throws Exception{
		String sql = "select * from   ca_loy_member_info,visitlist ";
		
		/**
		 *  1.ResultSet.TYPE_FORWARD_ONLY,只可向前滚动；  
		 *  2.ResultSet.TYPE_SCROLL_INSENSITIVE,双向滚动，但不及时更新，就是如果数据库里的数据修改过，
		 *  并不在ResultSet中反应出来。   (rs.absolute直接定位方法可用)
		 *  3.ResultSet.TYPE_SCROLL_SENSITIVE，双向滚动，并及时跟踪数据库的更新,以便更改ResultSet中的数据。(rs.absolute直接定位方法可用)
		 *  4.ResultSet.CONCUR_READ_ONLY   该常量指示不可以更新的 ResultSet 对象的并发模式。  
		 *  5.ResultSet.CONCUR_UPDATABLE   该常量指示可以更新的 ResultSet 对象的并发模式
		 */
		Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(sql);
		int j=0;
		//游标移动到1000001条数据的位置
		while(rs.next() && j++<startNum){
		
		}
		int i=0;
		//依次取出100条数据
		while(rs.next() && i++<100){
			
		}
			
	}
	/**
	 * hibernate采用的分页方式
	 * @param conn
	 * @throws Exception
	 */
	public static void testPageQuery2(Connection conn,long startNum) throws Exception{
		String startNumS = String.valueOf(startNum);
		String endNumS = String.valueOf(startNum+100);
		
		String sql = "select * from   ca_loy_member_info,visitlist ";
		
		StringBuffer pagingSelect = new StringBuffer( sql.length()+100 );
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) row_ where rownum <= "+ endNumS + ") where rownum_ > " + startNumS);

		Statement stmt = conn
					.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery(pagingSelect.toString());
		
		while(rs.next()){
			
		}		
	}
}