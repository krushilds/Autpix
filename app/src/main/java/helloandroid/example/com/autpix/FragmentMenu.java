package helloandroid.example.com.autpix;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentMenu extends Fragment implements View.OnClickListener {

    Button activity1, activity2, activity3, activity4, activity5, activity6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        activity1 = (Button) view.findViewById(R.id.button5);
        activity1.setOnClickListener(this);

        activity2 = (Button) view.findViewById(R.id.button6);
        activity2.setOnClickListener(this);

        activity3 = (Button) view.findViewById(R.id.button7);
        activity3.setOnClickListener(this);

        activity4 = (Button) view.findViewById(R.id.button8);
        activity4.setOnClickListener(this);

        activity5 = (Button) view.findViewById(R.id.button9);
        activity5.setOnClickListener(this);

        activity6 = (Button) view.findViewById(R.id.button10);
        activity6.setOnClickListener(this);

        return view;

    }





    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button5:
            FragmentManager fm1 = getFragmentManager();
            FragmentTransaction ft1 = fm1.beginTransaction();

            FragmentAboutMe fragmentAboutMe = new FragmentAboutMe();

            ft1.replace(R.id.fragment_container, fragmentAboutMe);
            ft1.commit();
            break;

            case R.id.button6:
                FragmentManager fm2 = getFragmentManager();
                FragmentTransaction ft2 = fm2.beginTransaction();

                FragmentMorning fragmentMorning = new FragmentMorning();

                ft2.replace(R.id.fragment_container, fragmentMorning);
                ft2.commit();
                break;

            case R.id.button7:
                FragmentManager fm3 = getFragmentManager();
                FragmentTransaction ft3 = fm3.beginTransaction();

                FragmentAfternoon fragmentAfternoon = new FragmentAfternoon();

                ft3.replace(R.id.fragment_container, fragmentAfternoon);
                ft3.commit();
                break;

            case R.id.button8:
                FragmentManager fm4 = getFragmentManager();
                FragmentTransaction ft4 = fm4.beginTransaction();

                FragmentEvening fragmentEvening = new FragmentEvening();

                ft4.replace(R.id.fragment_container, fragmentEvening);
                ft4.commit();
                break;

            case R.id.button9:
                FragmentManager fm5 = getFragmentManager();
                FragmentTransaction ft5 = fm5.beginTransaction();

                FragmentHome fragmentHome = new FragmentHome();

                ft5.replace(R.id.fragment_container, fragmentHome);
                ft5.commit();
                break;

            case R.id.button10:
                FragmentManager fm6 = getFragmentManager();
                FragmentTransaction ft6 = fm6.beginTransaction();

                FragmentPlayGround fragmentPlayGround = new FragmentPlayGround();

                ft6.replace(R.id.fragment_container, fragmentPlayGround);
                ft6.commit();
                break;
        }






    }





    @Override
    public void onDetach() {
        super.onDetach();

    }


}
