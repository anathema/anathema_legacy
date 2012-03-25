package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.stats.IStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicCostStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicDetailsStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicDurationStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicNameStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicSourceStatsGroup;
import net.sf.anathema.character.reporting.pdf.content.stats.magic.MagicTypeStatsGroup;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.MagicStatsFactoryVisitor;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleCharmContent extends AbstractSubBoxContent {

  private IGenericCharacter character;
  private ReportSession session;
  private IResources resources;

  public SimpleCharmContent(IGenericCharacter character, ReportSession session, IResources resources) {
    super(resources);
    this.character = character;
    this.session = session;
    this.resources = resources;
    if (!session.knowsMnemonic(CharmsAndSorceryMnemonic.class)) {
      List<IMagicStats> printMagic = collectPrintMagic();
      Collections.sort(printMagic);
      session.storeMnemonic(new CharmsAndSorceryMnemonic(printMagic));
    }
  }

  public List<IMagicStats> getPrintMagic() {
    return getMnemonic().getRemainingPrintMagic();
  }

  @Override
  public String getHeaderKey() {
    return "Charms";
  }

  private List<IMagicStats> collectPrintMagic() {
    List<IMagicStats> printStats = new ArrayList<IMagicStats>();
    addGenericCharmsForPrint(printStats);
    addConcreteLearnedMagicForPrint(printStats);
    return printStats;
  }

  private void addGenericCharmsForPrint(List<IMagicStats> printStats) {
    for (IMagicStats stats : GenericCharmUtilities.getGenericCharmStats(character)) {
      if (GenericCharmUtilities.shouldShowCharm(stats, character)) {
        printStats.add(stats);
      }
    }
  }

  private void addConcreteLearnedMagicForPrint(List<IMagicStats> printStats) {
    IMagicVisitor statsCollector = new MagicStatsFactoryVisitor(character, printStats);
    for (IMagic magic : character.getAllLearnedMagic()) {
      magic.accept(statsCollector);
    }
  }

  private CharmsAndSorceryMnemonic getMnemonic() {
    return session.retrieveMnemonic(CharmsAndSorceryMnemonic.class);
  }

  public void markAsPrinted(IMagicStats stats) {
   getMnemonic().removePrintMagic(stats);
  }

  public IStatsGroup<IMagicStats>[] createStatsGroups() {
    return new IStatsGroup[]{new MagicNameStatsGroup(resources), new MagicCostStatsGroup(resources),
            new MagicTypeStatsGroup(resources), new MagicDurationStatsGroup(resources),
            new MagicDetailsStatsGroup(resources), new MagicSourceStatsGroup(resources)};
  }

  public boolean hasUnprintedCharms() {
    return !getPrintMagic().isEmpty();
  }

  public String getGroupName(IMagicStats stats) {
    return  stats.getGroupName(resources);
  }
}

