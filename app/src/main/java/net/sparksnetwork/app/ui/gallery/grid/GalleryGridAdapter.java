package net.sparksnetwork.app.ui.gallery.grid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.sparksnetwork.app.databinding.ItemGalleryGridBinding;
import net.sparksnetwork.app.ui.base.BaseViewHolder;

import java.io.File;
import java.util.List;

public class GalleryGridAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final String TAG = GalleryGridAdapter.class.getSimpleName();

    private List<File> mFiles;

    public GalleryGridAdapter(List<File> files) {
        this.mFiles = files;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGalleryGridBinding itemGalleryGridBinding = ItemGalleryGridBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new PicViewHolder(itemGalleryGridBinding);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public void addItemAndRefresh(File file) {
        mFiles.add(file);
        int position = mFiles.indexOf(file);
        notifyItemInserted(position);
    }

    public void addAllItemsAndRefresh(List<File> files) {
        mFiles.addAll(files);
        notifyDataSetChanged();
    }

    public class PicViewHolder extends BaseViewHolder {
        private ItemGalleryGridBinding mBinding;
        private GalleryGridItemViewModel mGalleryGridItemViewModel;

        public PicViewHolder(ItemGalleryGridBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            mGalleryGridItemViewModel = new GalleryGridItemViewModel(mFiles.get(position));
            mBinding.setViewModel(mGalleryGridItemViewModel);
            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            //mBinding.executePendingBindings();
        }
    }
}
