package com.example.yang_.yangzhang_project.sample;


import com.example.yang_.yangzhang_project.model.DataItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleDataProvider {
    public static List<DataItem> dataItemList;
    // map interface has two generic decorations key & value
    public static Map<String, DataItem> dataItemMap;

    //static initializer block
    static {
        dataItemList = new ArrayList<>();
        //hashmap implement map interface
        dataItemMap = new HashMap<>();

//String itemId, String itemName, String category, String description, String instruction, int sortPosition, double cost, String image

        addItem(new DataItem(null, "House Salad", "5mins", "This salad has a Mexican spin: black beans, cilantro, avocado and a cumin-lime dressing.",
                "1. Warm olive/coconut oil in a medium saucepan over medium heat.\n" +
                        "2. While quinoa is cooking, prepare all the other ingredients. Adjust seasoning as necessary.\n" +
                        "3. When quinoa has finished cooking, remove from heat and fluff with a fork. .\n" +
                        "4. Let it cool for about five minutes then add all remaining ingredients, including the dressing.",
                1, 5, "house_salad.jpg"));

        addItem(new DataItem(null, "French Onion Soup", "5mins", "This soup is absolutely, positively, undeniably out of this world",
                "1. Warm olive/coconut oil in a medium saucepan over medium heat.\n" +
                        "2. While quinoa is cooking, prepare all the other ingredients. Adjust seasoning as necessary.\n" +
                        "3. When quinoa has finished cooking, remove from heat and fluff with a fork.\n" +
                        "4. Let it cool for about five minutes then add all remaining ingredients, including the dressing."
                ,
                2, 3, "french_onion_soup.jpg"));


        addItem(new DataItem(null, "Parmesan Deviled Eggs", "10mins", "These buttery deviled eggs are truly the best I’ve ever had.",
                "1.Put eggs in a pot and cover with water. Bring to a boil and then move to another range and allow to cook for around 10 – 11 minutes.\n" +
                        "2.Cut eggs in half lengthwise; carefully remove yolks. \n"
                +"3.Mash yolks; stir in butter, mayonnaise, mustard, and lemon juice. Add vinegar, red pepper, hot sauce, salt, and pepper, tasting for desired result.",
                3, 6, "deviled_eggs.jpg"));


        addItem(new DataItem(null, "Tomato Bruschetta Tortellini", "10mins", "Entrees",
                "This classic cheese tortellini is cooked in a sundried tomato sauce. Served with bruschetta topped with a tomato and basil marinara.",
                4, 8, "tortellini.jpg"));

        addItem(new DataItem(null, "Barbecued Tofu Skewers", "15mins", "Entrees",
                "Our barbecued skewers include tofu, cherry tomatoes, bell peppers, and zucchini marinated in a ginger sesame sauce and charbroiled. Served with steamed rice.",
                1, 8, "tofu_skewers.jpg"));

        addItem(new DataItem(null, "Cheesecake", "15mins","Desserts",
                "Our New York Style Cheesecake is rich, smooth, and creamy. Available in various flavors, and with seasonal fruit toppings.",
                2, 5, "cheesecake.jpg"));

    }

    private static void addItem(DataItem item) {
        dataItemList.add(item);
        dataItemMap.put(item.getItemId(), item);
    }

}
