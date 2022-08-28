# Add any ProGuard configurations specific to this
# extension here.

-keep public class com.aemo.screenofftime.ScreenOffTime {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'com/aemo/screenofftime/repack'
-flattenpackagehierarchy
-dontpreverify
