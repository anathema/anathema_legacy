package net.sf.anathema.hero.combos.display.presenter;

import net.sf.anathema.hero.magic.display.MagicLearnProperties;
import net.sf.anathema.hero.magic.display.MagicLearnView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ComboConfigurationView {

  void addComboEditor(ComboViewProperties properties);

  MagicLearnView addMagicLearnView(MagicLearnProperties viewProperties);

  ComboContainer addComboContainer();

  ITextView addComboNameView(String viewTitle);

  ITextView addComboDescriptionView(String viewTitle);
}