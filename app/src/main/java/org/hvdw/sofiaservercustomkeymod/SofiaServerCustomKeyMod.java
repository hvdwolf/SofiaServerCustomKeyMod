package org.hvdw.sofiaservercustomkeymod;

import android.util.Log;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.app.AndroidAppHelper;

import de.robv.android.xposed.XposedBridge;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
/*
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import static de.robv.android.xposed.XposedHelpers.getBooleanField;
import static de.robv.android.xposed.XposedHelpers.getByteField;
import static de.robv.android.xposed.XposedHelpers.getIntField;
import static de.robv.android.xposed.XposedHelpers.assetAsByteArray;
import static de.robv.android.xposed.XposedHelpers.getParameterTypes;
import static de.robv.android.xposed.XposedHelpers.getObjectField;
import static de.robv.android.xposed.XposedHelpers.getStaticObjectField;
import static de.robv.android.xposed.XposedHelpers.setIntField;
*/

public class SofiaServerCustomKeyMod implements IXposedHookLoadPackage {
	public static final String TAG = "SofiaServerCustomKeyMod";
	//public Context context;
	private static PackageManager pm;

	public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
		XposedBridge.log("Loaded app: " + lpparam.packageName);
		if (!lpparam.packageName.equals("com.syu.ms")) return;

/**********************************************************************************************************************************************/
		/* This is the No Kill function */
		findAndHookMethod("app.ToolkitApp", lpparam.classLoader, "killAppWhenSleep", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				XposedBridge.log(TAG + "skipping method killAppWhenSleep");
				Log.d(TAG, "skipping method killAppWhenSleep");
				param.setResult(null);
			}
		});


/**********************************************************************************************************************************************/
		/* Below are the captured key functions */
		findAndHookMethod("app.HandlerApp", lpparam.classLoader, "wakeup", new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				XposedBridge.log(TAG + " Execute the RESUME action to the launcher.sh");
				Log.d(TAG, "Execute the RESUME action to the launcher.sh");
				onItemSelectedp(99);
			}
		});


		findAndHookMethod("module.main.HandlerMain", lpparam.classLoader, "mcuKeyBtPhone", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				XposedBridge.log(TAG + " Execute the mcuKeyBtPhone action to the launcher.sh");
				Log.d(TAG, "Execute the mcuKeyBtPhone action to the launcher.sh");
				onItemSelectedp(27);
				param.setResult(null);
			}
		});

		findAndHookMethod("module.main.HandlerMain", lpparam.classLoader, "mcuKeyNavi", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				XposedBridge.log(TAG + " mcuKeyNavi  pressed; forward action to the launcher.sh");
				Log.d(TAG, "mcuKeyNavi pressed; forward action  to the launcher.sh");
				//onItemSelectedp(9);
				Context context = (Context) AndroidAppHelper.currentApplication();
				startActivityByPackageName(context, "com.generalmagic.magicearth");
				//startActivityByPackageName(context, "com.mapfactor.navigator");
				param.setResult(null);
			}
		});

		findAndHookMethod("module.main.HandlerMain", lpparam.classLoader, "mcuKeyBand", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				XposedBridge.log(TAG + " mcuKeyBand (Radio) pressed; forward action to the launcher.sh");
				Log.d(TAG, "mcuKeyBand (Radio) pressed; forward action to the launcher.sh");
				onItemSelectedp(34);
				param.setResult(null);
			}
		});


		findAndHookMethod("module.main.HandlerMain", lpparam.classLoader, "mcuKeyMode", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				XposedBridge.log(TAG + " Source/Mode pressed; forward action  to the launcher.sh");
				Log.d(TAG, "Source/Mode pressed; forward action  to the launcher.sh");
				onItemSelectedp(37);
				param.setResult(null);
			}
		});


		findAndHookMethod("util.JumpPage", lpparam.classLoader, "audioPlayer", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				XposedBridge.log(TAG + " Media button pressed; forward action  to the launcher.sh");
				Log.d(TAG, "Media button pressed; forward action  to the launcher.sh");
				onItemSelectedp(33);
				param.setResult(null);
			}
		});

		findAndHookMethod("util.JumpPage", lpparam.classLoader, "eq", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				XposedBridge.log(TAG + " EQ button pressed; forward action  to the launcher.sh");
				Log.d(TAG, "EQ button pressed; forward action  to the launcher.sh");
				//onItemSelectedp(33);
				Context context = (Context) AndroidAppHelper.currentApplication();
				startActivityByPackageName(context, "com.google.android.googlequicksearchbox"); // Google Voice Search
				param.setResult(null);
			}
		});


		findAndHookMethod("dev.ReceiverMcu", lpparam.classLoader, "onHandle", byte[].class, int.class, int.class, new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				//byte[] data  = getByteField(param.thisObject, "byte[].class");
				byte[] data =  (byte[]) param.args[0];
				/* int start = getIntField(param.thisObject, "start");
				int length = getIntField(param.thisObject, "length"); */
				int start = (int) param.args[1];
				int length = (int) param.args[2];
				byte b = data[start];

				//Log.d(TAG, "DVD or eject button; Executed the Media action to the launcher.sh");
				if ((b & 255) == 1 && (data[start + 1] & 255) == 0 && (data[start + 2] & 255) == 16 && (data[start + 3] & 255) == 80) {
					XposedBridge.log(TAG + " DVD button pressed; forward action  to the launcher.sh");
					Log.d(TAG, "DVD button pressed; forward action  to the launcher.sh");
					onItemSelectedp(31);
				}
				if ((b & 255) == 1 && (data[start + 1] & 255) == 161 && (data[start + 2] & 255) == 2 && (data[start + 3] & 255) == 91) {
					XposedBridge.log(TAG + " EJECT; forward action  to the launcher.sh");
					Log.d(TAG, "EJECT button pressed; forward action  to the launcher.sh");
					onItemSelectedp(32);
				}
			}
		});

		findAndHookMethod("util.JumpPage", lpparam.classLoader, "broadcastByIntentName", String.class, new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				String actionName = (String) param.args[0];
				XposedBridge.log(TAG + " broadcastByIntentName in util.JumpPage afterHooked " + actionName);
				Log.d(TAG, "broadcastByIntentName in util.JumpPage afterHooked " + actionName);
				if (actionName == "com.glsx.boot.ACCON") {
					Log.d(TAG, "ACC_ON command received");
					XposedBridge.log(TAG + " ACC_ON command received");
					onItemSelectedp(97);
				}
				if (actionName == "com.glsx.boot.ACCOFF") {
					Log.d(TAG, "ACC_OFF command received");
					XposedBridge.log(TAG + " ACC_OFF command received");
					onItemSelectedp(98);
				}
			}
		});
		/* End of the capture key functions */
/**********************************************************************************************************************************************/
	}

	private static void onItemSelectedp(int input) {
		StringBuffer output = new StringBuffer();
		String cmd = "/data/launcher.sh " + input + " ";
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			Log.d("MCUKEY", cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

	public void startActivityByPackageName(Context context, String packageName) {
		PackageManager pManager = context.getPackageManager();
		Intent intent = pManager.getLaunchIntentForPackage(packageName);
		if (intent != null) {
			context.startActivity(intent);
		}
	}

}
