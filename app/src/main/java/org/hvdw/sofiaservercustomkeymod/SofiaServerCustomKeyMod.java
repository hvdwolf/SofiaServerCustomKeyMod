package org.hvdw.sofiaservercustomkeymod;

import android.util.Log;
import android.content.Intent;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;

import de.robv.android.xposed.XposedBridge;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import static de.robv.android.xposed.XposedHelpers.getBooleanField;
import static de.robv.android.xposed.XposedHelpers.getByteField;
import static de.robv.android.xposed.XposedHelpers.getIntField;
import static de.robv.android.xposed.XposedHelpers.assetAsByteArray;
import static de.robv.android.xposed.XposedHelpers.getParameterTypes;
import static de.robv.android.xposed.XposedHelpers.getObjectField;
import static de.robv.android.xposed.XposedHelpers.getStaticObjectField;
import static de.robv.android.xposed.XposedHelpers.setIntField;

public class SofiaServerCustomKeyMod implements IXposedHookLoadPackage {
	public static final String TAG = "SofiaServerCustomKeyMod";
	private Context context;
	// The variables I need from SofiaServer
	public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
		XposedBridge.log("Loaded app: " + lpparam.packageName);
		if (!lpparam.packageName.equals("com.syu.ms")) return;

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
				onItemSelectedp(9);
				param.setResult(null);
			}
		});

		findAndHookMethod("module.main.HandlerMain", lpparam.classLoader, "mcuKeyBand", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				XposedBridge.log(TAG + " mcuKeyBand (Radio) pressed; forward action to the launcher.sh");
				Log.d(TAG, "mcuKeyBand (Radio) pressed; forward action to the launcher.sh");
				onItemSelectedp(34);
				//PackageManager pm = context.getPackageManager();
				//Intent LaunchIntent = pm.getLaunchIntentForPackage("com.generalmagic.magicearth");
				//content.startActivity( LaunchIntent );
				//startNewActivity(context, "com.generalmagic.magicearth");
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

		findAndHookMethod("util.JumpPage", lpparam.classLoader, "dvd", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				XposedBridge.log(TAG + " DVD button pressed; forward action  to the launcher.sh");
				Log.d(TAG, "DVD button pressed; forward action  to the launcher.sh");
				onItemSelectedp(31);
				param.setResult(null);
			}
		});

		findAndHookMethod("bsp.HandlerBspKey", lpparam.classLoader, "bspKeyEject", int.class, new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
				XposedBridge.log(TAG + " EJECT; forward action  to the launcher.sh");
				Log.d(TAG, "EJECT button pressed; forward action  to the launcher.sh");
				onItemSelectedp(32);
				param.setResult(null);
			}
		});

/* Not correct
		findAndHookMethod("bsp.HandlerBspKey", lpparam.classLoader, "bspKeyMute", new XC_MethodHook() {
			@Override
			protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
				XposedBridge.log(TAG + " MUTE; forward action  to the launcher.sh");
				Log.d(TAG, "MUTE button pressed; forward action  to the launcher.sh");
				onItemSelectedp(32);
				param.setResult(null);
			}
		}); */

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


	}

	public static void onItemSelectedp(int input) {
		StringBuffer output = new StringBuffer();
		String cmd = "/data/launcher.sh " + input + " ";
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			Log.d("MCUKEY", cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	};

/*	public static void keytrace2(int input, int input2, int input3, int input4) {
		if (input == 1 && input2 == 0 && input3 == 16 && input4 == 80) {
			Log.d(TAG, "DVD button pressed; Execute the DVD action to the launcher.sh");
			onItemSelectedp(31);
		}
		if (input == 1 && input2 == 161 && input3 == 2 && input4 == 91) {
			Log.d(TAG, "Eject button pressed; Execute the Eject action to the launcher.sh");
			onItemSelectedp(32);
		}
	};

	public static void sendKeyCode(int input) {
		StringBuffer output = new StringBuffer();
		String cmd = "input keyevent " + input + " ";
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			Log.d("MCUKEY", cmd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	public void startNewActivity(Context context, String packageName) {
		Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
		if (intent == null) {
			// Bring user to the market or let them choose an app?
			Intent intnt = new Intent(Intent.ACTION_VIEW);
			intnt.setData(Uri.parse("market://details?id=" + packageName));
			//Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
			//context.startActivity(intent);
		}
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

}
