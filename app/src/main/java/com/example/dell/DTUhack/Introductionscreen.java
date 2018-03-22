package com.example.dell.DTUhack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughActivity;
import com.shashank.sony.fancywalkthroughlib.FancyWalkthroughCard;

import java.util.ArrayList;

public class Introductionscreen extends FancyWalkthroughActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // setContentView(R.layout.activity_main);

        FancyWalkthroughCard card1 = new FancyWalkthroughCard("Priority Pass To All Gym & Studios", "Gym,Yoga,Zumba,Pilates,Crossfit and many more!");

        FancyWalkthroughCard card2 = new FancyWalkthroughCard("Personalized Diet Plans", "Get guaranteed results with our proactive diet plans!"

        );

        FancyWalkthroughCard card3 = new FancyWalkthroughCard("Get Fit and Stay Fit", "Your 360 degree fitness and lifestyle solution.Everything you need, on your fingertips!");


        card1.setBackgroundColor(R.color.white);

        card1.setIconLayoutParams(300, 300, 0, 0, 0, 0);


        card2.setBackgroundColor(R.color.white);

        card2.setIconLayoutParams(300, 300, 0, 0, 0, 0);

        card3.setBackgroundColor(R.color.white);

        card3.setIconLayoutParams(300, 300, 0, 0, 0, 0);


        ArrayList<FancyWalkthroughCard> pages = new ArrayList<>();

        pages.add(card1);

        pages.add(card2);

        pages.add(card3);


        for (FancyWalkthroughCard card : pages) {

            card.setDescriptionColor(R.color.black);

            card.setTitleColor(R.color.black);

        }

        setFinishButtonTitle("Get Fit Now!");

        showNavigationControls(true);

        setOnboardPages(pages);

        setImageBackground(R.drawable.gym);

    }

    @Override
    public void onFinishButtonPressed() {

        startActivity(new Intent(this, LoginActivity.class));
    }

}
