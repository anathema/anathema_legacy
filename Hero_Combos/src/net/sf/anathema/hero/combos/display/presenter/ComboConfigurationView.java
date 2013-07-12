package net.sf.anathema.hero.combos.display.presenter;

import net.sf.anathema.hero.magic.display.MagicLearnProperties;
import net.sf.anathema.hero.magic.display.MagicLearnView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface ComboConfigurationView {

  void initGui(ComboViewProperties properties);

  MagicLearnView addMagicLearnView(MagicLearnProperties viewProperties);

  ComboContainer addComboContainer();

  void addComboViewListener(ComboViewListener listener);

  ITextView addComboNameView(String viewTitle);

  ITextView addComboDescriptionView(String viewTitle);
}