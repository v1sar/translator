package ru.dmitriy.translator.ui.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.dmitriy.translator.MyApplication;
import ru.dmitriy.translator.R;

import ru.dmitriy.translator.dagger.translator.TranslatorModule;
import ru.dmitriy.translator.ui.presenters.TranslatorPresenter;

/**
 * Created by Dmitriy on 05.01.2018.
 */

public class TranslatorFragment extends MvpAppCompatFragment implements ITranslatorView{

    @Inject
    TranslatorPresenter daggerTranslatorPresenter;

    @InjectPresenter
    TranslatorPresenter moxyTranslatorPresenter;

    @ProvidePresenter
    TranslatorPresenter providePresenter() {
        return daggerTranslatorPresenter;
    }

    @BindView(R.id.loading_bar)
    ProgressBar loadingBar;
    @BindView(R.id.translated_text)
    TextView translatedText;
    @BindView(R.id.text_to_translate)
    EditText textToTranslate;

    @OnClick(R.id.translate_btn) void makeTranslate() {
        moxyTranslatorPresenter.doTranslate(textToTranslate.getText().toString());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        MyApplication.getAppComponent().plus(new TranslatorModule()).inject(this);
        super.onCreate(savedInstanceState);
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

        RxTextView.textChanges(textToTranslate)
                .debounce(2, TimeUnit.SECONDS)
                .map(v -> new StringBuilder(v).toString())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v -> {
                    if (!v.isEmpty()) moxyTranslatorPresenter.doTranslate(v);
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
