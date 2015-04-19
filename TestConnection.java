import java.util.ArrayList;


public class TestConnection
{

	public static void main(String[] args)
	{
		HTTPConnection connection = new HTTPConnection();
		
		try
		{
			//if login fails -1 is returned
			if(connection.loginToServer("root", "") != -1)
			{
				ArrayList<String> s = connection.executeQuery(1, "David", "Aardsma", "2004", null, null);
				
				System.out.println(s);
				
				connection.logoutFromServer();
			}
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
