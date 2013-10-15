package net.sf.anathema.charmdatabase.view.rules.fx;

import net.sf.anathema.character.main.magic.charm.prerequisite.CharmLearnPrerequisite;
import net.sf.anathema.charmdatabase.view.fx.AbstractFxListPanel;
import net.sf.anathema.charmdatabase.view.fx.IconlessCellRenderer;
import net.sf.anathema.charmdatabase.view.rules.CharmLearnPrerequisiteStringBuilder;
import net.sf.anathema.charmdatabase.view.rules.CharmPrerequisitesPanel;

public class FxCharmPrerequisitesPanel extends AbstractFxListPanel<CharmLearnPrerequisite> implements CharmPrerequisitesPanel {

  public FxCharmPrerequisitesPanel(final CharmLearnPrerequisiteStringBuilder labeler) {
    super(new IconlessCellRenderer<CharmLearnPrerequisite>() {
      @Override
      public String getLabel(CharmLearnPrerequisite prerequisite) {
        return prerequisite != null ? labeler.getStringForPrerequisite(prerequisite) : null;
      }
    });
  }

  @Override
  public void setPrerequisites(CharmLearnPrerequisite[] charms) {
    setObjects(charms);
  }
}