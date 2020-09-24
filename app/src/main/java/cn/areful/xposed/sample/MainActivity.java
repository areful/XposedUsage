package cn.areful.xposed.sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import cn.areful.xposed.sample.databinding.ActivityMainBinding;
import cn.areful.xposed.utils.AppUtils;
import cn.areful.xposed.utils.ConfigUtils;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private MainAdapter mAdapter;

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            PackageManager pm = getPackageManager();
            List<ApplicationInfo> list = AppUtils.getAppList(pm);
            mAdapter.setData(list);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initHeader(false);

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> list = AppUtils.getAppList(pm);
        mAdapter = new MainAdapter(pm);
        mAdapter.setOnItemSelectedListener(() -> {
            //
            mBinding.btn.setEnabled(mAdapter.getSelectedApplicationInfo() != null);
        });
        mBinding.recyclerView.setAdapter(mAdapter);
        mAdapter.setData(list);

        mBinding.btn.setVisibility(list.size() > 0 ? View.VISIBLE : View.GONE);
        mBinding.btn.setOnClickListener(v -> saveConfig(mAdapter.getSelectedApplicationInfo()));
        mBinding.btn.setEnabled(mAdapter.getSelectedApplicationInfo() != null);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_INSTALL);
        intentFilter.addAction(Intent.ACTION_UNINSTALL_PACKAGE);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addDataScheme("package");
        registerReceiver(mReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private void initHeader(boolean needReboot) {
        String packageName = ConfigUtils.getPackageName();
        if (!TextUtils.isEmpty(packageName)) {
            StringBuilder sb = new StringBuilder();
            sb.append("current package name: ").append(packageName);
            if (needReboot) {
                sb.append("\ntake effect after reboot.");
            }
            mBinding.currentPackageText.setText(sb.toString());
            mBinding.currentPackageText.setVisibility(View.VISIBLE);
        } else {
            mBinding.currentPackageText.setVisibility(View.GONE);
        }
    }

    private void saveConfig(ApplicationInfo ai) {
        if (ai == null) {
            Toast.makeText(getApplicationContext(), "Get app info failed", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!ConfigUtils.setPackageName(ai.packageName)) {
            Toast.makeText(getApplicationContext(), "Save config failed", Toast.LENGTH_SHORT).show();
            return;
        }

        String toastText = String.format("package name has changed to %s，  take effect after reboot……", ai.packageName);
        Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();

        initHeader(true);
    }
}