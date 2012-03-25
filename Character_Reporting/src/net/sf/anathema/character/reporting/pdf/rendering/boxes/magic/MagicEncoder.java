package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.magic.GenericCharmUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

public class MagicEncoder implements ContentEncoder {

  public static List<IMagicStats> collectPrintMagic(ReportSession session) {
    IGenericCharacter character = session.getCharacter();
    List<IMagicStats> printStats = new ArrayList<IMagicStats>();
    addGenericCharmsForPrint(character, printStats);
    addConcreteLearnedMagicForPrint(character, printStats);
    return printStats;
  }

  private static void addGenericCharmsForPrint(IGenericCharacter character, List<IMagicStats> printStats) {
    for (IMagicStats stats : GenericCharmUtilities.getGenericCharmStats(character)) {
      if (GenericCharmUtilities.shouldShowCharm(stats, character)) {
        printStats.add(stats);
      }
    }
  }

  private static void addConcreteLearnedMagicForPrint(IGenericCharacter character, List<IMagicStats> printStats) {
    IMagicVisitor statsCollector = new MagicStatsFactoryVisitor(character, printStats);
    for (IMagic magic : character.getAllLearnedMagic()) {
      magic.accept(statsCollector);
    }
  }

  private final MagicTableEncoder tableEncoder;
  private final IResources resources;

  public MagicEncoder(IResources resources) {
    this.resources = resources;
    this.tableEncoder = new MagicTableEncoder(resources, false);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    float top = bounds.getMinY();
    Bounds remainingBounds = new Bounds(bounds.getMinX(), top, bounds.getWidth(), bounds.getMaxY() - top);
    tableEncoder.encodeTable(graphics, reportSession, remainingBounds);
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }

  @Override
  public String getHeader(ReportSession session) {
    return resources.getString("Sheet.Header.Charms");
  }
}
