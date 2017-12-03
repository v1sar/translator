package ru.dmitriy.translator.ui.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.dmitriy.translator.MyApplication;
import ru.dmitriy.translator.R;
import ru.dmitriy.translator.dagger.translator.TranslatorModule;
import ru.dmitriy.translator.ui.presenters.ITranslatorPresenter;

/**
 * Created by Dmitriy on 26.11.2017.
 */

public class TranslatorActivity extends AppCompatActivity implements ITranslatorView {

    @Inject
    ITranslatorPresenter translatorPresenter;

    @BindView(R.id.loading_bar) ProgressBar loadingBar;
    @BindView(R.id.translated_text) TextView translatedText;
    @BindView(R.id.text_to_translate) EditText textToTranslate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translator_activity);
        MyApplication.getAppComponent().plus(new TranslatorModule()).inject(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.translate_btn) void makeTranslate() {
        translatorPresenter.doTranslate(textToTranslate.getText().toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        translatorPresenter.bindView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        translatorPresenter.unbindView();
    }

    @Override
    public void showLoading() {
        loadingBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void stopLoading() {
        loadingBar.setVisibility(ProgressBar.GONE);
    }

    public void onTranslateDone(String transalate) {
        translatedText.setText(transalate);
    }
}
