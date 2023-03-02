package com.arrowwould.proyouhash;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentAllHashtags extends Fragment
{
    Context mContext;
    List<Hashtags> hashtagsList;
    RecyclerView mRecyclerView;
    AdapterRecyclerViewHashtagsItem mAdapterRecyclerViewHashtagsItem;
    FragmentManager fragmentManager;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext=getActivity().getApplicationContext();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
     View allHashtagsView=inflater.inflate(R.layout.fragment_all_hashtags,container,false);
        mRecyclerView=allHashtagsView.findViewById(R.id.recyclerViewAllHashtags);
        hashtagsList=new ArrayList<>();
        fragmentManager=getActivity().getSupportFragmentManager();
        // Get FloatingActionButton Id


        //Adding A New Collections Here
        hashtagsList.clear();
        loadHashtagsList();


        // Link Adapter With RecyclerView
        mAdapterRecyclerViewHashtagsItem =new AdapterRecyclerViewHashtagsItem(getContext(),hashtagsList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        mRecyclerView.setAdapter(mAdapterRecyclerViewHashtagsItem);

       // Toast.makeText(getActivity().getApplicationContext(),"Test",Toast.LENGTH_LONG).show();

     return allHashtagsView;


    }



    public void loadHashtagsList()
    {

        HashtagsXmlParser hashtagsXmlParser=new HashtagsXmlParser(getContext());
        hashtagsList=hashtagsXmlParser.getHashtagsList();

        Collections.sort(hashtagsList,new Hashtags.SortBySubCategoryTitle());

    }





}
