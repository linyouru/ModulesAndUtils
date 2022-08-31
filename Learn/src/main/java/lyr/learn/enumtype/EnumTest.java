package lyr.learn.enumtype;


/**
 * @ClassName EnumTest
 * @Description TODO
 * @Author LYR
 * @Date 2020/1/3 9:57
 * @Version 1.0
 **/
public class EnumTest {

    public static void main(String[] args) {
//        EnumTest enumTest = new EnumTest();
//        enumTest.bySwitch(Color.GREEN);

    }

    public void bySwitch(Color color){
          switch (color){
              case RED:
                  color = Color.RED;
                  break;
              case BLANK:
                  color = Color.BLANK;
                  break;
              case GREEN:
                  color = Color.GREEN;
                  break;
              case YELLOW:
                  color = Color.YELLOW;
                  break;
              default:
                  break;
          }
        System.out.println(color);
    }

}
