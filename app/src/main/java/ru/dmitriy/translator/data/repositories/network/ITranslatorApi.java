package ru.dmitriy.translator.data.repositories.network;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Dmitriy on 04.12.2017.
 */

public interface ITranslatorApi {
    @POST("/api/v1.5/tr.json/translate")
    Observable<TranslatorAnswer> getMyJSON(@Query("key") String key, @Query("text") String text, @Query("lang") String lang);
}
