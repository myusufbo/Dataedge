package com.yusuf.iit.du.lms;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;


public class Apply3 extends ActionBarActivity {
    Drawer.Result result;
    AccountHeader.Result headerResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply3);

        initDrawer(savedInstanceState);
    }

    private void initDrawer(Bundle savedInstanceState) {

        final Toolbar toolbar = (Toolbar) findViewById(R.id.applyToolbar);
        setSupportActionBar(toolbar);

        headerResult = new AccountHeader()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName(MainActivity.employeeName).withEmail(MainActivity.employeeId).withIcon(getResources().getDrawable(R.drawable.profile))
//                        new ProfileDrawerItem().withName("Srabon").withEmail("kazisrabon@gmail.com").withIcon(getResources().getDrawable(R.drawable.profile2))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        // Handle Toolbar
        result = new Drawer()
                .withActivity(this)
                .withHeader(R.layout.header)
                .withAccountHeader(headerResult)
                .withTranslucentActionBarCompatibility(true).withDisplayBelowToolbar(true)
                .withToolbar(toolbar)
                        //.withTranslucentStatusBar(false)
                        //.withActionBarDrawerToggle(false)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home).withIdentifier(0),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_leave_apply).withIcon(FontAwesome.Icon.faw_calculator).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_approve).withIcon(R.drawable.approve).withIdentifier(2),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_relieve).withIcon(R.drawable.relieve).withIdentifier(3),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_my_leave).withIcon(R.drawable.myleave).withIdentifier(4),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_log_out).withIcon(R.drawable.logoutcopy).withIdentifier(5)
//                        new PrimaryDrawerItem().withName(R.string.drawer_item_vaccenation).withIcon(FontAwesome.Icon.faw_venus).withIdentifier(6)


                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {
                            getSupportActionBar().setTitle(((Nameable) drawerItem).getNameRes());
                            //toolbar.setTitle("Home");
                            Fragment fragment = null;
                            if (drawerItem.getIdentifier() == 0) {
                                //fragment = new Calculator();
                                startActivity(new Intent(Apply3.this, Home.class));
//                                Toast.makeText(getBaseContext(), "On Drawer Created", Toast.LENGTH_LONG).show();

                            } else if (drawerItem.getIdentifier() == 1) {
                                //fragment = new Calculator();
//                                 startActivity(new Intent(getApplicationContext(),HealthCalculator.class));


                            } else if (drawerItem.getIdentifier() == 2) {

                                startActivity(new Intent(Apply3.this, Approve.class));
                            }
                            else if (drawerItem.getIdentifier() == 3) {

                                startActivity(new Intent(Apply3.this, Relieve.class));
                            } else if (drawerItem.getIdentifier() == 4) {
                                startActivity(new Intent(Apply3.this, Myleave.class));
                            } else if (drawerItem.getIdentifier() == 5) {
//                                startActivity(new Intent(Apply3.this, MainActivity.class));
//                                finish();
//                                PackageInstaller.Session session= new PackageInstaller.Session();
                            }
                           /* else if (drawerItem.getIdentifier() == 6) {
                               // startActivity(new Intent(HealthCalculator.this, Vaccination.class));
                            }*/
                            if (fragment != null) {
                                FragmentManager fragmentManager = getSupportFragmentManager();
                                fragmentManager.beginTransaction()
                                        .replace(R.id.framecontainer, fragment)
                                        .commit();
                            }
                        }
                    }
                })
                .withFireOnInitialOnClick(true)
                .withSelectedItem(1)
                .build();
        //result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(false);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_apply3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
