package com.example.lamps;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

	private DatabaseHandler dbh=new DatabaseHandler(this);
	private SQLiteDatabase db;
	Button b1,b2;
	EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=dbh.getReadableDatabase();
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        et1=(EditText)findViewById(R.id.editText1);
        b1.setOnClickListener(this);
        Log.d("Insert:", "Inserting..");
		dbh.addContact(new Contact("accessible","Easily Obtained \nEg:making learning opportunities more accessible to adult","Inaccessible","Approachable"));
		dbh.addContact(new Contact("absorb","assimilate or take in \nEg:she absorbed the information in silence","abstain","consume"));
		dbh.addContact(new Contact("accept","consider or hold as true \n Eg:she accepted a temporary post as a clerk","lose","get"));
		dbh.addContact(new Contact("absence","the state of being absent \n Eg:The letter had arrived during his absence","presence","absenteeism"));
		dbh.addContact(new Contact("academic","associated with school or learning \n Eg:he had no academic qualifications","ignorant","collegiate"));
		dbh.addContact(new Contact("accommodate","provide with something desired or needed \n Eg:any language must accommodate new concepts","reject","contain"));
		dbh.addContact(new Contact("accurate","conforming exactly or almost exactly to fact or to a standard or performing with total accuracy \n Eg:accurate information about the illness is essential","careless","correct"));
		dbh.addContact(new Contact("alter","cause to change; make different; cause a transformation \n Eg:plans to alter the dining hall","stagnate","change"));
		dbh.addContact(new Contact("apology"," an expression of regret at having caused trouble for someone \n Eg:He has made mistakes, as he himself acknowledged during a televised apology last weekend","denail","excuse"));
		dbh.addContact(new Contact("arrange","put into a proper or systematic order \n Eg:the tables are arranged in class","disarrange","form"));
		dbh.addContact(new Contact("assets","anything of material value or usefulness that is owned by a person or company \n Eg:the school is an asset to the community","liabilities","credit"));
		dbh.addContact(new Contact("authentic","not counterfeit or copied \n Eg:the letter is now accepted as an authentic document","invalid","accurate"));
		
		dbh.addContact(new Contact("biology","study of living organisms","nil","nil"));
		dbh.addContact(new Contact("banal","repeated too often \n Eg:songs with banal, repeated words","sharp","trite"));
		dbh.addContact(new Contact("bass","the lowest adult male singing voice \nEg:He is a strong, imposing bass who also lends character to the performance","weak","dark"));
	    dbh.addContact(new Contact("bleak","unpleasantly cold and damp \n Eg:his mouth was set and his eyes were bleak","friendly","dreary"));
	    dbh.addContact(new Contact("blaze","a strong flame that burns brightly \nEg:the gardens in summer are a blaze of colour","nil","flame"));
	    dbh.addContact(new Contact("bore","a person who evokes boredom \nEg:I am bored","charmer","yawn"));
	    
		dbh.addContact(new Contact("caliber","a degree or grade of excellence or worth \n Eg:educational facilities of a very high calibre","inability","ability"));
		dbh.addContact(new Contact("caucus","a closed political meeting \nEg:Hawaii holds its nominating caucuses next Tuesday","going","group meeting"));
		dbh.addContact(new Contact("coincidence","the temporal property of two things happening at the same time \n Eg:they met by coincidence","disconnection","collaburation"));
		dbh.addContact(new Contact("comparison","the act of examining resemblances \n Eg:the two books invite comparison with one another","difference","relation"));
		dbh.addContact(new Contact("consume","use up (resources or materials) \n Eg:this process consumes enormous amounts of energy","neglect","absorb"));
		dbh.addContact(new Contact("conversion"," the act of changing from one use or function or purpose to another \nEg:the conversion of a house into flats","stagnation","transformation"));
		dbh.addContact(new Contact("counterpart"," a person or thing having the same function or characteristics as another \nEg:the minister held talks with his French counterpart","opposite","copy"));
		
		dbh.addContact(new Contact("decay","the organic phenomenon of rotting \nEg:bacterial decay","degeneration","decomposition"));
		dbh.addContact(new Contact("default","loss due to not showing up \n Eg:default settings","accuracy","absence"));
		dbh.addContact(new Contact("definite"," known for certain \n Eg:we had no definite plans","doubtful","clearly defined"));
		dbh.addContact(new Contact("detect","discover or determine the existence, presence, or fact of\n Eg:cancer may soon be detected in its earliest stages","find","hide"));
		dbh.addContact(new Contact("difference","the quality of being unlike or dissimilar \n Eg:the differences between men and women","similarity","changes"));
		dbh.addContact(new Contact("disagree","be of different opinions \n Eg:I disagree with your argument on every point","agree","clash"));
		dbh.addContact(new Contact("disappear","become invisible or unnoticeable \nEg:the tension had completely disappeared","appear","fade"));
		dbh.addContact(new Contact("distant","separated in space or coming from or going to a distance \n Eg:the town lay half a mile distant","near","far"));
		dbh.addContact(new Contact("divert ","turn aside; turn away from \n Eg:a scheme to divert water from the river to irrigate agricultural land","stay","direct"));
		dbh.addContact(new Contact("donate","give to a charity or good cause \nEg:all donated blood is tested for antibodies","take","contribute"));
		
		dbh.addContact(new Contact("effect"," a phenomenon that follows and is caused by some previous phenomenon \n Eg:entle music can have a soothing effect","cause","reaction"));
		dbh.addContact(new Contact("elaborate"," add details, as to an account or idea \n Eg: he would not elaborate on his news","unelaborate","detailed"));
		dbh.addContact(new Contact("eligible","qualified for or allowed or worthy of being chosen \n Eg:eligible candidates","ineligible","qualified"));
		dbh.addContact(new Contact("emerge","come out into view, as from concealment \n Eg:the economy has started to emerge from recession","disappear","appear"));
		dbh.addContact(new Contact("enhance"," make better or more attractive \n Eg:enhances the performance","discourage","boost up"));
		dbh.addContact(new Contact("enthusiastic","having or showing great excitement and interest \n Eg:he could be wildly enthusiastic about a project","unexcited","facinated"));
		dbh.addContact(new Contact("essence","the central meaning or theme of a speech or literary work \n Eg:conflict is the essence of drama","exterior","essentiality"));
		dbh.addContact(new Contact("excellent","very good; of the highest quality \n Eg:their results are excellent","bad","outstanding"));
		dbh.addContact(new Contact("exclude","prevent from being included orconsidered or accepted\n Eg:the public were excluded from the board meeting","add","ignore"));
		dbh.addContact(new Contact("existence","everything that exists anywhere \nEg:the organization has been in existence for fifteen years","end","presence"));
		dbh.addContact(new Contact("expect","regard something as probable or likely \n Eg:Celia was expecting a visitor","disregard","predict"));
		
		dbh.addContact(new Contact("fabulous","extremely pleasing \nEg:fabulous riches","poor","spectacular"));
		dbh.addContact(new Contact("flop","a complete failure \nEg:the film was a complete flop","success","loser"));
		dbh.addContact(new Contact("formation","an arrangement of people or things acting as a unit \n Eg:the formation of the Great Rift Valley","distraction","construction"));
		dbh.addContact(new Contact("feud","a bitter quarrel between two partie \nEg:a savage feud over drugs money","concord","discord"));
	    dbh.addContact(new Contact("fidelity","the quality of being faithful \nEg:his fidelity to liberal ideals","infidelity","attachment"));
		dbh.addContact(new Contact("finale","the concluding part of any performance \nEg:the festival ends with a grand finale","initiation","chaser"));
		dbh.addContact(new Contact("fray","v. wear away by rubbing \nEg:cheap fabric soon fray","peace","fracas"));
		dbh.addContact(new Contact("frequency","the number of occurrences within a given time period \nEg:an increase in the frequency of accidents due to increased overtime","infrequency","regularity"));
	    dbh.addContact(new Contact("fundamental"," being or involving basic facts or principles \nEg:the protection of fundamental human rights","least\naccessory","central\nessential"));
		dbh.addContact(new Contact("further","promote the growth of \nEg:we had walked further than I realized","fewer\nless","added \n another\nmore"));
		
		dbh.addContact(new Contact("generate","bring into existence \nEg:the income generated by the sale of council houses","destroy\nlose\nfail","develop\nmake"));
		dbh.addContact(new Contact("glorious","having great beauty and splendor\nEg:the most glorious victory of all time","bad\ndark\ngloomy","beautiful\nbright\ngrand"));
		dbh.addContact(new Contact("gravity","a manner that is serious and solemn\nEg:crimes of the utmost gravity","frovolity","pressure\nforce"));
		dbh.addContact(new Contact("grove","a small growth of trees without underbrush \nEg:an olive grove","nil","forest\nplantation"));
		dbh.addContact(new Contact("generate","bring into existence \nEg:changes which are likely to generate controversy","distroy","develop"));
		dbh.addContact(new Contact("glimpse","a brief or incomplete view \nEg:she caught a glimpse of the ocean","stare","glance\npeek"));
        
		dbh.addContact(new Contact("hack","a mediocre and disdained writer \nEg:I watched them hack the branches","master","drudge\ngrind"));
		dbh.addContact(new Contact("hale","exhibiting or restored to vigorous good health\nEg:he’s only just sixty, very hale and hearty","thin\n small","blooming\n fit"));
	    dbh.addContact(new Contact("handwriting"," something written by hand \nEg:her handwriting was small and neat","nil","writing\nscript"));
    	dbh.addContact(new Contact("heritage","practices that are handed down from the past by tradition \nEg:the estuary has a sense of history and heritage","whole","culture\n legacy"));
		dbh.addContact(new Contact("hollow","not solid; having a space or gap or cavity \nEg:a hollow metal tube","high\n full \nfrang","cupped \ncurved"));
		dbh.addContact(new Contact("hush","become quiet or still; fall silent \nEg:he placed a finger before pursed lips to hush her","agitation \nnoise","peace\ncalm\nsilence"));
		dbh.addContact(new Contact("hysteria","excessive or uncontrollable fear \nEg:The world cannot tolerate these old claims, most times based on sheer hysteria and emotion","calm \n peace","madness \npanic"));
		dbh.addContact(new Contact("hypothesis","a tentative insight into the natural world \nEg:the hypothesis that every event has a cause","proof \ntruth","conclution \ninference"));
    
		dbh.addContact(new Contact("illusion","an erroneous mental representation \nEg:he had no illusions about the trouble she was in","truth \ncorrecttion \nfact","confusion \nfantasy \nhallucination"));
		dbh.addContact(new Contact("imaginary","not based on fact; unreal \nEg:Chris had imaginary conversations with her","real \nordinary \nfactual","abstract \nunreal \nimagined"));
		dbh.addContact(new Contact("immoral","not adhering to ethical or moral principles \nEg: unseemly and immoral behaviour","pure \nnoble \ngood","corrupt \nindecent"));
		dbh.addContact(new Contact("implicit","implied though not directly expressed \nEg:an implicit faith in God","explicit \ndisloyal","constant\n definite"));
		dbh.addContact(new Contact("improper","not suitable or right or appropriate \nEg:an improper suggestion","correct \naccurate \nright","wrong \nirregular \nincorrect"));
		dbh.addContact(new Contact("incidence","the relative frequency of occurrence of something \nEg:an increased incidence of cancer","whole","tendency"));
		dbh.addContact(new Contact("incentive","a positive motivational influence \nEg:give farmers an incentive to improve their land","discouragement \ndetterent","encouragement \nmotivation"));																							
		Log.d("Reading:", "Reading all contacts..");
		List<Contact> contacts = dbh.getAllContacts();
		for(Contact cn : contacts){
			 String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Meaning " + cn.getMeaning() + " ,Antonyms " + cn.getAntonyms() + " ,Synonyms " + cn.getSynonyms();
			 Log.d("Name:", log);
		}
		/*List<Contact> contacts2 = dbh.getAllContacts();
		for(Contact cn : contacts){
			 String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Meaning " + cn.getMeaning() + " ,Antonyms " + cn.getAntonyms() + " ,Synonyms " + cn.getSynonyms();
			 Log.d("Name:", log);
		}*/
        b2.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    
    	if(item.getItemId()==R.id.button1){
        	Intent intent=new Intent(this, DBvalues.class);
        	startActivity(intent);
        }
         switch(item.getItemId()){
         case R.id.menu_anto:
        	 String name1=et1.getText().toString();
        	 Cursor c1=db.rawQuery("SELECT * FROM contacts " +"WHERE name='"+name1+"' COLLATE NOCASE;", null);
 			if(c1.getCount()!=0){
 				c1.moveToFirst();
 				String anto=c1.getString(3).toString();
 				
 				//String mean=c1.getString(2).toString();
        	 Intent intent=new Intent(this, Activity_Anto.class);
        	 intent.putExtra("name", name1);
     		intent.putExtra("anto", anto);
     		//intent.putExtra("data", mean);
         	startActivity(intent);
     	
         }
        return true;
         case R.id.menu_syno:
        	 String name2=et1.getText().toString();
        	 Cursor c2=db.rawQuery("SELECT * FROM contacts " +"WHERE name='"+name2+"' COLLATE NOCASE;", null);
 			if(c2.getCount()!=0){
 				c2.moveToFirst();
 				String syno=c2.getString(4).toString();
 				String mean=c2.getString(2).toString();
        	 Intent intent=new Intent(this, Activity_Syno.class);
        	 intent.putExtra("name", name2);
     		intent.putExtra("syno", syno);
     		//intent.putExtra("data", mean);
         	startActivity(intent);
        }
 			return true;
         case R.id.menu_History:
        	 Intent intent = new Intent(getApplicationContext(),Activity_histo.class);
        	 startActivity(intent);
        	 return true;
        	 
         }
         return super.onOptionsItemSelected(item);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.button1){
			/*Toast.makeText(getApplicationContext(), "testing.....", Toast.LENGTH_LONG).show();*/
			String name1=et1.getText().toString();
			//Cursor c1=db.rawQuery("SELECT * FROM contacts " +"WHERE name='"+name1+"';", null);
			Cursor c1=db.rawQuery("SELECT * FROM contacts " +"WHERE name='"+name1+"' COLLATE NOCASE;", null);
			if(c1.getCount()!=0){
				c1.moveToFirst();
				String mean=c1.getString(2).toString();
				dbh.getWritableDatabase();
 				dbh.addHistorycontact(new History(name1));
 				Log.d("Inserting int history :", name1);
				Intent in=new Intent(MainActivity.this,DBvalues.class);
				in.putExtra("data", mean);
				in.putExtra("name", name1);
				startActivity(in);
				/*Toast.makeText(getApplicationContext(), mean, Toast.LENGTH_LONG).show();*/
			}
			else{
				Toast.makeText(getApplicationContext(), "WORD NOT FOUND", Toast.LENGTH_LONG).show();
			}
		}
		if(v.getId()==R.id.button2){
			et1.setText("");
		}
	}
	
	}
	

