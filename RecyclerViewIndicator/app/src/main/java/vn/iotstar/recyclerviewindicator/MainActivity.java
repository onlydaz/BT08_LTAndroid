package vn.iotstar.recyclerviewindicator;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcIcon;
    private SearchView searchView;
    private IconAdapter iconAdapter;
    private List<IconModel> arrayList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ view
        rcIcon = findViewById(R.id.rcIcon);
        searchView = findViewById(R.id.searchView);

        // Khởi tạo danh sách icon
        arrayList1 = new ArrayList<>();
        arrayList1.add(new IconModel(R.drawable.icon1, "Item 1"));
        arrayList1.add(new IconModel(R.drawable.icon2, "Item 2"));
        arrayList1.add(new IconModel(R.drawable.icon3, "Item 3"));
        arrayList1.add(new IconModel(R.drawable.icon4, "Item 4"));
        arrayList1.add(new IconModel(R.drawable.icon5, "Item 5"));
        arrayList1.add(new IconModel(R.drawable.icon6, "Item 6"));
        arrayList1.add(new IconModel(R.drawable.icon7, "Item 7"));
        arrayList1.add(new IconModel(R.drawable.icon8, "Item 8"));
        arrayList1.add(new IconModel(R.drawable.icon9, "Item 9"));

        // Thiết lập LayoutManager cho RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcIcon.setLayoutManager(linearLayoutManager);

        // Gán Adapter
        iconAdapter = new IconAdapter(this, arrayList1);
        rcIcon.setAdapter(iconAdapter);

        // Xử lý sự kiện tìm kiếm
        setupSearchView();
    }

    private void setupSearchView() {
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterListener(newText);
                return true;
            }
        });
    }

    private void filterListener(String text) {
        List<IconModel> filteredList = new ArrayList<>();
        for (IconModel iconModel : arrayList1) {
            if (iconModel.getDesc().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(iconModel);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            iconAdapter.setListenerList(filteredList);
        }
    }
}