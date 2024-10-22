package com.example.ko_papir_ollo_dolgozat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView playerChoicePic;
    private ImageView computerChoicePic;
    private TextView pointCounter;
    private Button rockBtn;
    private Button paperBtn;
    private Button scissorsBtn;
    private Random rng;
    private int playerPoint;
    private int computerPoint;
    private int playerChoiceNum;
    private int computerChoiceNum;
    private int draw;
    private TextView dontetlenText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();



        rockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                getCompPick();
                playerChoiceNum = 1;
                playerChoicePic.setImageResource(R.drawable.rock);
                int result = resultCheck();
                if(result == 1)
                {

                    playerPoint++;
                    pointCounter.setText(String.format("Eredmény: Ember: %d Computer: %d",playerPoint,computerPoint));

                }
                else if(result == 3){
                    draw++;
                    dontetlenText.setText(String.format("Döntetlen: %d", draw));

                }
                else {
                    computerPoint++;
                    pointCounter.setText(String.format("Eredmény: Ember: %d Computer: %d",playerPoint,computerPoint));

                }
                pointCheck();

            }
        });

        paperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCompPick();
                playerChoiceNum = 2;
                playerChoicePic.setImageResource(R.drawable.paper);
                int result = resultCheck();
                if(result == 1)
                {
                    playerPoint++;
                    pointCounter.setText(String.format("Eredmény: Ember: %d Computer: %d",playerPoint,computerPoint));

                }
                else if(result == 3){
                   draw++;
                   dontetlenText.setText(String.format("Döntetlen: %d", draw));
                }
                else
                {
                    computerPoint++;
                    pointCounter.setText(String.format("Eredmény: Ember: %d Computer: %d",playerPoint,computerPoint));

                }
                pointCheck();

            }
        });

        scissorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCompPick();
                playerChoiceNum = 3;
                playerChoicePic.setImageResource(R.drawable.scissors);
                int result = resultCheck();
                if(result == 1)
                {
                    playerPoint++;
                    pointCounter.setText(String.format("Eredmény: Ember: %d Computer: %d",playerPoint,computerPoint));

                }
                else if(result == 3){
                   draw++;
                   dontetlenText.setText(String.format("Döntetlen: %d", draw));
                }
                else
                {
                    computerPoint++;
                    pointCounter.setText(String.format("Eredmény: Ember: %d Computer: %d",playerPoint,computerPoint));

                }
                pointCheck();

            }
        });


    }


    public void getCompPick()
    {
        computerChoiceNum = rng.nextInt(4);
        if(computerChoiceNum == 1)
        {
            computerChoicePic.setImageResource(R.drawable.rock);
        }
        else if(computerChoiceNum == 2)
        {
            computerChoicePic.setImageResource(R.drawable.paper);
        }
        else {
            computerChoicePic.setImageResource(R.drawable.scissors);
        }
    }

    public int resultCheck()
    {



        if(playerChoiceNum == 1 && computerChoiceNum == 3)
        {
            Toast.makeText(this, "A játékos nyert!", Toast.LENGTH_SHORT).show();
            return 1;
        }
        else if(playerChoiceNum == 2 && computerChoiceNum == 1 )
        {
            Toast.makeText(this, "A játékos nyert!", Toast.LENGTH_SHORT).show();
            return 1;
        }
        else if(playerChoiceNum == 3 && computerChoiceNum == 2)
        {
            Toast.makeText(this, "A játékos nyert!", Toast.LENGTH_SHORT).show();
            return 1;
        }
        else if (playerChoiceNum == computerChoiceNum)
        {
            Toast.makeText(this, "Döntetlen!", Toast.LENGTH_SHORT).show();
            return 3;
        }
        else {
            Toast.makeText(this, "A gép nyert!", Toast.LENGTH_SHORT).show();
            return 2;
        }


    }


    public void pointCheck()
    {
        if(playerPoint == 3)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("Győzelem");
            builder.setMessage(String.format("Szerentél új játékot kezdeni?"));

            builder.setCancelable(false);


            builder.setNegativeButton("NEM", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
                System.exit(0);
            });
            builder.setPositiveButton("IGEN", (DialogInterface.OnClickListener) (dialog, which) -> {
                newGame();
                dialog.cancel();
            });


            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        if(computerPoint == 3)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("Vereség");
            builder.setMessage(String.format("Szerentél új játékot kezdeni?"));

            builder.setCancelable(false);


            builder.setNegativeButton("NEM", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
                System.exit(0);
            });

            builder.setPositiveButton("IGEN", (DialogInterface.OnClickListener) (dialog, which) -> {
                newGame();
                dialog.cancel();
            });


            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }

    public void newGame(){
        playerPoint = 0;
        computerPoint = 0;
        draw = 0;
        dontetlenText.setText("Döntetlen: 0");
        playerChoicePic.setImageResource(R.drawable.rock);
        computerChoicePic.setImageResource(R.drawable.rock);
        pointCounter.setText(String.format("Eredmény: Ember: %d Computer: %d",playerPoint,computerPoint));

    }




    public void init()
    {
        playerChoicePic = findViewById(R.id.playerChoice);
        computerChoicePic = findViewById(R.id.computerChoice);
        pointCounter = findViewById(R.id.pointCounter);
        rockBtn = findViewById(R.id.rockBtn);
        paperBtn = findViewById(R.id.paperBtn);
        scissorsBtn = findViewById(R.id.scissorsBtn);
        rng = new Random();
        playerPoint = 0;
        computerPoint = 0;
        playerChoiceNum = 1;
        computerChoiceNum = 1;
        draw = 0;
        dontetlenText = findViewById(R.id.dontetlenText);
    }

}