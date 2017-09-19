import java.util.*;

/**
 * Created by joy12 on 2017/9/19.
 */
public class PlayRoom {

    private List<String> ds = new ArrayList<>();//01串 00000~11111，表示方向，0左1右
    private int[] sps = {30,80,110,160,250};//初始位置
    private int stickLen = 300;//杆子长度
    private double speed = 1;//爬行速度
    private Map<String, Long> result = new HashMap<>();//结果

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
    public void playGames(){
        for (String d: ds) {
            CreepingGame game = new CreepingGame();
            if (game.init(d,speed,sps,stickLen)){
                result.put(d,game.play());
            }
        }

    }

    public void endGame(){
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
    }

    public static void main(String[] args){
        PlayRoom pr = new PlayRoom();
        pr.buildDirections(pr.sps.length);

        //如果初始位置有单数，减小速度值防止交叉
        for (int i = 0; i < pr.sps.length; i++) {
            if (pr.sps[i] % 2 == 1){
                pr.speed = 0.5;
            }
        }

        pr.playGames();
        pr.endGame();
    }

}
