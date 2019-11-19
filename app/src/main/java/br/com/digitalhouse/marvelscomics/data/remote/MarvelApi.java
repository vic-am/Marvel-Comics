package br.com.digitalhouse.marvelscomics.data.remote;

import br.com.digitalhouse.marvelscomics.model.pojo.Comics;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelApi {
    @GET("comics?")
    Observable<Comics> getAllComics(
            @Query("format") String format,
            @Query("formatType") String formatType,
            @Query("noVariants") boolean noVariants,
            @Query("orderBy") String orderBy,
            @Query("ts") String ts,
            @Query("hash") String hash,
            @Query("apikey") String apikey);
}
