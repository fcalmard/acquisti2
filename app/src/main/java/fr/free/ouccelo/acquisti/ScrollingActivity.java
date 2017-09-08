package fr.free.ouccelo.acquisti;

import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ScrollingActivity extends AppCompatActivity implements FinishListener {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.context=this.getBaseContext();

        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Close(v);
            }
        });

        ListView lv = (ListView) findViewById(R.id.listview);

        ListAdapter lsa = new ListAdapter() {
            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEnabled(int i) {
                return false;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                return null;
            }

            @Override
            public int getItemViewType(int i) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        };

        //lv.setAdapter(ListAdapter lsa);

        //lv.addView();
        ImageButton imgbtn = new ImageButton(context);

        //adb.setIcon(android.R.drawable.ic_dialog_alert);

        imgbtn.setImageResource(R.drawable.ajout);
        //imgbtn.setImageDrawable(getDrawable(@));

     //   lv.addView(imgbtn);

        /*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void Close(final View view)
    {
        final Context context = view.getContext();

        AlertDialog.Builder adb = new AlertDialog.Builder(view.getContext());

        adb.setIcon(android.R.drawable.ic_dialog_alert);

        String msg= getString(R.string.msgquit);

        msg= msg.concat(" ");
        msg= msg.concat(getString(R.string.app_name));

        adb.setTitle(getString(R.string.app_name));

        adb.setPositiveButton("Ok", null);

        adb.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }

        });

        adb.setNegativeButton(R.string.no, null);

        adb.setMessage(msg);

        adb.show();
    }
    public void Close2(View View) {

        final Context context = this;

        AlertDialog.Builder adb = new AlertDialog.Builder(context);

        adb.setIcon(android.R.drawable.ic_dialog_alert);

        String msg= getString(R.string.msgquit);

        msg= msg.concat(" ");
        msg= msg.concat(getString(R.string.app_name));

        adb.setTitle(getString(R.string.app_name));

        adb.setPositiveButton("Ok", null);

        adb.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                //finishAffinity();
            }

        });

        adb.setNegativeButton(R.string.no, null);

        adb.setMessage(msg);

        adb.show();

    }
}
