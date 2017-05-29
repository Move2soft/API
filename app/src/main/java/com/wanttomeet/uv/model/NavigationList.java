package com.wanttomeet.uv.model;


import com.wanttomeet.uv.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by UV on 29-Nov-16.
 */
public class NavigationList {
    public static NavigationList _naviNavigationList;
    public static List<Integer> imageList;
    public static List<String> titleList;
    public static String location = "Surat,Magob";
    public static String cartCount;

    public static NavigationList getInstance() {
        if (_naviNavigationList == null) {
            _naviNavigationList = new NavigationList();
        }
        imageList = Arrays.asList(R.drawable.ic_home,R.drawable.ic_news, R.drawable.ic_ibout
                ,R.drawable.ic_contact,R.drawable.ic_profilr, R.drawable.ic_share, R.drawable.ic_rate,R.drawable.ic_delete_me,
                R.drawable.ic_logout);
        titleList = Arrays.asList("Home","News","About Us","Contact","Account","Share","Rate","Delete Me","Logout");
        return _naviNavigationList;
    }

    public static List<Integer> getImageList() {
        return imageList;
    }

    public static List<String> getTitleList() {
        return titleList;
    }

    public static void changeLocation(String mylocation) {
        location = mylocation;
        titleList.set(0, mylocation);
    }
}
