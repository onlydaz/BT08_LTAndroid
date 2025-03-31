package vn.iotstar.viewpager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.iotstar.viewpager2.databinding.FragmentNeworderBinding;


public class NewOrderFragment extends Fragment {
    private FragmentNeworderBinding binding;

    public NewOrderFragment() {
        // Constructor mặc định
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate layout bằng ViewBinding
        binding = FragmentNeworderBinding.inflate(inflater, container, false);

        // Xử lý RecyclerView (cần thiết lập Adapter nếu có)
        // binding.recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

