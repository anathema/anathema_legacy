package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.magic.AbstractMagicContent;
import net.sf.anathema.character.reporting.pdf.content.magic.MagicContentHelper;
import net.sf.anathema.character.reporting.pdf.content.magic.MagicMnemonic;
import net.sf.anathema.lib.resources.Resources;

import java.util.Collections;
import java.util.List;

public class SpellsOnlyContent extends AbstractMagicContent {

  private ReportSession session;

  public SpellsOnlyContent(ReportSession session, Resources resources) {
    super(resources);
    this.session = session;
    storeMnemonicIfNecessary(session);
  }

  @Override
  protected MagicMnemonic createMnemonic() {
    List<IMagicStats> printMagic = MagicContentHelper.collectPrintSpells(session);
    Collections.sort(printMagic);
    return new SpellsOnlyMnemonic(printMagic);
  }

  @Override
  protected boolean knowsMnemonic(ReportSession session) {
    return session.knowsMnemonic(SpellsOnlyMnemonic.class);
  }

  @Override
  protected MagicMnemonic getMnemonic() {
    return session.retrieveMnemonic(SpellsOnlyMnemonic.class);
  }

  @Override
  public String getHeaderKey() {
    return "Charms";
  }
}

