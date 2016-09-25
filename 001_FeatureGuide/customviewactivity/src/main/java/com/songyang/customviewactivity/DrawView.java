package com.songyang.customviewactivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by songy on 2016/7/15.
 */
public class DrawView extends View {
    //定义并创建画笔
    private float currentX=40;
    private float currentY=50;
    Paint paint=new Paint();
    public  DrawView(Context context){
        super(context);
    };
    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画笔颜色
        paint.setColor(Color.RED);
        //绘制一个小球
        canvas.drawCircle(currentX,currentY,100,paint);

    }
    //为该组件的触碰事件重写事件处理

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //修改currentX，currentY的两个属性
        currentX=event.getX();
        currentY=event.getY();
        //通知当前组件重绘自己
        invalidate();
        //返回true表明该方法已经处理该事件
        return true;

    }
}
