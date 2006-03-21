package net.sf.anathema.character.sidereal.reporting;

import java.io.InputStream;

import net.sf.anathema.character.generic.framework.reporting.template.voidstate.DefaultVoidstateSubreports;
import net.sf.anathema.character.generic.type.CharacterType;

public class SiderealVoidStateSubreports extends DefaultVoidstateSubreports {

  public SiderealVoidStateSubreports() {
    super(CharacterType.SIDEREAL);
  }

  @Override
  public InputStream loadDescriptionSubreport() {
    String descriptionSubreportResourceLocation = FILE_BASE + "VoidstateSiderealDescriptionSubreport.jasper"; //$NON-NLS-1$
    return loadSystemResource(descriptionSubreportResourceLocation);
  }
}