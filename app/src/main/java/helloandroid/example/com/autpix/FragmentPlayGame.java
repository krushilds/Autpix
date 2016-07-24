package helloandroid.example.com.autpix;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


// this is actully kids log in page
public class FragmentPlayGame extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_play_game, container, false);

        final DatabaseHelper dbHelper = new DatabaseHelper(getActivity());

        Button login = (Button) view.findViewById(R.id.Blogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("fsnidjnfsidnf");


                    EditText a = (EditText) view.findViewById(R.id.TFKname);
                    String strng = a.getText().toString();
                    EditText b = (EditText) view.findViewById(R.id.TFKpassword);
                    String pass = b.getText().toString();

                    String password = dbHelper.searchPass(strng);
                    if (pass.equals(password)){
                        Intent intent = new Intent(getActivity(), PlayQuiz.class);
                        intent.putExtra("Username", strng);
                        startActivity(intent);
                    }else
                    {
                        Toast passmsg = Toast.makeText(getActivity(),"Username and passwords do not match!", Toast.LENGTH_SHORT);
                        passmsg.show();
                    }



            }
        });

        Button signup = (Button) view.findViewById(R.id.Bsignup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), KidsSignup.class);
                    startActivity(intent);

            }
        });

        return view;
    }

//    public void onButtonClick(View view){
//
//    }

//public void onButtonSnClick(View view){
//
//
//
//}

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
