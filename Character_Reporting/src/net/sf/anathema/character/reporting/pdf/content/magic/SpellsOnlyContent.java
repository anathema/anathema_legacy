package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.magic.ExtendedMagicEncoder;
import net.sf.anathema.lib.resources.IResources;

import java.util.Collections;
import java.util.List;

public class SpellsOnlyContent extends AbstractMagicContent {

  private IGenericCharacter character;
  private ReportSession session;

  public SpellsOnlyContent(IGenericCharacter character, ReportSession session, IResources resources) {
    super(resources);
    this.character = character;
    this.session = session;
    storeMnemonicIfNecessary(session);
  }

  protected MagicMnemonic createMnemonic() {
    List<IMagicStats> printMagic = ExtendedMagicEncoder.collectPrintSpells(session);
    Collections.sort(printMagic);
    return new SpellsOnlyMnemonic(printMagic);
  }

  protected boolean knowsMnemonic(ReportSession session) {
    return session.knowsMnemonic(SpellsOnlyMnemonic.class);
  }

  protected MagicMnemonic getMnemonic() {
    return session.retrieveMnemonic(SpellsOnlyMnemonic.class);
  }

  @Override
  public String getHeaderKey() {
    return "Charms";
  }
}

