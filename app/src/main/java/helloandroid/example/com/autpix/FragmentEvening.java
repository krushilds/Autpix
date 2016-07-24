package helloandroid.example.com.autpix;

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

import java.util.ArrayList;
import java.util.Locale;


public class FragmentEvening extends Fragment implements AdapterView.OnItemClickListener {


    GridView gridView6;
    TextView textView;
    String text;
    int result;
    TextToSpeech textToSpeech;
    Button speakbtn;
    Button deletebtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evening, container, false);


        textView = (TextView) view.findViewById(R.id.textView5);
        textView.setMovementMethod(new ScrollingMovementMethod());

        speakbtn = (Button) view.findViewById(R.id.button38);
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

        deletebtn = (Button) view.findViewById(R.id.button37);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteWord();
            }
        });


        final EveningAdapter eveningAdapter = new EveningAdapter(getActivity());
        gridView6 = (GridView) view.findViewById(R.id.gridView13);
        gridView6.setAdapter(eveningAdapter);
        gridView6.setOnItemClickListener(this);
        gridView6.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                System.out.println(position);

                CharSequence sequence = eveningAdapter.arrayList.remove(position).imageNames + "deleted";
                gridView6.setAdapter(eveningAdapter);
                eveningAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(),sequence,Toast.LENGTH_SHORT).show();


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
        if (intent != null){
            EveningHolder eveningHolder = (EveningHolder) view.getTag();
            EveningList tempString = (EveningList) eveningHolder.imageHolder.getTag();
            intent.putExtra("imageNames",tempString.imageNames);
            String eveningNames = intent.getStringExtra("imageNames");

            eveningNames = textView.getText() + " " + eveningNames;

            textView.setText(eveningNames);


            textToSpeech.speak(tempString.imageNames, TextToSpeech.QUEUE_FLUSH, null);

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



    public class EveningAdapter extends BaseAdapter{

        ArrayList<EveningList> arrayList = null;
        Context context;

        EveningAdapter(Context context){

            arrayList = new ArrayList<EveningList>();
            this.context = context;
            Resources resources = context.getResources();
            String[] tempEveningNames = resources.getStringArray(R.array.evening_names);
            int[] eveningImages = {R.drawable.i_want, R.drawable.watchtv, R.drawable.changetvchannel,
                    R.drawable.computer, R.drawable.football, R.drawable.goout, R.drawable.sleep,
                    R.drawable.storytime, R.drawable.washface};
            for (int i = 0; i <9; i++) {
                EveningList tempString = new EveningList(eveningImages[i], tempEveningNames[i]);
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

            EveningHolder holder = null;
            View row = convertView;
            if (row == null){
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                row = layoutInflater.inflate(R.layout.single_item,parent,false);
                holder = new EveningHolder(row);
                row.setTag(holder);
            }else {
                holder = (EveningHolder) row.getTag();
            }

            EveningList temp = arrayList.get(position);
            holder.imageHolder.setImageResource(temp.nameId);
            holder.textHolder.setText(temp.imageNames);
            holder.imageHolder.setTag(temp);

            return row;
        }
    }

    class EveningList{
        int nameId;
        String imageNames;
        EveningList(int nameID, String imageNames){
            this.nameId = nameID;
            this.imageNames = imageNames;

        }

    }

    class EveningHolder {
        ImageView imageHolder;
        TextView textHolder;

        EveningHolder(View view){

            imageHolder = (ImageView) view.findViewById(R.id.imageView2);
            textHolder = (TextView) view.findViewById(R.id.textView12);


        }
    }


}



























