package com.example.tictactoe;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    private final ActivityResultLauncher<String> mLoadUserImage = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    Picasso.get().load(uri).into(imageView);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppContext.userName = getResources().getString(R.string.defaultUserName);

        EditText greetingEditText = findViewById(R.id.greetingEditText);
        greetingEditText.setVisibility(View.INVISIBLE);

        TextView greetingTextView = findViewById(R.id.greetingTextView);
        String greetingMessage = getResources().getString(R.string.greetingPrefix) + ", " + AppContext.userName + "!";
        greetingTextView.setText(greetingMessage);
        greetingTextView.setOnClickListener(view -> {
            System.out.println("greetingTextView clicked");
            greetingEditText.requestFocus();
            greetingEditText.setVisibility(View.VISIBLE);
            greetingTextView.setVisibility(View.INVISIBLE);

            greetingEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    System.out.println("onTextChanged" + i + " " + i1 + " " + i2);
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    System.out.println("afterTextChanged" + editable);
                    AppContext.userName = editable.toString();
                    final String greetingMessage = getResources().getString(R.string.greetingPrefix) + ", " + AppContext.userName + "!";
                    greetingTextView.setText(greetingMessage);

                    greetingEditText.setVisibility(View.INVISIBLE);
                    greetingTextView.setVisibility(View.VISIBLE);
                }
            });

            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInputFromWindow(greetingEditText.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        });

        imageView = findViewById(R.id.userImage);
        imageView.setImageDrawable(ResourcesCompat.getDrawable(
                getApplicationContext().getResources(), R.drawable.default_avatar, null));
        imageView.setOnClickListener(view -> mLoadUserImage.launch("image/*"));

        Button newGameButton = findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, GameActivity.class);
            startActivity(intent);
        });

        Button scoreButton = findViewById(R.id.scoreButton);
        scoreButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, ScoreActivity.class);
            startActivity(intent);
        });
    }
}