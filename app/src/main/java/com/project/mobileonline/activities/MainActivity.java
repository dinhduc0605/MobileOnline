package com.project.mobileonline.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.project.mobileonline.R;
import com.project.mobileonline.adapters.DrawerAdapter;
import com.project.mobileonline.fragments.HistoryFragment;
import com.project.mobileonline.fragments.NewsFragment;
import com.project.mobileonline.fragments.StoreFragment;
import com.project.mobileonline.models.Constants;
import com.project.mobileonline.models.DrawerItem;
import com.project.mobileonline.models.User;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    static boolean login = true;
    ActionBar actionBar;
    DrawerLayout drawerLayout;
    ListView drawerMenu;
    ArrayList<DrawerItem> items = new ArrayList<>();
    DrawerAdapter adapter;
    ActionBarDrawerToggle drawerToggle;
    String mTitle, mDrawerTitle;
    String[] titles;
    User currentUser;
    String avatarPath;

    int[] userIconRes = {
            0,
            R.drawable.ic_store_black_48dp,
            R.drawable.ic_categories_black_48dp,
            R.drawable.ic_history_black_48dp,
            R.drawable.ic_news_black_48dp,
            0,
            R.drawable.ic_info_black_48dp,
            R.drawable.ic_sign_out_black_48dp,
            R.drawable.ic_exit_black_48dp,
    };
    int[] guestIconRes = {
            R.drawable.ic_account_circle_black_48dp,
            0,
            R.drawable.ic_store_black_48dp,
            R.drawable.ic_categories_black_48dp,
            R.drawable.ic_news_black_48dp,
            0,
            R.drawable.ic_info_black_48dp,
            R.drawable.ic_exit_black_48dp
    };
    int[] iconRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SystemBarTintManager manager = new SystemBarTintManager(this);
        manager.setStatusBarTintEnabled(true);
        manager.setTintResource(R.color.actionbar_bg);

        initData();
        initView();
        getControlWidget();
    }

    private void initData() {
        if (login) {
            titles = getResources().getStringArray(R.array.user_menu);
            iconRes = userIconRes;
        } else {
            titles = getResources().getStringArray(R.array.guest_menu);
            iconRes = guestIconRes;
        }

        for (int i = 0; i < titles.length; i++) {
            DrawerItem item = new DrawerItem(iconRes[i], titles[i]);
            items.add(item);
            Log.w(TAG, items.get(i).getTitle() + "-" + items.get(i).getIconRes());
        }

        currentUser = new User();
        currentUser.setName("Nguyen Dinh Duc");
        File dir = new File(Constants.DIRECTORY_PATH + currentUser.getName());
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    private void signOut() {
        removeHeaderView();
        adapter.clear();
        login = false;
        initData();
        adapter.notifyDataSetChanged();
    }

    private void addHeaderView() {
        View view = getLayoutInflater().inflate(R.layout.drawer_header_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.profile_icon);
        avatarPath = Constants.DIRECTORY_PATH + "Nguyen Dinh Duc/" + Constants.AVATAR_IMAGE_NAME;
        Bitmap bitmap = BitmapFactory.decodeFile(avatarPath);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        view.setTag("hv");
        drawerMenu.addHeaderView(view);
    }

    private void removeHeaderView() {
        View view = drawerMenu.findViewWithTag("hv");
        drawerMenu.removeHeaderView(view);
    }

    private void initView() {
        // Enable Local Datastore.
//        Parse.enableLocalDatastore(this);
        //nit parse
//        Parse.initialize(this, "OYyLBiBkt53DVu4CXCBbr4UgCpQFMoeUisusPQWa", "i1WGIaqAT2Pvqy0E1SY1HhwHbf15KnSJHGBigdl1");

        mDrawerTitle = getResources().getString(R.string.app_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerMenu = (ListView) findViewById(R.id.drawer_menu);
        adapter = new DrawerAdapter(this, R.layout.drawer_item_layout, items);
        drawerMenu.setAdapter(adapter);
        if (login) {
            addHeaderView();
        }
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                actionBar.setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                actionBar.setTitle(mDrawerTitle);
            }
        };
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        selectItem(2);
    }

    private void getControlWidget() {
        drawerLayout.setDrawerListener(drawerToggle);
        drawerMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.w(TAG, position + "");
                selectItem(position);
            }
        });

    }

    //item_array_position = list_item_position -1
    private void selectItem(int position) {
        Fragment fragment = new Fragment();
        if (login) {
            switch (position) {
                case 0:
                    Intent intent = new Intent(this, ProfileActivity.class);
                    startActivityForResult(intent, Constants.PROFILE_ACTIVITY_REQUEST_CODE);
                    return;
                case 2:
                    fragment = new StoreFragment();
                    break;
                case 4:
                    fragment = new HistoryFragment();
                    break;
                case 5:
                    fragment = new NewsFragment();
                    break;
                case 7:
                    Intent intent1 = new Intent(this, AboutActivity.class);
                    startActivity(intent1);
                    return;
                case 8:
                    signOut();
                    return;
                case 9:
                    displayExitDialog();
                    return;
            }
            setTitle(items.get(position - 1).getTitle());
        } else {
            switch (position) {
                case 0:
                    Intent intent = new Intent(this, LoginAcitivity.class);
                    startActivity(intent);
                    finish();
                case 2:
                    fragment = new StoreFragment();
                    break;
                case 4:
                    fragment = new NewsFragment();
                    break;
                case 6:
                    Intent intent1 = new Intent(this, AboutActivity.class);
                    startActivity(intent1);
                    return;
                case 7:
                    displayExitDialog();
                    return;
            }
            setTitle(items.get(position).getTitle());
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main_content, fragment);
        ft.commit();

        drawerMenu.setItemChecked(position, true);
        drawerLayout.closeDrawer(drawerMenu);
    }

    private void displayExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titles[6])
                .setMessage("Do you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = (String) title;
        actionBar.setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.PROFILE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String filepath = data.getStringExtra("filepath");
                Bitmap bitmap = BitmapFactory.decodeFile(filepath);
                if (bitmap != null) {
                    View view = drawerMenu.findViewWithTag("hv");
                    ImageView imageView = (ImageView) view.findViewById(R.id.profile_icon);
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}
