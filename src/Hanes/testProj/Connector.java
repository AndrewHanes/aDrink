package Hanes.testProj;
/*
 * CSH Drink App by Andrew Hanes
 */
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import android.util.Log;

public class Connector {
	/*
	 * Class for connecting to the drink server
	 * 
	 * TODO Add SSL support when drink has SSL added to it.
	 */
	BufferedReader br = null;
	BufferedWriter bw = null;
	Socket skt = null;
	/*
	 * Connects to a drink server
	 * 
	 * @param host The host being connected to 
	 * @param port The port number
	 */
	public Connector(String host, int port)
	{
		try {
			skt = new Socket(host,port);
			this.br = new BufferedReader(new InputStreamReader(skt.getInputStream()));
			this.bw = new BufferedWriter(new OutputStreamWriter(skt.getOutputStream()));
			Log.d("Connected",this.recieve().toString());
		} catch (UnknownHostException e) {
			Log.d("UnknownHost","Cant Connect to "+host);
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("IOException","IOException in Constructor");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * Reconnects to the host
	 * Allows a user to log off
	 */
	public void reConnect()
	{
		InetAddress host = skt.getInetAddress();
		int port = skt.getPort();
		this.close();
		try {
			skt = new Socket(host,port);
			this.br = new BufferedReader(new InputStreamReader(skt.getInputStream()));
			this.bw = new BufferedWriter(new OutputStreamWriter(skt.getOutputStream()));
			Log.d("Connected",this.recieve().toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			Log.d("UnknownHost","Cant Connect to "+host);
			e.printStackTrace();
		} catch (IOException e) {
			Log.d("IOException","IOException in Constructor");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * Closes the socket and reader/writer
	 */
	public void close()
	{
		try {
			br.close();
			bw.close();
			skt.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/*
	 * Sends a string to the drink server
	 * 
	 * @param command The command to be send (see sunday protocol)
	 */
	public void send(String command)
	{
		try {
			bw.flush();
			bw.write(command);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
	}
	/*
	 * @return The info sent from the drink server to the client
	 */
	public ArrayList<String> recieve()
	{
		ArrayList <String> temp = new ArrayList<String>();
		try{
			temp.add(br.readLine());
			while(br.ready())
			{
				temp.add(br.readLine());
			}
		}catch(IOException e)
		{
			Log.d("IOException",e.toString());
			e.printStackTrace();
			return null;
		}
		catch(NullPointerException e)
		{
			Log.d("NullPointerException",e.toString());
			e.printStackTrace();
			if(br == null)
				Log.d("NULL","br "+br);
			if(bw == null)
				Log.d("NULL","bw "+bw);
			if(skt == null)
				Log.d("NULL","skt "+skt);
			//return new ArrayList<String>();
		}
		Log.d("RECIEVE", temp.toString());
		return temp;
	}
	/*
	 * Combines the receive and send functions
	 * @param command The command being sent to the server
	 * @return The info sent back from the server
	 */
	public ArrayList<String> command(String command)
	{
		Log.d("COMMAND", command+'\n');
		this.send(command+'\n');
		return this.recieve();		
	}
}
