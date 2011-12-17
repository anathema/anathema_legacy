package net.sf.anathema.character.lunar.reporting.sheet;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.character.*;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.lunar.reporting.stats.knacks.IKnackStats;
import net.sf.anathema.character.lunar.reporting.stats.knacks.KnackStats;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class KnackEncoder implements IPdfContentBoxEncoder {

	private static IIdentificate KNACK = new Identificate("Knack");
	
  public static List<IKnackStats> collectPrintKnacks(final IGenericCharacter character) {
    final List<IKnackStats> printKnacks = new ArrayList<IKnackStats>();
    ICharm[] charmSet = character.getLearnedCharms();
    for (ICharm charm : charmSet)
    {
    	if (!charm.hasAttribute(KNACK))
      	  continue;
        printKnacks.add(new KnackStats(charm));
    }
    return printKnacks;
  }

  private final IResources resources;
  private final BaseFont baseFont;

  public KnackEncoder(IResources resources, BaseFont baseFont)
  {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Lunar.Knacks"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
    new KnackTableEncoder(resources, baseFont, collectPrintKnacks(character)).encodeTable(directContent, character, bounds);
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }
}
