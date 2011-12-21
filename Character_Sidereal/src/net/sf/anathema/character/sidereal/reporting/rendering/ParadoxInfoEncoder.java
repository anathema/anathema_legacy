package net.sf.anathema.character.sidereal.reporting.rendering;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

import java.awt.*;

public class ParadoxInfoEncoder implements IBoxContentEncoder {

  private final IExaltedEdition edition;
  private final BaseFont basefont;
  private final IResources resources;
  private final int fontSize;
  private float lineHeight;

  public ParadoxInfoEncoder(BaseFont baseFont, int fontSize, IResources resources, IExaltedEdition edition) {
    this.fontSize = fontSize;
    this.resources = resources;
    this.basefont = baseFont;
    this.lineHeight = fontSize * 1.5f;
    this.edition = edition;
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    String animaResource = edition == ExaltedEdition.FirstEdition ? "Sheet.Paradox.AnimaHigh" : "Sheet.Paradox.AnimaHigh2nd";
    Phrase phrase = new Phrase("", new Font(basefont, fontSize, Font.NORMAL, Color.BLACK)); //$NON-NLS-1$
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
    graphics.encodeText(phrase, bounds, lineHeight, Element.ALIGN_LEFT);
  }

  public String getHeaderKey(ReportContent content) {
    return "Sidereal.Paradox"; //$NON-NLS-1$
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
