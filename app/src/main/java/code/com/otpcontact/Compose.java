package code.com.otpcontact;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Compose extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private ImageButton homebtn;
    private TextView name,phoneno,sendmsg,title;
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        getActionBarToolbar();
        activity = this;
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
                break;
            case R.id.home:
                finish();
                break;
            default:
                break;
        }
    }
}
