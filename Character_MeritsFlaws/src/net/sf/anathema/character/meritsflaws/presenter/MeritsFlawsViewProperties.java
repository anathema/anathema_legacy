package net.sf.anathema.character.meritsflaws.presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ListCellRenderer;

import net.sf.anathema.character.meritsflaws.model.perk.PerkCategory;
import net.sf.anathema.character.meritsflaws.model.perk.PerkType;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.framework.view.IdentificateSelectCellRenderer;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class MeritsFlawsViewProperties implements IMeritsFlawsViewProperties {

  private final IResources resources;
  private final IMeritsFlawsModel meritsFlawsModel;

  public MeritsFlawsViewProperties(IMeritsFlawsModel meritsFlawsModel, IResources resources) {
    this.meritsFlawsModel = meritsFlawsModel;
    this.resources = resources;
  }

  public String getSelectedString() {
    return resources.getString("Perks.Selected.Title"); //$NON-NLS-1$
  }

  public String getDetailsString() {
    return resources.getString("Perks.Details.Title"); //$NON-NLS-1$
  }

  public String getSelectionString() {
    return resources.getString("Perks.Available.Title"); //$NON-NLS-1$
  }

  public Icon getAddIcon() {
    return new BasicUi(resources).getAddIcon();
  }

  public Icon getRemoveIcon() {
    return new BasicUi(resources).getRemoveIcon();
  }

  public String getTypeString() {
    return resources.getString("Perks.Available.Filter.Type"); //$NON-NLS-1$
  }

  public String getCategoryString() {
    return resources.getString("Perks.Available.Filter.Category"); //$NON-NLS-1$
  }

  public IIdentificate[] getCategoryFilters() {
    java.util.List<IIdentificate> categoryList = new ArrayList<IIdentificate>();
    categoryList.add(null);
    Collections.addAll(categoryList, PerkCategory.values());
    return categoryList.toArray(new IIdentificate[categoryList.size()]);
  }

  public IIdentificate[] getTypeFilters() {
    List<IIdentificate> categoryList = new ArrayList<IIdentificate>();
    categoryList.add(null);
    Collections.addAll(categoryList, PerkType.values());
    return categoryList.toArray(new IIdentificate[categoryList.size()]);
  }

  public ListCellRenderer getTypeFilterListRenderer() {
    return new IdentificateSelectCellRenderer("Perks.Available.Filter.Type.", resources) { //$NON-NLS-1$
      @Override
      protected Object getCustomizedDisplayValue(Object value) {
        return super.getCustomizedDisplayValue(value);
      }

      @Override
      protected Object getNullValue() {
        return super.getCustomizedDisplayValue(new Identificate("All")); //$NON-NLS-1$
      }
    };
  }

  public ListCellRenderer getCategoryFilterListRenderer() {
    return new IdentificateSelectCellRenderer("Perks.Available.Filter.Category.", resources) { //$NON-NLS-1$
      @Override
      protected Object getCustomizedDisplayValue(Object value) {
        return super.getCustomizedDisplayValue(value);
      }

      @Override
      protected Object getNullValue() {
        return super.getCustomizedDisplayValue(new Identificate("All")); //$NON-NLS-1$
      }
    };
  }

  public ListCellRenderer getAvailableListRenderer() {
    return new MeritsFlawsAvailableCellRenderer(meritsFlawsModel, resources);
  }

  public ListCellRenderer getSelectionListRenderer() {
    return new MeritsFlawsSelectedCellRenderer(resources, meritsFlawsModel);
  }
}