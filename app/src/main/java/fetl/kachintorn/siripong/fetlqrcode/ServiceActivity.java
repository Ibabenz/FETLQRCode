package fetl.kachintorn.siripong.fetlqrcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceActivity extends AppCompatActivity {

    //  Explicit
    private TextView textView;
    private ImageView barImageView, qrImageView;
    private ListView listView;
    private String nameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);


        //Initial View
        initialView();

        //Show Text
        showText();

        //Create ListView
        createListView();


    }   //Main Method

    private void createListView() {

        try {

            MyConstant myConstant = new MyConstant();
            String urlJSON = myConstant.getUrlGetProduct();

            GetData getData = new GetData(this);
            getData.execute(urlJSON);
            String strJSON = getData.get();
            Log.d("18MayV1", "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);

            int i = jsonArray.length();
            final String[] iconStrings = new String[i];
            final String[] titleStrings = new String[i];
            final String[] detailStrings = new String[i];

            for (int i1 = 0; i1 < i; i1++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i1);
                iconStrings[i1] = jsonObject.getString("Image");
                titleStrings[i1] = jsonObject.getString("Produce");
                detailStrings[i1] = jsonObject.getString("Detail");

            }   //for

            MyAdapter myAdapter = new MyAdapter(this, iconStrings, titleStrings, detailStrings);
            listView.setAdapter(myAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ServiceActivity.this, DetailActivity.class);
                    intent.putExtra("Name", titleStrings[position]);
                    intent.putExtra("Detail", detailStrings[position]);
                    intent.putExtra("Icon", iconStrings[position]);
                    startActivity(intent);
                }
            });

        } catch (Exception e) {
            Log.d("18MayV1", "e createListView ==> " + e.toString());
        }

    }

    private void showText() {
        nameString = getIntent().getStringExtra("Login");
        textView.setText("สวัสดี " + nameString);
    }

    private void initialView() {
        textView = (TextView) findViewById(R.id.txtNameLogin);
        barImageView = (ImageView) findViewById(R.id.imvBarCode);
        qrImageView = (ImageView) findViewById(R.id.imvBarCode);
        listView = (ListView) findViewById(R.id.livProduct);
    }
}   //Main Class
