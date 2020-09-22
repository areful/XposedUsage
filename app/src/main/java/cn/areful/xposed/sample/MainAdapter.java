package cn.areful.xposed.sample;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.areful.xposed.sample.databinding.MainListItemViewBinding;
import cn.areful.xposed.sample.hooks.HookHelper;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final PackageManager mPackageManager;
    private List<ApplicationInfo> mList = new ArrayList<>();
    private int mIndex = -1;

    public MainAdapter(PackageManager pm) {
        mPackageManager = pm;
    }

    public void setData(@NonNull List<ApplicationInfo> list) {
        mList = list;
        for (int i = 0; i < list.size(); i++) {
            ApplicationInfo ai = list.get(i);
            if (TextUtils.equals(ai.packageName, HookHelper.PACKAGE_NAME)) {
                mIndex = i;
                break;
            }
        }
        notifyDataSetChanged();
    }

    public ApplicationInfo getSelectedApplicationInfo() {
        if (mIndex == -1 || mIndex >= mList.size()) {
            return null;
        }
        return mList.get(mIndex);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return MainViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MainViewHolder holder = (MainViewHolder) viewHolder;
        holder.setData(mPackageManager, mList.get(position), mIndex == position);
        holder.itemView.setOnClickListener(v -> {
            mIndex = position;
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private static class MainViewHolder extends RecyclerView.ViewHolder {
        private MainListItemViewBinding binding;

        public static RecyclerView.ViewHolder newInstance(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.main_list_item_view, parent, false);
            return new MainViewHolder(view);
        }

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }

        public void setData(@NonNull PackageManager pm, @NonNull ApplicationInfo packageInfo, boolean isSelected) {
            itemView.setSelected(isSelected);

            binding.appNameText.setText(pm.getApplicationLabel(packageInfo));
            binding.appDescText.setText(packageInfo.packageName);
        }
    }
}