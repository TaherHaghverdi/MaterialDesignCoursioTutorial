package ir.coursio.materialtheme;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private EditText edtName, edtMail;
    private TextInputLayout layoutName, layoutMail;
    private ViewGroup layoutMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        createNav();

        layoutMain = (ViewGroup) findViewById(R.id.activity_main);

        layoutMail = (TextInputLayout) findViewById(R.id.layout_mail);
        layoutName = (TextInputLayout) findViewById(R.id.layout_name);
        edtMail = (EditText) findViewById(R.id.edtMail);
        edtName = (EditText) findViewById(R.id.edtName);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);

        edtName.addTextChangedListener(new textWatcher(edtName));
        edtMail.addTextChangedListener(new textWatcher(edtMail));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_menu_1:
                Intent intent = new Intent(MainActivity.this, SnackbarActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_menu_2:
                Intent intent2 = new Intent(MainActivity.this, TabActivity.class);
                startActivity(intent2);
                return true;
            case R.id.action_menu_3:
                Intent intent3 = new Intent(MainActivity.this, BottomBarActivity.class);
                startActivity(intent3);
                return true;

            case R.id.action_menu_4:
                Intent intent4 = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent4);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void createNav() {
        final PrimaryDrawerItem itemHome = new PrimaryDrawerItem().withIdentifier(1).
                withName(R.string.drawer_item_home).
                withSelectable(false)
                .withIcon(GoogleMaterial.Icon.gmd_home);

        final SecondaryDrawerItem itemSettings = new SecondaryDrawerItem().withIdentifier(2)
                .withName(R.string.drawer_item_settings)
                .withSelectable(false)
                .withIcon(GoogleMaterial.Icon.gmd_settings);


        final SecondaryDrawerItem itemSearch = new SecondaryDrawerItem().withIdentifier(3)
                .withName(R.string.drawer_item_search)
                .withSelectable(false)
                .withIcon(GoogleMaterial.Icon.gmd_search);


        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Taher Haghverdi").withEmail("t.haghverdi@yahoo.com")
                                .withIcon(GoogleMaterial.Icon.gmd_person)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        itemHome,
                        new DividerDrawerItem(),
                        itemSearch,
                        itemSettings
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.equals(itemHome)) {
                            Toast.makeText(MainActivity.this, R.string.navigation_home, Toast.LENGTH_SHORT).show();
                        }
                        if (drawerItem.getIdentifier() == itemSettings.getIdentifier()) {
                            Toast.makeText(MainActivity.this, R.string.navigation_settings, Toast.LENGTH_SHORT).show();
                        }
                        if (drawerItem.getIdentifier() == itemSearch.getIdentifier()) {
                            Toast.makeText(MainActivity.this, R.string.navigation_search, Toast.LENGTH_SHORT).show();
                        }
                        return true;
                    }
                })
                .withSelectedItem(-1)
                .build();

    }

    private class textWatcher implements TextWatcher {

        private View view;

        private textWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {

            switch (view.getId()) {
                case R.id.edtName:
                    validateName();
                    break;
                case R.id.edtMail:
                    validateEmail();
                    break;

            }
        }

    }

    private boolean validateName() {
        if (edtName.getText().toString().trim().isEmpty()) {
            layoutName.setError(getString(R.string.name_error));
            requestFocus(edtName);
            return false;
        } else {
            layoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = edtMail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            layoutMail.setError(getString(R.string.email_error));
            requestFocus(edtMail);
            return false;
        } else {
            layoutMail.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void submit() {
        if (!validateName() || !validateEmail()) {
            return;
        }
        Snackbar snackbar = Snackbar
                .make(layoutMain, R.string.successfully_submitted, Snackbar.LENGTH_LONG);

        snackbar.show();
    }


}
