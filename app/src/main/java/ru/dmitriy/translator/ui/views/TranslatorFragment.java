package ru.dmitriy.translator.ui.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * Created by Dmitriy on 05.01.2018.
 */

public class TranslatorFragment extends Fragment implements ITranslatorView{

    @Inject
    ITranslatorPresenter translatorPresenter;

    @BindView(R.id.loading_bar)
    ProgressBar loadingBar;
    @BindView(R.id.translated_text)
    TextView translatedText;
    @BindView(R.id.text_to_translate)
    EditText textToTranslate;

    @OnClick(R.id.translate_btn) void makeTranslate() {
        translatorPresenter.doTranslate(textToTranslate.getText().toString());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getAppComponent().plus(new TranslatorModule()).inject(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.translator_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        translatorPresenter.bindView(this);
    }

    @Override
    public void onDestroy() {
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
