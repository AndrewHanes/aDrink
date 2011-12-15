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
	public User(String username, String password, Drink_Main dMain, SharedPreferences sp)
	{
		this.username = username;
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
	}
	public String getUsername()
	{
		return this.username;
	}
	public void setUser(String username)
	{
		//sp.edit().putString("user", input2.getText().toString());
		sp.edit().commit();
		//changePasswordAlert();
	}
	public void changeUsernameAlert()
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
	}
	/*
	public void changePasswordAlert()
	/*
	 * Logs in a user
	 
	{
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Password");
		alert.setMessage("Enter Password for Account "+sp.getString("user", "null"));
		final EditText input = new EditText(this);
		input.setTransformationMethod(new PasswordTransformationMethod());
		alert.setView(input);
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dMain.command("USER " +sp.getString("user", "nulluser"));
				ArrayList<String>temp = drinkServ.command("PASS "+input.getText().toString());
				if(temp.get(0).toLowerCase().indexOf("err") == -1)
				{
					dMain.updateTitle();
					return;
				}
				else
				{
					changePasswordAlert();
				}
			}
		});
		alert.show();
		}
		*/

}
