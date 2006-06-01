package net.sf.anathema.character.reporting.sheet.common.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.IGenericTrait;
import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractStatsTableEncoder;
import net.sf.anathema.character.reporting.sheet.util.statstable.IStatsGroup;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class PdfMagicTableEncoder extends AbstractStatsTableEncoder<IMagicStats> {

  private final IResources resources;

  public PdfMagicTableEncoder(IResources resources, BaseFont baseFont) {
    super(baseFont);
    this.resources = resources;
  }

  @Override
  protected IStatsGroup<IMagicStats>[] createStatsGroups(IGenericCharacter character) {
    return null;
  }

  @Override
  protected IMagicStats[] getPrintStats(IGenericCharacter character) {
    return null;
  }

  @Override
  protected IGenericTrait getTrait(IGenericCharacter character, IMagicStats equipment) {
    return null;
  }

  @Override
  protected boolean isLineValid(int line, Bounds bounds) {
    return false;
  }
}