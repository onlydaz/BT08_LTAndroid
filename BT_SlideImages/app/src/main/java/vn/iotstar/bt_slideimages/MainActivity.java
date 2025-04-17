package vn.iotstar.bt_slideimages;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;
import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    private List<Images> imagesList1;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager2 == null || imagesList1 == null) return;
            if (viewPager2.getCurrentItem() == imagesList1.size() - 1) {
                viewPager2.setCurrentItem(0);
            } else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // Ánh xạ viewpager2 và circleIndicator3
        viewPager2 = findViewById(R.id.viewpage2);
        circleIndicator3 = findViewById(R.id.circle_indicator3);

        // Kiểm tra xem viewPager2 có được ánh xạ thành công không
        if (viewPager2 == null) {
            Log.e("MainActivity", "ViewPager2 is null. Check if the ID 'viewpage2' exists in activity_main.xml");
            return; // Thoát nếu viewPager2 là null
        }

        if (circleIndicator3 == null) {
            Log.e("MainActivity", "CircleIndicator3 is null. Check if the ID 'circle_indicator3' exists in activity_main.xml");
            return; // Thoát nếu circleIndicator3 là null
        }

        // Khởi tạo imagesList1 để tránh lỗi NullPointerException
        imagesList1 = new ArrayList<>();

        // Gọi API để lấy danh sách hình ảnh
        fetchImagesFromApi();

        // Lắng nghe sự kiện chuyển trang và tự động chạy
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });

        // Thiết lập hiệu ứng chuyển trang
        viewPager2.setPageTransformer(new DepthPageTransformer());
    }

    private void fetchImagesFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://app.iotstar.vn/appfoods/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);
        Call<MessageModel> call = apiService.loadImageSlider(1); // Vị trí (position) = 1

        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    imagesList1 = response.body().getResult();
                    ImagesViewPager2Adapter adapter1 = new ImagesViewPager2Adapter(imagesList1);
                    viewPager2.setAdapter(adapter1);
                    circleIndicator3.setViewPager(viewPager2);
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage());
                // Nếu lỗi, sử dụng danh sách cục bộ
                imagesList1 = getListImages();
                ImagesViewPager2Adapter adapter1 = new ImagesViewPager2Adapter(imagesList1);
                viewPager2.setAdapter(adapter1);
                circleIndicator3.setViewPager(viewPager2);
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

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }
}