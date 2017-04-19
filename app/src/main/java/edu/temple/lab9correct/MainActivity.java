package edu.temple.lab9correct;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<WebViewFragment> fragmentArrayList;

    Button buttonPrevious;
    Button buttonNext;
    Button buttonNew;

    EditText urlEditText;

    WebViewFragment webViewFragment;

    String url;

    int status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentArrayList = new ArrayList<>();

        webViewFragment = new WebViewFragment();

        buttonPrevious = (Button)findViewById(R.id.buttonPrevious);
        buttonNext = (Button)findViewById(R.id.buttonNext);
        buttonNew = (Button)findViewById(R.id.buttonNew);

        urlEditText = (EditText)findViewById(R.id.urlEditText);

        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("new button clicked","new button clicked");
                url = urlEditText.getText().toString();
                fragmentArrayList.add(WebViewFragment.newInstance(url));

                status = fragmentArrayList.size() - 1;
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.webViewFrag, fragmentArrayList.get(status))
                        .commit();

            }
        });

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status > 0) {
                    status--;
                    urlEditText.setText(fragmentArrayList.get(status).getUrl());
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.webViewFrag, fragmentArrayList.get(status))
                            .commit();
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status < fragmentArrayList.size() -1){
                    status++;
                    urlEditText.setText(fragmentArrayList.get(status).getUrl());
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.webViewFrag, fragmentArrayList.get(status))
                            .commit();
                }
            }
        });
    }
}

