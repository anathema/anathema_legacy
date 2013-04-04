package net.sf.anathema.character.sidereal.reporting.rendering;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.Resources;

public class ParadoxInfoEncoder implements ContentEncoder {

  private final Resources resources;
  private final int fontSize;
  private float lineHeight;

  public ParadoxInfoEncoder(int fontSize, Resources resources) {
    this.fontSize = fontSize;
    this.resources = resources;
    this.lineHeight = fontSize * 1.5f;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    Phrase phrase = new Phrase("", graphics.createFont(fontSize));
    Chunk symbolChunk = graphics.createSymbolChunk();
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.Paradox.OutOfCharacter") + "\n");
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.Paradox.Resident") + "\n");
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.Paradox.Anima") + "\n");
    phrase.add(resources.getString("Sheet.Paradox.AnimaLow") + "\n");
    phrase.add(resources.getString("Sheet.Paradox.AnimaHigh2nd") + "\n");
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.Paradox.ConfusionExalted") + "\n");
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.Paradox.ConfusionResplendent") + "\n");
    phrase.add(resources.getString("Sheet.Paradox.ConfusionResplendentImitation") + "\n");
    phrase.add(resources.getString("Sheet.Paradox.ConfusionResplendentSupernatural") + "\n");
    graphics.createSimpleColumn(bounds).withLeading(lineHeight).andTextPart(phrase).encode();
  }

  @Override
  public String getHeader(ReportSession session) {
    return resources.getString("Sheet.Header.Sidereal.Paradox");
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }
}
