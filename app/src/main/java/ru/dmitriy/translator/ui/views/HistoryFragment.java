package ru.dmitriy.translator.ui.views;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.dmitriy.translator.MyApplication;
import ru.dmitriy.translator.R;
import ru.dmitriy.translator.dagger.translator.TranslatorModule;
import ru.dmitriy.translator.data.repositories.db.AppDatabase;
import ru.dmitriy.translator.data.repositories.db.translate.TranslatedWord;
import ru.dmitriy.translator.ui.adapters.HistoryAdapter;

/**
 * Created by Dmitriy on 05.01.2018.
 */

public class HistoryFragment extends Fragment{

    @Inject
    AppDatabase appDatabase;


    @BindView(R.id.recycler_view_hist)
    RecyclerView recyclerView;

    @BindView(R.id.history_search)
    EditText historySearch;

    private LinearLayoutManager  mLayoutManager;
    private HistoryAdapter historyAdapter;
    private List<TranslatedWord> wordsList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getAppComponent().plus(new TranslatorModule()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        historyAdapter = new HistoryAdapter(wordsList);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(historyAdapter);
        updateTranslations();
        setTextListener();
    }

    private void setTextListener() {
        historySearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                charSequence = charSequence.toString().toLowerCase();

                final List<TranslatedWord> filteredList = new ArrayList<>();

                for (int j = 0; j < wordsList.size(); j++) {
                    final String text = wordsList.get(j).getWord().toLowerCase();
                    if (text.contains(charSequence)) {
                        filteredList.add(wordsList.get(j));
                    }
                }
                historyAdapter.setWordsList(filteredList);
                historyAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    void updateTranslations(){
//        Room.databaseBuilder(getContext(),
//                AppDatabase.class, "words-db-test").build()
        appDatabase
                .getTranslatedWordDao()
                .getAllTranslatedWords()
                .flatMap(Flowable::fromIterable)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v -> {
                            if (!wordsList.contains(v)) {
                                wordsList.add(v);
                                System.out.println(v.getWord());
                                historyAdapter.setWordsList(wordsList);
                                historyAdapter.notifyItemChanged(wordsList.size());
                            }
                        },
                        e -> System.out.println("error: " + e),
                        () -> System.out.println("THATS ALL"));
    }

}
