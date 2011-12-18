package Hanes.testProj;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;

/*
 * Represents a user
 */
public class User {
	String username = null;
	Drink_Main dMain = null;
	SharedPreferences sp = null;
	String password = null;
	public User(String username, String password, Drink_Main dMain, SharedPreferences sp)
	{
		this.username = username;
		this.password = password;
		this.dMain = dMain;
		this.sp = sp;
	}
	public User(Drink_Main dMain, SharedPreferences sp)
	/*
	 * Connects using the information stored in the Shared Preferences
	 * for this app
	 */
	{
		this.dMain = dMain;
		this.sp = sp;
		this.username = sp.getString("user",this.changeUsernameAlert());
		this.password = sp.getString("pass", this.changePasswordAlert());
	}
	public boolean logIn()
	{
		dMain.drinkServ.command("USER " + sp.getString("user", "1235342343241"));
		ArrayList<String>temp = dMain.drinkServ.command("PASS "+sp.getString("pass", "none"));
		if (temp.get(0).indexOf("ERR") == -1)
		{
			return true;
		}
		return false;
	}
	public void setPass(String pass)
	{
		sp.edit().putString("pass", pass);
		sp.edit().commit();
	}
	public void setUser(String username)
	{
		sp.edit().putString("user", username);
		sp.edit().commit();
	}
	public String changeUsernameAlert()
	/*
	 * Opens a dialog box and prompts user for new username and new password
	 */
	{
		AlertDialog.Builder alert2 = new AlertDialog.Builder(dMain);
		alert2.setTitle("Username");
		alert2.setMessage("Enter New Username");
		final EditText input2 = new EditText(dMain);
		alert2.setView(input2);
		alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				setUser(input2.getText().toString());
			}
		});
		alert2.show();
		return sp.getString("user", "nullUser");
	}
	public String changePasswordAlert()
	/*
	 * Logs in a user
	 */
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(dMain);
		alert.setTitle("Password");
		alert.setMessage("Enter Password for Account "+sp.getString("user", "null"));
		final EditText input = new EditText(dMain);
		input.setTransformationMethod(new PasswordTransformationMethod());
		alert.setView(input);
		//Add check box to remember password
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				setPass(input.getText().toString());
			}
		});
		alert.show();
		return sp.getString("pass", "None");
	}
	public void clearUser()
	{
		sp.edit().remove("user");
		sp.edit().remove("pass");
		
	}
	
}
