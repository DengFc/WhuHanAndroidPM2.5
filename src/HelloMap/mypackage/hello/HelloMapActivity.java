//package HelloMap.mypackage.hello;
//
//import android.app.Activity;
//import android.os.Bundle;
//
//
//import com.esri.android.map.MapView;
//import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
//
//
//public class HelloMapActivity extends Activity {
//	
//	MapView mMapView;
//
//    /** Called when the activity is first created. */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.main);
////
////		mMapView = new MapView(this);
//  super.onCreate(savedInstanceState);
//     setContentView(R.layout.main);
//     mMapView=(MapView)this.findViewById(R.id.map);
//     mMapView.addLayer(new ArcGISTiledMapServiceLayer(
//   "http://cache1.arcgisonline.cn/ArcGIS/rest/services/ChinaCities_Community_BaseMap_CHN/BeiJing_Community_BaseMap_CHN/MapServer"));
//
//    }
//
//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
// }

//	@Override
//	protected void onPause() {
//		super.onPause();
//		mMapView.pause();
// }
//	@Override
//	protected void onResume() {
//		super.onResume();
//		mMapView.unpause();
//	}
//
//}



/* Copyright 2012 ESRI
*
* All rights reserved under the copyright laws of the United States
* and applicable international laws, treaties, and conventions.
*
* You may freely redistribute and use this sample code, with or
* without modification, provided you include the original copyright
* notice and use restrictions.
*
* See the Sample code usage restrictions document for further information.
*
*/

package HelloMap.mypackage.hello;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;

import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISLocalTiledLayer;


/**
* The HelloWorld app is the most basic Map app for the ArcGIS Runtime SDK for Android. It shows how to define a MapView
* in the layout XML of the activity. Within the XML definition of the MapView, MapOptions attributes are used to
* populate that MapView with a basemap layer showing streets, and also the initial extent and zoom level are set. By
* default, this map supports basic zooming and panning operations. This sample also demonstrates calling the MapView
* pause and unpause methods from the Activity onPause and onResume methods, which suspend and resume map rendering
* threads. A reference to the MapView is set within the onCreate method of the Activity which can be used at the
* starting point for further coding.
*/

public class HelloMapActivity extends Activity {
 MapView mMapView;
 ArcGISLocalTiledLayer local;  
 // Called when the activity is first created.
 @Override
 public void onCreate(Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   setContentView(R.layout.main);

   // After the content of this Activity is set, the map can be accessed programmatically from the layout.
   mMapView = (MapView) findViewById(R.id.map);
   
   local = new ArcGISLocalTiledLayer("file:///storage/sdcard0/YX1.tpk"); // 加载tpk文件 
   mMapView.addLayer(local);

 }

 
 @Override
 protected void onPause() {
   super.onPause();

   // Call MapView.pause to suspend map rendering while the activity is paused, which can save battery usage.
   if (mMapView != null)
   {
     mMapView.pause();
   }
 }

 @Override
 protected void onResume() {
   super.onResume();

   // Call MapView.unpause to resume map rendering when the activity returns to the foreground.
   if (mMapView != null)
   {
     mMapView.unpause();
   }
 }
 
 @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			setDialogActivityExit("温馨提示！", "您确认退出程序吗？");
		}
		return super.onKeyDown(keyCode, event);
	}

	// 自定义弹出对话框并选择是否退出程序
	public void setDialogActivityExit(String title, String message) {
		AlertDialog.Builder builder = new Builder(HelloMapActivity.this); // HelloActivity是当前Activity
		builder.setMessage(message);
		builder.setTitle(title);
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
//				HaiMapText1Activity.this.finish(); // 结束当前运行的Activity
				HelloMapActivity.this.finish();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.show();
	}

}