package com.example.draggablerectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import static android.content.ContentValues.TAG;

public class CustomView extends View {

//    int flag=0;
    boolean check=false;

    Rect rect;
    int[] pointsx=new int[2];
    int[] pointsy=new int[2];
    int corner;


    Paint paint;
    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init()
    {
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);
        rect=new Rect();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int Xa=pointsx[0];
        int Xb=pointsx[1];
        if(Xa>Xb){
            pointsx[0]=Xb;
            pointsx[1]=Xa;
        }
        int Ya=pointsy[0];
        int Yb=pointsy[1];
        if (Ya>Yb){
            pointsy[0]=Yb;
            pointsy[1]=Ya;
        }
        if(check==false)
        {
            rect.set(getWidth()/2-250, getHeight()/2-250, getWidth()/2+250, getHeight()/2+250);
            canvas.drawRect(getWidth()/2-250, getHeight()/2-250, getWidth()/2+250, getHeight()/2+250,paint);
            pointsx[0]=getWidth()/2-250;
            pointsy[0]=getHeight()/2-250;
            pointsx[1]=getWidth()/2+250;
            pointsy[1]=getHeight()/2+250;

            check=true;
        }
        else
        {

            if(corner==1||corner==2||corner==3||corner==4)
            {
                rect.set(pointsx[0],pointsy[0],pointsx[1],pointsy[1]);
                canvas.drawRect(rect,paint);
            }
            else if(corner==5)
            {
                canvas.drawRect(rect,paint);


            }
            else if(corner==6)
            {rect.set(pointsx[0],pointsy[0],pointsx[1],pointsy[1]);
                canvas.drawRect(rect,paint);
            }
            else
            {
                canvas.drawRect(getWidth()/2-250, getHeight()/2-250, getWidth()/2+250, getHeight()/2+250,paint);
            }



        }





    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointX = (int)event.getX();
        int pointY = (int)event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if((pointX>pointsx[0]-60 && pointX<pointsx[0]+60)&&(pointY>pointsy[0]-60 && pointY<pointsy[0]+60))
                {
                    corner=1;


                }
                else if((pointX>pointsx[1]-60 && pointX<pointsx[1]+60)&&(pointY>pointsy[1]-60 && pointY<pointsy[1]+60))
                {
                    corner=2;

                }
                else if((pointX>pointsx[1]-60 && pointX<pointsx[1]+60)&&(pointY>pointsy[0]-60 && pointY<pointsy[0]+60))
                {
                    corner=3;

                }
                else if((pointX>pointsx[0]-60 && pointX<pointsx[0]+60)&&(pointY>pointsy[1]-60 && pointY<pointsy[1]+60))
                {
                    corner=4;
                }
                else if((pointX>pointsx[0]+100 && pointX<pointsx[1]-100)&&(pointY>pointsy[0]+100 && pointY<pointsy[1]-100))
                {
                    corner=5;


                }





                break;
            case MotionEvent.ACTION_MOVE:

                if(corner==1)
                {


                    pointsx[0] = pointX;
                    pointsy[0] = pointY;




                }
                else if(corner==2)
                {
                    pointsx[1]=pointX;
                    pointsy[1]=pointY;

                }
                else  if(corner==3)
                {
                    pointsx[1]=pointX;
                    pointsy[0]=pointY;
                }
                else if(corner==4)
                {
                    pointsx[0]=pointX;
                    pointsy[1]=pointY;
                }
                else if(corner==5) {


                    int dX = rect.width();
                    int dY =  rect.height();

                    if(pointX>dX/2 && pointY>dY/2 && (pointX+dX/2)<1085 && (pointY+dY/2)<2015)
                    {
                        rect.left = pointX - dX / 2;
                        rect.top = pointY - dY / 2;
                        rect.right = rect.left + dX;
                        rect.bottom = rect.top + dY;

                        pointsx[0] = rect.left;
                        pointsx[1] = rect.right;
                        pointsy[0] = rect.top;
                        pointsy[1] = rect.bottom;


                    }
                    else if(pointX<dX/2)
                    {
                        if(pointY>dY/2 && (pointY+dY/2)<2015)
                        {
                            rect.top = pointY - dY / 2;
                            rect.bottom = rect.top + dY;

                            pointsx[0] = rect.left;
                            pointsx[1] = rect.right;
                            pointsy[0] = rect.top;
                            pointsy[1] = rect.bottom;
                        }

                    }
                    else if((pointX+dX/2)>1085)
                    {
                        if(pointY>dY/2 && (pointY+dY/2)<2015)
                        {
                            rect.top = pointY - dY / 2;
                            rect.bottom = rect.top + dY;

                            pointsx[0] = rect.left;
                            pointsx[1] = rect.right;
                            pointsy[0] = rect.top;
                            pointsy[1] = rect.bottom;
                        }
                    }
                    else if(pointY<dY/2)
                    {
                        if(pointX>dX/2 && (pointX+dX/2)<1085)
                        {
                            rect.left = pointX - dX / 2;
                            rect.right = rect.left + dX;
                            pointsx[0] = rect.left;
                            pointsx[1] = rect.right;
                            pointsy[0] = rect.top;
                            pointsy[1] = rect.bottom;

                        }
                    }
                    else if((pointY+dY/2)>2015)
                    {
                        rect.left = pointX - dX / 2;
                        rect.right = rect.left + dX;
                        pointsx[0] = rect.left;
                        pointsx[1] = rect.right;
                        pointsy[0] = rect.top;
                        pointsy[1] = rect.bottom;
                    }

                }



                break;

            case MotionEvent.ACTION_UP:
                corner=6;

                break;

            default:
                return false;
        }

        postInvalidate();

        return true;
    }




}