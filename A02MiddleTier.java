import java.sql.*;
public class A02MiddleTier {
	//This class will contain your code for interacting with Database, acquire the query result and display it in the GUI text area.
   Connection con=null;
   public void startup(){
	
    con=DriverManager.getConnection(  
"jdbc:mysql://localhost:3306/a02","root","root1234");  
Statement stmt=con.createStatement();  
   }
   public String statement(boolean conference, boolean journal, boolean book,boolean all, String from, String to){
      int num=0;
 
      if(!(conference||journal||book)){
      conference=true;
      journal=true;
      book=true;
      }
String query =" Select Name, Date as \"Date of Event\" from (";

if(conference){
query+="(Select Name, EvDate as Date from Event, EventConference  where EventConference.EventID=ID)";

if(journal||book){
   query+="union";
}
}
if(journal){
   query+="(Select Name, MAX(ActivityDate) as Date \nfrom Event, ActivityHappens, EventJournal  where ActivityHappens.EventID=ID and EventJournal.EventID=ID\nGroup By ID )";
   if(book){
      query+="union ";
   }
}
if(book){
  query+="(Select Name, MAX(ActivityDate) as Date \nfrom Event, ActivityHappens, EventBook  where ActivityHappens.EventID=ID and EventBook.EventID=ID\nGroup By ID) ";
   
}
   query+=")";
if(!all){
   if(from!=null){
   query+="where \"Date of Event\">"+from;
   if(to!=null){
      query+=" and  \"Date of Event\"<"+to;
   }
   }
   else{
     query+="where \"Date of Event\"<"+to;
   }
}
query+=";";
      
return query;


   }
   
}