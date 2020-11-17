package th.ac.su.rockpaperscisors.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    public final int id;

    @ColumnInfo(name = "name")
    public final String name;

    @ColumnInfo(name = "rOnee")
    public final String rOne;

    @ColumnInfo(name = "rTwo")
    public final String rTwo;

    @ColumnInfo(name = "rThree")
    public final String rThree;

    @ColumnInfo(name = "rFour")
    public final String rFour;

    @ColumnInfo(name = "rFive")
    public final String rFive;

    @ColumnInfo(name = "score")
    public final int score;


    public User(int id,String name, String rOne,String rTwo,String rThree,String rFour,String rFive,int score) {
        this.id = id;
        this.name = name;
        this.rOne = rOne;
        this.rTwo = rTwo;
        this.rThree = rThree;
        this.rFour = rFour;
        this.rFive = rFive;
        this.score = score;
    }
}

