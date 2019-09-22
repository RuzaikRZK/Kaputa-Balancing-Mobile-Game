package com.example.thilina.k_p_balance;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class kaputaBalanceView extends View {
   private Bitmap bird[] = new Bitmap[2];
   private Bitmap backgroundImage;

   private Bitmap cheese1;
   private Bitmap cherry1;
    private Bitmap nife1;
    private Bitmap town1;
    private Bitmap chiken1;

   private int birdX = 10;
   private int birdY;
   private int birdSpeed;

   private int canvesWidth;
   private int getCanvesHeight;



   private int cheeseX,cheeseY,cheeseSpeed =16;
   private int cherryX,cherryY,cherrySpeed =20;
   private int nifeX,nifeY,nifeSpeed =26;
    private int chikenX,chikenY,chikenSpeed =22;
    private int townX,townY,townSpeed =12;

   private int score,lifeCounterOfBird;

   private boolean touch = false;

   private Paint scorePaint = new Paint();


   private Bitmap life[] = new Bitmap[2];

    public kaputaBalanceView(Context context) {
        super(context);
        town1 = BitmapFactory.decodeResource(getResources(),R.drawable.town);
        bird[0] = BitmapFactory.decodeResource(getResources(),R.drawable.bird1);
        bird[1] = BitmapFactory.decodeResource(getResources(),R.drawable.bird2);
        backgroundImage = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        cheese1 = BitmapFactory.decodeResource(getResources(),R.drawable.cheese);
        cherry1 = BitmapFactory.decodeResource(getResources(),R.drawable.cherry);
        nife1 = BitmapFactory.decodeResource(getResources(),R.drawable.nife);
        chiken1 = BitmapFactory.decodeResource(getResources(),R.drawable.chiken);


        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(70);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);






        life[0] = BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);

        birdY = 700;
        score = 0;
        townY = 1300;
        lifeCounterOfBird=3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvesWidth = canvas.getWidth();
        getCanvesHeight = canvas.getHeight();
        canvas.drawBitmap(backgroundImage,0,0,null);

        int minBirdY = bird[0].getHeight();
        int maxBirdY = getCanvesHeight - bird[0].getHeight() * 3;

        birdY = birdY + birdSpeed;

        if(birdY<minBirdY){
            birdY = minBirdY;
        }

        if(birdY>maxBirdY){
            birdY = maxBirdY;
        }

        birdSpeed = birdSpeed +2;
        if (touch){

            canvas.drawBitmap(bird[1],birdX,birdY,null);
            touch = false;

        }else{
            canvas.drawBitmap(bird[0],birdX,birdY,null);
        }


        cheeseX =cheeseX - cheeseSpeed;

        if (hitFoodChecker(cheeseX,cheeseY)){
            score = score + 10;
            cheeseX = -100;
        }

        if (cheeseX<0){
            cheeseX = canvesWidth+21;
            cheeseY = (int) Math.floor(Math.random() * (maxBirdY-minBirdY))+minBirdY;
        }

        canvas.drawBitmap(cheese1,cheeseX,cheeseY,null);



        cherryX =cherryX - cherrySpeed;

        if (hitFoodChecker(cherryX,cherryY)){
            score = score + 15;
            cherryX = -100;
        }

        if (cherryX<0){
            cherryX = canvesWidth+21;
            cherryY = (int) Math.floor(Math.random() * (maxBirdY-minBirdY))+minBirdY;
        }

        canvas.drawBitmap(cherry1,cherryX,cherryY,null);

//

        chikenX =chikenX - chikenSpeed;

        if (hitFoodChecker(chikenX,chikenY)){
            score = score + 20;
            chikenX = -100;
        }

        if (chikenX<0){
            chikenX = canvesWidth+21;
            chikenY = (int) Math.floor(Math.random() * (maxBirdY-minBirdY))+minBirdY;
        }

        canvas.drawBitmap(chiken1,chikenX,chikenY,null);







        nifeX =nifeX - nifeSpeed;

        if (hitFoodChecker(nifeX,nifeY)){

            nifeX = -100;
            lifeCounterOfBird--;

            if (lifeCounterOfBird ==0){
                Toast.makeText(getContext(),"Game Over",Toast.LENGTH_SHORT);
                Intent gameOverIntent = new Intent(getContext(),GameOverActivity.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                getContext().startActivity(gameOverIntent);
            }
        }

        if (nifeX<0){
            nifeX = canvesWidth+21;
            nifeY = (int) Math.floor(Math.random() * (maxBirdY-minBirdY))+minBirdY;
        }

        canvas.drawBitmap(nife1,nifeX,nifeY,null);

        //=============town==========================


        townX =townX - townSpeed;

        if (hitFoodChecker(townX,townY)){

            townX = 300;
            lifeCounterOfBird--;

            if (lifeCounterOfBird ==0){
                Toast.makeText(getContext(),"Game Over",Toast.LENGTH_SHORT);
                Intent gameOverIntent = new Intent(getContext(),GameOverActivity.class);
                gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //gameOverIntent.putExtra("Score1",score);
                getContext().startActivity(gameOverIntent);
            }
        }

        if (townX<-3000){
            townX = canvesWidth+21;  //21
            townY = 1280;
        }

        canvas.drawBitmap(town1,townX,townY,null);

























        canvas.drawText("Score : "+score,20,60,scorePaint);


        for (int i=0;i<3;i++){
            int x = (int) (730 + life[0].getWidth()*1.2*i);
            int y =30;

            if (i < lifeCounterOfBird){
                canvas.drawBitmap(life[0],x,y,null);
            }else{
                canvas.drawBitmap(life[1],x,y,null);
            }
        }









    }

    public boolean hitFoodChecker(int x,int y){
        if (birdX<x && x<(birdX + bird[0].getWidth()) && birdY <y && y<(birdY + bird[0].getHeight())){
            return  true;
        }
        return false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            touch = true;

            birdSpeed = -22;

        }

        return true;
    }
}
