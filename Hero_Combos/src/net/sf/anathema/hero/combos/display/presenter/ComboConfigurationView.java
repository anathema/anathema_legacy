package net.sf.anathema.hero.combos.display.presenter;

import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ComboConfigurationView {

  void initGui(ComboViewProperties properties);

  void setAllCharms(Object[] charms);

  void addComboViewListener(ComboViewListener listener);

  void setComboCharms(Object[] charms);

  ITextView addComboNameView(String viewTitle);

  ITextView addComboDescriptionView(String viewTitle);

  void setEditState(boolean editing);

  ComboContainer addComboContainer();
}