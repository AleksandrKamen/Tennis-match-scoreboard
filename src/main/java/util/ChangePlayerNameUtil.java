package util;

import lombok.experimental.UtilityClass;
import java.util.Arrays;
import java.util.stream.Collectors;

@UtilityClass
public class ChangePlayerNameUtil {

    public String changePlayerNameForSearch(String name) {
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    public String changePlayerNameForWrite(String name) {
        return Arrays.stream(name.toLowerCase().trim()
                        .split(" "))
                .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1))
                .collect(Collectors.joining(" "));
    }
}
