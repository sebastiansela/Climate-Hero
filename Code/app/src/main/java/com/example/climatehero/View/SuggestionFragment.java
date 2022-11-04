package com.example.climatehero.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.climatehero.R;
import com.example.climatehero.ViewModel.CloudVisionViewModel;
import com.example.climatehero.ViewModel.DatabaseViewModel;
import com.example.climatehero.databinding.FragmentSuggestionBinding;

import java.util.ArrayList;


public class SuggestionFragment extends Fragment {

    private FragmentSuggestionBinding binding;
    private CloudVisionViewModel viewModel;
    private DatabaseViewModel databaseViewModel;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSuggestionBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CloudVisionViewModel.class);
        databaseViewModel = new ViewModelProvider(requireActivity()).get(DatabaseViewModel.class);

        binding.buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SuggestionFragment.this)
                        .navigate(R.id.action_Suggestion_to_FirstFragment);

            }
        });
        ArrayList<String> items = viewModel.getResult();
        databaseViewModel.queryDb(items);
        binding.suggestionText.setText(databaseViewModel.getSuggestedBin());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}