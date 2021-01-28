package pl.controller;

import pl.model.Meme;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MemeUtils {

    public static List<Meme> memeListData(){
        List<Meme>memeList = new ArrayList<>();
        Meme meme1 =new Meme(1,"Barbados","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT73lBVdy6tMcRpv3TcaMnlntso_zerXUhRRA&usqp=CAU", "This is a free HD wallpaper landscape themes,",Boolean.FALSE);
        Meme meme2 =new Meme(2,"View","https://www.askideas.com/media/37/Incredible-View-Of-Petronas-Towers-Malaysia.jpg","Malesia is a biogeographical region straddling the Equator and the boundaries of the Indomalayan and Australasian realms, and also a phytogeographical floristic region in the Paleotropical Kingdom.",Boolean.TRUE);
        Meme meme3 =new Meme(3,"Road","https://broganabroad.com/wp-content/uploads/2019/12/Road-to-Teide.jpg","The reality of landscape photography is that not only does the outcome depend on your own photography skills, but also mother nature.",Boolean.TRUE);
        Meme meme4 =new Meme(4,"Mountains","https://outdoormagazyn.pl/wp-content/uploads/2020/03/SanderGrefte14180-443-3.jpg","Przedstawiamy wyniki International Landscape Photographer of the Year 2019, jednego z ważniejszych międzynarodowych konkursów dla fotografów krajobrazowych.",Boolean.TRUE);
        Meme meme5 =new Meme(5,"Tree","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaYHlw9vs_pEkPx3WW51HvfFj0U-xxqPRVMg&usqp=CAU","Tree sdfsdfsadfsdafsdf dssafsdfsadfsda dfasdfsdfsadf asdf asdfsdfsdfasdfsadf sdf adsfas fds",Boolean.FALSE);
        Meme meme6 =new Meme(6,"Lake","https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Untersberg_Mountain_Salzburg_Austria_Landscape_Photography_%28256594075%29.jpeg/1280px-Untersberg_Mountain_Salzburg_Austria_Landscape_Photography_%28256594075%29.jpeg","Untersberg Mountain Salzburg Austria Landscape Photography",Boolean.TRUE);
        Meme meme7 =new Meme(7,"Panorama","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaV2IoLoi8WwXvwTJ0IMJdoMUxA9yuFieVtg&usqp=CAU","Moon",Boolean.TRUE);
        Meme meme8 =new Meme(8,"Krk","https://cdn.evergreenlandscapes.ca/wp-content/uploads/2015/02/plitvice-lakes.jpg","When you think of world landscapes you might think about mountains, hills and the countryside, but landscape means so much more",Boolean.TRUE);
        Meme meme9 =new Meme(9,"Home","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTWyG1iVuMEyywc1FDoeCH2OParIuO8ftyoOw&usqp=CAU","You've pulled out all the stops to make your house and yard look first-rate.",Boolean.TRUE);

        try {
            meme1.setImage(new URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT73lBVdy6tMcRpv3TcaMnlntso_zerXUhRRA&usqp=CAU").openStream());
            meme2.setImage(new URL("https://www.askideas.com/media/37/Incredible-View-Of-Petronas-Towers-Malaysia.jpg").openStream());
            meme3.setImage(new URL("https://broganabroad.com/wp-content/uploads/2019/12/Road-to-Teide.jpg").openStream());
            meme4.setImage(new URL("https://outdoormagazyn.pl/wp-content/uploads/2020/03/SanderGrefte14180-443-3.jpg").openStream());
            meme5.setImage(new URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaYHlw9vs_pEkPx3WW51HvfFj0U-xxqPRVMg&usqp=CAU").openStream());
            meme6.setImage(new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c8/Untersberg_Mountain_Salzburg_Austria_Landscape_Photography_%28256594075%29.jpeg/1280px-Untersberg_Mountain_Salzburg_Austria_Landscape_Photography_%28256594075%29.jpeg").openStream());
            meme7.setImage(new URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSaV2IoLoi8WwXvwTJ0IMJdoMUxA9yuFieVtg&usqp=CAU").openStream());
            meme8.setImage(new URL("https://cdn.evergreenlandscapes.ca/wp-content/uploads/2015/02/plitvice-lakes.jpg").openStream());
            meme9.setImage(new URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTWyG1iVuMEyywc1FDoeCH2OParIuO8ftyoOw&usqp=CAU").openStream());

        } catch(IOException e){
            e.printStackTrace();
        }


        memeList.add(meme1);
   memeList.add(meme2);
   memeList.add(meme3);
   memeList.add(meme4);
   memeList.add(meme5);
   memeList.add(meme6);
   memeList.add(meme7);
   memeList.add(meme8);
   memeList.add(meme9);
        return memeList;
    }
}
