package fetl.kachintorn.siripong.fetlqrcode;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LongSparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private EditText userEditText, passwordEditText;
    private TextView textView;
    private Button button;
    private String userString, passwordAString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Initial View
        initialView();

        // Controller
        controller();

    }   // Main Method

    private void controller() {
        textView.setOnClickListener(MainActivity.this);
        button.setOnClickListener(MainActivity.this);

    }

    private void initialView() {
        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        textView = (TextView) findViewById(R.id.txtNewRegis);
        button = (Button) findViewById(R.id.btnLogin);
    }

    @Override
    public void onClick(View v) {

        // For TextView
        if (v == textView) {

            //Intent to Register
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
            //

        }
        if (v == button) {
            // Get Value From EditText
            userString = userEditText.getText().toString().trim();
            passwordAString = passwordEditText.getText().toString().trim();

            //Check Space

            if (userString.equals("") || passwordAString.equals("")) {
                //Have Space
                MyAlert myAlert = new MyAlert(this);
                myAlert.myDialog(getResources().getString(R.string.title_HaveSpace),
                        getResources().getString(R.string.message_HaveSpace));
            } else {
                //No Space
                checkUserAnPass();
            }
        }
    }

    private void checkUserAnPass() {
        try {

            GetData getData = new GetData(this);
            MyConstant myConstant = new MyConstant();
            getData.execute(myConstant.getUrlGetUser());
            String strJSON = getData.get();
            Log.d("17MayV2", "JSON ==> " + strJSON);
            //showMessage(strJSON);
            JSONArray jsonArray = new JSONArray(strJSON);
            boolean b = true;  // User False
            String strName = null, strPassword = null;
            MyAlert myAlert = new MyAlert(this);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (userString.equals(jsonObject.getString("User"))) {
                    b = false;
                    strName = jsonObject.getString("Name");
                    strPassword = jsonObject.getString("Password");

                }
            }   //for

            if (b) {
                // User False
                myAlert.myDialog(getResources().getString(R.string.title_UserFalse),
                        getResources().getString(R.string.message_UserFalse));
            } else if (passwordAString.equals(strPassword)) {
                // Password True
                showMessage("Welcome " + strName);

                //Intent to Service
                Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                intent.putExtra("Login", strName);
                startActivity(intent);
                finish();

            } else {
                // Password False
                myAlert.myDialog(getResources().getString(R.string.title_PasswordFalse),
                        getResources().getString(R.string.message_PasswordFalse));
            }
        } catch (Exception e) {
            Log.d("17MayV2", "e checkUser ==> " + e.toString());

        }
    }   //checkUserAnPass

    private void showMessage(String strJSON) {
        Toast.makeText(MainActivity.this, strJSON, Toast.LENGTH_SHORT).show();
    }
}   //Main Class คลาสหลัก
