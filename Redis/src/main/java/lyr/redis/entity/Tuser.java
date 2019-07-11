package lyr.redis.entity;

/**
 * 测试redis存储对象的对象
 * @ClassName Tuser
 * @Description TODO
 * @Author LYR
 * @Date 2019/7/11 9:13
 * @Version 1.0
 **/
public class Tuser {

    private String name;
    private int age;
    private double score;

    public Tuser() {
    }

    public Tuser(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
