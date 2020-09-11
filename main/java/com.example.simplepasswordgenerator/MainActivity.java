package com.example.simplepasswordgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button generatorButton = (Button) findViewById(R.id.generatorButton);
        generatorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                ClipboardManager userClipboard;
                userClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                Random rng = new Random();
                Resources res = getResources();

                TextView passwordTextView = (TextView) findViewById(R.id.passwordText);
                TextView passwordExtraView = (TextView) findViewById(R.id.passwordExtra);
                int startLetter = rng.nextInt(23) + 1;
                int tempNum;
                String PasswordAdjective, PasswordName, EndCode;

                String[] adjective_count = res.getStringArray(R.array.adjective_count);
                String[] adjective_first = res.getStringArray(R.array.adjective_first);
                String[] AdjectivesList = res.getStringArray(R.array.adjectives);
                PasswordAdjective = AdjectivesList[Integer.parseInt(adjective_first[startLetter]) + rng.nextInt(Integer.parseInt(adjective_count[startLetter])) - 1];

                String[] species_count = res.getStringArray(R.array.species_count);
                String[] species_first = res.getStringArray(R.array.species_first);
                String[] SpeciesList = res.getStringArray(R.array.species);
                PasswordName = SpeciesList[Integer.parseInt(species_first[startLetter]) + rng.nextInt(Integer.parseInt(species_count[startLetter])) - 1];

                EndCode = "!" + rng.nextInt(9) + rng.nextInt(9) + rng.nextInt(9) + rng.nextInt(9);
                passwordTextView.setText(PasswordAdjective + PasswordName + EndCode);
                ClipData pwData = ClipData.newPlainText("text",PasswordAdjective + PasswordName + EndCode);
                userClipboard.setPrimaryClip(pwData);
                passwordExtraView.setText("The password has been copied to your clipboard.");
            }
        });
    }
}
