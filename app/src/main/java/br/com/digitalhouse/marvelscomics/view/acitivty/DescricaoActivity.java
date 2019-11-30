package br.com.digitalhouse.marvelscomics.view.acitivty;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.com.digitalhouse.marvelscomics.R;
import br.com.digitalhouse.marvelscomics.model.pojo.Result;

import static br.com.digitalhouse.marvelscomics.view.acitivty.MainActivity.COMIC_KEY;

public class DescricaoActivity extends AppCompatActivity {

    private ImageView imagemFundo;
    private ImageView imagemComic;
    private TextView textTitulo;
    private TextView textDescricao;
    private TextView textPublicacao;
    private TextView textPreco;
    private TextView textPaginas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao);

        initViews();

        if (getIntent() != null && getIntent().getExtras() != null) {
            Result result = getIntent().getExtras().getParcelable(COMIC_KEY);

            /* O código está crashando quando tenta puxar as fotos e o preço*/

//            Picasso.get().load(result.getImages().get(0).getPath() + ".jpg").into(imagemComic);
//            Picasso.get().load(result.getThumbnail().getPath() + ".jpg").into(imagemFundo);

            textTitulo.setText(result.getTitle());
            if (result.getDescription() == null || result.getDescription().isEmpty()) {
                textDescricao.setText("Descrição não disponível");
            } else {
                textDescricao.setText(result.getDescription());
            }
//            textPublicacao.setText(result.getDates().get(0).getDate());
//            textPreco.setText(result.getPrices().get(0).getType());
//            textPaginas.setText(result.getFormat());

        }
    }

    public void initViews() {
        imagemFundo = findViewById(R.id.fundoImageView);
        imagemComic = findViewById(R.id.comicImageView);
        textTitulo = findViewById(R.id.tituloTextView);
        textDescricao = findViewById(R.id.descricaoTextView);
        textPublicacao = findViewById(R.id.publicacaoTextView);
        textPreco = findViewById(R.id.precoTextView);
        textPaginas = findViewById(R.id.paginasTextView);

    }
}
