package ru.dmitriy.translator.dagger.translator;

import dagger.Subcomponent;
import ru.dmitriy.translator.ui.views.HistoryFragment;
import ru.dmitriy.translator.ui.views.TranslatorFragment;

/**
 * Created by Dmitriy on 03.12.2017.
 */

@Subcomponent(modules = {TranslatorModule.class})
@TranslatorScope
public interface TranslatorComponent {

    void inject(TranslatorFragment translatorFragment);

    void inject(HistoryFragment historyFragment);
}
