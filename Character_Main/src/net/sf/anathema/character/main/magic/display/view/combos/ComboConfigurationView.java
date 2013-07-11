package net.sf.anathema.character.main.magic.display.view.combos;

import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ComboConfigurationView {

  void initGui(ComboViewProperties properties);

  void setAllCharms(Object[] charms);

  void addComboViewListener(ComboViewListener listener);

  void setComboCharms(Object[] charms);

  ITextView addComboNameView(String viewTitle);

  ITextView addComboDescriptionView(String viewTitle);

  ComboView addComboView(String name, String description);

  void deleteView(ComboView view);

  void setEditState(boolean editing);
}