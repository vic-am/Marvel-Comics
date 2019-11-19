package br.com.digitalhouse.marvelscomics.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.digitalhouse.marvelscomics.R;
import br.com.digitalhouse.marvelscomics.model.pojo.Result;
import br.com.digitalhouse.marvelscomics.view.click.OnClick;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ViewHolder> {

    private List<Result> comicsList;
    private OnClick listener;

    public ComicAdapter(List<Result> comicsList, OnClick listener) {
        this.comicsList = comicsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result result = comicsList.get(position);
        holder.onBind(result);

        holder.itemView.setOnClickListener((View v) -> {
            listener.comicsOnClick(result);
        });

    }

    @Override
    public int getItemCount() {
        return comicsList.size();
    }

    public void atualizaLista(List<Result> novaLista) {
        if (this.comicsList.isEmpty()) {
            this.comicsList = novaLista;
        } else {
            this.comicsList.addAll(novaLista);
        }

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView comicImage;
        private TextView comicText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            comicImage = itemView.findViewById(R.id.comicImageView);
            comicText = itemView.findViewById(R.id.comicTextView);
        }

        public void onBind(Result result) {

            comicText.setText(result.getTitle());
            Picasso.get().load(result.getThumbnail().getPath() + ".jpg").into(comicImage);

        }
    }
}
