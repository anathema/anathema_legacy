package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.magic.GenericCharmUtilities;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.CharmStats;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MultipleEffectCharmStats;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.SpellStats;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import java.util.ArrayList;
import java.util.List;

public class ExtendedMagicEncoder implements ContentEncoder {
  static IIdentificate KNACK = new Identificate("Knack");
  private IResources resources;

  public static List<IMagicStats> collectPrintCharms(ReportSession session) {
    return collectPrintMagic(session.getCharacter(), false, true);
  }

  public static List<IMagicStats> collectPrintSpells(ReportSession session) {
    return collectPrintMagic(session.getCharacter(), true, false);
  }

  private static List<IMagicStats> collectPrintMagic(final IGenericCharacter character, final boolean includeSpells,
          final boolean includeCharms) {
    final List<IMagicStats> printStats = new ArrayList<IMagicStats>();
    if (includeCharms) {
      for (IMagicStats stats : GenericCharmUtilities.getGenericCharmStats(character)) {
        if (GenericCharmUtilities.shouldShowCharm(stats, character)) {
          printStats.add(stats);
        }
      }
    }

    IMagicVisitor statsCollector = new IMagicVisitor() {
      @Override
      public void visitCharm(ICharm charm) {
        if (!includeCharms) {
          return;
        }
        if (GenericCharmUtilities.isGenericCharmFor(charm, character)) {
          return;
        }
        if (charm.hasAttribute(KNACK)) {
          return;
        }

        if (character.isMultipleEffectCharm(charm)) {
          String[] effects = character.getLearnedEffects(charm);
          for (String effect : effects) {
            printStats.add(new MultipleEffectCharmStats(charm, effect));
          }
        } else {
          printStats.add(new CharmStats(charm, character));
        }
      }

      @Override
      public void visitSpell(ISpell spell) {
        if (includeSpells) {
          printStats.add(new SpellStats(spell));
        }
      }
    };
    for (IMagic magic : character.getAllLearnedMagic()) {
      magic.accept(statsCollector);
    }
    return printStats;
  }

  private final MagicTableEncoder tableEncoder;
  private final String headerKey;

  public ExtendedMagicEncoder(IResources resources, List<IMagicStats> printMagic) {
    this(resources, printMagic, false, "Charms"); //$NON-NLS-1$
  }

  public ExtendedMagicEncoder(IResources resources, List<IMagicStats> printMagic, boolean sectionHeaderLines,
          String headerKey) {
    this.resources = resources;
    this.tableEncoder = new MagicTableEncoder(sectionHeaderLines);
    this.headerKey = headerKey;
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
    return resources.getString("Sheet.Header." + headerKey);
  }
}
