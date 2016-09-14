package code.com.otpcontact;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ContactInfo extends AppCompatActivity implements View.OnClickListener{

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
        title = (TextView) findViewById(R.id.title);
        title.setText(getResources().getString(R.string.sendmsgcaps));
        phoneno =(TextView) findViewById(R.id.phoneno);
        sendmsg = (TextView) findViewById(R.id.sendmsg);
        homebtn.setVisibility(View.VISIBLE);
        name.setText(getIntent().getStringExtra("name"));
        phoneno.setText(getIntent().getStringExtra("phone"));
        homebtn.setOnClickListener(this);
        sendmsg.setOnClickListener(this);

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
                Intent i = new Intent(activity,Compose.class);
                startActivity(i);
                break;
            case R.id.home:
                finish();
                break;
            default:
                break;
        }
        }
}
