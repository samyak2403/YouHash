package com.arrowwould.proyouhash;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterRecyclerViewHashtagsItem extends RecyclerView.Adapter<AdapterRecyclerViewHashtagsItem.MyViewHolder>
{
   public static Context mContext;
   List<Hashtags> hashtagsList;



    public AdapterRecyclerViewHashtagsItem(Context mContext, List<Hashtags> hashtagsList) {
        this.mContext = mContext;
        this.hashtagsList = hashtagsList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        final int num=i;
        view=layoutInflater.inflate(R.layout.card_view_hashtag_item,viewGroup,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i)
    {
        myViewHolder.textViewHashtagCategory.setText(hashtagsList.get(i).getmCategory());
        myViewHolder.textViewHashtagSubCategory.setText(hashtagsList.get(i).getmSubCategory());
        myViewHolder.imageViewHashtagThumbnail.setImageResource(hashtagsList.get(i).getmThumbnail());

        final   int num=i;
      myViewHolder.itemView.setTag(hashtagsList.get(i));

    }

    @Override
    public int getItemCount() {
        return hashtagsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewHashtagCategory,textViewHashtagSubCategory;
        ImageView imageViewHashtagThumbnail,circleImageViewHashtag;
        Button buttonSeeMore;

        public MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            textViewHashtagCategory=(TextView)itemView.findViewById(R.id.textViewCategoryHashtagItem);
            textViewHashtagSubCategory=(TextView)itemView.findViewById(R.id.textViewSubCategoryHashtagItem);
            imageViewHashtagThumbnail=(ImageView)itemView.findViewById(R.id.imageViewHashtagItem);
            circleImageViewHashtag=itemView.findViewById(R.id.imageViewCircleHashtagItem);
            buttonSeeMore=itemView.findViewById(R.id.button_see_more_game_item);

            buttonSeeMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    buttonSeeMore.startAnimation(AnimationUtils.loadAnimation(mContext,R.anim.bubble_animation));
                    Hashtags mHashtags=((Hashtags) itemView.getTag());
                    FragmentContent fragmentContent =new FragmentContent();
                    Bundle bundle=new Bundle();
                    bundle.putString(Hashtags.HASHTAGS_TO_SHOW,mHashtags.getmHashtags());
                    bundle.putString(Hashtags.CONTENT_CATEGORY,mHashtags.getmCategory());
                    bundle.putInt(Hashtags.THUMBNAIL_CONTENT,mHashtags.getmThumbnail());

                    fragmentContent.setArguments(bundle);

                   if (MainActivity.clickCount>=MainActivity.clickCountToShowAds)
                   {
                       if (MainActivity.mInterstitialAd.isLoaded())
                       {
                           MainActivity.mInterstitialAd.show();
                           MainActivity.clickCount=0;
                       }
                   }
                   else
                   {
                       MainActivity.clickCount++;
                   }

                    ((FragmentActivity) AdapterRecyclerViewHashtagsItem.mContext).getSupportFragmentManager().beginTransaction().replace(R.id.container_framelayout, fragmentContent).addToBackStack(null).commit();
                  // Toast.makeText(MainActivity.mContext,game.getGameTitle().toString(),Toast.LENGTH_LONG).show();
                }
            });

        }
    }

}

