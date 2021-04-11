package com.tijani.waterrefineryshirzadproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tijani.waterrefineryshirzadproject.adapters.MenueAdapter;
import com.tijani.waterrefineryshirzadproject.models.MenueListItem;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class MainActivity extends AppCompatActivity {
    CardView cardEdary, cardShekayat, cardGozareshat, cardHosnBahrehBardary;
    ListView navigationListView;

    ListView navigationListView2;

    ImageView hambergurMenu;
    TextView txvSign, txvExit,txvDate;
    LinearLayout linearExit;

    MenueAdapter adapter;
    MenueAdapter adapter2;

    ArrayList<MenueListItem> menuesItem;
    ArrayList<MenueListItem> menuesItem2;

    DrawerLayout drawerLayout;
    boolean doubleBackToExitPressedOnce = false;
    public static SharedPreferences preferences;

    public final String passwordPrimitiveLogin = "451371";
    EditText edtPassword;
    CardView cardBtnConfirm, cardBtnCancel;

    Handler handler = new Handler(Looper.getMainLooper());




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        cardEdary = findViewById(R.id.card_mainActivity_edary);
        cardShekayat = findViewById(R.id.card_mainActivity_shekayat);
        cardGozareshat = findViewById(R.id.card_mainActivity_gozareshat);
        cardHosnBahrehBardary = findViewById(R.id.card_mainActivity_hosn_bahreBardary);
        navigationListView = findViewById(R.id.navigationListView);
        navigationListView2 = findViewById(R.id.navigationListView2);
        hambergurMenu = findViewById(R.id.hamberMenue);
        txvSign = findViewById(R.id.txvSign);
        txvExit = findViewById(R.id.txvExit);
        linearExit = findViewById(R.id.linearExit);
        drawerLayout = findViewById(R.id.drawer_layout);
        txvDate = findViewById(R.id.txtDate);

        if (!isOnline()) {
            Toast.makeText(this, "شما به اینترنت وصل نیستید!", Toast.LENGTH_SHORT).show();
        }


        preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
        final String userName = preferences.getString("userName", "");
        if (userName.equals("")) {
            txvSign.setText("ورود/ثبت نام");
        } else {
            txvSign.setText(userName);
        }

       String date = myCalander();
        txvDate.setText(date);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                changeTime();

            }
        }, 0, 1000);


        menuesItem = new ArrayList<>();
        menuesItem.add(new MenueListItem("نامه های اداری ثبت شده", R.drawable.official_laters));
        menuesItem.add(new MenueListItem("تخلفات ثبت شده", R.drawable.shekayat));
        menuesItem.add(new MenueListItem("گزارشات ثبت شده", R.drawable.reports));

        adapter = new MenueAdapter(MainActivity.this, R.layout.custom_item_menue_list_view, menuesItem);
        navigationListView.setAdapter(adapter);

        navigationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        if (!isOnline()) {
                            Toast.makeText(MainActivity.this, "شما به اینترنت وصل نیستید!", Toast.LENGTH_SHORT).show();

                        } else {


                            if (txvSign.getText().toString().equals("ورود/ثبت نام")) {
                                Toast.makeText(MainActivity.this, "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();

                            } else {

                                drawerLayout.closeDrawer(Gravity.RIGHT);

                                Intent intent = new Intent(MainActivity.this, ActivitySplashForGetEdariLaterData.class);
                                startActivity(intent);
/*
                                showAlertDialogInsertPasForEdaryLater();*/
                                break;
                            }
                        }

                    case 1:
                        if (!isOnline()) {
                            Toast.makeText(MainActivity.this, "شما به اینترنت وصل نیستید!", Toast.LENGTH_SHORT).show();

                        } else {


                            if (txvSign.getText().toString().equals("ورود/ثبت نام")) {
                                Toast.makeText(MainActivity.this, "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();

                            } else {
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                                Intent intent = new Intent(MainActivity.this, ActivitySplashForGetShekayatData.class);
                                startActivity(intent);
                              /*  showAlertDialogInsertPasForShekayat();*/
                                break;
                            }
                        }

                    case 2:
                        if (!isOnline()) {
                            Toast.makeText(MainActivity.this, "شما به اینترنت وصل نیستید!", Toast.LENGTH_SHORT).show();

                        } else {


                            if (txvSign.getText().toString().equals("ورود/ثبت نام")) {
                                Toast.makeText(MainActivity.this, "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();

                            } else {
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                                Intent intent = new Intent(MainActivity.this, ActivitySplashForGetGozareshatData.class);
                                startActivity(intent);


                               /* showAlertDialogInsertPasForGozareshat();*/
                                break;
                            }
                        }

                }
            }
        });


        menuesItem2 = new ArrayList<>();
        menuesItem2.add(new MenueListItem("نتایج میانگین پارامترهای آزمایشگاهی", R.drawable.hosn_bahrehbardary));
        menuesItem2.add(new MenueListItem("مقادیر لجن،دانه و آشغال تصفیه خانه", R.drawable.amunt));
        menuesItem2.add(new MenueListItem("گندزدایی پساب تصفیه خانه", R.drawable.gandzodaee));
        menuesItem2.add(new MenueListItem("نگهداری و تعمیرات برق", R.drawable.tamirat_barq));
        menuesItem2.add(new MenueListItem("نگهداری و تعمیرات تجهیزات مکانیکی", R.drawable.tamirat));
        menuesItem2.add(new MenueListItem("حوادث،قطعی برق و تجهیزات متوقف", R.drawable.accident));
        menuesItem2.add(new MenueListItem("بازسازی،نوسازی و رنگ آمیزی", R.drawable.bazsazi));
        menuesItem2.add(new MenueListItem("کارکرد ماهانه بلوئرها و پمپ های ایستگاه پمپاژ", R.drawable.karkard));
        menuesItem2.add(new MenueListItem("درباره ما", R.drawable.about_we));

        adapter2 = new MenueAdapter(MainActivity.this, R.layout.custom_item_menue_list_view, menuesItem2);
        navigationListView2.setAdapter(adapter2);
        navigationListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {

                    case 0:

                        if (!isOnline()) {
                            Toast.makeText(MainActivity.this, "شما به اینترنت وصل نیستید!", Toast.LENGTH_SHORT).show();

                        } else {


                            if (txvSign.getText().toString().equals("ورود/ثبت نام")) {
                                Toast.makeText(MainActivity.this, "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();

                            } else {
                                Intent intent = new Intent(G.context, ActivitySplashForGetAverageResultData.class);
                                startActivity(intent);
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                                break;
                            }
                        }


                    case 1:

                        if (!isOnline()) {
                            Toast.makeText(MainActivity.this, "شما به اینترنت وصل نیستید!", Toast.LENGTH_SHORT).show();

                        } else {


                            if (txvSign.getText().toString().equals("ورود/ثبت نام")) {
                                Toast.makeText(MainActivity.this, "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();

                            } else {
                                Intent intent = new Intent(G.context, ActivitySplashForGetMaqadirLajan.class);
                                startActivity(intent);
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                                break;
                            }
                        }


                    case 2:

                        if (!isOnline()) {
                            Toast.makeText(MainActivity.this, "شما به اینترنت وصل نیستید!", Toast.LENGTH_SHORT).show();

                        } else {


                            if (txvSign.getText().toString().equals("ورود/ثبت نام")) {
                                Toast.makeText(MainActivity.this, "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();

                            } else {
                                Intent intent = new Intent(G.context, ActivitySplashForGetGandZodaye.class);
                                startActivity(intent);
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                                break;
                            }
                        }


                    case 3:

                        if (!isOnline()) {
                            Toast.makeText(MainActivity.this, "شما به اینترنت وصل نیستید!", Toast.LENGTH_SHORT).show();

                        } else {


                            if (txvSign.getText().toString().equals("ورود/ثبت نام")) {
                                Toast.makeText(MainActivity.this, "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();

                            } else {
                                Intent intent = new Intent(G.context, ActivitySplashForGetNegahdariVaTamirBarq.class);
                                startActivity(intent);
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                                break;
                            }
                        }


                    case 4:

                        if (!isOnline()) {
                            Toast.makeText(MainActivity.this, "شما به اینترنت وصل نیستید!", Toast.LENGTH_SHORT).show();

                        } else {


                            if (txvSign.getText().toString().equals("ورود/ثبت نام")) {
                                Toast.makeText(MainActivity.this, "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();

                            } else {
                                Intent intent = new Intent(G.context, ActivitySplashForGetNegahdariVaTamirTajhizatMekaniki.class);
                                startActivity(intent);
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                                break;
                            }
                        }

                    case 5:

                        if (!isOnline()) {
                            Toast.makeText(MainActivity.this, "شما به اینترنت وصل نیستید!", Toast.LENGTH_SHORT).show();

                        } else {


                            if (txvSign.getText().toString().equals("ورود/ثبت نام")) {
                                Toast.makeText(MainActivity.this, "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();

                            } else {
                                Intent intent = new Intent(G.context, ActivitySplashForGetDataTabsHavadesVaQateeBarq.class);
                                startActivity(intent);
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                                break;
                            }
                        }


                    case 6:

                        if (!isOnline()) {
                            Toast.makeText(MainActivity.this, "شما به اینترنت وصل نیستید!", Toast.LENGTH_SHORT).show();

                        } else {


                            if (txvSign.getText().toString().equals("ورود/ثبت نام")) {
                                Toast.makeText(MainActivity.this, "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();

                            } else {
                                Intent intent = new Intent(G.context, ActivitySplashForGetDataTabsRangAmiziVaBazsaziVaNosazi.class);
                                startActivity(intent);
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                                break;
                            }
                        }


                    case 7:

                        if (!isOnline()) {
                            Toast.makeText(MainActivity.this, "شما به اینترنت وصل نیستید!", Toast.LENGTH_SHORT).show();

                        } else {


                            if (txvSign.getText().toString().equals("ورود/ثبت نام")) {
                                Toast.makeText(MainActivity.this, "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();

                            } else {
                                Intent intent = new Intent(G.context, ActivitySplashForGetDataTabsKarkardBoloerVaPomp.class);
                                startActivity(intent);
                                drawerLayout.closeDrawer(Gravity.RIGHT);
                                break;
                            }
                        }




                    case 8:

                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        Intent intent = new Intent(MainActivity.this, ActivityAboutWe.class);
                        startActivity(intent);

                        break;


                }

            }
        });


        cardEdary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOnline()) {
                    Toast.makeText(MainActivity.this, "شما به اینترنت وصل نیستید!", Toast.LENGTH_SHORT).show();

                } else {


                    if (txvSign.getText().toString().equals("ورود/ثبت نام")) {
                        drawerLayout.openDrawer(Gravity.RIGHT);
                        Toast.makeText(MainActivity.this, "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();

                    } else {

                        Intent intent = new Intent(MainActivity.this, ActivityEdaryLater.class);
                        startActivity(intent);

                       /* showAlertDialogInsertPasForActivityEdaryLater();*/
                    }
                }
            }
        });

        cardShekayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOnline()) {
                    Toast.makeText(MainActivity.this, "شما به اینترنت وصل نیستید!", Toast.LENGTH_SHORT).show();

                } else {


                    if (txvSign.getText().toString().equals("ورود/ثبت نام")) {
                        drawerLayout.openDrawer(Gravity.RIGHT);
                        Toast.makeText(MainActivity.this, "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();

                    } else {
                        Intent intent = new Intent(MainActivity.this, ActivityShekayat.class);
                        startActivity(intent);

                        /* showAlertDialogInsertPasForActivityShekayat();*/
                    }
                }
            }
        });

        cardGozareshat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOnline()) {
                    Toast.makeText(MainActivity.this, "شما به اینترنت وصل نیستید!", Toast.LENGTH_SHORT).show();

                } else {


                    if (txvSign.getText().toString().equals("ورود/ثبت نام")) {
                        drawerLayout.openDrawer(Gravity.RIGHT);
                        Toast.makeText(MainActivity.this, "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();

                    } else {
                        Intent intent = new Intent(MainActivity.this, ActivityGozareshat.class);
                        startActivity(intent);

                        /*showAlertDialogInsertPasForActivityGozareshat();*/
                    }
                }
            }

        });


        cardHosnBahrehBardary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isOnline()) {
                    Toast.makeText(MainActivity.this, "شما به اینترنت وصل نیستید!", Toast.LENGTH_SHORT).show();

                } else {


                    if (txvSign.getText().toString().equals("ورود/ثبت نام")) {
                        drawerLayout.openDrawer(Gravity.RIGHT);
                        Toast.makeText(MainActivity.this, "ابتدا به حساب کاربری خود وارد شوید!", Toast.LENGTH_SHORT).show();

                    } else {
                        Intent intent = new Intent(G.context, ActivityHosnBahrehBardary.class);
                        startActivity(intent);
                    }
                }

            }
        });

        hambergurMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
                String userNameShHamber = preferences.getString("userName", "");
                String registerUserDon = preferences.getString("do", "");

                if (userNameShHamber.equals("")) {

                    if (registerUserDon.equals("")) {
                        txvSign.setText("ورود/ثبت نام");

                    } else {
                        txvSign.setText(registerUserDon);
                    }
                } else {
                    txvSign.setText(userNameShHamber);
                }

                linearExit.setVisibility(View.GONE);
                drawerLayout.openDrawer(Gravity.RIGHT);

            }
        });


        txvSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txvSign.getText().toString().equals("ورود/ثبت نام")) {
                    Intent intent = new Intent(G.context, ActivityUserSignIn.class);
                    startActivityForResult(intent, 0);
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                } else {

                    if (linearExit.getVisibility() == View.VISIBLE) {

                        linearExit.setVisibility(View.GONE);


                    } else {
                        linearExit.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        txvExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("userName", "");
                editor.putString("do", "");
                editor.commit();
                drawerLayout.closeDrawer(Gravity.RIGHT);

                //// new code for work section exit and work click months////////

                preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
                String userName2 = preferences.getString("userName", "");
                String registerUserDon2 = preferences.getString("do", "");

                if (userName2.equals("")) {
                    if (registerUserDon2.equals("")) {
                        txvSign.setText("ورود/ثبت نام");

                    } else {
                        txvSign.setText(registerUserDon2);
                    }

                } else {
                    txvSign.setText(userName2);

                }
                linearExit.setVisibility(View.GONE);

                drawerLayout.openDrawer(Gravity.RIGHT);

                //////////////////////////

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String userNameSh = bundle.getString("userName");
            txvSign.setText(userNameSh);

            preferences = PreferenceManager.getDefaultSharedPreferences(G.context);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("userName", userNameSh);
            editor.commit();
        }


    }

    private boolean isOnline() {

        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(G.context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }

    }

    ////// Override Method Exit app with double click Back  /////
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "لطفا کلید بازگشت را مجددا فشار دهید", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    ///////


    private void showAlertDialogInsertPasForEdaryLater() {


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(R.layout.custom_layout_dialog_insert_password);
        final AlertDialog dialog = builder.create();
        dialog.show();

        edtPassword = dialog.findViewById(R.id.edt_custom_dialog_Password_go_controlPanel);
        cardBtnConfirm = dialog.findViewById(R.id.cardDialog_custom_dialog_go_controlPanel_btn_confirm);
        cardBtnCancel = dialog.findViewById(R.id.cardDialog_custom_dialog_go_controlPanel_btn_cancel);

        cardBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = edtPassword.getText().toString();

                if (pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "فیلد مربوطه خالی است", Toast.LENGTH_SHORT).show();
                } else {

                    if (pass.equals(passwordPrimitiveLogin)) {
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        dialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, ActivitySplashForGetEdariLaterData.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "رمز اشتباه است!", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });

        cardBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
    }

    private void showAlertDialogInsertPasForShekayat() {


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(R.layout.custom_layout_dialog_insert_password);
        final AlertDialog dialog = builder.create();
        dialog.show();

        edtPassword = dialog.findViewById(R.id.edt_custom_dialog_Password_go_controlPanel);
        cardBtnConfirm = dialog.findViewById(R.id.cardDialog_custom_dialog_go_controlPanel_btn_confirm);
        cardBtnCancel = dialog.findViewById(R.id.cardDialog_custom_dialog_go_controlPanel_btn_cancel);

        cardBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = edtPassword.getText().toString();

                if (pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "فیلد مربوطه خالی است", Toast.LENGTH_SHORT).show();
                } else {

                    if (pass.equals(passwordPrimitiveLogin)) {
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        dialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, ActivitySplashForGetShekayatData.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "رمز اشتباه است!", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });

        cardBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
    }

    private void showAlertDialogInsertPasForGozareshat() {


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(R.layout.custom_layout_dialog_insert_password);
        final AlertDialog dialog = builder.create();
        dialog.show();

        edtPassword = dialog.findViewById(R.id.edt_custom_dialog_Password_go_controlPanel);
        cardBtnConfirm = dialog.findViewById(R.id.cardDialog_custom_dialog_go_controlPanel_btn_confirm);
        cardBtnCancel = dialog.findViewById(R.id.cardDialog_custom_dialog_go_controlPanel_btn_cancel);

        cardBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = edtPassword.getText().toString();

                if (pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "فیلد مربوطه خالی است", Toast.LENGTH_SHORT).show();
                } else {

                    if (pass.equals(passwordPrimitiveLogin)) {
                        drawerLayout.closeDrawer(Gravity.RIGHT);
                        dialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, ActivitySplashForGetGozareshatData.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "رمز اشتباه است!", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });

        cardBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
    }

    private void showAlertDialogInsertPasForActivityEdaryLater() {


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(R.layout.custom_layout_dialog_insert_password);
        final AlertDialog dialog = builder.create();
        dialog.show();

        edtPassword = dialog.findViewById(R.id.edt_custom_dialog_Password_go_controlPanel);
        cardBtnConfirm = dialog.findViewById(R.id.cardDialog_custom_dialog_go_controlPanel_btn_confirm);
        cardBtnCancel = dialog.findViewById(R.id.cardDialog_custom_dialog_go_controlPanel_btn_cancel);

        cardBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = edtPassword.getText().toString();

                if (pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "فیلد مربوطه خالی است", Toast.LENGTH_SHORT).show();
                } else {

                    if (pass.equals(passwordPrimitiveLogin)) {
                        dialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, ActivityEdaryLater.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "رمز اشتباه است!", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });

        cardBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
    }

    private void showAlertDialogInsertPasForActivityShekayat() {


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(R.layout.custom_layout_dialog_insert_password);
        final AlertDialog dialog = builder.create();
        dialog.show();

        edtPassword = dialog.findViewById(R.id.edt_custom_dialog_Password_go_controlPanel);
        cardBtnConfirm = dialog.findViewById(R.id.cardDialog_custom_dialog_go_controlPanel_btn_confirm);
        cardBtnCancel = dialog.findViewById(R.id.cardDialog_custom_dialog_go_controlPanel_btn_cancel);

        cardBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = edtPassword.getText().toString();

                if (pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "فیلد مربوطه خالی است", Toast.LENGTH_SHORT).show();
                } else {

                    if (pass.equals(passwordPrimitiveLogin)) {
                        dialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, ActivityShekayat.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "رمز اشتباه است!", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });

        cardBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
    }

    private void showAlertDialogInsertPasForActivityGozareshat() {


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(R.layout.custom_layout_dialog_insert_password);
        final AlertDialog dialog = builder.create();
        dialog.show();

        edtPassword = dialog.findViewById(R.id.edt_custom_dialog_Password_go_controlPanel);
        cardBtnConfirm = dialog.findViewById(R.id.cardDialog_custom_dialog_go_controlPanel_btn_confirm);
        cardBtnCancel = dialog.findViewById(R.id.cardDialog_custom_dialog_go_controlPanel_btn_cancel);

        cardBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = edtPassword.getText().toString();

                if (pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "فیلد مربوطه خالی است", Toast.LENGTH_SHORT).show();
                } else {

                    if (pass.equals(passwordPrimitiveLogin)) {
                        dialog.dismiss();
                        Intent intent = new Intent(MainActivity.this, ActivityGozareshat.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "رمز اشتباه است!", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });

        cardBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
    }

    private String myCalander(){
        PersianDate persianDate = new PersianDate();

                PersianDateFormat persianDateFormat = new PersianDateFormat("l j F Y \n H:i:s");

      /*  PersianDateFormat persianDateFormat = new PersianDateFormat("l j F Y ");*/

        String date = persianDateFormat.format(persianDate);

        return date;


    }

    private String number2persian(String text) {
        text = text.replaceAll("0", "۰");
        text = text.replaceAll("1", "۱");
        text = text.replaceAll("2", "۲");
        text = text.replaceAll("3", "۳");
        text = text.replaceAll("4", "۴");
        text = text.replaceAll("5", "۵");
        text = text.replaceAll("6", "۶");
        text = text.replaceAll("7", "۷");
        text = text.replaceAll("8", "۸");
        text = text.replaceAll("9", "۹");
        return text;
    }


    private void changeTime() {
        handler.post(new Runnable() {
            @Override
            public void run() {

                txvDate.setText(number2persian(new PersianDateFormat("l j F Y \n H:i:s").format(new PersianDate())));

            }
        });
    }



}