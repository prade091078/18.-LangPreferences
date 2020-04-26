package com.example.langpreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String lang;
    TextView textView;
    String getLanguage = "";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.example.langpreferences", Context.MODE_PRIVATE);
        getLanguage = sharedPreferences.getString("language", "");

        textView = (TextView) findViewById(R.id.textLanguage);
        if (getLanguage == "")
        {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Choose Language")
                    .setMessage("Do you want to choose any one the language ?")
                    .setPositiveButton("TAMIL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setSelectedLanguage("TAMIL");
                        }
                    })
                    .setNegativeButton("MALAYALAM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setSelectedLanguage("MALAYALAM");
                        }
                    })
                    .show();

    }
        else
        {
            textView.setText(getLanguage);
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // WE CAN USE if ELSE ALSO sINCE WE HAVE only two options here
        switch(item.getItemId())
        {
            case R.id.menuItemTamil:
                setSelectedLanguage("TAMIL");
                return true;
            case R.id.menuItemMalayalam:
                setSelectedLanguage("MALAYALAM");
                return true;
            default:
                return false;

        }

    }

    public void setSelectedLanguage(String value)
    {
        textView.setText(value);
        sharedPreferences.edit().putString("language",value).apply();
    }
}
