import java.util.ArrayList;


public class TestConnection
{

	public static void main(String[] args)
	{
		HTTPConnection connection = new HTTPConnection();
		
		try
		{
			System.out.println(connection.loginToServer("root", ""));
			
			ArrayList<String> s = connection.executeQuery(1, "David", "Aardsma", "2004", null, null);
		
			System.out.println(s);
			
			System.out.println(connection.logoutFromServer());
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
