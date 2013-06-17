package net.sf.anathema.character.presenter.magic.combo;

import net.sf.anathema.character.generic.framework.magic.view.AbstractMagicLearnProperties;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.model.charm.IComboConfiguration;
import net.sf.anathema.character.view.magic.IComboViewProperties;
import net.sf.anathema.charmtree.builder.stringbuilder.CharmInfoStringBuilder;
import net.sf.anathema.charmtree.builder.stringbuilder.ICharmInfoStringBuilder;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.list.LegalityCheck;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;

public class ComboViewProperties extends AbstractMagicLearnProperties implements IComboViewProperties {
  private final IComboConfiguration comboConfiguration;
  private final ICharmInfoStringBuilder charmInfoStringProvider;

  ComboViewProperties(Resources resources, IComboConfiguration comboConfiguration, MagicDescriptionProvider magicDescriptionProvider) {
    super(resources);
    this.charmInfoStringProvider = new CharmInfoStringBuilder(getResources(), magicDescriptionProvider);
    this.comboConfiguration = comboConfiguration;
  }

  @Override
  public RelativePath getFinalizeButtonIcon() {
    return new CharacterUI().getFinalizeIconPath();
  }

  @Override
  public String getAvailableComboCharmsLabel() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.AvailableLabel");
  }

  @Override
  public String getComboedCharmsLabel() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.SelectedLabel");
  }

  @Override
  public boolean isMagicSelectionAvailable(Object object) {
    return object != null && comboConfiguration.isComboLegal((ICharm) object);
  }

  @Override
  public ListSelectionListener getRemoveButtonEnabledListener(final JButton button, final JList list) {
    return new CharmRemoveEnabledListener(button, list);
  }

  @Override
  public AgnosticUIConfiguration<Identifier> getLearnedMagicRenderer() {
    return new CharmUIConfiguration(getResources(), charmInfoStringProvider);
  }

  @Override
  public AgnosticUIConfiguration getAvailableMagicRenderer() {
    return new CharmUIConfiguration(getResources(), charmInfoStringProvider);
  }

  @Override
  public LegalityCheck getLegalityCheck() {
    return new ComboLegality(comboConfiguration);
  }

  @Override
  public RelativePath getClearButtonIcon() {
    return new BasicUi().getClearIconPath();
  }

  @Override
  public String getFinalizeButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.FinalizeToolTip");
  }

  @Override
  public String getClearButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.ClearToolTip");
  }

  @Override
  public String getAddButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.AddToolTip");
  }

  @Override
  public String getRemoveButtonToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.RemoveToolTip");
  }

  @Override
  public RelativePath getCancelEditButtonIcon() {
    return new CharacterUI().getCancelComboEditIconPath();
  }

  @Override
  public String getFinalizeButtonEditToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.FinalizeEditToolTip");
  }

  @Override
  public String getCancelButtonEditToolTip() {
    return getResources().getString("CardView.CharmConfiguration.ComboCreation.ClearEditToolTip");
  }
}