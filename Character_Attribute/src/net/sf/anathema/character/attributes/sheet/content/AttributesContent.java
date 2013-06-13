package net.sf.anathema.character.attributes.sheet.content;

import net.sf.anathema.character.attributes.model.AttributesModel;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.lib.resources.Resources;

import java.util.List;

public class AttributesContent extends AbstractSubBoxContent {

  private AttributesModel attributeModel;
  private IGenericCharacter character;

  public AttributesContent(IGenericCharacter character, Resources resources) {
    super(resources);
    this.character = character;
    this.attributeModel = new AttributesModel(character);
  }

  public int getTraitMax() {
    return attributeModel.getTraitMax();
  }

  @Override
  public String getHeaderKey() {
    return "Attributes";
  }

  public List<PrintAttributeGroup> getAttributeGroups() {
    PrintAttributeIterator iterator = new PrintAttributeIterator(getResources(), character);
    attributeModel.iterate(iterator);
    return iterator.groups;
  }
}
