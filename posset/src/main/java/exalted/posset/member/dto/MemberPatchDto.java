package exalted.posset.member.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class MemberPatchDto {

    private long memberId;
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "/^\\d{3}-\\d{3,4}-\\d{4}$/", message = "전화번호 양식대로 작성하시오")
    private String phone;

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
}
