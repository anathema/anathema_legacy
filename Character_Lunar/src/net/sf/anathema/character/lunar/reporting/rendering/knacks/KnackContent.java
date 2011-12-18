package net.sf.anathema.character.lunar.reporting.rendering.knacks;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.lunar.reporting.content.stats.knacks.IKnackStats;
import net.sf.anathema.character.lunar.reporting.content.stats.knacks.KnackNameStatsGroup;
import net.sf.anathema.character.lunar.reporting.content.stats.knacks.KnackSourceStatsGroup;
import net.sf.anathema.character.lunar.reporting.content.stats.knacks.KnackStats;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

import java.util.ArrayList;
import java.util.List;

public class KnackContent extends AbstractSubContent {

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

  private IGenericCharacter character;

  public KnackContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  public IStatsGroup<IKnackStats>[] createStatsGroups() {
    IResources resources = getResources();
    return new IStatsGroup[]{new KnackNameStatsGroup(resources), new KnackSourceStatsGroup(resources)};
  }

  public List<IKnackStats> createPrintKnacks() {
    return collectPrintKnacks(character);
  }

  @Override
  public String getHeaderKey() {
    return "Lunar.Knacks";
  }
}
