package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.character.main.magic.sheet.content.IMagicStats;
import net.sf.anathema.hero.magic.sheet.content.AbstractMagicContent;
import net.sf.anathema.hero.magic.sheet.content.mnemonic.MagicMnemonic;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
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
    List<IMagicStats> printMagic = CharmContentHelper.collectPrintCharms(session);
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

