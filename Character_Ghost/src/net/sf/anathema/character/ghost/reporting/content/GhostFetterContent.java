package net.sf.anathema.character.ghost.reporting.content;

import com.google.common.collect.Lists;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.ghost.fetters.GhostFettersTemplate;
import net.sf.anathema.character.ghost.fetters.model.Fetter;
import net.sf.anathema.character.ghost.fetters.model.IGhostFettersModel;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.general.NamedValue;
import net.sf.anathema.lib.resources.IResources;

import java.util.Arrays;
import java.util.List;

import static com.google.common.collect.Lists.transform;

public class GhostFetterContent extends AbstractSubBoxContent {
  private IGenericCharacter character;

  public GhostFetterContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "Ghost.Fetters"; //$NON-NLS-1$
  }

  public List<NamedValue> getFetters() {
    List<Fetter> fetters = Arrays.asList(getFettersModel().getFetters());
    return transform(fetters, new ToPrintFetter());
  }

  private IGhostFettersModel getFettersModel() {
    return (IGhostFettersModel) character.getAdditionalModel(GhostFettersTemplate.ID);
  }

  public int getTraitMaximum() {
    return 5;
  }

}
