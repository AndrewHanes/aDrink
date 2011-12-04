package Hanes.testProj;
/*
 * CSH Drink App by Andrew Hanes
 */
import Hanes.testProj.R;
import android.os.Bundle;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * This isnt used anymore
 */
public class Options extends Activity {
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options);
	}

}
