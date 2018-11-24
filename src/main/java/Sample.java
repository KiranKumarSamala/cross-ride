import java.sql.Date;
import java.sql.Timestamp;

public class Sample {

	public static void main(String[] args) {
		String dt = "2018-11-02 09:00:00";
		String dt1 = "2018-11-02 10:00:00";
		Timestamp ts1 = Timestamp.valueOf(dt);
		Timestamp ts2 = Timestamp.valueOf(dt1);
		
		//System.out.println(ts2-ts1);
	}
}
