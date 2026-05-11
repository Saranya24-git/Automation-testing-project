public class loginPage
{
	private string username;
	private string password;
	
	public loginPage(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	public String getusername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	
	
}