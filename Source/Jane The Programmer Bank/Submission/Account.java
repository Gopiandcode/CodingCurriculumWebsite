
public class Account {

	private int money;
	
	public int withdraw(int amount){
		money-=amount;
		return amount;
	}
	
}