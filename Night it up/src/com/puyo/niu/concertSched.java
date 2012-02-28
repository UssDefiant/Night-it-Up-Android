package com.puyo.niu;

import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class concertSched extends Activity implements OnCheckedChangeListener {

	
	TextView show;
   LinearLayout eventAdder;
   LinearLayout popupAdder;
   Context mContext;
   Dialog dialog;
    ArrayList<Event> friEventList = new ArrayList<Event>();
    ArrayList<Event> satEventList = new ArrayList<Event>();
	boolean friday = true;
    public void onCreate(Bundle savedInstanceState) {
		 mContext = getApplicationContext();
		 dialog = new Dialog(concertSched.this);
    	//setListAdapter(new ArrayAdapter<String>(this, R.layout.schedule));
    	//  ListView lv = getListView();
    	//  lv.setTextFilterEnabled(true);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.schedule);
        eventAdder = (LinearLayout) findViewById (R.id.schedLayout);
        RadioGroup selector = (RadioGroup)findViewById(R.id.radioGroup1);
        selector.setOnCheckedChangeListener(this);
		addFriEvents();
		addSatEvents();
        populate(friEventList);

}

	private void addSatEvents() {
        
		satEventList.add(new Event("Lily Cheng", "Singing", "5:00PM", "Lily C’s is a folk/pop singer-songwriter whose music has been described as \"a breath of fresh air.\" She previously independently released a bilingual (English/Mandarin) album in Taiwan which garnered radio airplay and national distribution. Lily C is currently working on her second album and hopes to use her songs to inspire others to pursue their dreams. www.lilyc.com, www.facebook.com/lilycmusic", "Lily Cheng")); 
		satEventList.add(new Event("Young", "Beatboxing", "5:10PM", "", ""));
		satEventList.add(new Event("Kenneth Yeung", "Looping Machine", "5:15PM", "Ken is a singer who has been composing and performing music for many years. As a member of the acapella group Touch he has performed and competed at many events throughout the city. This year at Night It Up! he will be performing solo, creating his acapella arrangements live using only his voice, a microphone and a loop machine ", "Kenneth Yeung"));
		satEventList.add(new Event("Ally & Kevin", "Guitar & Vocal", "5:30PM", "Ally & Kevin are a dynamic duo that has only been performing together for a few months, but their musical talents and undeniable on-stage chemistry has already allowed them to garner widespread attention. This is their first time performing at Night It Up! and they are grateful to share their music and story", "Ally and Kevin"));
		satEventList.add(new Event("Jennifer Li", "Solo Ballad", "5:50PM", "Thrilled to be part of this year’s great line up! I’ll be singing a Thrilled to be part of this year’s great line up! I’ll be singing a Cantonese ballad originally performed by Julian Cheung. If you’re into TVB dramas you should be able to recognize it, especially if Mother of Emily Lai you’re one of those reminiscing about the 90s. Looking forward to seeing everyone.", "Jennifer Li"));
		satEventList.add(new Event("PUYO TIME - E3", "PUYO TIME", "5:55PM" , "", ""));
		satEventList.add(new Event("Tam Duong", "Singing", "6:05PM","Tam's music is thoughtful and thought provoking. He sings with a gentle intensity and captures imaginations with his poetic storytelling. Artists such as John Mayer, Al Green, Jason Mraz and Jimi Hendrix inspire Tam to blend jazz, blues, and pop, creating a refined yet soulful sound. You can check out more of his music at www.tamduongmusic.com", "Tam Duong"));
		satEventList.add(new Event("TBA", "TBA", "6:25PM", "", "" ));
		satEventList.add(new Event("TBA", "TBA", "6:45PM", "", ""));
		satEventList.add(new Event("Otherhalf", " ", "6:50PM" , "", ""));
		satEventList.add(new Event("Violets & Viruses", " ", "6:55PM", "The Toronto based hard-rock group Violets & Viruses is back at Night It Up 2011. With a new singer Tina and the addition of guitarist Yu, the band will take the Night It Up stage with songs from their latest CD titled RESURGENCE. Look for updates on Youtube and Facebook", "Violets & Viruses"));
		satEventList.add(new Event("ONO! Vitality", "Dance", "7:25PM", "For our performance, we are bringing variety and different dance genres and putting them together to make them one. So we are mixing contemporary, lyrical hiphop, popping, locking, breaking, krumping and many different genres into one complete performance. Art of creativity creates the engergy that will bring the unity among the society and the engergy that brings together society is ONO VITALITY because ONO VITALITY stands for ONE AND ONLY VITALITY", "ONO Vitaity"));
		satEventList.add(new Event("Philip Wong", "Martial Arts", "7:45PM", "Don’t miss The Most Dangerous Stunt Performance at NIU 2011. Phillip Wong’s demonstration in Wushu, breakdance with extreme acrobatics to create an intense thrilling high energy performance. His heart-stopping performance is seen in TV shows, commercials, and local live concerts. 15 year old Phillip is currently training in Sunny Tang Martial Art Centre.", "Philip Wong"));
		satEventList.add(new Event("DICE", " ", "7:50PM", "DICE was first gathered to let the musicians creativity fly, playing the local Toronto hard rock scene for over 6 years, we are together for the love of music. Because one great rock show can change the world, once and for all, DICE away.", "DICE"));
		satEventList.add(new Event("WOOF Legacy", "Dance", "8:20PM", "Always remembering to love dance and to be proud of their craft, WOOF Legacy is an example of dancers who strive to better themselves. With new members and a larger team, WOOF Legacy aims to deliver intricate choreography and to show their audience that dance is fun. Love & Pride!", "WOOF Legacy"));
		satEventList.add(new Event("PUYO TIME - SCAW", " ", "8:35PM", "", ""));
		satEventList.add(new Event("Maybe Refuge", " ", "8:45PM", "Maybe Refuge will be returning to this year's stage at Night It Up! with a more refined sound and a slightly different line-up. With the addition of a new drummer and a support guitarist, the band is looking forward to playing some of their new material.", "Maybe Refuge"));
		satEventList.add(new Event("Stephen Cheung", "Magician", "9:15PM", "We’ve all seen magic/ illusions on TV. But is it REAL or is it STAGED??? Well, this is your chance to find out. Since 2008, Stephen have dazzled his audience around the world with his style of magic/ illusions. The question is: ARE YOU READY???", "Stephen Cheung"));
		satEventList.add(new Event("Cretem", "Guitar & Vocal", "9:35PM", "Cretem is a pop rock band with members from Korea, Hong Kong and Japan. Their music reflects experiences that they encounter in everyday life. All members are here to enjoy music, which is the one thing that brought these individuals together. Visit www.cretem.net for more info", "Billy Chan - CRETEM"));
		satEventList.add(new Event("RFD", "Sponsor Time", "10:05PM", "", ""));
		satEventList.add(new Event("K*WHY", "R&B", "10:10PM", "K*WHY is a uniquely urban sounding rap/RnB duo of UEC and Karla Maxim. Appealing to fans of progressive, modern hip hop/urban pop music, UEC’s intricately crafted rhyme patterns are sweetened by Karla’s crisp, powerful vocal delivery and immersed in a fresh, catchy blend of edgy sound - check us out at www.whykwhy.com! K*WHY - like the jelly, but smoother.", "Yui & Vlad"));
		satEventList.add(new Event("Vybe Dance", "Dance", "10:25PM", "", ""));
		satEventList.add(new Event("PU Time", " ", "10:40PM", "", ""));
		
		
	}

	private void addFriEvents() {
		friEventList.add(new Event("AJ", "Singing", "6:00pm", "My name is AJ Libramonte and I've been singing for 4 years. I find singing to be very rewarding, because after giving a performance the reaction and criticism from an audience is fulfilling and very constructive. All my life I have been taking any opportunity to sing at special occasions, concerts, shows, and competitions. Why? It’s because singing is what I love to do, it’s my passion. And I want to show to those who doubted me in my music path that I’ll keep on singing no matter what, whether I sound bad or not; I’ll keep on following my dream", "AJ Libramonte"));
		friEventList.add(new Event("Yeomi Ri Lee", "Singing", "6:05pm", "Amy and Julie will be doing a duet performance for Night It Up. Both girls are vocalists and Amy will also be accompanying the performance on keys. For this event they will be performing original songs and covers of some of their favorite artists.", "Yeomi Ri Lee"));
		friEventList.add(new Event("Mary Wong", "Singing", "6:20pm", "Mary Chu Mai Wong won First Place in 4 singing contests in 1963, 1964, 2000, 2006 in Sabah, Malaysia. She was a Soloist with 2 professional choirs and performed throughout Malaysia and Singapore. She sang at many charity concerts. Mary recently released her first CD with 10 original songs in Mandarin.", "Mary Wong"));
		friEventList.add(new Event("Sherlyn Vuong", "Singing", "6:35pm", "To me, performing is just another way of expressing my inner thoughts and emotions. My love for music is something that cannot be described in words, but shown every time I perform. Live, Love, Sing is my motto and it will never die, even when I'm old.", "Sherlyn Vuong"));
		friEventList.add(new Event("Sarah Yip", "Singing", "6:45pm", "Sarah Yip will be singing a classical song originally sung by Billy Joel to create a relaxing atmosphere. She has been performing for over a year and will eagerly share her enthusiasm with you through this song. Although it is not a song of our day and age, it attempts to bring simplicity back into our busy lives.", "Sarah Yip"));
		friEventList.add(new Event("Otherhalf", "Singing", "7:05PM" ,"" ,"" ));
		friEventList.add(new Event("Maiden China", "Girl Dance | Sing Group", "7:10PM", "Maiden China' is a Chinese girl-group formed in Toronto. These four girls, Lisa, Amy, Jessie and Tina, are all talented at singing, dancing and performing. They are working hard with their producer Cory Noziglia on their acts and an upcoming musical album of both original and cover material. Maiden China's goals are to continue developing their skills and hope to one day be recognized in mainstream media", "Maiden China"));
		friEventList.add(new Event("UW Hip Hop", "Dance", "7:15PM", "UW Hip Hop teaches different styles of hip hop dance to all dancers ranging from inexperienced to advanced level. Members develop balance, coordination, strength and confidence. The performance will incorporate various choreos learned during the past term", "UW Hip Hop"));
        friEventList.add(new Event("Opening Ceremony", "Ceremony", "7:30PM", "", ""));
        friEventList.add(new Event("Young", "Beatbox", "8:30PM", "", ""));
        friEventList.add(new Event("Kenneth Yeung", "Looping Machine", "8:35PM", "Ken is a singer who has been composing and performing music for many years. As a member of the acapella group Touch he has performed and competed at many events throughout the city. This year at Night It Up! he will be performing solo, creating his acapella arrangements live using only his voice, a microphone and a loop machine ", "Kenneth Yeung"));
        friEventList.add(new Event("Shangrila Bellydance", "Belly Dancing", "8:50PM", "Exotic beats of the Mid-East and the dance of mother earth arrives at Night It Up. Shangrila Bellydance will be performing various solos once again at NIU. Get ready for lots of shimmies and Arabic rhythms!", "Shangrila Bellydance"));
        friEventList.add(new Event("Yellow in Red & White", "Singing", "9:10PM", "Each of us came together coincidently from different parts of China, although we’re just a normal person that one can find anywhere, when “my music” unifies into “our music”, our chemical is what you cannot miss. We’d like to invite you to witness our passion through this year’s NIU! -- YRW, Alternative/Metal/Post-hardcore (Scarborough, Ontario)", "Yellow in Red & White"));
        friEventList.add(new Event("Ally & Kevin", "Guitar & Vocal", "9:35PM", "Ally & Kevin are a dynamic duo that has only been performing together for a few months, but their musical talents and undeniable on-stage chemistry has already allowed them to garner widespread attention. This is their first time performing at Night It Up! and they are grateful to share their music and story", "Ally & Kevin"));
        friEventList.add(new Event("RFD", "Sponsor Time", "9:55PM", "", ""));
        friEventList.add(new Event("T3C Band", "Band", "10:00PM", "T3C Band hello nite it up fans! glad to meet you! we’re a band from Toronto Chinese Community Church (tccc.ca). if you like our sound, come check us out on a Sunday at 9:40am. Lead singer Matt Luey (mattLuey.com) composes originals! hopefully you’ll be around when we paint the air with our sound.", "T3C Band"));
        friEventList.add(new Event("Patrick Simeon", "Singing", "10:30PM", "", ""));
        friEventList.add(new Event("PUYO Band", "Band", "10:40PM", "PU Rock Band is back by popular demand! Riding the overwhelming success of last year’s show-stopping performance (literally, the shows stopped after their performance), they are back - only at NIU – with a brand new act! Watch the organizers behind Night It Up! rock out and dedicate their act to the organization that made this all possible – PU Rock Band’s performance will be one not soon forgotten!", "PUYO Band"));

	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		switch(arg1){
		case R.id.friday:
			friday = true;
			eventAdder.removeAllViews();
			populate(friEventList);
			break;
		case R.id.saturday:
			friday = false;
			eventAdder.removeAllViews();
			populate(satEventList);
			break;
		}
	}
	
	private void populate(ArrayList<Event> eventList){
        for(int i = 0; i <= (eventList.size() -1 ); i++){
        	final Event e = eventList.get(i);
            LinearLayout eventLine = new LinearLayout (this);
            TextView time = new TextView(this);
            show = new TextView(this);
            TextView type = new TextView(this);
            time.setTextSize(25);
            eventAdder.addView(eventLine);
             eventLine.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//Toast.makeText(getApplicationContext(), e.getName(), Toast.LENGTH_LONG).show();
					showPopup(e);
					
				}
			}); 
            eventLine.addView(time);
            eventLine.addView(show);
            eventLine.addView(type);
            time.setTextColor(Color.CYAN);
            time.setText(e.getTime() + " ");
            show.setTextSize(20);
            show.setTextColor(Color.WHITE);
            show.setText(e.getName() + " ");
            //type.setTextSize(20);
            //type.setTextColor(Color.WHITE);
            //type.setText(e.getType() + " ");
            }
	}
	
	private void showPopup(Event e){

		final Dialog dialog = new Dialog(concertSched.this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle(e.getName());
        dialog.setCancelable(true);
        //there are a lot of settings, for dialog, check them all out!

        //set up text
        TextView tInfo = (TextView) dialog.findViewById(R.id.info);
        tInfo.setText(e.getInfo());


        
        TextView genre = (TextView) dialog.findViewById(R.id.genre);
        genre.setTextColor(Color.WHITE);
        genre.setTextSize(15);
        genre.setText(e.getType());

        //set up button
        Button button = (Button) dialog.findViewById(R.id.Button01);
        button.setOnClickListener(new OnClickListener() {
        @Override
            public void onClick(View v) {
        	dialog.dismiss();
            }
        });
        dialog.show();
      }
}

	
		
		
	


class Event {
	  private String name;

	  private String type;

	  private String time;
	  private String info;
	  private String realName;
	  public Event(String name, String type, String time, String info, String realName) {
	    this.name = name;
	    this.type = type;
	    this.time = time;
	    this.info = info;
	    this.realName = realName;
	  };
	  
	  public String getName() {
		    return this.name;
		  }

		  public String getType() {
		    return this.type;
		  }

		  public String getTime() {
		    return this.time;
		  }
		  public String getInfo() {
			    return this.info;
			  }
		  public String getRealName() {
			    return this.realName;
			  }


}


