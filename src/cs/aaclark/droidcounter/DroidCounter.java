package cs.aaclark.droidcounter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Adapter;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.AdapterView;
import android.widget.ListView;

public class DroidCounter extends Activity {

	private final int NEW_COUNTER = 1;
	private final int EDIT_COUNTER = 2;
	private final int DEL_COUNTER = 3;
	private final int AGR_STATS = 4;
	private final String filename = "droidcounter.sav";
	private static ReadWriteHandler rwHandler;

	protected ArrayList<CounterModel> counterModelArray = new ArrayList<CounterModel>(); 
	private ArrayAdapter<CounterModel> listViewAdapter;

	// onCreate from Activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_droid_counter);

		//Identify listView from Layout for ArrayAdapter
		ListView listView = (ListView) findViewById(R.id.listView);

		//Bind listView adapter
		listViewAdapter = new ArrayAdapter<CounterModel>(
				this,
				android.R.layout.simple_list_item_1,
				counterModelArray);
		listView.setAdapter(listViewAdapter);

		//Populate ListView
		try {
			rwHandler = new ReadWriteHandler(filename);
			counterModelArray = rwHandler.readIn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//...and let list items access context menus
		registerForContextMenu(listView);

		// This is for regular (not-context) clicks...
		listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, 
					int position, long id){
				CounterModel counter = (CounterModel) parent.getItemAtPosition(position);
				counter.increment();
				listViewAdapter.notifyDataSetChanged();
			}
		}
				);

	}

	// onResume from Activity
	protected void onResume(Bundle savedInstanceState){
		super.onResume();

		try {
			counterModelArray = rwHandler.readIn();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// applies Action Bar menu items

	//Top-Bar Menu Actions
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case(R.id.action_new_counter):
			CounterModel newCounter = new CounterModel("New Counter", 0);
		counterModelArray.add(newCounter);
		listViewAdapter.notifyDataSetChanged();
		return true;
		default: 
			return false;
		}
	}

	//Context Menu Actions
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, view, menuInfo);
		if(view.getId()==R.id.listView){
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
			menu.setHeaderTitle(counterModelArray.get(info.position).getName());
			menu.add(0, view.getId(), 0, "Edit");
			menu.add(0, view.getId(), 0, "Restart");
			menu.add(0, view.getId(), 0, "Statistics");
		}
	}

	//Don't remember what this does
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.droid_counter,menu);
		return super.onCreateOptionsMenu(menu);
	}

	private void editCounter(int requestCode) {
		Intent counterEditor = new Intent(this, EditCounterActivity.class);
		startActivityForResult(counterEditor, requestCode);		
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data){
		switch(requestCode){
		case(NEW_COUNTER):
			break;
		case(EDIT_COUNTER):
			break;
		case(DEL_COUNTER):
			break;
		case(AGR_STATS):
			break;
		}
	}


}
