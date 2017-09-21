import java.util.ArrayList;
import java.util.List;
/**
 * Created by joy12 on 2017/9/19.
 */
public class CreepingGame{

    private List<Ant> ants;
    private Stick stick;

    private int timeCost = 0;


    public boolean init(String directions, double speed, int[] startPos, int stickLength){
        System.out.println("----------------------- 开始初始化：" + directions + " -----------------------");
        if (directions.length() != startPos.length){
            System.out.println("输入错误！directions " + directions.length() + "  startPos " + startPos.length);
            return false;
        }

        stick = new Stick(stickLength);
        ants = new ArrayList<>();

        for (int i=0; i<directions.length(); i++){

            if (startPos[i] > stickLength){
                System.out.println("输入错误！startPos = " + startPos[i]);
                return false;
            }

            Ant ant = new Ant();
            ant.setId(i);
            switch (directions.charAt(i)){
                case '0': ant.setDirection(false); break;
                case '1': ant.setDirection(true); break;
                default: System.out.println("方向值设定错误！");return false;
            }
            ant.setSpeed(speed);
            ant.setPosition( (double) startPos[i]);
            ants.add(ant);
        }
        System.out.println("初始化成功，杆子上现在有 " + ants.size() + " 只蚂蚁");
        return true;
    }

    private void oneSecondPass(){
        timeCost++;

        //爬行
        List<Integer> removeIndexes = new ArrayList<>();
        for (int i = 0; i < ants.size(); i++) {
            Ant a = ants.get(i);
            a.creeping();

            if (!stick.isOnStick(a)){
                removeIndexes.add(i);
            }
        }

        //删除掉下木杆的蚂蚁
        for (Integer index : removeIndexes) {
            System.out.println(ants.get(index).getId() + "号蚂蚁掉下木杆");
            ants.remove(ants.get(index));
        }

        System.out.println("还剩" + ants.size() + "只");

        //若相遇，改变方向,蚂蚁只能和左右邻居相遇
        for (int j = 0; j < ants.size()-1; j++) {
            Ant a1 = ants.get(j);
            Ant a2 = ants.get(j+1);
            if (a1.isCollision(a2)){
                a1.changeDirection();
                a2.changeDirection();
                j++;
            }
        }
    }

    public long play(){
        System.out.println("play()");

        while (!ants.isEmpty()){
            System.out.println("******** 这一秒里，蚂蚁的移动和状态 ********");
            //过了单位时间1
            oneSecondPass();
        }

        System.out.println("本局结束");
        return timeCost;
    }

}
