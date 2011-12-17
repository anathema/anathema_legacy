package net.sf.anathema.character.sidereal.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.util.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.sidereal.flawedfate.SiderealFlawedFateTemplate;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SiderealFlawedFateEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {

  private final BaseFont baseFont;
  private final Font boldFont;
  private final IResources resources;
  private final VirtueFlawBoxEncoder traitEncoder;

  public SiderealFlawedFateEncoder(BaseFont baseFont, BaseFont symbolBaseFont, IResources resources) {
    this.baseFont = baseFont;
    this.resources = resources;
    this.traitEncoder = new VirtueFlawBoxEncoder(baseFont);
    this.boldFont = new Font(baseFont);
    boldFont.setStyle(Font.BOLD);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
	IVirtueFlaw virtueFlaw = ((IVirtueFlawModel) character.getAdditionalModel(SiderealFlawedFateTemplate.ID)).getVirtueFlaw();
    Bounds textBounds = traitEncoder.encode(directContent, bounds, virtueFlaw.getLimitTrait().getCurrentValue());
    //float lineHeight = (textBounds.height - TEXT_PADDING) / 2;
    /*String effects = resources.getString("Sheet.GreatCurse.Sidereal.CurrentEffects") + ":"; //$NON-NLS-1$ //$NON-NLS-2$
    drawLabelledContent(
        directContent,
        effects,
        null,
        new Position(textBounds.x, textBounds.getMaxY() - lineHeight),
        bounds.width);*/

    Font font = TableEncodingUtilities.createFont(getBaseFont());
    Phrase phrase = new Phrase("", font); //$NON-NLS-1$
    phrase.add(new Chunk(resources.getString("Sheet.GreatCurse.Sidereal.LimitBreak") + ": ", boldFont)); //$NON-NLS-1$
    String fateString = resources.getString("Sheet.GreatCurse.Sidereal.FlawedFate." + character.getCasteType().getId()) + "\n";
    if (fateString.startsWith("#")) fateString = "\n";
    phrase.add(fateString); 
    PdfTextEncodingUtilities.encodeText(directContent, phrase, textBounds, IVoidStateFormatConstants.LINE_HEIGHT - 2);
  }

  public String getHeaderKey() {
    return "FlawedFate"; //$NON-NLS-1$
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }
}