package nl.uva.data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MyInputModel {

    @Min(value = 2022, message = "De waarde moet minimaal 2022 zijn.")
    @Max(value = 2999, message = "De waarde mag niet hoger liggen dan 2999.")
    @NotNull(message = "Dit is een verplicht veld.")
    int jaartal;

    @Size(min = 9, max = 9)
    @NotNull(message = "Dit is een verplicht veld.")
    String bsn;

    @NotNull(message = "Dit is een verplicht veld.")
    boolean isDiabetes;

    @NotNull(message = "Dit is een verplicht veld.")
    boolean voldoetAanKeurmerk;

    @Min(value = 0, message = "De waarde moet minimaal 0 zijn.")
    @NotNull(message = "Dit is een verplicht veld.")
    long gramSuiker;

    @Min(value = 0, message = "De waarde moet minimaal 0 zijn.")
    @NotNull(message = "Dit is een verplicht veld.")
    long gewichtProductInGram;


    public int getJaartal() {
        return jaartal;
    }

    public void setJaartal(int jaartal) {
        this.jaartal = jaartal;
    }

    public String getBsn() {
        return bsn;
    }

    public void setBsn(String bsn) {
        this.bsn = bsn;
    }

    public boolean isDiabetes() {
        return isDiabetes;
    }

    public void setDiabetes(boolean diabetes) {
        isDiabetes = diabetes;
    }

    public boolean isVoldoetAanKeurmerk() {
        return voldoetAanKeurmerk;
    }

    public void setVoldoetAanKeurmerk(boolean voldoetAanKeurmerk) {
        this.voldoetAanKeurmerk = voldoetAanKeurmerk;
    }

    public long getGramSuiker() {
        return gramSuiker;
    }

    public void setGramSuiker(long gramSuiker) {
        this.gramSuiker = gramSuiker;
    }

    public long getGewichtProductInGram() {
        return gewichtProductInGram;
    }

    public void setGewichtProductInGram(long gewichtProductInGram) {
        this.gewichtProductInGram = gewichtProductInGram;
    }
}
