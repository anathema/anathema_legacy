package net.sf.anathema.character.meritsflaws.presenter;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

import net.sf.anathema.lib.util.IIdentificate;

public interface IMeritsFlawsViewProperties {

  public String getSelectedString();

  public String getDetailsString();

  public String getSelectionString();

  public Icon getAddIcon();

  public Icon getRemoveIcon();

  public String getTypeString();

  public String getCategoryString();

  public IIdentificate[] getCategoryFilters();

  public IIdentificate[] getTypeFilters();

  public ListCellRenderer getTypeFilterListRenderer();

  public ListCellRenderer getSelectionListRenderer();

  public ListCellRenderer getCategoryFilterListRenderer();

  public ListCellRenderer getAvailableListRenderer();
}