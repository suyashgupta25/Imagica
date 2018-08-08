package net.sparksnetwork.app.utils.ui.glide;

import android.content.Context;

import com.bumptech.glide.Glide;

import net.sparksnetwork.app.R;

public class GlideUtils {

    public static void initConfig(Context context) {
        GlideBindingConfig.registerProvider(context.getString(R.string.glide_config1), (iv, request) ->
                request
                        .placeholder(R.drawable.image_placeholder)
                        .error(R.drawable.image_placeholder)
                        .crossFade()
        );
        GlideBindingConfig.setDefault(context.getString(R.string.glide_config1));
    }
}
