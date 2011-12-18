package net.sf.anathema.character.lunar.reporting.rendering.knacks;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.lunar.reporting.content.stats.knacks.IKnackStats;
import net.sf.anathema.character.lunar.reporting.content.stats.knacks.KnackStats;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.Graphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import java.util.ArrayList;
import java.util.List;

public class KnackEncoder implements IBoxContentEncoder {

  private static IIdentificate KNACK = new Identificate("Knack");

  public static List<IKnackStats> collectPrintKnacks(final IGenericCharacter character) {
    final List<IKnackStats> printKnacks = new ArrayList<IKnackStats>();
    ICharm[] charmSet = character.getLearnedCharms();
    for (ICharm charm : charmSet) {
      if (!charm.hasAttribute(KNACK)) {
        continue;
      }
      printKnacks.add(new KnackStats(charm));
    }
    return printKnacks;
  }

  private final IResources resources;
  private final BaseFont baseFont;

  public KnackEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public String getHeaderKey(ReportContent reportContent) {
    return "Lunar.Knacks"; //$NON-NLS-1$
  }

  public void encode(Graphics graphics, ReportContent reportContent) throws DocumentException {
    List<IKnackStats> printKnacks = collectPrintKnacks(reportContent.getCharacter());
    KnackTableEncoder encoder = new KnackTableEncoder(resources, baseFont, printKnacks);
    encoder.encodeTable(graphics.getDirectContent(), reportContent, graphics.getBounds());
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
