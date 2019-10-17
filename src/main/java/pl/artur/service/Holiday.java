package pl.artur.service;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

public class Holiday {

    private LocalDate ISO;

    private String name;

    public Holiday() {
    }

    public Holiday(LocalDate iSO, String name) {
        ISO = iSO;
        this.name = name;
    }

    public LocalDate getISO() {
        return ISO;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public void setISO(LocalDate iSO) {
        ISO = iSO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
