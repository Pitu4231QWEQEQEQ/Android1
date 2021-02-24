package stu.cn.ua.lab1.Activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import stu.cn.ua.lab1.R;
import stu.cn.ua.lab1.model.Player;

public class QuestionsActivity extends AppCompatActivity {

    public static final String ARG_PLAYER = "PLAYER";
    private static final String KEY_PLAYER = "PLAYER";
    private List<String> answer;

    private EditText question;
    private Player player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        if(savedInstanceState != null){
            player = savedInstanceState.getParcelable(KEY_PLAYER);
        }

        answer = Arrays.asList(getString(R.string.yes),
                getString(R.string.likely),
                getString(R.string.may_be),
                getString(R.string.dont_kown),
                getString(R.string.unlikely),
                getString(R.string.no));

        player = (Player)getIntent().getParcelableExtra(ARG_PLAYER);
        question = findViewById(R.id.questionEditText);


        findViewById(R.id.questionButton).setOnClickListener(v -> {
            int variant = (question.getText().toString() + player.toString()).hashCode()%6;
            variant = Math.abs(variant);
            Toast.makeText(this,answer.get(variant), Toast.LENGTH_LONG).show();
        });

        findViewById(R.id.cancelButton).setOnClickListener(v -> {
            finish();
        });
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_PLAYER, player);
    }
}
