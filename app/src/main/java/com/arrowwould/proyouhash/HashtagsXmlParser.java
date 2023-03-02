package com.arrowwould.proyouhash;

import android.content.Context;
import android.content.res.Resources;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HashtagsXmlParser
{
    Context mContext;

    public HashtagsXmlParser(Context mContext)
    {
        this.mContext=mContext;
    }


    public List<Hashtags> InitializeXmlParser()
    {

        List<Hashtags> hashtagsList =new ArrayList<>();

        try
        {
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            InputStream inputStream = mContext.getAssets().open("hashtag_list.xml");
            xmlPullParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            xmlPullParser.setInput(inputStream,null);
            hashtagsList=processParsingOrigami(xmlPullParser);


        }
        catch (XmlPullParserException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hashtagsList;

    }

    private List<Hashtags> processParsingOrigami(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException
    {
        List<Hashtags> hashtagsList=new ArrayList<>();
        int eventType=xmlPullParser.getEventType();
        Hashtags currentHashtag=null;
        while (eventType!= XmlPullParser.END_DOCUMENT)
        {
            String elementName=null;
            switch (eventType)
            {
                case XmlPullParser.START_TAG:
                {
                    elementName=xmlPullParser.getName();
                    if ("Hashtag".equals(elementName))
                    {
                        currentHashtag=new Hashtags();
                        currentHashtag.setmCategory(xmlPullParser.getAttributeValue(0));
                        currentHashtag.setmSubCategory(xmlPullParser.getAttributeValue(1));
                        currentHashtag.setmThumbnail(getImageResourceByName(xmlPullParser.getAttributeValue(2)));
                        currentHashtag.setmHashtags(xmlPullParser.nextText());
                        hashtagsList.add(currentHashtag);
                    }


                    break;
                }

            }

            eventType=xmlPullParser.next();
        }

        return hashtagsList;
    }
    private int getImageResourceByName(String name)
    {
        Resources resources = MainActivity.mContext.getResources();
        return resources.getIdentifier(name, "drawable",
                MainActivity.mContext.getPackageName());
    }

    public List<Hashtags> getHashtagsList()
    {
        return InitializeXmlParser();
    }


}
