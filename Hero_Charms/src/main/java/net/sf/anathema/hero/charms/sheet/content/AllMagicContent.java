package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.hero.charms.model.CharmsModelFetcher;
import net.sf.anathema.hero.charms.sheet.content.mnemonic.AllMagicMnemonic;
import net.sf.anathema.hero.charms.sheet.content.mnemonic.MagicMnemonic;
import net.sf.anathema.hero.sheet.pdf.session.PageBreakChecker;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllMagicContent extends AbstractMagicContent {

  private ReportSession session;

  public AllMagicContent(ReportSession session, Resources resources) {
    super(resources);
    this.session = session;
    storeMnemonicIfNecessary(session);
    session.setPageBreakChecker(new AllMagicPageBreakChecker());
  }

  @Override
  protected MagicMnemonic createMnemonic() {
    List<IMagicStats> printMagic = collectPrintMagic();
    Collections.sort(printMagic);
    return new AllMagicMnemonic(printMagic);
  }

  @Override
  protected boolean knowsMnemonic(ReportSession session) {
    return session.knowsMnemonic(AllMagicMnemonic.class);
  }

  @Override
  protected MagicMnemonic getMnemonic() {
    return session.retrieveMnemonic(AllMagicMnemonic.class);
  }

  @Override
  public String getHeaderKey() {
    return "Charms";
  }

  private List<IMagicStats> collectPrintMagic() {
    List<IMagicStats> printStats = new ArrayList<>();
    CharmsModelFetcher.fetch(session.getHero()).addPrintMagic(printStats);
    return printStats;
  }

  private class AllMagicPageBreakChecker implements PageBreakChecker {
    @Override
    public boolean isRequired() {
      return hasUnprintedCharms();
    }
  }
}

