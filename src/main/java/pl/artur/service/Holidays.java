package pl.artur.service;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Holidays {
    private List<Holiday> holidays;

    public Holidays() {
    }

    public Holidays(List<Holiday> list) {
        this.holidays = list;
    }

    public List<Holiday> getHolidays() {
        return holidays;
    }

    public void setHolidays(List<Holiday> holidays) {
        this.holidays = holidays;
    }

}
