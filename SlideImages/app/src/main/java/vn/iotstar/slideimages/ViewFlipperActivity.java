package vn.iotstar.slideimages;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class ViewFlipperActivity extends AppCompatActivity {
    private ViewFlipper viewFlipperMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);

        viewFlipperMain = findViewById(R.id.viewFlipperMain);
        setupViewFlipper();
    }

    private void setupViewFlipper() {
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("http://app.iotstar.vn/appfoods/flipper/quangcao.png");
        imageUrls.add("http://app.iotstar.vn/appfoods/flipper/coffee.jpg");
        imageUrls.add("http://app.iotstar.vn/appfoods/flipper/companypizza.jpeg");
        imageUrls.add("http://app.iotstar.vn/appfoods/flipper/themoingon.jpeg");

        for (String url : imageUrls) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(this).load(url).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipperMain.addView(imageView);
        }

        viewFlipperMain.setFlipInterval(3000);
        viewFlipperMain.setAutoStart(true);

        // Thiết lập animation cho ViewFlipper
        Animation slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        Animation slideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);

        viewFlipperMain.setInAnimation(slideIn);
        viewFlipperMain.setOutAnimation(slideOut);
    }
}

