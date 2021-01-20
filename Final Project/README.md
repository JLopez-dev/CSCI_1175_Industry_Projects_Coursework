# Currency Converter

## Synopsis
This is my project and is used to convert 10 different currencies.

## Motivation
Currency is important because the exchange rate, the price of one currency in terms of another, helps to determine a nation's economic health and hence the well-being of all the people residing in it.

## How to Run
First you need a program or app that can run Java.

![image](https://github.com/JLopez-dev/CSCI_1175_Industry_Projects_Coursework/blob/main/Currency%20Converter.png)

You enter the amount, select to currency and convert the currency.

## Code Example
```
class CurrencyConverter extends Panel {
    enum Currency {
        USD("United States Dollar"),
        CND("Canadian Dollar"),
        RUB("Russia Ruble"),
        JPY("Japanese Yen"),
        EUR("Euros"),
        INR("Indian Rupee"),
        BRL("Brazilian Rial"),
        NOK("Norwegian Krona"),
        GBR("Pound Sterling"),
        SKW("South Korea Won");

        private String description;

        Currency(String description) {
            this.description = description;
        }

        @Override public String toString() {
            return this.name() + " - " + this.description;
        }
    }

    class CurrencyPair {
        private final Currency from;
        private final Currency to;

        public CurrencyPair(Currency from, Currency to) {
            this.from = from;
            this.to = to;
        }

        @Override public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            CurrencyPair that = (CurrencyPair) o;
            if (from != that.from) return false;
            return to == that.to;
        }

        @Override public int hashCode() {
            int result = from.hashCode();
            result = 31 * result + to.hashCode();
            return result;
        }
    }
```
