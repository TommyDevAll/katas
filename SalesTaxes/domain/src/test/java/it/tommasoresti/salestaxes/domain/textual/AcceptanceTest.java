package it.tommasoresti.salestaxes.domain.textual;


import org.junit.Before;
import org.junit.Test;

import it.tommasoresti.salestaxes.domain.SalesTaxes;
import it.tommasoresti.salestaxes.domain.round.RoundUp5CentsPolicy;
import it.tommasoresti.salestaxes.domain.tax.DefaultTaxRuleHandler;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AcceptanceTest {

    private TextualSalesTaxes salesTaxes;

    @Before
    public void setUp() throws Exception {
        salesTaxes = new TextualSalesTaxes(new SalesTaxes(new DefaultTaxRuleHandler(), new RoundUp5CentsPolicy()));
    }

    @Test
    public void given_a_book_a_cd_and_a_chocolate_bar() throws Exception {
        String receipt = salesTaxes.of("1 book at 12.49 1 music CD at 14.99 1 chocolate bar at 0.85");
        assertThat(receipt, is("1 book: 12.49 1 music CD: 16.49 1 chocolate bar: 0.85 Sales Taxes: 1.50 Total: 29.83"));
    }

    @Test
    public void given_imported_chocolate_and_imported_perfume() throws Exception {
        String receipt = salesTaxes.of("1 imported box of chocolates at 10.00 1 imported bottle of perfume at 47.50");
        assertThat(receipt, is("1 imported box of chocolates: 10.50 1 imported bottle of perfume: 54.65 Sales Taxes: 7.65 Total: 65.15"));
    }

    @Test
    public void given_imported_perfume_a_not_imported_purfume_some_pills_and_chocolate() throws Exception {
        String receipt = salesTaxes.of("1 imported bottle of perfume at 27.99 1 bottle of perfume at 18.99 1 packet of headache pills at 9.75 1 box of imported chocolates at 11.25");
        assertThat(receipt, is("1 imported bottle of perfume: 32.19 1 bottle of perfume: 20.89 1 packet of headache pills: 9.75 1 imported box of chocolates: 11.85 Sales Taxes: 6.70 Total: 74.68"));
    }
}