package net.sf.anathema.scribe.scroll.conversion;

import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.model.StyledTextualDescription;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.framework.styledtext.presentation.TextPart;
import org.junit.Test;

import static net.sf.anathema.lib.text.FontStyle.BOLD;
import static net.sf.anathema.lib.text.FontStyle.BOLD_ITALIC;
import static net.sf.anathema.lib.text.FontStyle.ITALIC;
import static net.sf.anathema.lib.text.FontStyle.PLAIN;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StyledTextToWikiTextConverter_Test {

  @Test
  public void convertsPlainText() throws Exception {
    TextPart part = new TextPart("Plain", new TextFormat());
    String expectation = "Plain";
    convertSinglePart(part, expectation);
  }

  @Test
  public void convertsBoldText() throws Exception {
    convertSinglePart(new TextPart("Bold", new TextFormat(BOLD, false)), "**Bold**");
  }

  @Test
  public void convertsItalicText() throws Exception {
    convertSinglePart(new TextPart("Italic", new TextFormat(ITALIC, false)), "*Italic*");
  }

  @Test
  public void convertsUnderlinedText() throws Exception {
    convertSinglePart(new TextPart("Underline", new TextFormat(PLAIN, true)), "++Underline++");
  }

  @Test
  public void convertsCombinationsAsWell() throws Exception {
    convertSinglePart(new TextPart("BoldItalic", new TextFormat(BOLD_ITALIC, false)), "***BoldItalic***");
  }

  @Test
  public void convertsAllParts() throws Exception {
    TextPart part1 = new TextPart("Plain", new TextFormat(PLAIN, false));
    TextPart part2 = new TextPart("Bold", new TextFormat(BOLD, false));
    convertParts("Plain**Bold**", part1, part2);
  }

  private void convertSinglePart(TextPart input, String expectation) {
    StyledTextualDescription description = new StyledTextualDescription();
    description.setText(new ITextPart[]{input});
    assertThat(new StyledTextToWikiTextConverter().convert(description), is(expectation));
  }

  private void convertParts(String expectation, TextPart... input) {
    StyledTextualDescription description = new StyledTextualDescription();
    description.setText(input);
    assertThat(new StyledTextToWikiTextConverter().convert(description), is(expectation));
  }
}
