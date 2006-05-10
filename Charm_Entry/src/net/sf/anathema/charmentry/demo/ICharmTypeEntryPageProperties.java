package net.sf.anathema.charmentry.demo;

import javax.swing.ListCellRenderer;

import net.disy.commons.core.message.IBasicMessage;

public interface ICharmTypeEntryPageProperties {

  public String getPageHeader();

  public String getCharmTypeMessage();

  public String getTypeLabel();

  public String getSpecialModelLabel();

  public String getReflexiveStepLabel();

  public String getDefaultStepLabel();

  public String getDefensiveStepLabel();

  public String getSplitStepLabel();

  public String getDefenseLabel();

  public String getModifiersLabel();

  public String getSpeedLabel();

  public ListCellRenderer getDefaultIdentificateRenderer();

  public String getReflexiveSpecialsTitle();

  public String getSimpleSpecialsTitle();

  public IBasicMessage getSimpleSpecialsMessage();

  public IBasicMessage getReflexiveSpecialsMessage();

  public String getTurnTypeLabel();

}