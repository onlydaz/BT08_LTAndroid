package vn.iotstar.bt_slideimages;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;

public class DepthPageTransformer implements ViewPager2.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // Page nằm ngoài màn hình bên trái
            view.setAlpha(0f);
        } else if (position <= 0) { // Di chuyển sang trang bên trái
            view.setAlpha(1f);
            view.setTranslationX(0f);
            view.setScaleX(1f);
            view.setScaleY(1f);
        } else if (position <= 1) { // Di chuyển sang trang bên phải
            view.setAlpha(1 - position);
            view.setTranslationX(pageWidth * -position);
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        } else { // Page nằm ngoài màn hình bên phải
            view.setAlpha(0f);
        }
    }
}
