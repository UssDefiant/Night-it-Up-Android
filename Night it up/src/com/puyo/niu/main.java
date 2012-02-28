package com.puyo.niu;


import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Tweet;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.http.AccessToken;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.puyo.niu.appliaction.TwitterApplication;
import com.puyo.niu.util.Constants;



public class main extends Activity implements Runnable{

	private Twitter twitter;
	private OAuthProvider provider;
	private CommonsHttpOAuthConsumer consumer;
	private EditText tweetBox;
	private Tweet tweetPass;
	QueryResult result = null;
	private TextView tweetTextView, test;
	LinearLayout tweetLine;
	private boolean oldAuth = false, newAuth = false;
	String TAG;
	private volatile Thread thread;

	private String CONSUMER_KEY =           Constants.CONSUMER_KEY;
	private String CONSUMER_SECRET =        Constants.CONSUMER_SECRET;
	private String CALLBACK_URL =           "callback://tweeter";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
    	this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); 
        test = (TextView)findViewById(R.id.textViewTest);
         tweetBox = (EditText) findViewById(R.id.editText1);
         twitter = new TwitterFactory().getInstance();
		System.setProperty("http.keepAlive", "false");
		tweetLine = (LinearLayout) findViewById(R.id.linearLayout1);
		
		//check for saved log in details..

		//set consumer and provider on teh Application service
		checkForSavedLogin();
		getConsumerProvider();
		
		test.setText("Loading Twitter Feed...");
     	
		startThread();
		//Define login button and listener
        tweetBox.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {


				
				if (oldAuth== false && newAuth == false){
						askOAuth();
				}
				
				
			}
			
        	
        });
        
        Button tweetButton = (Button) findViewById(R.id.tweet);
        tweetButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					postTweet();
					tweetBox.setText("@nightitup");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					
				}
				
			}
		});
        
        Button refreshButton = (Button) findViewById(R.id.refresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {

					tweetLine.removeAllViews();
					tweetLine.addView(test);
					test.setText("Refreshing...");
			        test.setVisibility(ImageButton.VISIBLE);
					startThread();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				
			}
		});
        
        ImageButton interactiveMap = (ImageButton) findViewById(R.id.interactiveMap);
        interactiveMap.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				startActivity(new Intent("com.puyo.niu.LAYOUTMAP"));
			}
		});
        
        ImageButton perSchedule = (ImageButton) findViewById(R.id.perSchedule);
        perSchedule.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				startActivity(new Intent("com.puyo.niu.CONCERT"));
			}
		});
        
        ImageButton parking = (ImageButton) findViewById(R.id.parking);
        parking.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				startActivity(new Intent("com.puyo.niu.PARKING"));
			}
		});
        
        ImageButton information = (ImageButton) findViewById(R.id.information);
        information.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				startActivity(new Intent("com.puyo.niu.INFO"));
			}
		});

	}

	protected void postTweet() {
		Twitter t = ((TwitterApplication)getApplication()).getTwitter();
		try {
			t.updateStatus(tweetBox.getText().toString());
			Toast.makeText(this, "Posting to twitter: " + tweetBox.getText().toString(), Toast.LENGTH_LONG).show();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		
		
	}

	private void checkForSavedLogin() {
		// Get Access Token and persist it
		AccessToken a = getAccessToken();
		if (a==null) return;	//if there are no credentials stored then return to usual activity

		// initialize Twitter4J
		twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		twitter.setOAuthAccessToken(a);
		((TwitterApplication)getApplication()).setTwitter(twitter);
		oldAuth = true;
		//finish();
	}

	/**
	 * Kick off the activity to display 
	 */
	/*
	private void startFirstActivity() {
		System.out.println("STARTING FIRST ACTIVITY!");
		Intent i = new Intent(this, TweetsActivity.class);
		startActivityForResult(i, Constants.ACTIVITY_LATEST_TWEETS);
	} */

	/**
	 * This method checks the shared prefs to see if we have persisted a user token/secret
	 * if it has then it logs on using them, otherwise return null
	 * 
	 * @return AccessToken from persisted prefs
	 */
	public synchronized void startThread(){
		  if(thread == null){
			  thread = new Thread(this);
			  thread.start();
		  }
		}

		public synchronized void stopThread(){
		  if(thread != null){
		    Thread moribund = thread;
		    thread = null;
		    moribund.interrupt();
		  }
		}
		
	private AccessToken getAccessToken() {
		SharedPreferences settings = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
		String token = settings.getString("accessTokenToken", "");
		String tokenSecret = settings.getString("accessTokenSecret", "");
		if (token!=null && tokenSecret!=null && !"".equals(tokenSecret) && !"".equals(token)){
			return new AccessToken(token, tokenSecret);
		}
		return null;
	}

	

	/**
	 * Open the browser and asks the user to authorize the app.
	 * Afterwards, we redirect the user back here!
	 */
	private void askOAuth() {
		try {
			consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
			provider = new DefaultOAuthProvider("http://twitter.com/oauth/request_token", "http://twitter.com/oauth/access_token", "http://twitter.com/oauth/authorize");
			String authUrl = provider.retrieveRequestToken(consumer, CALLBACK_URL);
			Toast.makeText(this, "Please authorize this app!", Toast.LENGTH_LONG).show();
			setConsumerProvider();
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(authUrl)));
		} catch (Exception e) {
			//Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}
	

	/**
	 * As soon as the user successfully authorized the app, we are notified
	 * here. Now we need to get the verifier from the callback URL, retrieve
	 * token and token_secret and feed them to twitter4j (as well as
	 * consumer key and secret).
	 */
	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("RESUMING!!");
		if (this.getIntent()!=null && this.getIntent().getData()!=null){
			Uri uri = this.getIntent().getData();
			if (uri != null && uri.toString().startsWith(CALLBACK_URL)) {
				String verifier = uri.getQueryParameter(oauth.signpost.OAuth.OAUTH_VERIFIER);
				try {
					// this will populate token and token_secret in consumer
					provider.retrieveAccessToken(consumer, verifier);

					// Get Access Token and persist it
					AccessToken a = new AccessToken(consumer.getToken(), consumer.getTokenSecret());
					storeAccessToken(a);

					// initialize Twitter4J
					twitter = new TwitterFactory().getInstance();
					twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
					twitter.setOAuthAccessToken(a);
					((TwitterApplication)getApplication()).setTwitter(twitter);
					//Log.e("Login", "Twitter Initialised");
					newAuth = true;
					
					//startFirstActivity();

				} catch (Exception e) {
					//Log.e(APP, e.getMessage());
					//e.printStackTrace();
					//Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
				}
			}
		}
	}

	/**
	 * This method persists the Access Token information so that a user
	 * is not required to re-login every time the app is used
	 * 
	 * @param a - the access token
	 */
	private void storeAccessToken(AccessToken a) {
		SharedPreferences settings = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("accessTokenToken", a.getToken());
		editor.putString("accessTokenSecret", a.getTokenSecret());
		editor.commit();
	}
	
	
	/**
	 * Get the consumer and provider from the application service (in the case that the
	 * activity is restarted so the objects are not lost
	 */
	private void getConsumerProvider() {
		OAuthProvider p = ((TwitterApplication)getApplication()).getProvider();
		if (p!=null){
			provider = p;
		}
		CommonsHttpOAuthConsumer c = ((TwitterApplication)getApplication()).getConsumer();
		if (c!=null){
			consumer = c;
		}
	}
	
	
	/**
	 * Set the consumer and provider from the application service (in the case that the
	 * activity is restarted so the objects are not lost)
	 */
	private void setConsumerProvider() {
		if (provider!=null){
			((TwitterApplication)getApplication()).setProvider(provider);
		}
		if (consumer!=null){
			((TwitterApplication)getApplication()).setConsumer(consumer);
		}
	}

	private Handler handler = new Handler();
    


	@Override
	public void run() {

		while(Thread.currentThread() == thread) {
        	Query query = new Query("nightitup").rpp(50);
		try {

			 result = twitter.search(query);

		} catch (TwitterException e) {
			e.printStackTrace();
		}
		if (result != null){
		    handler.post(new Runnable() {
		    	@Override
			    public void run() {
		        for (final Tweet tweet : result.getTweets()) {
			    
		        	TextView addLine = new TextView (main.this);
		        	tweetLine.addView(addLine);
		        	addLine.setTextSize(17);
		        	addLine.setTextColor(Color.WHITE);
		            addLine.setText(tweet.getFromUser() + ": " + tweet.getText() + " " + tweet.getCreatedAt() + "\n");
		            test.setVisibility(ImageButton.GONE); 
			    }
			   stopThread();
          }
        });
        } 
		handler.post(new Runnable(){

			@Override
			public void run() {
				
				test.setText("Unable to connect to Twitter");
				
			}
			
		});

		}
		
            }
	}



