package vn.iotstar.slideimages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ImagesViewPagerAdapter extends PagerAdapter {
    private final List<Images> imagesList;

    public ImagesViewPagerAdapter(List<Images> imagesList) {
        this.imagesList = imagesList;
    }

    @Override
    public int getCount() {
        return imagesList != null ? imagesList.size() : 0;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.item_images, container, false);

        ImageView imageView = view.findViewById(R.id.imgView);
        Images image = imagesList.get(position);
        imageView.setImageResource(image.getImagesId());

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
