package fr.free.ouccelo.acquisti;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> achatsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        achatsList = new ArrayList<>();

        //this.context=this.getBaseContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        achatsList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        //new GetAchats().execute();

    }


private class GetAchats extends AsyncTask<Void, Void, Void> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

    }

    @Override
    protected Void doInBackground(Void... arg0) {
        HttpHandler sh = new HttpHandler();

        // Making a request to url and getting response
        String url = "http://api.androidhive.info/achats/";

        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray achats = jsonObj.getJSONArray("achats");

                // looping through All Achats
                for (int i = 0; i < achats.length(); i++) {
                    JSONObject c = achats.getJSONObject(i);
                    String id = c.getString("id");
                    String libelle = c.getString("libelle");

                    // Phone node is JSON Object
                   // JSONObject phone = c.getJSONObject("phone");
                   // String mobile = phone.getString("mobile");
                   // String home = phone.getString("home");
                   // String office = phone.getString("office");

                    // tmp hash map for single achat
                    HashMap<String, String> achat = new HashMap<>();

                    // adding each child node to HashMap key => value
                    achat.put("id", id);
                    achat.put("libelle", libelle);

                    // adding achat to achat list
                    achatsList.add(achat);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

            }

        } else {
            Log.e(TAG, "Couldn't get json from server.");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Couldn't get json from server. Check LogCat for possible errors!",
                            Toast.LENGTH_LONG).show();
                }
            });
        }

        return null;
    }

    public void Klose(View view)
    {
        AlertDialog.Builder adb = new AlertDialog.Builder(view.getContext());

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

    protected void xfinish()
    {

    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        ListAdapter adapter = new SimpleAdapter(MainActivity.this, achatsList,
                R.layout.list_item, new String[]{ "libelle"},
                new int[]{R.id.libelle});
        lv.setAdapter(adapter);
    }
}

}