package es.molestudio.navigationdrawer;

import android.content.res.Configuration;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private String[] mOptionMenu;
    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawerRelativeLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mTitleSection;
    private CharSequence mTitleApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOptionMenu = new String[] { "Opción 1", "Opción 2", "Opción 3" };
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerRelativeLayout = (RelativeLayout)
                findViewById(R.id.left_drawer);
        mDrawerList = (ListView) findViewById(R.id.list_view_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<String>(getSupportActionBar()
                .getThemedContext(), android.R.layout.simple_list_item_1,
                mOptionMenu));
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);


        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Toast.makeText(MainActivity.this, "Has pulsado el item " + mOptionMenu[position], Toast.LENGTH_SHORT).show();

                mDrawerList.setItemChecked(position, true);

                mTitleSection = mOptionMenu[position];
                getSupportActionBar().setTitle(mTitleSection);

                mDrawerLayout.closeDrawer(mDrawerRelativeLayout);
            }
        });
        mDrawerList.setItemChecked(0, true);
        mTitleSection = getTitle();
        mTitleApp = getTitle(); 

        getSupportActionBar().setTitle(mTitleApp);

        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                R.string.open,
                R.string.close) {

            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitleSection);
                ActivityCompat.invalidateOptionsMenu(MainActivity.this);
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mTitleSection);
                ActivityCompat.invalidateOptionsMenu(MainActivity.this);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}
