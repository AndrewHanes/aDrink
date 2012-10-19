package Hanes.testProj;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

/*
 * The title bar on the main page of the UI
 */
public class Head extends TextView
{
	Connector drinkServ;
	String text = "Drink App";
	Drink_Main dMain;

	public Head (Context context, Drink_Main dMain, Connector drinkServ)
	{
		super(context);
		this.drinkServ = drinkServ;
		this.dMain = dMain;
		this.setGravity(Gravity.CENTER_HORIZONTAL);
		this.setClickable(false);
		this.setTextColor(Color.BLACK);
		this.setBackgroundColor(Color.rgb(50, 100, 200));
		this.update();
	}

	/*
	 * Updated the text within the title
	 */
	public void update ()
	{
		ArrayList<String> output = drinkServ.command("GETBALANCE");
		if(output.get(0).indexOf("ERR") == -1)
		{
			int balance = Integer.parseInt(output.get(0).split("\\s+")[1]);
			this.setText("Drink App" + "\n Current User: "
					+ dMain.sp.getString("user", "null")
					+ "\nCurrent Credits: " + balance);
		} else
		{
			this.setText("Drink App\nNo User Logged In");
		}
	}

}
