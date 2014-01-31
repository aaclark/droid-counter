package cs.aaclark.droidcounter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

	private int NEW_COUNTER = 1;
	private int EDIT_COUNTER = 2;
	private int DEL_COUNTER = 4;
	private int AGR_STATS = 8;
	
	protected ArrayList<CounterModel> counterModelArray = new ArrayList<CounterModel>(); 
	private ArrayAdapter<CounterModel> listViewAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_droid_counter);
		
		//Identify listView from Layout for ArrayAdapter
		ListView listView = (ListView) findViewById(R.id.listView);
		//Create an ArrayList into which we will load
		//our counters (from file)
		// TODO Implement loadCounters(file);
		
		//Bind a suitably initialized adapter to our listView
		//Set this as our listView's adapter
		listViewAdapter = new ArrayAdapter<CounterModel>(
				this,
				android.R.layout.simple_list_item_1,
				counterModelArray);
		listView.setAdapter(listViewAdapter);
				
		//...and let list items access context menus
		registerForContextMenu(listView);
		
		// This is for regular (not-context) clicks...
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
			}
		});
		
		/*
		// ...and this is for long clicks.
		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		*/
		
	}
	
	//Top-Bar Menu Actions
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		// applies Action Bar menu items
		switch(item.getItemId()){
		case(R.id.action_new_counter):
			//'+' button adds new counters
			// editCounter(NEW_COUNTER);
			counterModelArray.add(new CounterModel("name", 0));
			listViewAdapter.notifyDataSetChanged();
			return true;
		case(R.id.action_edit_counter):
			//'+' button adds new counters
			editCounter(EDIT_COUNTER);
			return true;
		default: 
			return false;
		}
	}
	
	//Context Menu Actions
	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, view, menuInfo);
		menu.setHeaderTitle("Actions");
		menu.add(0, view.getId(), 0, "Type");
		
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


}
