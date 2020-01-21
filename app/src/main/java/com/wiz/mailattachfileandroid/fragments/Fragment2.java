package com.wiz.mailattachfileandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wiz.mailattachfileandroid.MainActivity;
import com.wiz.mailattachfileandroid.R;
import com.wiz.mailattachfileandroid.activities.SecondActivity;

public class Fragment2 extends Fragment {

    private Button btnPageUpload;
    private Button btnFrag1;
    private Button btnFrag2;
    private Button btnSecondActivity;

@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment1_layout,container,false);

    btnFrag1 =(Button)view.findViewById(R.id.btnFragment1);
    btnFrag1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Going to Fragment 1", Toast.LENGTH_SHORT).show();
            ((MainActivity)getActivity()).setViewpager(0);
        }
    });


    btnPageUpload = (Button)view.findViewById(R.id.btnUploadPage);
    btnPageUpload.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Navigate to Fragment method called
            Toast.makeText(getActivity(), "Clicked to proceed to Upload file Fragment", Toast.LENGTH_SHORT).show();

            ((MainActivity)getActivity()).setViewpager(1);

        }
    });

    btnFrag2 =(Button)view.findViewById(R.id.btnFragment2);
    btnFrag2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Going to Fragment 2", Toast.LENGTH_SHORT).show();
            ((MainActivity)getActivity()).setViewpager(2);
        }
    });

    btnSecondActivity =(Button)view.findViewById(R.id.btnSecondActivity);
    btnSecondActivity.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), "Going to Activity", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), SecondActivity.class);
            startActivity(intent);
        }
    });


    return view;
}
}
