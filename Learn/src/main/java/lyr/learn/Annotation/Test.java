package lyr.learn.Annotation;

/**
 * @ClassName Test
 * @Description TODO
 * @Author LYR
 * @Date 2019/10/30 10:12
 * @Version 1.0
 **/
@MyAnnotation
public class Test {

    //MyAnnotation的APT
    public static void main(String[] args) {
        boolean annotationPresent = Test.class.isAnnotationPresent(MyAnnotation.class);
        if(annotationPresent){
            MyAnnotation annotation = Test.class.getAnnotation(MyAnnotation.class);
            System.out.println("id:"+annotation.id());
            System.out.println("msg:"+annotation.msg());
        }
    }

}
