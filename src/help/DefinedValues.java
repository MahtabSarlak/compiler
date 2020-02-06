package help;

import java.nio.file.Paths;

public class DefinedValues {
    public static String nameClass = "$Main";
    public static boolean DEBUG = false;
    public static String compilePath = Paths.get("compiled/").toAbsolutePath().toString();
    private static boolean GLOBAL = true;

    public static boolean getScope() {
        return DefinedValues.GLOBAL;
    }

    public static void changeScope() {
        DefinedValues.GLOBAL = !DefinedValues.GLOBAL;
    }
}
