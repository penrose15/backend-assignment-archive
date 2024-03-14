import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * 1. Browser에 존재하는 stringify 함수를 직접 구현해 봅니다.
 * stringify 함수는 input 값을 JSON 형식으로 변환합니다.
 *
 * 2. stringify는 아래와 같이 작동합니다.
 * - Boolean이 input으로 주어졌을 경우
 * stringify(true);                // "true"
 * - String이 input으로 주어졌을 경우
 * stringify("foo");               // "foo"
 * - null이 주어졌을 경우
 * stringify(null)                 // "null"
 * - HashMap이 input으로 주어졌을 경우
 * HashMap<Object, Object> map = new HashMap<>();
 * map.put("null", 2);
 * map.put("true", "false");
 * stringify(map);                // "{"null":2,"true":"false"}"
 * Map 자료형의 Key는 항상 String으로 저장됩니다. null은 입력할 수 없습니다.
 *
 * 예시에 해당되지 않는 자료형의 경우 모두 null을 반환합니다.
 *
 * 3. test/java/stringifyJSON_test.java의 테스트에서 어떤 input 값들이 주어지고, 어떻게 stringify 주어야 할지 생각해 보세요.
 *
 *
 * 4. 입력받은 전달인자의 타입은 instanceof 메서드를 활용합니다.
 *
 * 5. 그냥 테스트 통과를 하고 싶으시다면, 다음과 같이 구현하면 될 거예요.
 * ObjectMapper mapper = new ObjectMapper();
 * return mapper.writeValueAsString(data);
 * 위의 코드는 ObjectMapper 메소드로 이미 구현되어 있습니다. main 클래스에서 직접 사용해보세요!
 *
 * 하지만 이 과제의 목적은 재귀를 공부하는 것이니, 처음부터 구현해봐야겠지요?:
 */

public class stringifyJSON_test {

    private static stringifyJSON test = new stringifyJSON();

    public String ObjectMapper(Object data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }

    @Test
    @DisplayName("null을 입력받을 경우, 알맞은 형태의 JSON으로 변환합니다")
    public void null을_JSON으로_변경합니다() throws JsonProcessingException {
        assertThat(test.stringify(null)).isEqualTo(ObjectMapper(null));
    }

    @Test
    @DisplayName("Boolean 타입을 입력받을 경우, 알맞은 형태의 JSON으로 변환합니다")
    public void Boolean_타입의_입력을_JSON으로_변경합니다_1() throws JsonProcessingException {
        assertThat(test.stringify(false)).isEqualTo(ObjectMapper(false));
    }

    @Test
    @DisplayName("Boolean 타입을 입력받을 경우, 알맞은 형태의 JSON으로 변환합니다")
    public void Boolean_타입의_입력을_JSON으로_변경합니다_2() throws JsonProcessingException {
        assertThat(test.stringify(true)).isEqualTo(ObjectMapper(true));
    }

    @Test
    @DisplayName("String 타입을 입력받을 경우, 알맞은 형태의 JSON으로 변환합니다")
    public void String_타입의_입력을_JSON으로_변경합니다() throws JsonProcessingException {
        assertThat(test.stringify("Hello CodeStates!")).isEqualTo(ObjectMapper("Hello CodeStates!"));
    }

    @Test
    @DisplayName("배열을 입력받을 경우, 알맞은 형태의 JSON으로 변환합니다")
    public void 배열을_입력받았을_경우_JSON으로_변경합니다_1() throws JsonProcessingException {
        assertThat(test.stringify(new Object[]{})).isEqualTo(ObjectMapper(new Object[]{}));
    }

    @Test
    @DisplayName("배열을 입력받을 경우, 알맞은 형태의 JSON으로 변환합니다")
    public void 배열을_입력받았을_경우_JSON으로_변경합니다_2() throws JsonProcessingException {
        assertThat(test.stringify(new Object[]{8})).isEqualTo(ObjectMapper(new Object[]{8}));
    }

    @Test
    @DisplayName("배열을 입력받을 경우, 알맞은 형태의 JSON으로 변환합니다")
    public void 배열을_입력받았을_경우_JSON으로_변경합니다_3() throws JsonProcessingException {
        assertThat(test.stringify(new Object[]{"hi"})).isEqualTo(ObjectMapper(new Object[]{"hi"}));
    }

    @Test
    @DisplayName("배열을 입력받을 경우, 알맞은 형태의 JSON으로 변환합니다")
    public void 배열을_입력받았을_경우_JSON으로_변경합니다_4() throws JsonProcessingException {
        assertThat(test.stringify(new Object[]{8, "hi"})).isEqualTo(ObjectMapper(new Object[]{8, "hi"}));
    }

    @Test
    @DisplayName("배열을 입력받을 경우, 알맞은 형태의 JSON으로 변환합니다")
    public void 배열을_입력받았을_경우_JSON으로_변경합니다_5() throws JsonProcessingException {
        assertThat(test.stringify(new Object[]{8, new Object[]{new Object[]{}, 3, 4}})).isEqualTo(ObjectMapper(new Object[]{8, new Object[]{new Object[]{}, 3, 4}}));
    }

    @Test
    @DisplayName("배열을 입력받을 경우, 알맞은 형태의 JSON으로 변환합니다")
    public void 배열을_입력받았을_경우_JSON으로_변경합니다_6() throws JsonProcessingException {
        assertThat(test.stringify(new Object[]{new Object[]{new Object[]{}}})).isEqualTo(ObjectMapper(new Object[]{new Object[]{new Object[]{}}}));
    }

    @Test
    @DisplayName("HashMap을 입력받을 경우, 알맞은 형태의 JSON으로 변환합니다")
    public void Map_을_입력받았을_경우_JSON으로_변경합니다_1() throws JsonProcessingException {
        HashMap<Object, Object> map = new HashMap<>();
        assertThat(test.stringify(map)).isEqualTo(ObjectMapper(map));
    }

    @Test
    @DisplayName("HashMap을 입력받을 경우, 알맞은 형태의 JSON으로 변환합니다")
    public void Map_을_입력받았을_경우_JSON으로_변경합니다_2() throws JsonProcessingException {
        HashMap<Object, Object> map = new HashMap<>(){{
            put("a", "apple");
        }};
        assertThat(test.stringify(map)).isEqualTo(ObjectMapper(map));
    }

    @Test
    @DisplayName("HashMap을 입력받을 경우, 알맞은 형태의 JSON으로 변환합니다")
    public void Map_을_입력받았을_경우_JSON으로_변경합니다_3() throws JsonProcessingException {
        HashMap<Object, Object> map = new HashMap<>(){{
            put("foo", true);
            put("bar", false);
            put("baz", null);
        }};
        assertThat(test.stringify(map)).isEqualTo(ObjectMapper(map));
    }
}
