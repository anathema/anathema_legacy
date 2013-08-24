package net.sf.anathema.scribe.editor.model;

import net.sf.anathema.platform.markdown.HtmlConverter;
import net.sf.anathema.platform.markdown.HtmlText;
import net.sf.anathema.platform.markdown.WikiText;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HtmlConverterBasicTest {

  private final HtmlConverter converter = new HtmlConverter();

  @org.junit.Test
  public void convertsLesserThan() {
    assertConvertsTo(new WikiText("<"), new HtmlText("&lt;"));
  }

  @org.junit.Test
  public void convertsGreaterThan() {
    assertConvertsTo(new WikiText(">"), new HtmlText("&gt;"));
  }

  @org.junit.Test
  public void doesNotConvertSimpleText() {
    assertConvertsTo(new WikiText("Mee"), new HtmlText("Mee"));
  }

  private void assertConvertsTo(WikiText wikiText, HtmlText htmlText) {
    HtmlText convertedText = converter.convert(wikiText);
    assertThat(convertedText.getHtmlText(), is("<p>" + htmlText.getHtmlText() + "</p>\n"));
  }
}
