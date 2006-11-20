package net.sf.anathema.character.lunar.reporting.face;

import net.sf.anathema.character.reporting.sheet.util.statstable.AbstractNameStatsGroup;
import net.sf.anathema.lib.resources.IResources;

public class FaceNameStatsGroup extends AbstractNameStatsGroup<IFaceStats> {

  public FaceNameStatsGroup(IResources resources) {
    super(resources);
  }

  @Override
  protected String getHeaderResourceKey() {
    return "Sheet.Lunar.Face.Name";
  }

  @Override
  protected String getResourceBase() {
    return "Lunar.Renown."; //$NON-NLS-1$
  }
}