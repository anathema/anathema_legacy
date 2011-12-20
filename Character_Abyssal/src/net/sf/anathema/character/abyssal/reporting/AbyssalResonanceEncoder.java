package net.sf.anathema.character.abyssal.reporting;

import java.util.List;

import net.sf.anathema.character.generic.character.*;
import net.sf.anathema.character.generic.impl.traits.ValueWeightGenericTraitSorter;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.virtueflaw.VirtueFlawBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfEncodingUtilities;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;

public class AbyssalResonanceEncoder  implements IBoxContentEncoder {
  private final VirtueFlawBoxEncoder traitEncoder;
  private final IResources resources;
  private final Chunk symbolChunk;

  public AbyssalResonanceEncoder(BaseFont symbolBaseFont, IResources resources) {
    this.resources = resources;
    this.traitEncoder = new VirtueFlawBoxEncoder();
    this.symbolChunk = PdfEncodingUtilities.createCaretSymbolChunk(symbolBaseFont);
  }

  public String getHeaderKey(ReportContent content) {
    return "GreatCurse.Abyssal"; //$NON-NLS-1$
  }

  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    Bounds textBounds = traitEncoder.encode(graphics, bounds, 0);
    Font font = TableEncodingUtilities.createFont(graphics.getBaseFont());
    Phrase phrase = new Phrase("", font); //$NON-NLS-1$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.GreatCurse.SocialPoolMessage", getMaxVirtueValue(reportContent.getCharacter())) + "\n"); //$NON-NLS-1$ //$NON-NLS-2$
    phrase.add(symbolChunk);
    phrase.add(resources.getString("Sheet.GreatCurse.VirtueDifficulty")); //$NON-NLS-1$
    graphics.encodeTextWithReducedLineHeight(textBounds, phrase);
  }
  
  public boolean hasContent(ReportContent content)
  {
	  return true;
  }

  private int getMaxVirtueValue(IGenericCharacter character) {
    IGenericTrait[] virtues = character.getTraitCollection().getTraits(VirtueType.values());
    List<IGenericTrait> sortedVirtues = new ValueWeightGenericTraitSorter().sortDescending(virtues);
    return sortedVirtues.get(0).getCurrentValue();
  }
}
