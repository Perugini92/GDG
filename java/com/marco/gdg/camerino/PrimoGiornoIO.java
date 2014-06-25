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
public class PrimoGiornoIO extends Fragment implements View.OnClickListener {

    public PrimoGiornoIO() {

    }
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        //return inflater.inflate(R.layout.fragment_a,container,false);
        View rootView = inflater.inflate(R.layout.fragment_a, container, false);

        TextView bot1=(TextView)rootView.findViewById(R.id.tx1);
        TextView bot2=(TextView)rootView.findViewById(R.id.tx2);
        TextView bot3=(TextView)rootView.findViewById(R.id.tx3);
        TextView bot4=(TextView)rootView.findViewById(R.id.tx4);
        TextView bot5=(TextView)rootView.findViewById(R.id.tx5);
        TextView bot6=(TextView)rootView.findViewById(R.id.tx6);
        TextView bot7=(TextView)rootView.findViewById(R.id.tx7);

        bot1.setOnClickListener(this);
        bot2.setOnClickListener(this);
        bot3.setOnClickListener(this);
        bot4.setOnClickListener(this);
        bot5.setOnClickListener(this);
        bot6.setOnClickListener(this);
        bot7.setOnClickListener(this);

        return rootView;
    }
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.tx1:

                Intent intent1 = new Intent(getActivity(), Aeromobili.class);
                startActivity(intent1);
                break;
            case R.id.tx2:

                Intent intent2 = new Intent(getActivity(), Aeromobili.class);
                startActivity(intent2);
                break;
            case R.id.tx3:

                Intent intent3 = new Intent(getActivity(), FotoGametria.class);
                startActivity(intent3);
                break;
            case R.id.tx4:

                Intent intent4 = new Intent(getActivity(), ExtendedArea.class);
                startActivity(intent4);
                break;
            case R.id.tx5:

                Intent intent5 = new Intent(getActivity(), KeyNote.class);
                startActivity(intent5);
                break;
            case R.id.tx6:

                Intent intent6 = new Intent(getActivity(), TuneLing.class);
                startActivity(intent6);
                break;
            case R.id.tx7:

                Intent intent7 = new Intent(getActivity(), LanParty.class);
                startActivity(intent7);
                break;
        }
    }


}
