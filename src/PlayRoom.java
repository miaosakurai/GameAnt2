import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by joy12 on 2017/9/19.
 */
public class PlayRoom extends JFrame {

    private List<String> ds = new ArrayList<>();//01串 00000~11111，表示方向，0左1右
    private int[] sps = {30,80,110,160,250};//初始位置
    private int stickLen = 300;//杆子长度
    private double speed = 5;//爬行速度
    private Map<String, Long> result = new HashMap<>();//结果
    PlayRoom(){
        setTitle("GameAntTest");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

    }
    //构造所有可能的初始方向状态
    public void buildDirections(int antNum){
        double n = Math.pow(2,antNum);
        for (int i = 0; i < n; i++) {
            String binary = Integer.toBinaryString(i);
            String s = String.format("%05d", Integer.parseInt(binary));
            System.out.println(s);
            ds.add(s);
        }

    }

    //开始游戏
    public void playGames() {
        CreepingGame game = new CreepingGame();
        long cost=0;
        this.add(game);
        if (game.init(ds.get(0), speed, sps, stickLen)) {
            result.put(ds.get(0), game.play());
        }

        if (game.init(ds.get(1), speed, sps, stickLen)) {

            result.put(ds.get(1), game.play());
        }

//        CreepingGame game1=new CreepingGame();
//        if (game1.init(ds.get(1),speed,sps,stickLen)){
//            this.add(game1);
//                result.put(ds.get(1),game1.play());
//            }

    }



    public JTextArea printMinAndMax(){
        List<Map.Entry<String,Long>> sortedResults = new ArrayList<>(result.entrySet());
        Collections.sort(sortedResults, (o1, o2) -> {
            if (o1.getValue() > o2.getValue()){
                return 1;
            } else if (o1.getValue() < o2.getValue()){
                return -1;
            } else {
                return 0;
            }

        });

        System.out.println("最快 == " + sortedResults.get(0).getKey() + " - " + sortedResults.get(0).getValue());
        System.out.println("最慢 == " + sortedResults.get(sortedResults.size()-1).getKey() + " - " + sortedResults.get(sortedResults.size()-1).getValue());
        return new JTextArea("最快："+sortedResults.get(0).getValue()+"  最慢："+sortedResults.get(sortedResults.size()-1).getValue());
    }

    public static void main(String[] args){
        PlayRoom pr = new PlayRoom();


//        //如果初始位置有单数，减小速度值防止交叉
//        for (int i = 0; i < pr.sps.length; i++) {
//            if (pr.sps[i] % 2 == 1){
//                pr.speed = 0.5;
//            }
//        }
        pr.buildDirections(5);
        pr.setVisible(true);
        pr.playGames();

    }

}
