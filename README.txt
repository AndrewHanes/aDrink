Drink App
Andrew Hanes
amh7738@rit.edu

This app is intended to allow a user to order items from the vending
machines at Computer Science House using their android phone.

The app is mostly functional, but still needs some polishing.  While 
I have not experienced issues with purchasing things, it should be noted 
that it is possible for the app to make incorrect purchases.

*** Drink does not currently have SSL support, because of that ***
*** this app does NOT USE SSL.  Please see HowToSSL.txt for    ***
*** information on how SSL will be implemented when it is      ***
*** added to drink					       ***
*** Because this app doesn't use ssl, all user credentials are ***
*** sent over the internet as plain text, which can be read by ***
*** anyone watching!!!					       ***

How To install:
	Turn on allow unknown sources and remote debugging

	Connect phone to computer

	Download the latest version of the app from github and 
	locate the .apk file in bin/

	type adb install Drink_Main.apk

			or

	Put the apk file on your phone's SD card and 
	use a utility on your phone to install it

Using the app:
	Credits/Current User:
		At the top of the app you will see a header that 
		shows the current user and the number of credits that 
		the user has, or indicate that there is no logged in user 

	Buying Things:
		The app creates a single DrinkButton for each item in the 
		vending machine it is currently connected to.  When the button 
		is hit the app asks the user to confim the purchase, then 
		drops the item

	Change settings:
		By hitting the menu key, the user is 
		able to change several settings.  Below 
		is a summary of the different menu options:

		Change Server - Select either Big Drink, Little Drink, 
				or Snack as the vending machine

		Set Delay - Set a delay between the time a drink ordered and
				the time it is dropped

		Change Username - Log in as another user and reconnects to drink
					as the new user

		Wipe Credentials - Wipes all user credentials and reconnects to drink

Please email me at amh7738@rit.edu or open a github issue if you find a bug or 
find something in this app that you feel could be improved upon
