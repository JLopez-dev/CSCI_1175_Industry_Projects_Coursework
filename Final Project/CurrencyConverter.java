/*
 Author: John Lopez
 Date: 1/18/2021.
 */

package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static javax.swing.JFrame.EXIT_ON_CLOSE;


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

    private final Map<CurrencyPair, BigDecimal> exchangeRates = new HashMap<CurrencyPair, BigDecimal>() {{
        put(new CurrencyPair(CurrencyConverter.Currency.USD, CurrencyConverter.Currency.USD), BigDecimal.valueOf(1));
        put(new CurrencyPair(CurrencyConverter.Currency.CND, CurrencyConverter.Currency.CND), BigDecimal.valueOf(1));
        put(new CurrencyPair(CurrencyConverter.Currency.RUB, CurrencyConverter.Currency.RUB), BigDecimal.valueOf(1));
        put(new CurrencyPair(CurrencyConverter.Currency.JPY, CurrencyConverter.Currency.JPY), BigDecimal.valueOf(1));
        put(new CurrencyPair(CurrencyConverter.Currency.EUR, CurrencyConverter.Currency.EUR), BigDecimal.valueOf(1));
        put(new CurrencyPair(CurrencyConverter.Currency.INR, CurrencyConverter.Currency.INR), BigDecimal.valueOf(1));
        put(new CurrencyPair(CurrencyConverter.Currency.BRL, CurrencyConverter.Currency.BRL), BigDecimal.valueOf(1));
        put(new CurrencyPair(CurrencyConverter.Currency.NOK, CurrencyConverter.Currency.NOK), BigDecimal.valueOf(1));
        put(new CurrencyPair(CurrencyConverter.Currency.GBR, CurrencyConverter.Currency.GBR), BigDecimal.valueOf(1));
        put(new CurrencyPair(CurrencyConverter.Currency.SKW, CurrencyConverter.Currency.SKW), BigDecimal.valueOf(1));

        put(new CurrencyPair(CurrencyConverter.Currency.USD, CurrencyConverter.Currency.CND), BigDecimal.valueOf(1.33));
        put(new CurrencyPair(CurrencyConverter.Currency.USD, CurrencyConverter.Currency.RUB), BigDecimal.valueOf(63.62));
        put(new CurrencyPair(CurrencyConverter.Currency.USD, CurrencyConverter.Currency.JPY), BigDecimal.valueOf(109.74));
        put(new CurrencyPair(CurrencyConverter.Currency.USD, CurrencyConverter.Currency.EUR), BigDecimal.valueOf(0.92));
        put(new CurrencyPair(CurrencyConverter.Currency.USD, CurrencyConverter.Currency.INR), BigDecimal.valueOf(71.54));
        put(new CurrencyPair(CurrencyConverter.Currency.USD, CurrencyConverter.Currency.BRL), BigDecimal.valueOf(4.31));
        put(new CurrencyPair(CurrencyConverter.Currency.USD, CurrencyConverter.Currency.NOK), BigDecimal.valueOf(9.26));
        put(new CurrencyPair(CurrencyConverter.Currency.USD, CurrencyConverter.Currency.GBR), BigDecimal.valueOf(0.77));
        put(new CurrencyPair(CurrencyConverter.Currency.USD, CurrencyConverter.Currency.SKW), BigDecimal.valueOf(1183.46));

        put(new CurrencyPair(CurrencyConverter.Currency.CND, CurrencyConverter.Currency.USD), BigDecimal.valueOf(0.75));
        put(new CurrencyPair(CurrencyConverter.Currency.CND, CurrencyConverter.Currency.RUB), BigDecimal.valueOf(48.33));
        put(new CurrencyPair(CurrencyConverter.Currency.CND, CurrencyConverter.Currency.JPY), BigDecimal.valueOf(84.34));
        put(new CurrencyPair(CurrencyConverter.Currency.CND, CurrencyConverter.Currency.EUR), BigDecimal.valueOf(0.70));
        put(new CurrencyPair(CurrencyConverter.Currency.CND, CurrencyConverter.Currency.INR), BigDecimal.valueOf(54.05));
        put(new CurrencyPair(CurrencyConverter.Currency.CND, CurrencyConverter.Currency.BRL), BigDecimal.valueOf(3.31));
        put(new CurrencyPair(CurrencyConverter.Currency.CND, CurrencyConverter.Currency.NOK), BigDecimal.valueOf(7.03));
        put(new CurrencyPair(CurrencyConverter.Currency.CND, CurrencyConverter.Currency.GBR), BigDecimal.valueOf(0.59));
        put(new CurrencyPair(CurrencyConverter.Currency.CND, CurrencyConverter.Currency.SKW), BigDecimal.valueOf(910.68));

        put(new CurrencyPair(CurrencyConverter.Currency.RUB, CurrencyConverter.Currency.USD), BigDecimal.valueOf(0.016));
        put(new CurrencyPair(CurrencyConverter.Currency.RUB, CurrencyConverter.Currency.CND), BigDecimal.valueOf(0.021));
        put(new CurrencyPair(CurrencyConverter.Currency.RUB, CurrencyConverter.Currency.JPY), BigDecimal.valueOf(1.72));
        put(new CurrencyPair(CurrencyConverter.Currency.RUB, CurrencyConverter.Currency.EUR), BigDecimal.valueOf(0.014));
        put(new CurrencyPair(CurrencyConverter.Currency.RUB, CurrencyConverter.Currency.INR), BigDecimal.valueOf(1.12));
        put(new CurrencyPair(CurrencyConverter.Currency.RUB, CurrencyConverter.Currency.BRL), BigDecimal.valueOf(0.068));
        put(new CurrencyPair(CurrencyConverter.Currency.RUB, CurrencyConverter.Currency.NOK), BigDecimal.valueOf(0.15));
        put(new CurrencyPair(CurrencyConverter.Currency.RUB, CurrencyConverter.Currency.GBR), BigDecimal.valueOf(0.012));
        put(new CurrencyPair(CurrencyConverter.Currency.RUB, CurrencyConverter.Currency.SKW), BigDecimal.valueOf(18.63));

        put(new CurrencyPair(CurrencyConverter.Currency.JPY, CurrencyConverter.Currency.USD), BigDecimal.valueOf(0.0089));
        put(new CurrencyPair(CurrencyConverter.Currency.JPY, CurrencyConverter.Currency.CND), BigDecimal.valueOf(0.012));
        put(new CurrencyPair(CurrencyConverter.Currency.JPY, CurrencyConverter.Currency.RUB), BigDecimal.valueOf(0.57));
        put(new CurrencyPair(CurrencyConverter.Currency.JPY, CurrencyConverter.Currency.EUR), BigDecimal.valueOf(0.0083));
        put(new CurrencyPair(CurrencyConverter.Currency.JPY, CurrencyConverter.Currency.INR), BigDecimal.valueOf(0.64));
        put(new CurrencyPair(CurrencyConverter.Currency.JPY, CurrencyConverter.Currency.BRL), BigDecimal.valueOf(0.039));
        put(new CurrencyPair(CurrencyConverter.Currency.JPY, CurrencyConverter.Currency.NOK), BigDecimal.valueOf(0.083));
        put(new CurrencyPair(CurrencyConverter.Currency.JPY, CurrencyConverter.Currency.GBR), BigDecimal.valueOf(0.069));
        put(new CurrencyPair(CurrencyConverter.Currency.JPY, CurrencyConverter.Currency.SKW), BigDecimal.valueOf(10.79));

        put(new CurrencyPair(CurrencyConverter.Currency.EUR, CurrencyConverter.Currency.USD), BigDecimal.valueOf(1.08));
        put(new CurrencyPair(CurrencyConverter.Currency.EUR, CurrencyConverter.Currency.CND), BigDecimal.valueOf(1.44));
        put(new CurrencyPair(CurrencyConverter.Currency.EUR, CurrencyConverter.Currency.RUB), BigDecimal.valueOf(68.99));
        put(new CurrencyPair(CurrencyConverter.Currency.EUR, CurrencyConverter.Currency.JPY), BigDecimal.valueOf(119.02));
        put(new CurrencyPair(CurrencyConverter.Currency.EUR, CurrencyConverter.Currency.INR), BigDecimal.valueOf(77.55));
        put(new CurrencyPair(CurrencyConverter.Currency.EUR, CurrencyConverter.Currency.BRL), BigDecimal.valueOf(4.67));
        put(new CurrencyPair(CurrencyConverter.Currency.EUR, CurrencyConverter.Currency.NOK), BigDecimal.valueOf(10.03));
        put(new CurrencyPair(CurrencyConverter.Currency.EUR, CurrencyConverter.Currency.GBR), BigDecimal.valueOf(0.83));
        put(new CurrencyPair(CurrencyConverter.Currency.EUR, CurrencyConverter.Currency.SKW), BigDecimal.valueOf(1283.70));

        put(new CurrencyPair(CurrencyConverter.Currency.INR, CurrencyConverter.Currency.USD), BigDecimal.valueOf(0.014));
        put(new CurrencyPair(CurrencyConverter.Currency.INR, CurrencyConverter.Currency.CND), BigDecimal.valueOf(0.018));
        put(new CurrencyPair(CurrencyConverter.Currency.INR, CurrencyConverter.Currency.RUB), BigDecimal.valueOf(0.89));
        put(new CurrencyPair(CurrencyConverter.Currency.INR, CurrencyConverter.Currency.JPY), BigDecimal.valueOf(1.56));
        put(new CurrencyPair(CurrencyConverter.Currency.INR, CurrencyConverter.Currency.EUR), BigDecimal.valueOf(0.013));
        put(new CurrencyPair(CurrencyConverter.Currency.INR, CurrencyConverter.Currency.BRL), BigDecimal.valueOf(0.061));
        put(new CurrencyPair(CurrencyConverter.Currency.INR, CurrencyConverter.Currency.NOK), BigDecimal.valueOf(0.13));
        put(new CurrencyPair(CurrencyConverter.Currency.INR, CurrencyConverter.Currency.GBR), BigDecimal.valueOf(0.011));
        put(new CurrencyPair(CurrencyConverter.Currency.INR, CurrencyConverter.Currency.SKW), BigDecimal.valueOf(16.79));

        put(new CurrencyPair(CurrencyConverter.Currency.BRL, CurrencyConverter.Currency.USD), BigDecimal.valueOf(0.23));
        put(new CurrencyPair(CurrencyConverter.Currency.BRL, CurrencyConverter.Currency.CND), BigDecimal.valueOf(0.30));
        put(new CurrencyPair(CurrencyConverter.Currency.BRL, CurrencyConverter.Currency.RUB), BigDecimal.valueOf(14.59));
        put(new CurrencyPair(CurrencyConverter.Currency.BRL, CurrencyConverter.Currency.JPY), BigDecimal.valueOf(25.50));
        put(new CurrencyPair(CurrencyConverter.Currency.BRL, CurrencyConverter.Currency.EUR), BigDecimal.valueOf(0.21));
        put(new CurrencyPair(CurrencyConverter.Currency.BRL, CurrencyConverter.Currency.INR), BigDecimal.valueOf(16.38));
        put(new CurrencyPair(CurrencyConverter.Currency.BRL, CurrencyConverter.Currency.NOK), BigDecimal.valueOf(2.12));
        put(new CurrencyPair(CurrencyConverter.Currency.BRL, CurrencyConverter.Currency.GBR), BigDecimal.valueOf(0.18));
        put(new CurrencyPair(CurrencyConverter.Currency.BRL, CurrencyConverter.Currency.SKW), BigDecimal.valueOf(275.03));

        put(new CurrencyPair(CurrencyConverter.Currency.NOK, CurrencyConverter.Currency.USD), BigDecimal.valueOf(0.11));
        put(new CurrencyPair(CurrencyConverter.Currency.NOK, CurrencyConverter.Currency.CND), BigDecimal.valueOf(0.14));
        put(new CurrencyPair(CurrencyConverter.Currency.NOK, CurrencyConverter.Currency.RUB), BigDecimal.valueOf(6.88));
        put(new CurrencyPair(CurrencyConverter.Currency.NOK, CurrencyConverter.Currency.JPY), BigDecimal.valueOf(12.01));
        put(new CurrencyPair(CurrencyConverter.Currency.NOK, CurrencyConverter.Currency.EUR), BigDecimal.valueOf(0.099));
        put(new CurrencyPair(CurrencyConverter.Currency.NOK, CurrencyConverter.Currency.INR), BigDecimal.valueOf(7.72));
        put(new CurrencyPair(CurrencyConverter.Currency.NOK, CurrencyConverter.Currency.BRL), BigDecimal.valueOf(0.47));
        put(new CurrencyPair(CurrencyConverter.Currency.NOK, CurrencyConverter.Currency.GBR), BigDecimal.valueOf(0.083));
        put(new CurrencyPair(CurrencyConverter.Currency.NOK, CurrencyConverter.Currency.SKW), BigDecimal.valueOf(129.58));

        put(new CurrencyPair(CurrencyConverter.Currency.GBR, CurrencyConverter.Currency.USD), BigDecimal.valueOf(1.29));
        put(new CurrencyPair(CurrencyConverter.Currency.GBR, CurrencyConverter.Currency.CND), BigDecimal.valueOf(1.71));
        put(new CurrencyPair(CurrencyConverter.Currency.GBR, CurrencyConverter.Currency.RUB), BigDecimal.valueOf(82.64));
        put(new CurrencyPair(CurrencyConverter.Currency.GBR, CurrencyConverter.Currency.JPY), BigDecimal.valueOf(144.10));
        put(new CurrencyPair(CurrencyConverter.Currency.GBR, CurrencyConverter.Currency.EUR), BigDecimal.valueOf(1.19));
        put(new CurrencyPair(CurrencyConverter.Currency.GBR, CurrencyConverter.Currency.INR), BigDecimal.valueOf(92.67));
        put(new CurrencyPair(CurrencyConverter.Currency.GBR, CurrencyConverter.Currency.BRL), BigDecimal.valueOf(5.65));
        put(new CurrencyPair(CurrencyConverter.Currency.GBR, CurrencyConverter.Currency.NOK), BigDecimal.valueOf(12.00));
        put(new CurrencyPair(CurrencyConverter.Currency.GBR, CurrencyConverter.Currency.SKW), BigDecimal.valueOf(1555.65));

        put(new CurrencyPair(CurrencyConverter.Currency.SKW, CurrencyConverter.Currency.USD), BigDecimal.valueOf(0.00083));
        put(new CurrencyPair(CurrencyConverter.Currency.SKW, CurrencyConverter.Currency.CND), BigDecimal.valueOf(0.0011));
        put(new CurrencyPair(CurrencyConverter.Currency.SKW, CurrencyConverter.Currency.RUB), BigDecimal.valueOf(0.053));
        put(new CurrencyPair(CurrencyConverter.Currency.SKW, CurrencyConverter.Currency.JPY), BigDecimal.valueOf(0.093));
        put(new CurrencyPair(CurrencyConverter.Currency.SKW, CurrencyConverter.Currency.EUR), BigDecimal.valueOf(0.00077));
        put(new CurrencyPair(CurrencyConverter.Currency.SKW, CurrencyConverter.Currency.INR), BigDecimal.valueOf(0.060));
        put(new CurrencyPair(CurrencyConverter.Currency.SKW, CurrencyConverter.Currency.BRL), BigDecimal.valueOf(0.0036));
        put(new CurrencyPair(CurrencyConverter.Currency.SKW, CurrencyConverter.Currency.GBR), BigDecimal.valueOf(0.00064));
        put(new CurrencyPair(CurrencyConverter.Currency.SKW, CurrencyConverter.Currency.NOK), BigDecimal.valueOf(0.0077));

    }};

    public CurrencyConverter() {
        super(new FlowLayout(FlowLayout.LEADING));

        JTextField amountInput = new JTextField(20);
        JPanel amount = new JPanel();
        amount.add(amountInput);
        amount.setBorder(BorderFactory.createTitledBorder("Enter Amount"));
        add(amount, BorderLayout.CENTER);

        JPanel from = new JPanel();
        JComboBox fromOptions = new JComboBox(Currency.values());
        from.add(fromOptions);
        from.setBorder(BorderFactory.createTitledBorder("Select currency"));
        add(from, BorderLayout.CENTER);

        JComboBox toOptions = new JComboBox(Currency.values());
        JPanel to = new JPanel();
        to.add(toOptions);
        to.setBorder(BorderFactory.createTitledBorder("Convert to"));
        add(to, BorderLayout.CENTER);

        JLabel convertText = new JLabel();
        JButton convertCmd = new JButton("Convert");
            convertCmd.addActionListener(convertAction(amountInput, fromOptions, toOptions, convertText));
        JPanel convert = new JPanel();
        convert.add(convertCmd);
        convert.add(convertText);
        add(convert);
    }

    private ActionListener convertAction(
            final JTextField amountInput,
            final JComboBox fromOptions,
            final JComboBox toOptions,
            final JLabel convertText) {

        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amountInputText = amountInput.getText();
                if ("".equals(amountInputText)) { return; }

                BigDecimal conversion = convertCurrency(amountInputText);
                convertText.setText(NumberFormat
                        .getCurrencyInstance(Locale.US)
                        .format(conversion));
            }

            private BigDecimal convertCurrency(String amountInputText) {
                CurrencyPair currencyPair = new CurrencyPair(
                        (Currency) fromOptions.getSelectedItem(),
                        (Currency) toOptions.getSelectedItem());
                BigDecimal rate = exchangeRates.get(currencyPair);
                BigDecimal amount = new BigDecimal(amountInputText);
                return amount.multiply(rate);
            }
        };
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();

        JMenu menu, submenu;
        JMenuItem i1; JMenuItem i2;
        JMenuItem i3; JMenuItem i4;
        JMenuItem i5;
        JMenuBar mb=new JMenuBar();
        menu = new JMenu("Options");
        i1 = new JMenuItem("Exit");
        submenu = new JMenu("Color");
        i2 = new JMenuItem("Yellow");
        i3 = new JMenuItem("Red");
        i4 = new JMenuItem("White");
        i1.addActionListener(e -> System.exit(0));
        i2.addActionListener(e -> frame.setBackground(Color.YELLOW));
        i3.addActionListener(e -> frame.setBackground(Color.RED));
        i4.addActionListener(e -> frame.setBackground(Color.WHITE));
        menu.add(submenu);
        menu.add(i1);
        submenu.add(i2);
        submenu.add(i3);
        submenu.add(i4);
        mb.add(menu);

        frame.getContentPane().add(new CurrencyConverter());
        frame.setTitle("Currency Converter");
        frame.setSize(500, 230);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setJMenuBar(mb);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}