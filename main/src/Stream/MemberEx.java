package codestatePrac.Stream;

public class MemberEx {
    private int age;
    private String gender;
    private String className;

    public MemberEx(int age, String gender, String className) {
        this.age = age;
        this.gender = gender;
        this.className = className;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
