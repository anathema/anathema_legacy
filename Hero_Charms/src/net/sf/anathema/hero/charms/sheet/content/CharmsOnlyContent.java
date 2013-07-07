package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.character.main.magic.IMagicStats;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.hero.charms.sheet.content.mnemonic.CharmsOnlyMnemonic;
import net.sf.anathema.character.reporting.pdf.content.magic.MagicContentHelper;
import net.sf.anathema.hero.charms.sheet.content.mnemonic.MagicMnemonic;
import net.sf.anathema.lib.resources.Resources;

import java.util.Collections;
import java.util.List;

public class CharmsOnlyContent extends AbstractMagicContent {

  private ReportSession session;

  public CharmsOnlyContent(ReportSession session, Resources resources) {
    super(resources);
    this.session = session;
    storeMnemonicIfNecessary(session);
  }

  @Override
  protected MagicMnemonic createMnemonic() {
    List<IMagicStats> printMagic = MagicContentHelper.collectPrintCharms(session);
    Collections.sort(printMagic);
    return new CharmsOnlyMnemonic(printMagic);
  }

  @Override
  protected boolean knowsMnemonic(ReportSession session) {
    return session.knowsMnemonic(CharmsOnlyMnemonic.class);
  }

  @Override
  protected MagicMnemonic getMnemonic() {
    return session.retrieveMnemonic(CharmsOnlyMnemonic.class);
  }

  @Override
  public String getHeaderKey() {
    return "Charms";
  }
}

