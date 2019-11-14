package br.com.digitalhouse.marvelscomics.Repository;

import br.com.digitalhouse.marvelscomics.model.pojo.Comics;
import br.com.digitalhouse.marvelscomics.model.pojo.Result;
import io.reactivex.Observable;

import static br.com.digitalhouse.marvelscomics.data.remote.RetrofitService.getApiService;

public class ComicsRepository {
    public Observable<Comics> getComics(String format, String formatType, boolean noVariants,
                                        String orderBy, String ts, String hash, String apiKey){
        return getApiService().getAllComics(format, formatType, noVariants, orderBy, ts,
                hash, apiKey);
    }
}
