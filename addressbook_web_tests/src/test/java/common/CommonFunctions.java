package common;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
  public static String randomString1(int n) {
    var rnd = new Random();
    var result = "";
    for (int i = 1; i < n; i++) {
      result = result + (char)('a' + rnd.nextInt(26));
    }
    return result;
  }

  public static String randomFile(String dir) {
    var fileNames = new File(dir).list();
    var rnd = new Random();
    var index = rnd.nextInt(fileNames.length);
    return Paths.get(dir, fileNames[index]).toString();
  }

  public static String randomString(int n) {
    var rnd = new Random();
    Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);
    var result = Stream.generate(randomNumbers)
            .limit(n)
            .map(i -> 'a' + i)
            .map(i -> Character.toString(i))
            .collect(Collectors.joining());
    return result;
  }
}
