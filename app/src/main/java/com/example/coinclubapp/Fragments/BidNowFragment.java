package com.example.coinclubapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coinclubapp.R;
import com.example.coinclubapp.databinding.FragmentBidNowBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class BidNowFragment extends BottomSheetDialogFragment {
FragmentBidNowBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentBidNowBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}