package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.generic.magic.IMagicVisitor;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.MagicStatsFactoryVisitor;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllMagicContent extends AbstractMagicContent {

  private IGenericCharacter character;
  private ReportSession session;

  public AllMagicContent(IGenericCharacter character, ReportSession session, IResources resources) {
    super(resources);
    this.character = character;
    this.session = session;
    storeMnemonicIfNecessary(session);
  }

  protected MagicMnemonic createMnemonic() {
    List<IMagicStats> printMagic = collectPrintMagic();
    Collections.sort(printMagic);
    return new CharmsAndSorceryMnemonic(printMagic);
  }

  protected boolean knowsMnemonic(ReportSession session) {
    return session.knowsMnemonic(CharmsAndSorceryMnemonic.class);
  }

  protected MagicMnemonic getMnemonic() {
    return session.retrieveMnemonic(CharmsAndSorceryMnemonic.class);
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
}

