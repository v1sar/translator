package ru.dmitriy.translator.data.repositories.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dmitriy on 04.12.2017.
 */

public class TranslatorAnswer {
    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("lang")
    @Expose
    private String lang;

    @SerializedName("text")
    @Expose
    private String[] text;

    public void setCode(String code) {
        this.code = code;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setText(String[] text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public String getLang() {
        return lang;
    }

    public String[] getText() {
        return text;
    }
}
