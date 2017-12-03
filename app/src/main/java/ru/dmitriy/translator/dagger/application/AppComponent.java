package ru.dmitriy.translator.dagger.application;

/**
 * Created by Dmitriy on 03.12.2017.
 */

import javax.inject.Singleton;

import dagger.Component;
import ru.dmitriy.translator.dagger.translator.TranslatorComponent;
import ru.dmitriy.translator.dagger.translator.TranslatorModule;

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    TranslatorComponent plus(TranslatorModule translatorModule);
}
