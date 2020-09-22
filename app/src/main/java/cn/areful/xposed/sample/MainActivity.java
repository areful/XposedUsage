package cn.areful.xposed.sample;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import cn.areful.xposed.sample.hooks.HookHelper;
import cn.areful.xposed.sample.hooks.HookPreferences;
import cn.areful.xposed.utils.AppUtils;

public class MainActivity extends AppCompatActivity {
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HookHelper.PACKAGE_NAME = HookPreferences.getPackageName(this);
        Log.d(HookHelper.TAG, Log.getStackTraceString(new Throwable()));
        TextView currentPackageText = findViewById(R.id.currentPackageText);
        if (!TextUtils.isEmpty(HookHelper.PACKAGE_NAME)) {
            currentPackageText.setText(String.format("current package name: %s", HookHelper.PACKAGE_NAME));
            currentPackageText.setVisibility(View.VISIBLE);
        } else {
            currentPackageText.setVisibility(View.GONE);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PackageManager pm = getPackageManager();
        recyclerView.setAdapter(mAdapter = new MainAdapter(pm));
        mAdapter.setData(AppUtils.getAppList(pm));

        findViewById(R.id.btn).setOnClickListener(v -> {
            ApplicationInfo ai = mAdapter.getSelectedApplicationInfo();
            if (ai != null) {
                HookPreferences.setPackageName(v.getContext(), ai.packageName);

                String toastText = String.format("package name has changed to %s， " +
                        "take effect after reboot……", ai.packageName);
                Toast.makeText(v.getContext().getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
            }
        });
    }
}