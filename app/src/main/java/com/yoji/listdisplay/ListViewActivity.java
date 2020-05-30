package com.yoji.listdisplay;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListViewActivity extends AppCompatActivity {

    private final String TEXT_KEY = "text_key";
    private final String NUM_OF_SYMBOL_KEY = "num_of_symbol_key";
    private List<Map<String,String>> content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView list = findViewById(R.id.list);

        String[] values = prepareContent();

        initList(values);

        BaseAdapter listSimpleAdapter = createSimpleAdapter();

        BaseAdapter listContentAdapter = createAdapter(values);

        list.setAdapter(listSimpleAdapter);
    }

    @NonNull
    private BaseAdapter createAdapter(String[] values) {
        return new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
    }

    private BaseAdapter createSimpleAdapter(){
        return new SimpleAdapter(this, content, R.layout.item_to_display,
                new String[]{TEXT_KEY, NUM_OF_SYMBOL_KEY}, new int[]{R.id.mainTextTxtViewId,
                R.id.numOfSymbolTxtViewId});
    }

    @NonNull
    private String[] prepareContent() {
        return getString(R.string.large_text).split("\n\n");
    }

    private void initList(String[] values) {
        content = new ArrayList<>();
        for (String value: values){
            Map<String, String> map = new HashMap<>();
            map.put(TEXT_KEY, value);
            map.put(NUM_OF_SYMBOL_KEY, String.valueOf(value.length()));
            content.add(map);
        }
    }
}
