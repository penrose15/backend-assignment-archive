package codestatePrac.람쥐썬더;

public class LamdaConstructor {
    private String name;
    private String id;

    public LamdaConstructor() {
        System.out.println("클래스 실행");
    }
    public LamdaConstructor(String id) {
        System.out.println("클래스 (String id) 실행");
        this.id = id;
    }

    public LamdaConstructor(String name, String id) {
        System.out.println("클래스 (String id, String Name) 실행");
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
