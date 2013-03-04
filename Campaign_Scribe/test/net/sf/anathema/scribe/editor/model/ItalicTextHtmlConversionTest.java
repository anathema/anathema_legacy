package net.sf.anathema.scribe.editor.model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ItalicTextHtmlConversionTest {

  private final HtmlConverter converter = new HtmlConverter();

  @org.junit.Test
  public void convertsCompleteBoldText() {
    assertConvertsTo(new WikiText("*Urs*"), new HtmlText("<em>Urs</em>"));
  }

  private void assertConvertsTo(WikiText wikiText, HtmlText htmlText) {
    HtmlText convertedText = converter.convert(wikiText);
    assertThat(convertedText.getHtmlText(), is("<p>" + htmlText.getHtmlText() + "</p>\n"));
  }
}
