import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTime {

    public String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return formatter.format(date);
    }
}
