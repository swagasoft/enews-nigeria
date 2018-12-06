package swagasoft.e_newsnigeria;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.solver.widgets.Helper;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class TheSun  extends AppCompatActivity{
    private ActionBarDrawerToggle mToogle;
    private WebView webView;
    private SwipeRefreshLayout myswipe;
    private  Intent chooser;
    private Button AboutClick;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allviews);



        //setting click for navigation view
        NavigationView navigationView = findViewById(R.id.myNavigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case  R.id.gohome:
                        Intent homeIntent = new Intent(TheSun.this, MainActivity.class);
                        homeIntent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(homeIntent);
                        break;

                    case  R.id.rate:
                        Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        // To count with Play market backstack, After pressing back button,
                        // to taken back to our application, we need to add following flags to intent.
                        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        try {
                            startActivity(goToMarket);
                        } catch (ActivityNotFoundException e) {
                            startActivity(new Intent(Intent.ACTION_VIEW,
                                    Uri.parse("https://play.google.com/store/apps" + getApplicationContext().getPackageName())));
                        }
                        break;

                    case R.id.about:
                        Intent aboutus = new Intent(TheSun.this , AboutUs.class);
                        startActivity(aboutus);




                        break;
                    case  R.id.mailus:
                        Intent mailIntent = new Intent(Intent.ACTION_SEND);
                        mailIntent.setData(Uri.parse("mailTo:"));
                        String[] to ={"swagasoft@gmail.com",""};
                        mailIntent.putExtra(Intent.EXTRA_EMAIL , to);
                        mailIntent.setType("message/rfc822");
                        chooser =Intent.createChooser(mailIntent,"Send Email");
                        startActivity(chooser );

                }
                return  false;
            }

        });

        //casting swipe
        myswipe = findViewById(R.id.swipe);
        myswipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadWebviewInSwipe();

            }
        });

        if(!isInternetavalable()){
            android.app.AlertDialog.Builder checkBuilder = new android.app.AlertDialog.Builder(TheSun.this);
            checkBuilder.setIcon(R.drawable.alert);
            checkBuilder.setTitle("FAILED!");
            checkBuilder.setMessage("Check your internet Connection.");
            //setting retry  button
            checkBuilder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int Id) {
                    //restart activity
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });
            checkBuilder.setNegativeButton("Home", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            android.app.AlertDialog alertDialog = checkBuilder.create();
            alertDialog.show();
        }

        if (isInternetavalable()) {
            loadWebviewInSwipe();
        }


        //casting drawerlayout and toggle
        DrawerLayout mdrawerLayout = findViewById(R.id.myDrawerlayout);
        mdrawerLayout.closeDrawers();
        mToogle = new ActionBarDrawerToggle(this, mdrawerLayout, R.string.open, R.string.close);
        mdrawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();

        // display and hide action action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setTitle("  >>>> The Sun ");


    }

    private void loadWebviewInSwipe()
    {
        webView = findViewById(R.id.myWebview);
        myswipe.setRefreshing(true);
        WebSettings webSettings = webView.getSettings();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.loadUrl("https://www.nairaland.com");
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.getAllowUniversalAccessFromFileURLs();
        webSettings.getBlockNetworkLoads();
        webSettings.getMinimumLogicalFontSize();
        webSettings.getMinimumFontSize();
        webSettings.setJavaScriptEnabled(false);
        if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        else {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100){
                    progressDialog.show();
                    progressDialog.setMessage("loading content....");


                }
                if (newProgress == 100){
                    progressDialog.dismiss();
                }
            }
        });
        webView.setWebViewClient( new WebViewClient(){


            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                Toast.makeText(getApplicationContext(), "OOP! Internet Connection Problem",Toast.LENGTH_LONG).show();
                view.loadUrl("about:blank");


            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                if (handler !=null){
                    handler.proceed();
                }else {
                    super.onReceivedSslError(view, handler, error);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                myswipe.setRefreshing(false);


            }


        });



    }







    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }else {
            AlertDialog.Builder checkBuider = new AlertDialog.Builder(TheSun.this);
            checkBuider.setIcon(R.drawable.alert);
            checkBuider.setTitle("No More Page");
            checkBuider.setMessage("Select another News ?");
            checkBuider.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent sameIntent = new Intent(TheSun.this, BBCnews.class);
                }
            });

            checkBuider.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            AlertDialog alertDialog =  checkBuider.create();
            alertDialog.show();

        }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return  mToogle.onOptionsItemSelected(item)|| super.onOptionsItemSelected(item);
    }


    private  Boolean isInternetavalable(){
        ConnectivityManager connectivityManager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

}

