package brzocitanje.aplikacija.moji.contactme;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private ImageView contactimg,favoritesimg;
    private ArrayList<Dataitems> items;
    private ListView list;
    private Database database;
    private TextView startmessage;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactimg = (ImageView)findViewById(R.id.imageView);
        favoritesimg = (ImageView)findViewById(R.id.imageView2);

        favoritesimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent favarites = new Intent(MainActivity.this,Favorits.class);
                startActivity(favarites);
                finish();
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newcontact = new Intent(MainActivity.this,NewContact.class);
                startActivity(newcontact);
                finish();
            }
        });
        list = (ListView) findViewById(R.id.lista);
        startmessage = (TextView)findViewById(R.id.startmessage);


        items = new ArrayList<Dataitems>();

        database = new Database();

        if(database.getItems() == null) {

            contactimg.setVisibility(View.GONE);
            favoritesimg.setVisibility(View.GONE);
            list.setVisibility(View.GONE);
            fab.setVisibility(View.GONE);
            startmessage.setVisibility(View.VISIBLE);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    startmessage.setVisibility(View.GONE);
                    contactimg.setVisibility(View.VISIBLE);
                    favoritesimg.setVisibility(View.VISIBLE);
                    list.setVisibility(View.VISIBLE);
                    fab.setVisibility(View.VISIBLE);

                }
            }, 2500);

            ArrayList<Dataitems> test = new ArrayList<Dataitems>();
            database.setItems(test);
        }

        items = database.getItems();
        if(items.isEmpty() ) {
            inicialdata();
        }

        Collections.sort(items, new Comparator<Dataitems>() {

            public int compare(Dataitems o1, Dataitems o2) {
                String firstlatter1 = o1.getName().substring(0,1);
                String firstlatter2 = o2.getName().substring(0,1);
                return ( firstlatter1+" "+o1.getSurname()).compareTo(firstlatter2+" "+o2.getSurname());
            }
        });

        ArrayAdapter<Dataitems> adapter = new myListAdapter();
        list.setAdapter(adapter);
    }

    private class myListAdapter extends ArrayAdapter<Dataitems> {

        public myListAdapter() {
            super(MainActivity.this, R.layout.item, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View itemView = convertView;
            ViewHolder holder;

            if (itemView == null) {

                itemView = getLayoutInflater().inflate(R.layout.item, parent, false);
                holder = new ViewHolder();
                itemView.setTag( holder );
            }else {
                holder = (ViewHolder) itemView.getTag();
            }
            Dataitems ovaj = items.get(position);

            String header = ovaj.getName();
            header = header.substring(0, 1).toUpperCase();
            holder.section_header = (TextView) itemView.findViewById(R.id.section_header);
            holder.section_header.setText( header);

            holder.underline = (View)itemView.findViewById(R.id.underline);

            if(position > 0 ) {
            Dataitems headerpr = items.get(position-1);
            Dataitems headers = items.get(position);

            String heraderpre = headerpr.getName().substring(0, 1).toUpperCase();
            String heradersada = headers.getName().substring(0, 1).toUpperCase();

                if (!heradersada.equalsIgnoreCase(heraderpre) ) {
                    holder.section_header.setVisibility(View.VISIBLE);
                    holder.underline.setVisibility(View.VISIBLE);
                } else {
                    holder.section_header.setVisibility(View.GONE);
                    holder.underline.setVisibility(View.GONE);
                }
            }else{
                holder.section_header.setVisibility(View.VISIBLE);
                holder.underline.setVisibility(View.VISIBLE);
            }

            holder.avatar = (ImageView) itemView.findViewById(R.id.itemavatar);

            int imageResource = getResources().getIdentifier("@drawable/"+ovaj.getImg(), null, getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            holder.avatar.setImageDrawable(res);

            holder.itemdata = (TextView) itemView.findViewById(R.id.itemdata);
            holder.itemdata.setText(ovaj.getName()+" "+ovaj.getSurname());

            holder.favorits = (ImageView) itemView.findViewById(R.id.favorits);
            holder.favorits.setImageResource(R.drawable.ic_favoritsoff);
            if(ovaj.isFavorite()) {
                holder.favorits.setImageResource(R.drawable.ic_favoritsimg);
                notifyDataSetChanged();
            }
            holder.favorits.setOnClickListener( new myOnClickListener(holder.favorits,ovaj,position) );

            return itemView;
        }
    }
    public class ViewHolder{

        public ImageView avatar,favorits;
        public TextView section_header,itemdata;
        public View underline;

    }

    private class myOnClickListener implements View.OnClickListener{

        private ImageView img;
        private Dataitems obj;
        private int position;
        public myOnClickListener(ImageView img,Dataitems obj,int position){
            this.img = img;
            this.obj = obj;
            this.position = position;
        }
        @Override
        public void onClick(View view) {
            if(this.obj.isFavorite()) {
                this.img.setImageResource(R.drawable.ic_favoritsoff);
                this.obj.setFavorite(false);
            }else{
                this.img.setImageResource(R.drawable.ic_favoritsimg);
                this.obj.setFavorite(true);
            }

            items.set(this.position,obj);
            database.setItems(items);

        }
    }

    private void inicialdata() {

        items.add(new Dataitems("user_avatar1","Noel", "Ulrich","+3811234567",true));
        items.add(new Dataitems("user_avatar4","David", "Ugorkovic","+3811234567",true));
        items.add(new Dataitems("user_avatar3","Angela", "Orasio","+3811234567",true));
        items.add(new Dataitems("user_avatar5","Nicholas", "Perry","+3811234567",false));
        items.add(new Dataitems("user_avatar1","Adam", "Keys","+3811234567",true));
        items.add(new Dataitems("user_avatar3","Rilley", "Madsen","+3811234567",true));
        items.add(new Dataitems("user_avatar4","Antonio", "Walker","+3811234567",false));
        items.add(new Dataitems("user_avatar2","Nelson", "Pace","+3811234567",true));
        items.add(new Dataitems("user_avatar3","Nichol", "Ferry","+3811234567",false));
        items.add(new Dataitems("user_avatar2","Mikey", "Kekulas","+3811234567",true));
        items.add(new Dataitems("user_avatar5","Rahf", "Alh","+3811234567",true));
        items.add(new Dataitems("user_avatar2","Anton", "Paris","+3811234567",false));
        items.add(new Dataitems("user_avatar4","Nilson", "Pase","+3811234567",true));

        database.setItems(items);

    }

}
