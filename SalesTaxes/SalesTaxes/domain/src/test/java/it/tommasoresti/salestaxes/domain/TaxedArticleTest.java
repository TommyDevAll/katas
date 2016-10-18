package it.tommasoresti.salestaxes.domain;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import it.tommasoresti.salestaxes.domain.article.Article;
import it.tommasoresti.salestaxes.domain.article.Food;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TaxedArticleTest {
    @Test
    public void given_an_article_taxed_by_10_percent() throws Exception {
        Food food = new Food("food");
        food.setPrice(100);

        TaxedArticle taxedArticle = new TaxedArticle(food, 10);

        assertThat(taxedArticle.getArticle(), CoreMatchers.<Article>is(food));
        assertThat(taxedArticle.getFinalPrice(), is(110f));
        assertThat(taxedArticle.getTaxesPercentage(), is(10f));
    }
}