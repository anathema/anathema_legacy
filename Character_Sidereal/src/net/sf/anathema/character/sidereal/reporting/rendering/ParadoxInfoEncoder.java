package net.sf.anathema.character.sidereal.reporting.rendering;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class ParadoxInfoEncoder implements ContentEncoder {

  private final IExaltedEdition edition;
  private final IResources resources;
  private final int fontSize;
  private float lineHeight;

  public ParadoxInfoEncoder(int fontSize, IResources resources, IExaltedEdition edition) {
    this.fontSize = fontSize;
    this.resources = resources;
    this.lineHeight = fontSize * 1.5f;
    this.edition = edition;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    String animaResource = edition == ExaltedEdition.FirstEdition ? "Sheet.Paradox.AnimaHigh" : "Sheet.Paradox.AnimaHigh2nd";
    Phrase phrase = new Phrase("", graphics.createFont(fontSize)); //$NON-NLS-1$
    Chunk symbolChunk = graphics.createSymbolChunk();
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.Paradox.OutOfCharacter") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.Paradox.Resident") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.Paradox.Anima") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(resources.getString("Sheet.Paradox.AnimaLow") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(resources.getString(animaResource) + "\n"); //$NON-NLS-1$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.Paradox.ConfusionExalted") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.Paradox.ConfusionResplendent") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(resources.getString("Sheet.Paradox.ConfusionResplendentImitation") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    phrase.add(resources.getString("Sheet.Paradox.ConfusionResplendentSupernatural") + "\n"); //$NON-NLS-1$//$NON-NLS-2$
    graphics.createSimpleColumn(bounds).withLeading(lineHeight).andTextPart(phrase).encode();
  }

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header.Sidereal.Paradox");
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}
