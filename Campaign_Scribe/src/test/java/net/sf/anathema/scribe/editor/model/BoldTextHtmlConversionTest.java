package net.sf.anathema.scribe.editor.model;

import net.sf.anathema.platform.markdown.HtmlConverter;
import net.sf.anathema.platform.markdown.HtmlText;
import net.sf.anathema.platform.markdown.WikiText;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BoldTextHtmlConversionTest {

  private final HtmlConverter converter = new HtmlConverter();

  @org.junit.Test
  public void convertsCompleteBoldText() {
    assertConvertsTo(new WikiText("**Sandra**"), new HtmlText("<strong>Sandra</strong>"));
  }

  @org.junit.Test
  public void convertsLeadingBoldText() {
    assertConvertsTo(new WikiText("**Sandra** ist lieb"), new HtmlText("<strong>Sandra</strong> ist lieb"));
  }

  @org.junit.Test
  public void convertsTrailingBoldText() {
    assertConvertsTo(new WikiText("Meine **Sandra**"), new HtmlText("Meine <strong>Sandra</strong>"));
  }

  @org.junit.Test
  public void convertsMultipleBoldTexts() {
    assertConvertsTo(new WikiText("Du **bist** meine **Sandra**"), new HtmlText("Du <strong>bist</strong> meine <strong>Sandra</strong>"));
  }

  private void assertConvertsTo(WikiText wikiText, HtmlText htmlText) {
    HtmlText convertedText = converter.convert(wikiText);
    assertThat(convertedText.getHtmlText(), is("<p>" + htmlText.getHtmlText() + "</p>\n"));
  }
}
