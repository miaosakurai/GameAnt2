

/**
 * Created by joy12 on 2017/9/19.
 */
public class Ant{
    private int id;
    private Double speed;// cm/s
    private Boolean direction;// 0左 1右
    private Double position;// 0~300

    public Ant(){}

    public Ant(int id, Double speed, Boolean direction, Double position) {
        this.speed = speed;
        this.direction = direction;
        this.position = position;
    }

    public Boolean changeDirection(){
        direction = !direction;
        System.out.println("蚂蚁" + id + "改变方向为" + direction);
        return direction;
    }

    public Double creeping(){
        if (direction) {
            position += speed;
        } else if (!direction){
            position -= speed;
        }
        System.out.println("蚂蚁" + id + "爬到了" + position);

        return position;
    }



    public Boolean isCollision(Ant other){
        return other.getPosition().compareTo(position) == 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Boolean getDirection() {
        return direction;
    }

    public void setDirection(Boolean direction) {
        this.direction = direction;
    }

    public Double getPosition() {
        return position;
    }

    public void setPosition(Double position) {
        this.position = position;
    }
}
