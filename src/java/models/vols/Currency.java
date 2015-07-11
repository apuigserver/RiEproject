/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.vols;

/**
 *
 * @author arnaudpuigserver
 */
public class Currency {
    
    private String code;
    private String symbol;
    private String thousandSeparator;
    private String decimalSeparator;
    private boolean symbolOnLeft;
    private boolean spaceBetweenAmountAndSymbol;
    private long roundingCoeffcient;
    private long decimalDigits;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getThousandSeparator() {
        return thousandSeparator;
    }

    public void setThousandSeparator(String thousandSeparator) {
        this.thousandSeparator = thousandSeparator;
    }

    public String getDecimalSeparator() {
        return decimalSeparator;
    }

    public void setDecimalSeparator(String decimalSeparator) {
        this.decimalSeparator = decimalSeparator;
    }

    public boolean isSymbolOnLeft() {
        return symbolOnLeft;
    }

    public void setSymbolOnLeft(boolean symbolOnLeft) {
        this.symbolOnLeft = symbolOnLeft;
    }

    public boolean isSpaceBetweenAmountAndSymbol() {
        return spaceBetweenAmountAndSymbol;
    }

    public void setSpaceBetweenAmountAndSymbol(boolean spaceBetweenAmountAndSymbol) {
        this.spaceBetweenAmountAndSymbol = spaceBetweenAmountAndSymbol;
    }

    public long getRoundingCoeffcient() {
        return roundingCoeffcient;
    }

    public void setRoundingCoeffcient(long roundingCoeffcient) {
        this.roundingCoeffcient = roundingCoeffcient;
    }

    public long getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(long decimalDigits) {
        this.decimalDigits = decimalDigits;
    }
    
}
