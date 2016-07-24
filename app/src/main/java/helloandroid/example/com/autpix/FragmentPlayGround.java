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


public class FragmentPlayGround extends Fragment implements AdapterView.OnItemClickListener {



    GridView gridView7;
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
        View view = inflater.inflate(R.layout.fragment_play_ground, container, false);

        textView = (TextView) view.findViewById(R.id.textView17);
        textView.setMovementMethod(new ScrollingMovementMethod());


        speakbtn = (Button) view.findViewById(R.id.button22);
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

        deletebtn = (Button) view.findViewById(R.id.button21);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteWord();
            }
        });



        final PlaygroundAdapter playgroundAdapter = new PlaygroundAdapter(getActivity());
        gridView7 = (GridView) view.findViewById(R.id.gridView6);
        gridView7.setAdapter(playgroundAdapter);
        gridView7.setOnItemClickListener(this);
        gridView7.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                System.out.println(position);

                CharSequence sequence = playgroundAdapter.arrayList.remove(position).imageNames + "deleted";
                gridView7.setAdapter(playgroundAdapter);
                playgroundAdapter.notifyDataSetChanged();
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
            PlaygroundHolder playgroundHolder = (PlaygroundHolder) view.getTag();
            PlaygroundList tempString = (PlaygroundList) playgroundHolder.imageHolder.getTag();
            intent.putExtra("imageNames",tempString.imageNames);
            String playgroundNames = intent.getStringExtra("imageNames");

            playgroundNames = textView.getText() + " " + playgroundNames;

            textView.setText(playgroundNames);


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



    public class PlaygroundAdapter extends BaseAdapter{


        ArrayList<PlaygroundList> arrayList = null;
        Context context;

        PlaygroundAdapter(Context context){
            arrayList = new ArrayList<PlaygroundList>();
            this.context = context;
            Resources resources = context.getResources();
            String[] tempPlaygroundNames = resources.getStringArray(R.array.playground_names);
            int[] playgroundImages = {R.drawable.i_want, R.drawable.computer, R.drawable.draw,
                    R.drawable.colour, R.drawable.paint, R.drawable.jumprope, R.drawable.eat,
                    R.drawable.puzzle, R.drawable.readbook, R.drawable.queueup};
            for (int i = 0; i <9; i++) {
                PlaygroundList tempString = new PlaygroundList(playgroundImages[i], tempPlaygroundNames[i]);
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

            PlaygroundHolder holder = null;
            View row = convertView;
            if (row == null){
                LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                row = layoutInflater.inflate(R.layout.single_item,parent,false);
                holder = new PlaygroundHolder(row);
                row.setTag(holder);
            }else {
                holder = (PlaygroundHolder) row.getTag();
            }

            PlaygroundList temp = arrayList.get(position);
            holder.imageHolder.setImageResource(temp.nameId);
            holder.textHolder.setText(temp.imageNames);
            holder.imageHolder.setTag(temp);

            return row;
        }
    }


    class PlaygroundList{
        int nameId;
        String imageNames;

        PlaygroundList(int nameID, String imageNames){
            this.nameId = nameID;
            this.imageNames = imageNames;

        }

    }

    class PlaygroundHolder {
        ImageView imageHolder;
        TextView textHolder;

        PlaygroundHolder(View view){

            imageHolder = (ImageView) view.findViewById(R.id.imageView2);
            textHolder = (TextView) view.findViewById(R.id.textView12);


        }
    }


}
