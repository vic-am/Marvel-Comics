package br.com.digitalhouse.marvelscomics.view.acitivty;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import br.com.digitalhouse.marvelscomics.R;
import br.com.digitalhouse.marvelscomics.model.pojo.Result;

import static br.com.digitalhouse.marvelscomics.view.acitivty.MainActivity.COMIC_KEY;
import static br.com.digitalhouse.marvelscomics.view.acitivty.MainActivity.IMAGE_KEY;
import static br.com.digitalhouse.marvelscomics.view.acitivty.MainActivity.THUMB_KEY;

public class DescricaoActivity extends AppCompatActivity {

    private ImageView imagemFundo;
    private ImageView imagemComic;
    private TextView textTitulo;
    private TextView textDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao);

        initViews();

        if (getIntent() != null && getIntent().getExtras() != null) {

            Result result = getIntent().getExtras().getParcelable(COMIC_KEY);

            Picasso.get().load(getIntent().getExtras().getString(THUMB_KEY) + ".jpg").into(imagemFundo);
            Picasso.get().load(getIntent().getExtras().getString(IMAGE_KEY) + ".jpg").into(imagemComic);

            textTitulo.setText(result.getTitle());
            if (result.getDescription() == null || result.getDescription().isEmpty()) {
                textDescricao.setText("Descrição não disponível");
            } else {
                textDescricao.setText(result.getDescription());
            }
        }
    }

    public void initViews() {
        imagemFundo = findViewById(R.id.fundoImageView);
        imagemComic = findViewById(R.id.comicImageView);
        textTitulo = findViewById(R.id.tituloTextView);
        textDescricao = findViewById(R.id.descricaoTextView);

    }
}
