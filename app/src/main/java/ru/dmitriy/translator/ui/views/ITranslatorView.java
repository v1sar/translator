package ru.dmitriy.translator.ui.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Dmitriy on 26.11.2017.
 */

public interface ITranslatorView extends MvpView{
    void showLoading();
    void stopLoading();
    @StateStrategyType(AddToEndSingleStrategy.class)
    void onTranslateDone(String transalate);
}
