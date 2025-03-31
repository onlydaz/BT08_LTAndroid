package vn.iotstar.bt_slideimages;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;
import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    private List<MediaStore.Images> imagesList1;
    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ ViewPager2 và Indicator
        viewPager2 = findViewById(R.id.viewpage2);
        circleIndicator3 = findViewById(R.id.circle_indicator3);

        // Lấy danh sách hình ảnh
        imagesList1 = getListImages();

        // Thiết lập Adapter
        ImagesViewPager2Adapter adapter1 = new ImagesViewPager2Adapter(this, imagesList1);
        viewPager2.setAdapter(adapter1);

        // Liên kết ViewPager2 với Indicator
        circleIndicator3.setViewPager(viewPager2);

        // Tự động chạy ViewPager2
        autoRunViewPager();

        // Hiệu ứng chuyển đổi trang
        viewPager2.setPageTransformer(new DepthPageTransformer());

        // Lắng nghe sự kiện chuyển trang
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });
    }

    private List<Images> getListImages() {
        List<Images> list = new ArrayList<>();
        list.add(new Images(R.drawable.quangcao));
        list.add(new Images(R.drawable.coffee));
        list.add(new Images(R.drawable.companypizza));
        list.add(new Images(R.drawable.themoingon));
        return list;
    }

    private void autoRunViewPager() {
        runnable = new Runnable() {
            @Override
            public void run() {
                if (viewPager2.getCurrentItem() == imagesList1.size() - 1) {
                    viewPager2.setCurrentItem(0);
                } else {
                    viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                }
                handler.postDelayed(this, 3000);
            }
        };
        handler.postDelayed(runnable, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
