package com.can.lychee;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.can.uilib.lychee.LycheeFragment;
import com.can.lychee.R;
import android.os.Bundle;


public class LycheeActivity extends SherlockFragmentActivity {

	private LycheeFragment mFragment;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_lychee);
        
		mFragment = new LycheeFragment();

		getSupportFragmentManager().beginTransaction().replace(R.id.lychee_act, mFragment).commit();
        
        
    }
    
}
