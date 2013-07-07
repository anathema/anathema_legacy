package net.sf.anathema.hero.charms.sheet.encoder;

import com.google.common.base.Objects;
import com.itextpdf.text.pdf.PdfPTable;
import net.sf.anathema.character.main.magic.IMagicStats;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.charms.sheet.content.AbstractMagicContent;
import net.sf.anathema.hero.sheet.pdf.content.stats.IStatsGroup;
import net.sf.anathema.hero.sheet.pdf.encoder.extent.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.general.stats.AbstractStatsTableEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;

public class MagicTableEncoder <C extends AbstractMagicContent> extends AbstractStatsTableEncoder<IMagicStats, ReportSession> {

  private final boolean sectionHeaderLines;
  private Class<C> contentClass;

  public MagicTableEncoder(boolean sectionHeaderLines, Class<C> contentClass) {
    super(sectionHeaderLines);
    this.sectionHeaderLines = sectionHeaderLines;
    this.contentClass = contentClass;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected IStatsGroup<IMagicStats>[] createStatsGroups(ReportSession session) {
    return createContent(session).createStatsGroups();
  }

  @Override
  protected void encodeContent(SheetGraphics graphics, PdfPTable table, ReportSession session, Bounds bounds) {
    AbstractMagicContent content = createContent(session);
    float heightLimit = bounds.height - 3;
    IStatsGroup<IMagicStats>[] groups = createStatsGroups(session);
    boolean encodeLine = true;
    String groupName = null;
    for (IMagicStats stats : content.getPrintMagic()) {
      String newGroupName = content.getGroupName(stats);
      if (!Objects.equal(groupName, newGroupName)) {
        groupName = newGroupName;
        encodeSectionLine(graphics, table, groupName);
        if (sectionHeaderLines) {
          encodeHeaderLine(graphics, table, groups);
        }
        encodeLine = table.getTotalHeight() < heightLimit;
        if (!encodeLine) {
          table.deleteLastRow();
          return;
        }
      }
      if (encodeLine) {
        encodeContentLine(graphics, table, groups, stats);
        encodeLine = table.getTotalHeight() < heightLimit;
        if (!encodeLine) {
          table.deleteLastRow();
          return;
        }
        content.markAsPrinted(stats);
      }
    }
    while (encodeLine) {
      encodeContentLine(graphics, table, groups, null);
      encodeLine = table.getTotalHeight() < heightLimit;
    }
    table.deleteLastRow();
  }

  private AbstractMagicContent createContent(ReportSession session) {
    return session.createContent(contentClass);
  }
}
