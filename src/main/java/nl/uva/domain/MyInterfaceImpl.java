package nl.uva.domain;

import nl.uva.data.MyInputModel;
import nl.uva.data.MyOutputModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class MyInterfaceImpl implements MyInterface {

    BigDecimal staffel1 = new BigDecimal("10");
    BigDecimal laagTarief = new BigDecimal("2");

    BigDecimal hoogTarief = new BigDecimal("4");


    @Override
    public MyOutputModel getCalculation(MyInputModel myInputModel) {
        MyOutputModel myOutputModel = new MyOutputModel();

        if (FALSE.equals(myInputModel.isVoldoetAanKeurmerk())) {
            myOutputModel.setVerschuldigdeBelasting(0);
            return myOutputModel;
        }

        if (TRUE.equals(myInputModel.isDiabetes())) {
            myOutputModel.setVerschuldigdeBelasting(0);
            return myOutputModel;
        }

        BigDecimal tussenTijdseVariabele = BigDecimal.valueOf(myInputModel.getGramSuiker())
                .divide(BigDecimal.valueOf(myInputModel.getGewichtProductInGram()), MathContext.DECIMAL32)
                .setScale(0, RoundingMode.DOWN);

        if (tussenTijdseVariabele.compareTo(staffel1) < 1) {
            BigDecimal uitkomst = BigDecimal.valueOf(myInputModel.getGramSuiker()).multiply(laagTarief);
            myOutputModel.setVerschuldigdeBelasting(uitkomst.longValue());
            return myOutputModel;
        }
        else {
            BigDecimal uitkomstHoogTarief = BigDecimal.valueOf(myInputModel.getGramSuiker()).multiply(hoogTarief);
            myOutputModel.setVerschuldigdeBelasting(uitkomstHoogTarief.longValue());
            return myOutputModel;
        }
    }
}
