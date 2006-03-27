package net.sf.anathema.character.lunar.reporting;

import java.io.InputStream;

import net.sf.anathema.character.generic.framework.reporting.template.voidstate.DefaultVoidstateSubreports;
import net.sf.anathema.character.generic.type.CharacterType;

public class LunarVoidstateSubreports extends DefaultVoidstateSubreports {

  public LunarVoidstateSubreports() {
    super(CharacterType.LUNAR);
  }

  @Override
  public InputStream loadHealthSubreport() {
    String descriptionSubreportResourceLocation = FILE_BASE + "VoidstateLunarHealthSubreport.jasper"; //$NON-NLS-1$
    return loadSystemResource(descriptionSubreportResourceLocation);
  }

  @Override
  public InputStream loadDescriptionSubreport() {
    String descriptionSubreportResourceLocation = FILE_BASE + "VoidstateLunarDescriptionSubreport.jasper"; //$NON-NLS-1$;
    return loadSystemResource(descriptionSubreportResourceLocation);
  }

  @Override
  public InputStream loadSpecialpageSubreport() {
    String beastmanSubreportResourceLocation = FILE_BASE + "VoidstateBeastformPage.jasper"; //$NON-NLS-1$;
    return loadSystemResource(beastmanSubreportResourceLocation);
  }

  @Override
  public InputStream loadAttributeSubreport() {
    String abilitySubreportResourceLocation = FILE_BASE + "VoidstateLunarAttributeSubreport.jasper"; //$NON-NLS-1$
    return loadSystemResource(abilitySubreportResourceLocation);
  }

  @Override
  public InputStream loadAbilitySetSubreport() {
    String abilitySubreportResourceLocation = FILE_BASE + "VoidstateThreeGroupAbilitySetSubreport.jasper"; //$NON-NLS-1$
    return loadSystemResource(abilitySubreportResourceLocation);
  }

  public InputStream loadLunarAttributeSubreport() {
    String subreportResourceLocation = FILE_BASE + "VoidstateBeastformAttributeSubreport.jasper"; //$NON-NLS-1$;
    return loadSystemResource(subreportResourceLocation);
  }

  public InputStream loadLunarMiddleColumnSubreport() {
    String subreportResourceLocation = FILE_BASE + "BeastformMiddleColumnSubreport.jasper"; //$NON-NLS-1$;
    return loadSystemResource(subreportResourceLocation);
  }

  public InputStream loadLunarWillpowerSubreport() {
    String subreportResourceLocation = FILE_BASE + "VoidstateBeastformWillpowerSubreport.jasper"; //$NON-NLS-1$;
    return loadSystemResource(subreportResourceLocation);
  }

  public InputStream loadLunarGiftsSubreport() {
    String subreportResourceLocation = FILE_BASE + "VoidstateBeastformGiftsSubreport.jasper"; //$NON-NLS-1$;
    return loadSystemResource(subreportResourceLocation);
  }
}