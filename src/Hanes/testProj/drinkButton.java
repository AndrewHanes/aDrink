package Hanes.testProj;
/*
 * CSH Drink App by Andrew Hanes
 */
import java.util.ArrayList;

import Hanes.testProj.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class drinkButton extends Button {
	/*
	 * drinkButton supports all of the Button methods, but is automatically set up
	 * to provide the needed information
	 */
	String drink; //drink name
	int price; //drink price
	int count; //the number of this drink available
	int slot;
	Connector drinkServ;
	Context soopaContext;
	Drink_Main drinkMain;
	public drinkButton(Context context, String drink, int price, int count, int slot, Connector drinkServ, Drink_Main drinkMain)
	{
		super(context); 
		this.soopaContext = context;
		this.drink = drink;
		this.price = price;
		this.drinkMain = drinkMain;
		this.count = count;
		this.slot = slot;
		this.drinkServ = drinkServ;
		this.setText(this.drink+"  Price = "+this.price+"  Count = "+this.count);
		this.setOnClickListener(new OnClickListener(){
			public void onClick(View v)
			/*
			 * Starts a new thread when order is created so that the phone
			 * doesn't lag as much
			 */
			{
				setEnabled(false);
				order();
				setEnabled(true);
			}
		});
	}
	public String getDrink()
	/*
	 * Returns the drink name
	 */
	{
		return this.drink;
	}
	public int getPrice()
	/*
	 * Returns the drink price
	 */
	{
		return this.price;
	}
	public int getCount()
	/*
	 * Returns the number of this drink
	 */
	{
		return count;
	}
	public String toString()
	/*
	 * Prints out a string representation of the button
	 */
	{
		return this.drink+"  Price = "+this.price+"  Count = "+this.count;
	}
	public void order()
	/*
	 * This method handles dropping drinks
	 */
	{
		ArrayList<String> back = drinkServ.command("DROP "+this.slot+" "+drinkMain.sp.getInt("delay", 0));	
		if (back.get(0).indexOf("ERR") > -1)
		{
			drinkMain.displayAlert("Error dropping drink");
		}
		else
		{
			drinkMain.displayAlert("Successful Drop");	
			drinkMain.updateButtons();
		}
	}
}