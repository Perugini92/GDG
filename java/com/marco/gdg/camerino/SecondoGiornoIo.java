package com.marco.gdg.camerino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Marco on 10/05/14.
 */
public class SecondoGiornoIo extends Fragment implements View.OnClickListener {
    public SecondoGiornoIo() {

    }
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_b, container, false);

        TextView bot1=(TextView)rootView.findViewById(R.id.tex1);
        bot1.setOnClickListener(this);
        TextView bot2=(TextView)rootView.findViewById(R.id.tx1);
        bot2.setOnClickListener(this);
        TextView bot3=(TextView)rootView.findViewById(R.id.tx7);
        bot3.setOnClickListener(this);
        TextView bot4=(TextView)rootView.findViewById(R.id.textView3);
        bot4.setOnClickListener(this);
        return rootView;
    }
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.tex1:

                Intent intent1 = new Intent(getActivity(), Board.class);
                startActivity(intent1);
                break;
           case R.id.tx1:

                Intent intent2 = new Intent(getActivity(), Board.class);
                startActivity(intent2);
                break;
            case R.id.tx7:

                Intent intent7 = new Intent(getActivity(), Board.class);
                startActivity(intent7);
                break;
            case R.id.textView3:

                Intent intent3 = new Intent(getActivity(), Board.class);
                startActivity(intent3);
                break;

        }
    }
}
