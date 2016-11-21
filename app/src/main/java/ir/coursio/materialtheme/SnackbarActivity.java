package ir.coursio.materialtheme;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SnackbarActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);


        Toolbar  mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        Button btnSayHello = (Button) findViewById(R.id.btnSayHello);
        Button btnShowWarning = (Button) findViewById(R.id.btnShowWarning);
        Button btnInternet = (Button) findViewById(R.id.btnInternet);

        btnSayHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar mySnackbar = Snackbar
                        .make(coordinatorLayout, R.string.hey_bud, Snackbar.LENGTH_LONG);

                mySnackbar.show();
            }
        });

        btnInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar mySnackbar = Snackbar
                        .make(coordinatorLayout, R.string.internet_off, Snackbar.LENGTH_LONG)
                        .setAction("Turn on", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Snackbar snackbar1 = Snackbar.make(coordinatorLayout, R.string.internet_on, Snackbar.LENGTH_SHORT);
                                snackbar1.show();
                            }
                        });

                mySnackbar.show();
            }
        });

        btnShowWarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar mySnackbar = Snackbar
                        .make(coordinatorLayout, R.string.ghostbusters, Snackbar.LENGTH_LONG);

                View sbView = mySnackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(ContextCompat.getColor(SnackbarActivity.this,R.color.orange));
                mySnackbar.show();
            }
        });
    }
}
