import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.ls.LSOutput;

import java.util.*;


public class stringifyJSON {

  public String ObjectMapper(Object data) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(data);
  }

  public String stringify(Object data) {

    //입력된 값이 문자열일 경우
    if(data instanceof String)

      return "\""+data.toString()+"\"";

    //입력된 값이 Integer일 경우
    if( data instanceof Integer)
      return data.toString();

    //입력된 값이 Boolean일 경우
    if( data instanceof Boolean)
      return data.toString();

    //입력된 값이 Object[]일 경우
    if( data instanceof Object[]) {
      Object[] newData = (Object[]) data;
      Object[] arr = new Object[newData.length];
      for (int i = 0; i< newData.length;i++) {
          newData[i] = stringify(newData[i]);
          arr[i] = newData[i];

      }
      String result = Arrays.toString(arr).replaceAll(" ","");
      return result;

    }


    //입력된 값이 HashMap일 경우
    if(data instanceof HashMap) {
      HashMap<Object, Object> newHash= (HashMap<Object, Object>) data;
      Set<Map.Entry<Object,Object>> entrySet = newHash.entrySet();
      HashMap<Object,Object> newMap = new LinkedHashMap<>();

      for(Map.Entry<Object,Object> e : entrySet) {
       Object key = stringify(e.getKey());
       Object value = stringify(e.getValue());
        newMap.put(key,value);
      }

      return  newMap.toString().replaceAll("=", ":").replaceAll(" ","");

    }

    //지정되지 않은 타입의 경우에는 "null"을 리턴합니다.
    return "null";
  }
}
