package ru.dmitriy.translator.dagger.translator;

import dagger.Subcomponent;
import ru.dmitriy.translator.ui.views.TranslatorActivity;

/**
 * Created by Dmitriy on 03.12.2017.
 */

@Subcomponent(modules = {TranslatorModule.class})
@TranslatorScope
public interface TranslatorComponent {

    void inject(TranslatorActivity translatorActivity);

}
