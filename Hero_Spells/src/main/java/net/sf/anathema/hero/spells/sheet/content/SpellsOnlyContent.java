package net.sf.anathema.hero.spells.sheet.content;

import net.sf.anathema.character.main.magic.model.magic.IMagicStats;
import net.sf.anathema.hero.magic.sheet.content.AbstractMagicContent;
import net.sf.anathema.hero.magic.sheet.content.mnemonic.MagicMnemonic;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
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
    List<IMagicStats> printMagic = new ArrayList<>();
    new PrintSpellsProvider(session.getHero()).addPrintMagic(printMagic);
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

