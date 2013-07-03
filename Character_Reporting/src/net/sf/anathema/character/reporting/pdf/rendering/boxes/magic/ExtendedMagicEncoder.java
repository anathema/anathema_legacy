package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.magic.AbstractMagicContent;
import net.sf.anathema.character.reporting.pdf.content.magic.MagicContentHelper;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.CharmStats;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MultipleEffectCharmStats;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.SpellStats;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.ArrayList;
import java.util.List;

public class ExtendedMagicEncoder<C extends AbstractMagicContent> implements ContentEncoder {
  static Identifier KNACK = new SimpleIdentifier("Knack");
  private Resources resources;

  public static List<IMagicStats> collectPrintCharms(ReportSession session) {
    return collectPrintMagic(session.getHero(), false, true);
  }

  public static List<IMagicStats> collectPrintSpells(ReportSession session) {
    return collectPrintMagic(session.getHero(), true, false);
  }

  private static List<IMagicStats> collectPrintMagic(final Hero hero,  final boolean includeSpells, final boolean includeCharms) {
    final List<IMagicStats> printStats = new ArrayList<>();
    final MagicContentHelper helper = new MagicContentHelper(hero);
    if (includeCharms) {
      for (IMagicStats stats : helper.getGenericCharmStats()) {
        if (helper.shouldShowCharm(stats)) {
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
        if (helper.isGenericCharmFor(charm)) {
          return;
        }
        if (charm.hasAttribute(KNACK)) {
          return;
        }

        if (helper.isMultipleEffectCharm(charm)) {
          String[] effects = helper.getLearnedEffects(charm);
          for (String effect : effects) {
            printStats.add(new MultipleEffectCharmStats(charm, effect));
          }
        } else {
          printStats.add(new CharmStats(charm, new MagicContentHelper(hero)));
        }
      }

      @Override
      public void visitSpell(ISpell spell) {
        if (includeSpells) {
          printStats.add(new SpellStats(spell));
        }
      }
    };
    for (IMagic magic : helper.getAllLearnedMagic()) {
      magic.accept(statsCollector);
    }
    return printStats;
  }

  private final MagicTableEncoder tableEncoder;
  private final String headerKey;

  public ExtendedMagicEncoder(Resources resources, Class<C> contentClass, boolean sectionHeaderLines, String headerKey) {
    this.resources = resources;
    this.tableEncoder = new MagicTableEncoder(sectionHeaderLines, contentClass);
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
