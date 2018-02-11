# SofiaServerCustomKeyMod
This xposed module captures the hardware keys from the Joying unit and allows you to reprogram them.
This should replace the custom key mod from Gustden. It mimics the same behavior and therefore also needs a launcher.sh
in contradiction to the mods of Gustden, these Xposed modules do not touch the original SofiaServer binary and are therefore firmware version independent.

**Big thanks to Gustden who analyzed which functions to hook (or directly mod in his case) and how to apply them in the launcher.sh script**

## This Xposed module is considered stable
It will stay as it is right now. No further development will take place, only bug fixing when necessary.
The module that is under development is the XSofia Tweaker Xposed module.


**Working:** 
* ACC_ON/ACC_OF (key 97 and 98)
* Wake_up/resume (key 99)
* NAVI (key 9)
* Phone/BT(key 27) (confirmed by gtx(aspec))
* BAND (radio) (key 34)
* DVD (key 31) (confirmed by gtx(aspec))
* Eject (key 32) (confirmed by gtx(aspec))
* MEDIA (key 33)
* SRC/Mode (key 37) (confirmed by gtx(aspec))
* double tap/triple tap of keys, meaning that you can "rotate" keys. For example: Your steering wheel BT or Mode/SRC button, can start the radio app on 1 tap, start a media player on 2 taps, or start the phone app on 3 taps.


This Xposed module also contains the NoKill functionality. The SofiaServer kills almost all apps when going into deep-sleep. The NoKill option skips this "kill all apps" function.

Note: Some apps prevent the unit from going into deep-sleep. The old SofiaServer simply killed these apps. This module doesn't do that. The MCU detects the apps keeping the CPU cores at higher frequency and can completely switch off the unit, resulting in a cold-boot upon switching on the contact.
That is not the fault of this module. It is the fault of these bad behaving apps.
A way to overcome this is to kill this specific bad behaving app(s) on ACC_OFF event (key 98), and to start them again on the ACC_ON event (key 97).
