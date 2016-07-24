package helloandroid.example.com.autpix;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FragmentCarerLogin extends Fragment {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_carer_login, container, false);

        final DatabaseHelper dbHelper = new DatabaseHelper(getActivity());

        Button pclogin = (Button) view.findViewById(R.id.BPCLogin);
        pclogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText a = (EditText) view.findViewById(R.id.TFPCloginUname);
                String strng = a.getText().toString();
                EditText b = (EditText) view.findViewById(R.id.TFPCpass);
                String pass = b.getText().toString();

                String password = dbHelper.searchPcPass(strng);
                if (pass.equals(password)){

                    Intent intent = new Intent(getActivity(), AddNewQuiz.class);
                    intent.putExtra("Username",strng);
                    startActivity(intent);
                }
                else
                {
                    Toast passmsg = Toast.makeText(getActivity(),"Username and passwords do not match!", Toast.LENGTH_SHORT);
                    passmsg.show();
                }
            }
        });
        Button pcsignup = (Button) view.findViewById(R.id.BPCSignup);
        pcsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Intent intent = new Intent(getActivity(), PCSignup.class);
                    startActivity(intent);


            }
        });

        return view;
    }

//    public void onPCSignUpClick(View view){
//
//        if (view.getId() == R.id.BPCLogin){
//
//
//
//
//
//        }
//
//
//
//
//    }



    @Override
    public void onDetach() {
        super.onDetach();

    }

}
