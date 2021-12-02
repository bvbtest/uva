package nl.uva.domain;

import nl.uva.data.MyInputModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class InputCalculatorImpl implements InputCalculator {

    // Alle variabelen die we nodig hebben voor het berekenen van de verschuldigde belasting
    // zetten we bovenin de classe, zodat ze makkelijk en op een plaats aan te passen zijn.

    BigDecimal omzetTarief = new BigDecimal("0.001");
    BigDecimal tariefPerKilometer = new BigDecimal("0.01");
    Long vrijgesteldAantalWerknemers = 50L;
    Long tariefPerWerknemer = 5L;
    BigDecimal kortingLabelA = new BigDecimal("0.5");
    BigDecimal kortingLabelB = new BigDecimal("0.25");
    BigDecimal kortingLabelC = new BigDecimal("0.10");
    BigDecimal kortingLabelD = new BigDecimal("0.0");
    BigDecimal kortingLabelE = new BigDecimal("-0.05");
    BigDecimal kortingLabelF = new BigDecimal("-0.15");
    BigDecimal kortingLabelG = new BigDecimal("-0.25");

    @Override
    public Long getCalculation(MyInputModel model) {

        // De verschuldigde belasting is een "Long" waarde, dus een getal.
        // We zetten hier de default waarde op 0. De "L" geeft aan dat het om
        // een "Long" gaat en is nodig voor de codel.
        Long verschuldigdeBelasting = 0L;

        // Als de entiteit die een berekening maakt geen rechtspersoon is,
        // dan is de verschuldugde belasting € 0.
        // We kunnen dan stoppen met rekenen en de uitkomst teruggeven aan de client.
        // Dit "teruggeven" doen we door middel van een return.
        if (model.isRechtspersoon.equals(Boolean.FALSE)) {
            return verschuldigdeBelasting;
        }

        // We hebben afgesproken dat de verschuldigde belasting een combinatie van factoren gaat worden.
        // In de eerste plaats is de verschuldigde belasting afhankelijk van de omzet. We hebben afgesproken
        // dat de belastingplichtige een percentage van 0,1% van de omzet moet betalen.
        // Het tarief van 0,1% staat als variabele bovenin de class gedefinieerd.
        if (model.omzet >  0L) {
            // Omdat een "Long" waarde altijd een heel getal is en geen decimalen bevat, moeten we
            // eerst de Long omzetten in een BigDecimal, die wel met decimalen overweg kan.
            // We berekenen nu omzet x tarief en ronden af naar beneden. De uitkomst zetten we weer om
            // in een Long, omdat de applicatie een Long verwacht.
            verschuldigdeBelasting = verschuldigdeBelasting + BigDecimal.valueOf(model.omzet)
                    .multiply(omzetTarief)
                    .setScale(0, RoundingMode.DOWN)
                    .longValue();
        }

        // We hebben afgesproken dat de verschuldigde belasting ook afhankelijk is van het aantal werknemers
        // dat een belastingplichtige heeft. De hebben vastgesteld dat tot en met 50 werknemers
        // geen extra heffing verschuldigd is. Daarboven wordt de verschuldigde belasting verhoogd met € 5 per werknemer per tijdvak.
        if (model.aantalWerknemers > vrijgesteldAantalWerknemers) {
            // Het vrijgesteld aantal werknemers staat bovenin de class vermeld als variabele, zodat we hier
            // alleen de rekenlogica zien. Het tarief van € 5 staat ook bovenin de class als variabele.
            verschuldigdeBelasting = verschuldigdeBelasting
                    + (model.aantalWerknemers - vrijgesteldAantalWerknemers)
                    * tariefPerWerknemer;
        }

        // We hebben afgesporken dat de verschuldigde belasting verhoogd wordt
        // met een bedrag per gereden kilometer. Er geldt geen vrijstelling voor een aantal kilometers.
        // Omdat we rekenen met decimalen, moeten we eerst alle getallen converteren naar BigDecimals.
        // Vervolgens verhogen we de tot nu toe berekende belasting.
        verschuldigdeBelasting = verschuldigdeBelasting
                + BigDecimal.valueOf(model.aantalGeredenKilometers)
                .multiply(tariefPerKilometer)
                .setScale(0, RoundingMode.DOWN)
                .longValue();

        // We hebben afgesproken dat belastingplichtigen een verhoging of een verlaging krijgen van de
        // verschuldigde belasting al naar gelang hun energielabel. De verhoging of verlaging bedraagt een
        // percentage van de tot nu toe berekende belasting. Omdat we rekenen met percenteges, moet we weer gebruik
        // maken van BigDecimals. Omdat in het MyInputData model staat dat het energielabel zowel een hoofdletter als
        // een kleine letter mag zijn, moeten we eventuele inkomende hoofdletters converteren naar kleine letters, zodat
        // we een vergelijking kunnen maken.
        // De percentages van de (negatieve) kortingen staan als variabelen bovenin de class.
        if (model.energieLabel.equalsIgnoreCase("a")) {
            // Bij label A bedraagt de korting 50%.
            verschuldigdeBelasting = verschuldigdeBelasting - BigDecimal.valueOf(verschuldigdeBelasting)
                    .multiply(kortingLabelA)
                    .setScale(0, RoundingMode.DOWN)
                    .longValue();
        }
        if (model.energieLabel.equalsIgnoreCase("b")) {
            // Bij label B bedraagt de korting 25%.
            verschuldigdeBelasting = verschuldigdeBelasting - BigDecimal.valueOf(verschuldigdeBelasting)
                    .multiply(kortingLabelB)
                    .setScale(0, RoundingMode.DOWN)
                    .longValue();
        }
        if (model.energieLabel.equalsIgnoreCase("c")) {
            // Bij label C bedraagt de korting 10%.
            verschuldigdeBelasting = verschuldigdeBelasting - BigDecimal.valueOf(verschuldigdeBelasting)
                    .multiply(kortingLabelC)
                    .setScale(0, RoundingMode.DOWN)
                    .longValue();
        }
        if (model.energieLabel.equalsIgnoreCase("d")) {
            // Bij label D bedraagt de korting 0%.
            verschuldigdeBelasting = verschuldigdeBelasting - BigDecimal.valueOf(verschuldigdeBelasting)
                    .multiply(kortingLabelD)
                    .setScale(0, RoundingMode.DOWN)
                    .longValue();
        }
        if (model.energieLabel.equalsIgnoreCase("e")) {
            // Bij label E bedraagt de korting -5%, dus een "boete".
            verschuldigdeBelasting = verschuldigdeBelasting - BigDecimal.valueOf(verschuldigdeBelasting)
                    .multiply(kortingLabelE)
                    .setScale(0, RoundingMode.DOWN)
                    .longValue();
        }
        if (model.energieLabel.equalsIgnoreCase("f")) {
            // Bij label F bedraagt de korting -15%.
            verschuldigdeBelasting = verschuldigdeBelasting - BigDecimal.valueOf(verschuldigdeBelasting)
                    .multiply(kortingLabelF)
                    .setScale(0, RoundingMode.DOWN)
                    .longValue();
        }
        if (model.energieLabel.equalsIgnoreCase("g")) {
            // Bij label G bedraagt de korting -25%.
            verschuldigdeBelasting = verschuldigdeBelasting - BigDecimal.valueOf(verschuldigdeBelasting)
                    .multiply(kortingLabelG)
                    .setScale(0, RoundingMode.DOWN)
                    .longValue();
        }

        // Nadat we de verschuldigde belasting hebben berekend, geven we de berekende waarde terug aan de interface.
        // Dat doen we door middel van een return. Na een retrun stop de berekening.
        return verschuldigdeBelasting;
    }
}
