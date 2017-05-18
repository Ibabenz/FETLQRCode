package fetl.kachintorn.siripong.fetlqrcode;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by Siripong.K on 18/05/2017.
 */

public class SearchKey extends AsyncTask<String, Void, String> {

    private Context context;


    public SearchKey(Context context) {

        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        try {

            MyConstant myConstant = new MyConstant();
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("KeyCode", params[0])
                    .build();

            Request.Builder builder = new Request.Builder();
            Request request = builder.url(myConstant.getUrlGetProductWhere())
                    .post(requestBody).build();

            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}   //Main Class
