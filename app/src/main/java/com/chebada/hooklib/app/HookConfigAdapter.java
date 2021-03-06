package com.chebada.hooklib.app;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.chebada.hooklib.app.databinding.HookListItemViewBinding;
import com.chebada.hooklib.utils.ConfigUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gj21798 on 2020/09/24.
 */
public class HookConfigAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final PackageManager mPackageManager;
    private List<ApplicationInfo> mList = new ArrayList<>();
    private int mIndex = -1;
    private OnItemSelectedListener mListener;

    public interface OnItemSelectedListener {
        void onItemSelected();
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        mListener = listener;
    }

    public HookConfigAdapter(PackageManager pm) {
        mPackageManager = pm;
    }

    public void setData(@NonNull List<ApplicationInfo> list) {
        mList = list;
        mIndex = -1;
        String packageName = ConfigUtils.getPackageName();
        for (int i = 0; i < list.size(); i++) {
            ApplicationInfo ai = list.get(i);
            if (TextUtils.equals(ai.packageName, packageName)) {
                mIndex = i;
                break;
            }
        }

        if (mListener != null) {
            mListener.onItemSelected();
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
            if (mIndex == position) {
                mIndex = -1;
            } else {
                mIndex = position;
            }
            if (mListener != null) {
                mListener.onItemSelected();
            }
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private static class MainViewHolder extends RecyclerView.ViewHolder {
        private HookListItemViewBinding binding;

        public static RecyclerView.ViewHolder newInstance(ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.hook_list_item_view, parent, false);
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