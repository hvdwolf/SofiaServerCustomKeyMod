# SofiaServerCustomKeyMod
This xposed module captures the hardware keys from the Joying unit and allows you to reprogram them.
This should replace the custom key mod mod from Gustden. It mimics the same behavior and therefore also needs a launcher.sh

# Experimental !!

**Working:** 
* ACC_ON/ACC_OF (key 97 and 98)
* Wake_up/resume (key 99)
* NAVI (key 9)
* BAND (radio) (key 34)

**Working, but with strange side effects:**
* MEDIA (33); Works as such but disables hardware keys for approx 1 minute, then mutes sound and unmutes sound (2-3 seconds), activates hardware keys again.

**Untested:**
* Phone/BT(key 27) (I don't have steering wheel keys)
* DVD (key 31) (I don't have that hardware button) => might not be correct. SofiaServer has 2 functions for it ??
* Eject (32) (I don't have that key)
* SRC/Mode (37) (I don't have steering wheel keys)

**ToDo list:**
* HOME (key 3)
* BACK (key 4)
* Dimmer key
* Settings page
* ....
