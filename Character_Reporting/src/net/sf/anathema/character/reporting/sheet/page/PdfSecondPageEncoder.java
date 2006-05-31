package net.sf.anathema.character.reporting.sheet.page;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfSecondPageEncoder implements IPdfPageEncoder {

  private final IPdfContentEncoder nullContentEncoder = new IPdfContentEncoder() {
    public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds)
        throws DocumentException {
    }
  };
  private final BaseFont baseFont;
  private final PdfPageConfiguration configuration;
  private PdfBoxEncoder boxEncoder;
  private final IResources resources;

  public PdfSecondPageEncoder(IResources resources, BaseFont baseFont, PdfPageConfiguration configuration) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.configuration = configuration;
    this.boxEncoder = new PdfBoxEncoder(resources, baseFont);
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description)
      throws DocumentException {
    float distanceFromTop = 0;
    float languageHeight = 80;
    float experienceHeight = 30;
    encodeBackgrounds(directContent, character, distanceFromTop, languageHeight
        + experienceHeight
        + IVoidStateFormatConstants.PADDING);
    encodeLanguages(directContent, character, distanceFromTop, languageHeight);
    distanceFromTop += languageHeight + IVoidStateFormatConstants.PADDING;
    encodeExperience(directContent, character, distanceFromTop, experienceHeight);
    distanceFromTop += experienceHeight + IVoidStateFormatConstants.PADDING;
    float comboHeight = encodeCombos(directContent, character, distanceFromTop);
    if (comboHeight > 0) {
      distanceFromTop += comboHeight + IVoidStateFormatConstants.PADDING;
    }
  }

  private float encodeCombos(PdfContentByte directContent, IGenericCharacter character, float distanceFromTop)
      throws DocumentException {
    IGenericCombo[] combos = character.getCombos();
    if (combos.length == 0) {
      return 0;
    }
    Bounds restOfPage = boxEncoder.calculateContentBounds(new Bounds(
        configuration.getLeftColumnX(0),
        configuration.getLowerContentY(),
        configuration.getContentWidth(),
        configuration.getContentHeight() - distanceFromTop));
    Bounds contentBounds = boxEncoder.calculateContentBounds(restOfPage);
    Phrase phrase = new Phrase();
    Font font = PdfTextEncodingUtilities.createTextFont(baseFont);
    Font nameFont = new Font(font);
    nameFont.setStyle(Font.BOLD);
    for (IGenericCombo combo : combos) {
      if (!phrase.isEmpty()) {
        phrase.add(new Chunk("\n", font)); //$NON-NLS-1$
      }
      phrase.add(new Chunk(combo.getName() + ": ", nameFont)); //$NON-NLS-1$
      String[] charmNames = ArrayUtilities.transform(
          combo.getCharms(),
          String.class,
          new ITransformer<ICharm, String>() {
            public String transform(ICharm input) {
              return resources.getString(input.getId());
            }
          });
      String charmString = AnathemaStringUtilities.concat(charmNames, ", "); //$NON-NLS-1$
      phrase.add(new Chunk(charmString, font));
      PdfTextEncodingUtilities.encodeText(directContent, phrase, contentBounds, IVoidStateFormatConstants.LINE_HEIGHT);
    }
    return 0;
  }

  private float encodeExperience(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds bounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    boxEncoder.encodeBox(directContent, nullContentEncoder, character, bounds, "Experience"); //$NON-NLS-1$
    return height;
  }

  private float encodeLanguages(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds languageBounds = configuration.getThirdColumnRectangle(distanceFromTop, height);
    boxEncoder.encodeBox(directContent, nullContentEncoder, character, languageBounds, "Languages"); //$NON-NLS-1$
    return height;
  }

  private float encodeBackgrounds(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      float height) throws DocumentException {
    Bounds backgroundBounds = configuration.getFirstColumnRectangle(distanceFromTop, height, 2);
    boxEncoder.encodeBox(directContent, nullContentEncoder, character, backgroundBounds, "Backgrounds"); //$NON-NLS-1$
    return height;
  }
}