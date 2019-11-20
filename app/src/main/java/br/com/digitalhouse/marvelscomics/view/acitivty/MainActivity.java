package br.com.digitalhouse.marvelscomics.view.acitivty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.digitalhouse.marvelscomics.R;
import br.com.digitalhouse.marvelscomics.model.pojo.Result;
import br.com.digitalhouse.marvelscomics.view.adapter.ComicAdapter;
import br.com.digitalhouse.marvelscomics.view.click.OnClick;
import br.com.digitalhouse.marvelscomics.viewmodel.MainActivityViewModel;

import static br.com.digitalhouse.marvelscomics.Utils.AppUtils.md5;
import static br.com.digitalhouse.marvelscomics.viewmodel.MainActivityViewModel.PRIVATE_KEY;
import static br.com.digitalhouse.marvelscomics.viewmodel.MainActivityViewModel.PUBLIC_KEY;

public class MainActivity extends AppCompatActivity implements OnClick {

    public static final String COMIC_KEY = "comic";
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ComicAdapter comicAdapter;
    private List<Result> resultList = new ArrayList<>();
    private MainActivityViewModel viewModel;
    private OnClick listener;
    String ts = Long.toString(System.currentTimeMillis() / 1000);
    String hash = md5(ts + PRIVATE_KEY + PUBLIC_KEY);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        viewModel.getAllComics();
        viewModel.getListaComics().observe(this, results -> {
            if (results != null && !results.isEmpty()) {
                comicAdapter.atualizaLista(results);

            } else {
                Toast.makeText(this, "Quadrinho não encontrado", Toast.LENGTH_LONG).show();
            }
        });

        viewModel.getLoading().observe(this, aBoolean -> {
            if (aBoolean) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });

        viewModel.getComicsLiveDataError().observe(this, error -> {
            Toast.makeText(this, "Erro " + error, Toast.LENGTH_LONG).show();
        });
    }

    public void initViews() {
        recyclerView = findViewById(R.id.comicsRecycler);
        progressBar = findViewById(R.id.progressBar);
        comicAdapter = new ComicAdapter(resultList, this);
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        recyclerView.setAdapter(comicAdapter);
    }

    @Override
    public void comicsOnClick(Result result) {
        Intent intent = new Intent(this, DescricaoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(COMIC_KEY, result);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
