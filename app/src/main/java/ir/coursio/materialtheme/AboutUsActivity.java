package ir.coursio.materialtheme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.truizlop.fabreveallayout.FABRevealLayout;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ImageView imgCancel = (ImageView) findViewById(R.id.imgCancel);
        final FABRevealLayout fabRevealLayout = (FABRevealLayout) findViewById(R.id.fab_reveal_layout);

        assert imgCancel != null;
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assert fabRevealLayout != null;
                fabRevealLayout.revealMainView();

            }
        });

    }
}
