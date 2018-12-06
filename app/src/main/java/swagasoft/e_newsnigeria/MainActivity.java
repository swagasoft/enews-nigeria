package swagasoft.e_newsnigeria;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating Action bar
       getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
       getSupportActionBar().setTitle(" >>> e-news-Nigeria");
    }

    //creating click listener for every image
    public void bbbNewsClick (View view)
    {
        Intent intent = new Intent(this, BBCnews.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public  void  dailyTrustClick(View view)
    {
        Intent intent = new Intent(this , Pm_news.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public  void  dailyPostClick(View view)
    {
        Intent intent = new Intent(this , DailyPost.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void cnnClick(View view)
    {
        Intent intent = new Intent(this, CNN.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void informationClick(View view)
    {
        Intent intent = new Intent(this, Information.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void leadershipClick(View view)
    {
        Intent intent = new Intent(this, Leadership.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void premiumTimes(View view)
    {
        Intent intent = new Intent(this, PremiumTime.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void naijClick(View view)
    {
        Intent intent = new Intent(this, Naij.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void tribuneClick (View view)
    {
        Intent intent = new Intent(this, Tribune.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void theSunClick (View view)
    {
        Intent intent = new Intent(this, TheSun.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void thisDayClick (View view)
    {
        Intent intent = new Intent(this, ThisDay.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void punchClick (View view)
    {
        Intent intent = new Intent(this, Punch.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void saharaClick (View view)
    {
        Intent intent = new Intent(this, Sahara.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void theCableClick (View view)
    {
        Intent intent = new Intent(this, TheCable.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public  void theGuardianClick(View view)
    {
        Intent intent = new Intent( this , Guardian.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public  void vanGuardClick(View view)
    {
        Intent intent = new Intent( this , Vanguard.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public  void theNationClick(View view)
    {
        Intent intent = new Intent( this , Thenation.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public  void pulseClick(View view)
    {
        Intent intent = new Intent( this , Naijajist.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void superSportClick (View view){
        Intent intent = new Intent(this , SuperSport.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void eurosportClick (View view){
        Intent intent = new Intent(this , EuroSport.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void nairalandClick(View view){
        Intent intent = new Intent(this, Nairaland.class);
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
