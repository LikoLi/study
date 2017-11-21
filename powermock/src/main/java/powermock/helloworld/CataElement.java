package powermock.helloworld;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CataElement {
    private boolean isAvailable = false;
    private List<FileItem> items = new ArrayList<>();
    private Date parseDate(String date) {
        if (!isAvailable)  return null;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            if (date == null || date.isEmpty()) {
                return null;
            }
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
