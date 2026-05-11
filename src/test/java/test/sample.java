package test;

public class sample
{
	public static void main(String[] args)
	{
		boolean check = ispalindrome(1221);
		if (check)
			System.out.println("palindrome");
		else
			System.out.println("Not a palindrome");
	}
	
	public static boolean ispalindrome(int x)
	{
		if(x < 0 || (x % 10 == 0 && x != 0))
			return false;
		
		int reversed = 0;
		
		while(x>reversed)
		{
			reversed = reversed*10+x%10;
			x/=10;
		}
		
		return x==reversed || x == reversed/10;
	}
}