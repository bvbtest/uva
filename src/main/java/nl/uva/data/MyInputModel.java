package nl.uva.data;

import javax.validation.constraints.*;

public class MyInputModel {

    @NotNull
    public Boolean isRechtspersoon;

    @NotNull
    @Size(min = 3, max = 50)
    public String naamRechtspersoon;

    @NotNull
    @Min(-9999999999L)
    @Max(9999999999L)
    public Long omzet;

    @NotNull
    @Min(0L)
    @Max(100000L)
    public Long aantalWerknemers;

    @NotNull
    @Size(min = 1, max = 1)
    @Pattern(regexp = "^[A-Ga-g]$")
    public String energieLabel;

    @NotNull
    @Min(0L)
    @Max(10000000L)
    public Long aantalGeredenKilometers;

    @Min(0L)
    @Max(100000000L)
    public Long verschuldigdeBelasting;

    @NotNull
    public Boolean isBinnenlandsBelastingplichtig;

    @NotNull
    @Min(2021L)
    @Max(2021L)
    public Long aangifteJaar;

    @NotNull
    @Min(0L)
    @Max(10000000L)
    public Long kilogrammenUitstoot;

    @NotNull
    @Size(min = 9, max = 9)
    @Pattern(regexp = "^[0-9]{9}$")
    public String fiscaalNummer;

}
