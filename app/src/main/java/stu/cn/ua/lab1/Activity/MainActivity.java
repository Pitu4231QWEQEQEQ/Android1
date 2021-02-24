package stu.cn.ua.lab1.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import stu.cn.ua.lab1.R;
import stu.cn.ua.lab1.model.Player;

public class MainActivity extends AppCompatActivity {
    private static final int RQ_CODE = 1;
    private static final String KEY_PLAYER = "PLAYER";
    private Player player = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){
            player = savedInstanceState.getParcelable(KEY_PLAYER);
        }

        findViewById(R.id.getPredictionButton).setOnClickListener(v -> {
            if( player != null) {
                Intent intent = new Intent(this, QuestionsActivity.class);
                intent.putExtra(QuestionsActivity.ARG_PLAYER, player);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Error! Player needs initialization!", Toast.LENGTH_SHORT).show();
            }

        });

        findViewById(R.id.optionsButton).setOnClickListener(v -> {
            Intent intent = new Intent(this, OptionsActivity.class);
            if(player != null) {
                intent.putExtra(OptionsActivity.ARG_PLAYER, player);
            }
            startActivityForResult(intent, RQ_CODE);

        });

        findViewById(R.id.quitButton).setOnClickListener(v -> {
            finish();
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode == RQ_CODE && resultCode == RESULT_OK){
            player = data.getParcelableExtra(OptionsActivity.EXTRA_PLAYER);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_PLAYER, player);
    }
}