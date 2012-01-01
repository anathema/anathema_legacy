package net.sf.anathema.character.ghost.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.ghost.passions.GhostPassionsTemplate;
import net.sf.anathema.character.ghost.passions.model.IGhostPassionsModel;
import net.sf.anathema.character.library.trait.subtrait.ISubTrait;
import net.sf.anathema.character.library.trait.subtrait.ISubTraitContainer;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.reporting.pdf.content.general.NamedValue;
import net.sf.anathema.lib.resources.IResources;

import java.util.ArrayList;
import java.util.List;

public class GhostPassionContent extends AbstractSubBoxContent {

  private IGenericCharacter character;

  public GhostPassionContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "Ghost.Passions"; //$NON-NLS-1$
  }

  public List<NamedValue> getPrintPassions() {
    IGhostPassionsModel model = (IGhostPassionsModel) character.getAdditionalModel(GhostPassionsTemplate.ID);
    List<NamedValue> printPassions = new ArrayList<NamedValue>();
    for (final VirtueType virtue : VirtueType.values()) {
      ISubTraitContainer container = model.getPassionContainer(virtue);
      for (final ISubTrait passion : container.getSubTraits()) {
        printPassions.add(new PrintPassion(getResources(), virtue, passion));
      }
    }
    return printPassions;
  }

  public int getTraitMaximum() {
    return 5;
  }
}
