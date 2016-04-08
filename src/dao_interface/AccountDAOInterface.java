package dao_interface;

import java.util.ArrayList;

import models.Account;

public interface AccountDAOInterface {
	public boolean addAccount(Account account); // adds new account
	public boolean updateAccount(Account account);
	public boolean deleteAccount(Account account);
	public Account getAccountByID(int id);
	public ArrayList<Account> getAccountsByName(String name);
	
	//changes
	public boolean checkLoginDetails(String username, String password);
	public Account getAccountByUsername(String username);
	public boolean checkIfUsernameExists(String username);
}
