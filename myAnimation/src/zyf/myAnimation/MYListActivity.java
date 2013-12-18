package zyf.myAnimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class MYListActivity extends Activity implements OnItemClickListener,
		OnItemSelectedListener {
	private ListView mylist;
	private ArrayAdapter<String> arrayAdapter;
	private String[] contentString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout);
		contentString = new String[] { 
				"ʾ��", "͸������",
				"��������", "�ƶ�����",
				"��ת����", "͸��_����",
				"͸��_�ƶ�", "͸��_��ת",
				"����_�ƶ�","����_��ת",
				"�ƶ�_��ת", "͸��_����_�ƶ�",
				"͸��_����_��ת", "͸��_�ƶ�_��ת",
				"����_�ƶ�_��ת",
				"͸��_����_�ƶ�_��ת", "myown_Design "
	};
		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_single_choice,
				contentString);
		mylist = (ListView) findViewById(R.id.ListView01);
		mylist.setAdapter(arrayAdapter);
		mylist.setOnItemClickListener(this);
		mylist.setOnItemSelectedListener(this);
		mylist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
		// TODO Auto-generated method stub
		Intent back_intent= new Intent();
		Bundle back_Bundle=new Bundle();
		
		back_Bundle.putInt("INDEX", index);	
		back_intent.putExtras(back_Bundle);
		setResult(RESULT_OK, back_intent);
		finish();
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		setTitle(arrayAdapter.getItem(arg2));
		mylist.setItemChecked(arg2, true);
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
}
