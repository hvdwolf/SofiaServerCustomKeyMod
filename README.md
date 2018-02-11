# SofiaServerCustomKeyMod
This xposed module captures the hardware keys from the Joying unit and allows you to reprogram them.
This should replace the custom key mod from Gustden. It mimics the same behavior and therefore also needs a launcher.sh
in contradiction to the mods of Gustden, these Xposed modules do not touch the original SofiaServer binary and are therefore firmware version independent.

# Considered stable

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
