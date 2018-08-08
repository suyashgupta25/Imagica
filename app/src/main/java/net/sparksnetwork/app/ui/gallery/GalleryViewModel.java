package net.sparksnetwork.app.ui.gallery;

import net.sparksnetwork.app.data.DataManager;
import net.sparksnetwork.app.ui.base.BaseViewModel;
import net.sparksnetwork.app.utils.rx.SchedulerProvider;

public class GalleryViewModel extends BaseViewModel<GalleryNavigator> {

    public GalleryViewModel(SchedulerProvider mSchedulerProvider, DataManager mDataManager) {
        super(mSchedulerProvider, mDataManager);
    }
}
