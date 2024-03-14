import java.util.HashMap;

public class main {
  public static void main(String[] args) {
    stringifyJSON test = new stringifyJSON();
    System.out.println(test.stringify(new HashMap<>(){{
      put("foo", true);
      put("bar", false);
      put("baz", null);
    }}));
  }
}
