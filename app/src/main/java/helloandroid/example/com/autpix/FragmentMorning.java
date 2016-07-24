package helloandroid.example.com.autpix;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.speech.tts.TextToSpeech;
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


public class FragmentMorning extends Fragment implements AdapterView.OnItemClickListener {



    GridView gridview3, gridview4;
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

        View view =  inflater.inflate(R.layout.fragment_morning, container, false);


        gridview3 = (GridView) view.findViewById(R.id.gridView3);
        gridview4 = (GridView) view.findViewById(R.id.gridView4);

        textView = (TextView) view.findViewById(R.id.textView16);
        textView.setMovementMethod(new ScrollingMovementMethod());

        nextbutton = (Button) view.findViewById(R.id.button20);
        prevbutton = (Button) view.findViewById(R.id.button19);

        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gridview3.setVisibility(View.INVISIBLE);
                gridview4.setVisibility(View.VISIBLE);

            }
        });

        prevbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gridview3.setVisibility(View.VISIBLE);
                gridview4.setVisibility(View.INVISIBLE);

            }
        });

        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper2);

        speakbtn = (Button) view.findViewById(R.id.button16);
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


        deletebtn = (Button) view.findViewById(R.id.button);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteWord();
            }
        });

        final MorningAdapter morningAdapter = new MorningAdapter(getActivity());
        gridview3.setAdapter(morningAdapter);
        gridview3.setOnItemClickListener(this);
        gridview3.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                System.out.println(position);

                CharSequence sequence = morningAdapter.arrayList.remove(position).imageNames + "deleted";
                gridview3.setAdapter(morningAdapter);
                morningAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(),sequence,Toast.LENGTH_SHORT).show();

                return false;
            }
        });


        final DrinkAdapter drinkAdapter = new DrinkAdapter(getActivity());
        gridview4.setAdapter(drinkAdapter);

        gridview4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent secondGrid = new Intent();
                if (secondGrid != null){

                    DrinksHolder drinkSHolder = (DrinksHolder) view.getTag();
                    DrinksList tempStrng = (DrinksList) drinkSHolder.drinksHolder.getTag();
                    secondGrid.putExtra("drinksNames",tempStrng.drinkNames);
                    String drinksnames = secondGrid.getStringExtra("drinksNames");
                    drinksnames = textView.getText() + " " + drinksnames;
                    textView.setText(drinksnames);
                    textToSpeech.speak(tempStrng.drinkNames, TextToSpeech.QUEUE_FLUSH,null);

                }

            }
        });
        gridview4.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                System.out.println(position);

                CharSequence sequence = drinkAdapter.drinksLists.remove(position).drinkNames + "deleted";
                gridview4.setAdapter(drinkAdapter);
                drinkAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(),sequence,Toast.LENGTH_SHORT).show();

                return false;
            }
        });



        // text to speech
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

        if (position == 1){
            viewFlipper.showNext();
            System.out.println("went to second");
        }


        if (intent !=null){

            MorningHolder morningHolder = (MorningHolder) view.getTag();
            MorningList tempString = (MorningList) morningHolder.imageHolder.getTag();
            intent.putExtra("imageNames",tempString.imageNames);
            String morningNames = intent.getStringExtra("imageNames");
            morningNames = textView.getText() + " " + morningNames;
            textView.setText(morningNames);

            textToSpeech.speak(tempString.imageNames, TextToSpeech.QUEUE_FLUSH, null);

        }



    }




    public class MorningAdapter extends BaseAdapter{

        ArrayList<MorningList> arrayList = null;
        Context context;

        MorningAdapter(Context context){

            this.context = context;
            arrayList = new ArrayList<MorningList>();
            Resources resources = context.getResources();
            String[] tempMorningNames = resources.getStringArray(R.array.morning_names);
            int[] morningImages = {R.drawable.i_want,R.drawable.drink,R.drawable.cake,
                                     R.drawable.chocolate,R.drawable.banana,R.drawable.cookies,
                                        R.drawable.cereal,R.drawable.eggs,R.drawable.grapes,
                                            R.drawable.bread};
            for (int i=0; i<10; i++){
                MorningList tempString = new MorningList(morningImages[i],tempMorningNames[i]);
                arrayList.add(tempString);
            }


        }


        public int removeItem(int i){
            arrayList.remove(i);
            return 0;
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

            MorningHolder holder = null;
            View row = convertView;
            if (row == null){
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                row = layoutInflater.inflate(R.layout.single_item,parent,false);
                holder = new MorningHolder(row);
                row.setTag(holder);

            }else {
                holder = (MorningHolder) row.getTag();
            }


            MorningList temp = arrayList.get(position);
            holder.imageHolder.setImageResource(temp.nameId);
            holder.textHolder.setText(temp.imageNames);
            holder.imageHolder.setTag(temp);



            return row;
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





    class MorningList{

        int nameId;
        String imageNames;

        MorningList(int nameID, String imageNames){

            this.nameId = nameID;
            this.imageNames = imageNames;
        }

    }

    class MorningHolder{

        ImageView imageHolder;
        TextView textHolder;

        MorningHolder(View view){

            imageHolder = (ImageView) view.findViewById(R.id.imageView2);
            textHolder = (TextView) view.findViewById(R.id.textView12);


        }

    }




    public class DrinkAdapter extends BaseAdapter{

        ArrayList <DrinksList> drinksLists = null;
        Context context;

        DrinkAdapter(Context context) {

            this.context = context;
            drinksLists = new ArrayList<DrinksList>();
            Resources resources = context.getResources();
            String[] tempDrinksNames = resources.getStringArray(R.array.morningdrink_names);
            int[] drinksImages = {R.drawable.hotchocolate,R.drawable.chocolatemilk,R.drawable.lemonade,
                                    R.drawable.applejuice,R.drawable.coffee,R.drawable.orangejuice,
                                        R.drawable.milk,R.drawable.water,R.drawable.koolaid};
            for (int i=0; i<9; i++){
                DrinksList drinksList = new DrinksList(drinksImages[i], tempDrinksNames[i]);
                drinksLists.add(drinksList);

            }

        }


        public int removeItem(int i){
            drinksLists.remove(i);
            return 0;
        }


        @Override
        public int getCount() {
            return drinksLists.size();
        }

        @Override
        public Object getItem(int position) {
            return drinksLists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            DrinksHolder holder = null;
            View gridRow = convertView;
            if (gridRow == null){

                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                gridRow = layoutInflater.inflate(R.layout.single_item,parent,false);
                holder = new DrinksHolder(gridRow);
                gridRow.setTag(holder);

            }else {

                holder = (DrinksHolder) gridRow.getTag();

            }

            DrinksList tempArray = drinksLists.get(position);
            holder.drinksHolder.setImageResource(tempArray.drinkId);
            holder.drinksTextHolder.setText(tempArray.drinkNames);
            holder.drinksHolder.setTag(tempArray);

            return gridRow;
        }
    }



    class DrinksList{

        int drinkId;
        String drinkNames;

        DrinksList(int drinkId, String drinkNames){

            this.drinkId = drinkId;
            this.drinkNames = drinkNames;

        }

    }



    class DrinksHolder{

        ImageView drinksHolder;
        TextView drinksTextHolder;

        DrinksHolder(View view){


            drinksHolder = (ImageView) view.findViewById(R.id.imageView2);
            drinksTextHolder = (TextView) view.findViewById(R.id.textView12);


        }


    }






}
