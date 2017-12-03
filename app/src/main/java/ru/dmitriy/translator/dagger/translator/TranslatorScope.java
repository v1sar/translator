package ru.dmitriy.translator.dagger.translator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Dmitriy on 03.12.2017.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface TranslatorScope {
}
