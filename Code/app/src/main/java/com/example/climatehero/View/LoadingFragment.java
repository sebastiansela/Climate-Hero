package com.example.climatehero.View;

import androidx.lifecycle.ViewModelProvider;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.climatehero.MainActivity;
import com.example.climatehero.ViewModel.CloudVisionViewModel;
import com.example.climatehero.R;
import com.example.climatehero.ViewModel.DatabaseViewModel;
import com.example.climatehero.databinding.FragmentLoadingBinding;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoadingFragment extends Fragment{

    private FragmentLoadingBinding binding;
    private CloudVisionViewModel cloudVisionViewModel;
    private DatabaseViewModel databaseViewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLoadingBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cloudVisionViewModel = new ViewModelProvider(requireActivity()).get(CloudVisionViewModel.class);
        databaseViewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);
        final Executor executor = Executors.newSingleThreadExecutor();
        final Handler handler = new Handler(Looper.getMainLooper());

        binding.factText.setText(databaseViewModel.getFact());
        binding.continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LoadingFragment.this)
                        .navigate(R.id.action_loading_to_suggestion);
            }
        });

        executor.execute(() -> {
            cloudVisionViewModel.recognizeImage();
                handler.post(() -> {
                            binding.gifImageView.setVisibility(View.GONE);
                            binding.continueButton.setVisibility(View.VISIBLE);
                            binding.continueButton.setClickable(true);
                        });
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}