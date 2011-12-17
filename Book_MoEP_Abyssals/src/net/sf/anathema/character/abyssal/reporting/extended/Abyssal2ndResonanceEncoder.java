package net.sf.anathema.character.abyssal.reporting.extended;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.abyssal.resonance.AbyssalResonanceTemplate;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.reporting.common.encoder.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.extended.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.extended.util.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.lib.resources.IResources;

public class Abyssal2ndResonanceEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {

  private final BaseFont baseFont;
  private final VirtueFlawBoxEncoder traitEncoder;
  private final IResources resources;

  public Abyssal2ndResonanceEncoder(BaseFont baseFont, IResources resources) {
    this.baseFont = baseFont;
    this.resources = resources;
    this.traitEncoder = new VirtueFlawBoxEncoder(baseFont);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "GreatCurse.Abyssal"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
    IVirtueFlaw resonance = ((IVirtueFlawModel) character.getAdditionalModel(AbyssalResonanceTemplate.ID)).getVirtueFlaw();
    Bounds textBounds = traitEncoder.encode(directContent, bounds, resonance.getLimitTrait().getCurrentValue());
    Font font = createFont(getBaseFont());
    Font nameFont = createNameFont(getBaseFont());
    Phrase phrase = new Phrase("", font); //$NON-NLS-1$
    phrase.add(new Chunk(resources.getString("Sheet.GreatCurse.FlawedVirtue") + ": ", nameFont)); //$NON-NLS-1$ //$NON-NLS-2$
    if (resonance.isFlawComplete()) {
      phrase.add(resonance.getRoot().getId() + ".\n");
    } else {
      Font undefinedFont = new Font(font);
      undefinedFont.setStyle(Font.UNDERLINE);
      phrase.add(new Chunk("                                          ", undefinedFont)); //$NON-NLS-1$
      phrase.add(".\n");
    }
    phrase.add(resources.getString("Sheet.GreatCurse.ResonanceReference")); //$NON-NLS-1$
    encodeTextWithReducedLineHeight(directContent, textBounds, phrase);
  }

  public boolean hasContent(IGenericCharacter character) {
    return true;
  }

  private Font createNameFont(BaseFont baseFont) {
    Font newFont = createFont(baseFont);
    newFont.setStyle(Font.BOLD);
    return newFont;
  }

  private Font createFont(BaseFont baseFont) {
    return TableEncodingUtilities.createFont(baseFont);
  }
}
