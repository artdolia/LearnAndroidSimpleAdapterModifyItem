package com.dolia.artsiom.p0511_simpleadapterdata;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {

    private static final int CM_DELETE_ID = 1;
    private static final int CM_CHANGE_ID = 2;

    final String ATTR_NAME_TEXT = "text";
    final String ATTR_NAME_IMG = "image";

    ListView lvSimple;
    SimpleAdapter sAdapter;
    ArrayList<Map<String, Object>> data;
    Map<String, Object> m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        data = new ArrayList<Map<String, Object>>();

        for (int i = 0; i < 5; i++){
            addItemWithID(i);
        }

        String[] from = { ATTR_NAME_TEXT, ATTR_NAME_IMG };
        int[] to = { R.id.tvText, R.id.ivImg };

        sAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);

        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);
        registerForContextMenu(lvSimple);
    }


    public void onButtonClick(View v){

        addItemWithID(data.size());
        sAdapter.notifyDataSetChanged();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                       ContextMenu.ContextMenuInfo menuInfo){

        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_CHANGE_ID, 0, "Change Record");
        menu.add(0, CM_DELETE_ID, 1, "Delete Record");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item){

        if(item.getItemId() == CM_DELETE_ID){

            AdapterView.AdapterContextMenuInfo acmi =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            data.remove(acmi.position);
            sAdapter.notifyDataSetChanged();
            return true;

        }else if (item.getItemId() == CM_CHANGE_ID){

            AdapterView.AdapterContextMenuInfo acmi =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

            m = data.get(acmi.position);
            m.put(ATTR_NAME_TEXT, "New Text " + (acmi.position));

            sAdapter.notifyDataSetChanged();

            return true;
        }
        
        return super.onContextItemSelected(item);
    }


    void addItemWithID(int i){

        m = new HashMap<String, Object>();
        m.put(ATTR_NAME_TEXT, "Some Text " + i);
        m.put(ATTR_NAME_IMG, R.mipmap.ic_launcher);
        data.add(m);
    }
}
