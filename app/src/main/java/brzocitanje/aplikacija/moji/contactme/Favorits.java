package brzocitanje.aplikacija.moji.contactme;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Favorits extends AppCompatActivity {

    private ImageView contactimg,favoritesimg;
    private ArrayList<Dataitems> items;
    private ArrayList<Favoritesitems> favoritesitems;
    private GridView gridview;
    private Database database;
    private MyAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorits);
        contactimg = (ImageView)findViewById(R.id.imageView);
        favoritesimg = (ImageView)findViewById(R.id.imageView2);

        contactimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Favorits.this,MainActivity.class));
                finish();
            }
        });


        gridview = (GridView) findViewById(R.id.gridview);
        items = new ArrayList<Dataitems>();
        favoritesitems = new ArrayList<Favoritesitems>();

        database = new Database();
        items = database.getItems();

        for(int i=0;i<items.size();i++){
            Dataitems obj = items.get(i);
            if(obj.isFavorite()){

                favoritesitems.add(new Favoritesitems( obj.getImg(), obj.getName(), obj.getSurname(), obj.getTel(), i));

            }
        }

        myadapter = new MyAdapter(this, favoritesitems);
        gridview.setAdapter(myadapter);
    }

    private  class MyAdapter extends BaseAdapter {

            private  Context mContext;
            private  ArrayList<Favoritesitems> favoritesitems;

            // 1
            public MyAdapter(Context context, ArrayList<Favoritesitems> favoritesitems) {
                this.mContext = context;
                this.favoritesitems = favoritesitems;
            }

            // 2
            @Override
            public int getCount() {
                return favoritesitems.size();
            }

            // 3
            @Override
            public long getItemId(int position) {
                return 0;
            }

            // 4
            @Override
            public Object getItem(int position) {
                return null;
            }

        public View getView(final int position, View convertView, ViewGroup parent) {

            View itemView = convertView;
            ViewHolder holder;

            if (itemView == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(mContext);
                itemView = layoutInflater.inflate(R.layout.itemfavirits, null);

                holder = new ViewHolder();
                itemView.setTag( holder );

            }else {
                holder = (ViewHolder) itemView.getTag();
            }
            Favoritesitems ovajfavorit = favoritesitems.get(position);


                holder.removefavorite  = (ImageView)itemView.findViewById(R.id.removefav);
                holder.removefavorite.setOnClickListener(new myOnClickListener( ovajfavorit,myadapter,position,ovajfavorit.getPosition() ));

                holder.favoritsimg = (ImageView) itemView.findViewById(R.id.favoritsimg);
                int imageResource = getResources().getIdentifier("@drawable/"+ovajfavorit.getImg(), null, getPackageName());
                Drawable res = getResources().getDrawable(imageResource);
                holder.favoritsimg.setImageDrawable(res);

                holder.favoritsdata = (TextView) itemView.findViewById(R.id.favoritsdata);
                holder.favoritsdata.setText(ovajfavorit.getName()+" "+ovajfavorit.getSurname());

                holder.favoritstel = (TextView) itemView.findViewById(R.id.favoritstel);


            return itemView;
        }

        }
    public class ViewHolder{

        public ImageView favoritsimg,removefavorite;
        public TextView favoritsdata,favoritstel;

    }

    private class myOnClickListener implements View.OnClickListener{

        private MyAdapter myadapter;
        private Favoritesitems ovajfavorit;
        private int position,pozitionIyems;

        public myOnClickListener(Favoritesitems ovajfavorit,MyAdapter myadapter,int position,int pozitionIyems){
            this.ovajfavorit = ovajfavorit;
            this.myadapter = myadapter;
            this.position = position;
            this.pozitionIyems = pozitionIyems;
        }
        @Override
        public void onClick(View view) {


            Dataitems obj = items.get(ovajfavorit.getPosition());
            obj.setFavorite(false);

            items.set(pozitionIyems,obj);
            database.setItems(items);

            favoritesitems.remove(position);
            myadapter.notifyDataSetChanged();


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(Favorits.this,MainActivity.class));
        finish();
    }
}
