package helloandroid.example.com.autpix;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.Snackbar;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Locale;


public class FragmentAboutMe extends Fragment implements AdapterView.OnItemClickListener {

    GridView gridView1, gridView2;
    TextView textView;
    String text;
    int result;
    TextToSpeech textToSpeech;
    ViewFlipper viewFlipper;
    Button speakbtn;
    Button deletebtn;
    Button nextbutton, prevbutton;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment_about_me, container, false);

        textView = (TextView) view.findViewById(R.id.textView3);
        textView.setMovementMethod(new ScrollingMovementMethod());

        gridView1 = (GridView) view.findViewById(R.id.gridView);
        gridView2 = (GridView) view.findViewById(R.id.gridView2);
        viewFlipper= (ViewFlipper) view.findViewById(R.id.viewFlipper);
        deletebtn = (Button) view.findViewById(R.id.button15);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteWord();
            }
        });

        nextbutton = (Button) view.findViewById(R.id.button18);
        prevbutton = (Button) view.findViewById(R.id.button17);

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gridView1.setVisibility(View.INVISIBLE);
                gridView2.setVisibility(View.VISIBLE);

            }
        });

        prevbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gridView1.setVisibility(View.VISIBLE);
                gridView2.setVisibility(View.INVISIBLE);

            }
        });


        speakbtn = (Button) view.findViewById(R.id.speak);
        speakbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){

                    Toast.makeText(getActivity(),"Feature not supported for this device",Toast.LENGTH_SHORT).show();

                }
                else
                {

                    text = textView.getText().toString();

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null,null);
                    }
                }

            }
        });




        final AboutMeAdapter aboutMeAdapter = new AboutMeAdapter(getActivity());

        gridView1.setAdapter(aboutMeAdapter);
        gridView1.setOnItemClickListener(this);
        gridView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                System.out.println(position);

                CharSequence sequence = aboutMeAdapter.arrayList.remove(position).imageNames + "deleted";
                gridView1.setAdapter(aboutMeAdapter);
                aboutMeAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(),sequence,Toast.LENGTH_SHORT).show();


                return false;
            }
        });


        final FamilyAdapter familyAdapter = new FamilyAdapter(getActivity());
        gridView2.setAdapter(familyAdapter);
        gridView2.setOnItemClickListener(this);

        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent secondGrid = new Intent();
                if (secondGrid != null){

                    FamilyHolder familyHolder = (FamilyHolder) view.getTag();
                    FamilyString tempStrng = (FamilyString) familyHolder.imageView.getTag();
                    secondGrid.putExtra("familyNames",tempStrng.familyImageNames);
                    String familynames = secondGrid.getStringExtra("familyNames");
                    familynames = textView.getText() + " " + familynames;
                    textView.setText(familynames);
                    textToSpeech.speak(tempStrng.familyImageNames, TextToSpeech.QUEUE_FLUSH,null);

                }

            }
        });


        gridView2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {




                return false;
            }
        });



    textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {

            if (status == TextToSpeech.SUCCESS)
            {

                result = textToSpeech.setLanguage(Locale.UK);

            }
            else
            {

                Toast.makeText(getActivity(),
                        "Language not supported",Toast.LENGTH_SHORT).show();

            }

        }
    });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent();

        if (position == 4){
            viewFlipper.showNext();
            System.out.println("went to second");
        }


        if (intent != null){
            AboutMeHolder aboutMeHolder = (AboutMeHolder) view.getTag();
            AboutMeString tempString = (AboutMeString) aboutMeHolder.imageHolder.getTag();
            intent.putExtra("imageNames",tempString.imageNames);
            String aboutmeNames = intent.getStringExtra("imageNames");

            aboutmeNames = textView.getText() + " " + aboutmeNames;

            textView.setText(aboutmeNames);


            textToSpeech.speak(tempString.imageNames, TextToSpeech.QUEUE_FLUSH, null);

        }



    }


    // custom adapter for setting gridview1 items
    public class AboutMeAdapter extends BaseAdapter {

        ArrayList<AboutMeString> arrayList = null;
        Context context;

         AboutMeAdapter(Context context) {

             this.context = context;
             arrayList = new ArrayList<AboutMeString>();
             Resources resources = context.getResources();

             // getting the image names from xml file
             String[] tempNames = resources.getStringArray(R.array.aboutme_names);

             int [] aboutMeImages = {R.drawable.hi,R.drawable.my_name_is,R.drawable.i_want,R.drawable.to_see,
                     R.drawable.yes,R.drawable.no,R.drawable.thank_you,R.drawable.i_dont_know,
                     R.drawable.i_need_help,R.drawable.im_good,R.drawable.to_hear,R.drawable.i_live_in_england};
            for (int i = 0; i<12; i++){

                // combining image and the name together
                AboutMeString tempString = new AboutMeString(aboutMeImages[i],tempNames[i]);
                arrayList.add(tempString);
            }
        }
        // remove item on position i
        public int removeItem(int i){
            arrayList.remove(i);
            return 0;
        }

        // this will allow me to add  an image inside tempstrring  arrayList
        public void add(AboutMeString aboutMeString){
            arrayList.add(aboutMeString);
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            AboutMeHolder holder = null;
            View row = convertView;
            if (row == null){
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                row = layoutInflater.inflate(R.layout.single_item,parent,false);
                holder = new AboutMeHolder(row);
                row.setTag(holder);
            }
            else{

                holder= (AboutMeHolder) row.getTag();
            }

            AboutMeString temp = arrayList.get(position);
            holder.imageHolder.setImageResource(temp.nameID);
            holder.textHolder.setText(temp.imageNames);
            holder.imageHolder.setTag(temp);

            return row;
        }
    }


    // binding the
    class AboutMeString {
        // id of the image
        int nameID;
        String imageNames;

        AboutMeString(int nameID, String imageNames) {

            this.nameID = nameID;
            this.imageNames = imageNames;

        }
    }

    //
    class AboutMeHolder{

        ImageView imageHolder;
        TextView textHolder;

        AboutMeHolder(View view){

            imageHolder = (ImageView) view.findViewById(R.id.imageView2);
            textHolder = (TextView) view.findViewById(R.id.textView12);

        }
    }

    public void deleteWord(){

        deletebtn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                textView.setText("");
                return false;
            }
        });


        String deletetext = textView.getText().toString();
        if (deletetext.length() > 0){
            textView.setText(deletetext.substring(0, deletetext.length() - 1));


        }else{

            Toast toast = Toast.makeText(getActivity(),"no text",Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public class FamilyAdapter extends BaseAdapter {


        ArrayList<FamilyString> arrayFamily = null;
        Context context;

        FamilyAdapter(Context context){

            this.context = context;
            arrayFamily = new ArrayList<FamilyString>();
            Resources resources = context.getResources();
            String[] tempFamilyNames = resources.getStringArray(R.array.family_names);
            int [] familyImages = {R.drawable.dad,R.drawable.mum,R.drawable.sister,
                                    R.drawable.brother,R.drawable.niece,R.drawable.nephew,
                                    R.drawable.uncle,R.drawable.aunt,R.drawable.grandfather,
                                    R.drawable.grandmother,R.drawable.son};
            for (int i = 0; i<11; i++){
                FamilyString tempString = new FamilyString(familyImages[i],tempFamilyNames[i]);
                arrayFamily.add(tempString);
            }



        }

        public int removeItem(int i){
            arrayFamily.remove(i);
            return 0;
        }

        public void addItem(FamilyString familyString){
            arrayFamily.add(familyString);
        }

        @Override
        public int getCount() {
            return arrayFamily.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayFamily.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            FamilyHolder familyHolder = null;
            View grow = convertView;
            if (grow == null){

                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                grow = layoutInflater.inflate(R.layout.single_item,parent,false);
                familyHolder = new FamilyHolder(grow);
                grow.setTag(familyHolder);
            }
            else
            {
                familyHolder = (FamilyHolder) grow.getTag();
            }

            FamilyString tempString = arrayFamily.get(position);
            familyHolder.imageView.setImageResource(tempString.familyNameID);
            familyHolder.textView.setText(tempString.familyImageNames);
            familyHolder.imageView.setTag(tempString);


            return grow;
        }
    }

    class FamilyString{

        int familyNameID;
        String familyImageNames;

        FamilyString(int familyNameID, String familyImageNames){

            this.familyNameID = familyNameID;
            this.familyImageNames = familyImageNames;

        }

    }

    class FamilyHolder{

        ImageView imageView;
        TextView textView;

        FamilyHolder(View view){

            imageView = (ImageView) view.findViewById(R.id.imageView2);
            textView = (TextView) view.findViewById(R.id.textView12);

        }

    }
}
