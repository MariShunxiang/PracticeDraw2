package com.hencoder.hencoderpracticedraw2.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.hencoder.hencoderpracticedraw2.R;

public class Practice05ComposeShaderView extends View {
  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

  public Practice05ComposeShaderView(Context context) {
    super(context);
  }

  public Practice05ComposeShaderView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public Practice05ComposeShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  {
    setLayerType(LAYER_TYPE_SOFTWARE, null); // 硬件加速下 ComposeShader 不能使用两个同类型的 Shader

    // 用 Paint.setShader(shader) 设置一个 ComposeShader
    // Shader 1: BitmapShader 图片：R.drawable.batman
    // Shader 2: BitmapShader 图片：R.drawable.batman_logo

    Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
    //int width = bitmap1.getWidth();
    //int height = bitmap1.getHeight();
    //int newWidth = 200;
    //int newHeight = 200;
    //// 计算缩放比例
    //float scaleWidth = ((float) newWidth) / width;
    //float scaleHeight = ((float) newHeight) / height;
    //Log.i(" tag ", "width : " + width + " , height : " + height);
    //// 取得想要缩放的matrix参数
    //Matrix matrix = new Matrix();
    //matrix.postScale(scaleWidth, scaleHeight);
    //// 得到新的图片
    //Bitmap newbm = Bitmap.createBitmap(bitmap1, 0, 0, width, height, matrix, true);
    Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

    //Shader shader1 =  new RadialGradient(200, 200, 200, Color.parseColor("#E91E63"), Color.parseColor("#2196F3"),
    //    Shader.TileMode.CLAMP);

    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
    Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

    Shader shader = new ComposeShader(shader1, shader2, PorterDuff.Mode.SRC_OUT);
    paint.setShader(shader);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    canvas.drawCircle(200, 200, 200, paint);
    //canvas.drawRect(0, 0, 200, 200, paint);
  }
}
