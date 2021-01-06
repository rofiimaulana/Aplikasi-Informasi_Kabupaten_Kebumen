package com.example.kebumenan;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.xml.transform.Result;

public class kuis extends AppCompatActivity {
    private TextView nomerkuis, pertanyaan_Label;
    private Button jawaban1, jawaban2, jawaban3, jawaban4;
    private String jawabanBenar;
    private int rightAnswerCount = 0;
    private int nomer_kuis = 1;
    static final private int total_pertanyaan = 10;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String Data_kuis[][] = {
            // {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
            {"Lintang Astronomi kebumen adalah ?", "7°27' - 7°50' Lintang Selatan dan 109°22' - 109°50' Bujur Timur", "9°27' - 7°50' Lintang Timur dan 109°22' - 109°50' Bujur Sangkar", "7°57' - 9°50' Lintang Utara dan 109°22' - 109°50' Bujur Barat", "7°27' - 7°50' Lintang Selatan dan 509°22' - 509°50' Bujur selatan"},
            {"Berapa Luas Wilayah Kebumen ?", "1.581, 11 km²", "4.000 km²", "2.500 km²", "500 km²"},
            {"Kabupaten kebumen merupakan tempat pelarian pangeran ?", "Pageran Bumidirja", "Pangeran kiansantang", "Pangeran Antasari", "Pangeran Prabu siliwangi"},
            {"Pada zaman dahulu nama kebumen adalah ?", "penjer", "panser", "pansur", "panjar"},
            {"Anak dari Pangkubuwono 1 yang melegendaris adalah ?", "Joko Sangrib", "Djoko Maghrib", "Joko Suwarno", "Djoko santoso"},
            {"Batas wilayah sebelah utara kebumen adalah ?", "Banjar Negoro", "Surabaya", "Jakarta", "Maluku"},
            {"Manakah sungai yang ada di kebumen ?", "Sungai Logending", "Sungai Ciliwung", "Sungai Kapuas", "Sungai Mahakam"},
            {"Goa Tumang berasal dari ?", "Kebumen", "Malang", "Tasik malaya", "Makasar"},
            {"Berapa orang syarat untuk memainkan Tari Cepet ?", "12 Orang", "20 Orang", "2 Orang", "1 Orang"},
            {"Pendiri Tari Cepet adalah ?", "Perta Wijaya ", "Basuki Abdillah", "Affandi", "Raden Saleh"},
            {"Perbup yang mengatur tentang pakaian adat khas kebumen ?", "Perpub nomor 35 tahun 2017", "Perpub nomor 20 tahun 2020", "Perpub nomor 35 tahun 1935", "Perpub nomor 17 tahun 2019"},
            {"Apa warna pada baju lurik motif kecil ?", "Coklat tanah kehijauan", "Coklat muda kemerahan", "Coklat tua kehitaman", "Coklat"},
            {"Berapa Jarak yang harus di tempuh untuk ke pantai menganti dari kec.kebumen ?", "48 km", "84 km", "4 km", "8 km"},
            {"Benteng van der wijk adalah benteng peninggalan ?", "Belanda", "Mexico", "Portugis", "Jepang"},
            {"Pasir hitam yang berada di kebumen terdapat di pantai ?", "Pantai Suwuk", "Pantai Labuan bajo", "Pantai Watukarung", "Pantai Anyer"}
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis);
        nomerkuis = findViewById(R.id.nomerLabel);
        pertanyaan_Label = findViewById(R.id.pertanyaanLabel);
        jawaban1 = findViewById(R.id.jawabanBtn1);
        jawaban2 = findViewById(R.id.jawabanBtn2);
        jawaban3 = findViewById(R.id.jawabanBtn3);
        jawaban4 = findViewById(R.id.jawabanBtn4);

        // Receive quizCategory from StartActivity.
        // Create quizArray from quizData.
        for (int i = 0; i < Data_kuis.length; i++) {

            // Prepare array.
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(Data_kuis[i][0]); // Country
            tmpArray.add(Data_kuis[i][1]); // Right Answer
            tmpArray.add(Data_kuis[i][2]); // Choice1
            tmpArray.add(Data_kuis[i][3]); // Choice2
            tmpArray.add(Data_kuis[i][4]); // Choice3

            // Add tmpArray to quizArray.
            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz() {

        // Update quizCountLabel.
        nomerkuis.setText("No" + nomer_kuis);

        // Generate random number between 0 and 14 (quizArray's size - 1)
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        // Pick one quiz set.
        ArrayList<String> quiz = quizArray.get(randomNum);

        // Set question and right answer.
        // Array format: {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
        pertanyaan_Label.setText(quiz.get(0));
        jawabanBenar = quiz.get(1);

        // Remove "Country" from quiz and Shuffle choices.
        quiz.remove(0);
        Collections.shuffle(quiz);

        // Set choices.
        jawaban1.setText(quiz.get(0));
        jawaban2.setText(quiz.get(1));
        jawaban3.setText(quiz.get(2));
        jawaban4.setText(quiz.get(3));

        // Remove this quiz from quizArray.
        quizArray.remove(randomNum);
    }

    public void checkAnswer(View view) {

        // Get pushed button.
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(jawabanBenar)) {
            // Correct
            alertTitle = "Jawaban anda benar!";
            rightAnswerCount++;

        } else {
            alertTitle = "Jawaban anda salah";
        }

        // Create AlertDialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer : " + jawabanBenar);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (nomer_kuis == total_pertanyaan) {
                    // Show Result.
                    Intent intent = new Intent(getApplicationContext(), hasilkuis.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);

                } else {
                    nomer_kuis++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}


