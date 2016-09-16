package code.com.otpcontact;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Compose extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private ImageButton homebtn;
    private TextView name,phoneno,sendmsg,title;
    private Activity activity;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        getActionBarToolbar();
        activity = this;

        handler = new Handler();
        homebtn = (ImageButton)findViewById(R.id.home);
        name= (TextView) findViewById(R.id.name);
        phoneno =(TextView) findViewById(R.id.phoneno);
        name.setVisibility(View.GONE);
        phoneno.setText(getResources().getString(R.string.smstext) + getOTP());
        sendmsg = (TextView) findViewById(R.id.sendmsg);
        sendmsg.setText(getResources().getString(R.string.send));
        homebtn.setVisibility(View.VISIBLE);
        title = (TextView) findViewById(R.id.title);
        title.setText(getResources().getString(R.string.send));
        homebtn.setOnClickListener(this);
        sendmsg.setOnClickListener(this);
    }

    private String getOTP()
    {
        String otp ="123456";
        return otp;
    }

    public Toolbar getActionBarToolbar() {
        if (toolbar == null) {
            toolbar = (Toolbar) findViewById(R.id.main_toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        }
        return toolbar;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.sendmsg:
                callSmsApi();
                break;
            case R.id.home:
                finish();
                break;
            default:
                break;
        }
    }

    private void callSmsApi()
    {
        String url ="https://rest.nexmo.com/sms/json?api_key=41db1a32&api_secret=dc9a0efc4f7a97b3&to=+919789809188&from=Abhay-Nexmo-OTP-Test&text="+getResources().getString(R.string.smstext) + getOTP();

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Asciisuccess",""+response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    String responseBody = new String(error.networkResponse.data, "utf-8");
                    Log.e("Asciierror",""+responseBody);
                    JSONObject jsonObject = new JSONObject(responseBody);

                    Log.e("Asciierror",""+responseBody);
                    Log.v("Ascii",""+jsonObject.toString() );
                }
                catch (JSONException ee)
                {

                }
                catch (UnsupportedEncodingException er)
                {

                }
            }
        });
        queue.add(stringRequest);

    }
}
