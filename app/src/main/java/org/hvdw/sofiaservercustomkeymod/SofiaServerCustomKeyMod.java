package org.hvdw.sofiaservercustomkeymod;

import android.util.Log;

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
	// The variables I need from SofiaServer
	public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

		if (!lpparam.packageName.equals("com.syu.ms")) return;

		findAndHookMethod("app.HandlerApp", lpparam.classLoader, "wakeup", new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				Log.d(TAG, "Execute the wakeup action to the launcher.sh");
				onItemSelectedp(99);
			}
		});


		findAndHookMethod("module.main.HandlerMain", lpparam.classLoader, "mcuKeyBtPhone", new XC_MethodHook() {
			protected void replaceHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				Log.d(TAG, "Execute the mcuKeyBtPhone action to the launcher.sh");
				onItemSelectedp(27);
			}
		});

		findAndHookMethod("module.main.HandlerMain", lpparam.classLoader, "mcuKeyNavi", new XC_MethodHook() {
			protected void replaceHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				Log.d(TAG, "Execute the mcuKeyNavi action to the launcher.sh");
				onItemSelectedp(9);
			}
		});

		findAndHookMethod("module.main.HandlerMain", lpparam.classLoader, "mcuKeyBand", new XC_MethodHook() {
			protected void replaceHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				Log.d(TAG, "Execute the mcuKeyBand (Radio) action to the launcher.sh");
				onItemSelectedp(34);
			}
		});


		findAndHookMethod("module.main.HandlerMain", lpparam.classLoader, "mcuKeyMode", new XC_MethodHook() {
			protected void replaceHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				Log.d(TAG, "Execute the Source/Mode action to the launcher.sh");
				onItemSelectedp(37);
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

				Log.d(TAG, "MEDIA button; Execute the Media action to the launcher.sh");
				keytrace2(b & 255, data[start + 1] & 255, data[start + 2] & 255, data[start + 3] & 255);
				//IReceiverEx receiver; //Infra Red receiver??? if so, simply skip.
			}
		});

/*		findAndHookMethod("util.ToolkitPlatform", lpparam.classLoader, "accStatusFor3Others", new XC_MethodHook() {
			@Override
			protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
				Log.d(TAG, "Execute the accStatusFor3Others action to the launcher.sh");
				int accOn = getIntField(param.thisObject, "accOn");
				if (accOn != 0) {
					Log.d(TAG, "ACC_ON command received");
					onItemSelectedp(97);
					return;
				}
				Log.d(TAG, "ACC_OFF command received");
				onItemSelectedp(98);
			}
		});
*/

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

	public static void keytrace2(int input, int input2, int input3, int input4) {
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

}
