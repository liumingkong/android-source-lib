package wyb.ui;

import java.io.BufferedInputStream;
import java.net.URL;


import dd.dd.R;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity{
    /** Called when the activity is first created. */
	ImageView imageView;
	TextView textView;
	Display display;
	Bitmap mBitmap;
	String url="http://hiphotos.baidu.com/baidu/pic/item/f603918fa0ec08fabf7a641659ee3d6d55fbda0d.jpg";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        imageView=(ImageView) findViewById(R.id.logo_bg);
        textView=(TextView) findViewById(R.id.tv);
        display = getWindowManager().getDefaultDisplay();
        //Ҳ��һ���첽����ͼƬ�ķ���
        new Thread(new Runnable() {
	        public void run() {
	        	try {
					mBitmap = getBitmapFromUrl();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	imageView.post(new Runnable() {
	                public void run() {
	                	imageView.setImageBitmap(mBitmap);
	                }
	            });
	        }
	    }).start();
        imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				initPopWindow();
			}
		});
    }
    private void initPopWindow() {  
        // ����popupWindow�Ĳ����ļ�  
        View contentView = LayoutInflater.from(getApplicationContext())  
                .inflate(R.layout.popup, null);  
        // ����popupWindow�ı�����ɫ  
        contentView.setBackgroundColor(Color.BLACK);
        // ����һ��������  
        final PopupWindow popupWindow = new PopupWindow(  
        		 contentView, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);  
         popupWindow.setFocusable(true);
         popupWindow.showAtLocation(textView, Gravity.CENTER_VERTICAL, 0, 0);  
         ImageView imageView=(ImageView) contentView.findViewById(R.id.logo_b);
         int width = display.getWidth()-50;
         int height = display.getHeight()-50;
         //��ͼƬ����Ϊ��100����200��������Ϳ���ʵ��ͼƬ�Ĵ�С����
          Bitmap resize=Bitmap.createScaledBitmap(mBitmap, height, width, true);
          Matrix m=new Matrix();
          m.setRotate(90); //��ʱ����ת15��
          //������ת���С֮�����´���λͼ��0-width�������ʾ�����򣬸߶�����
          Bitmap b=Bitmap.createBitmap(resize, 0, 0, height, width, m, true);
          //��ʾͼƬ
         imageView.setImageBitmap(b);
         imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popupWindow.dismiss();
			}
		});
    }
    /**
	 * ��urlת��Ϊbitmap(ͼƬ)
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public  Bitmap getBitmapFromUrl() throws Exception {
		URL mUrl = new URL(url);
		BufferedInputStream bis = new BufferedInputStream(mUrl.openConnection().getInputStream());
		Bitmap bitmap = BitmapFactory.decodeStream(bis);
		bis.close();
		return bitmap;
	}
	
}