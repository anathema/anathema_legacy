package net.sf.anathema.character.generic.caste;

public interface ICasteType extends ITypedDescriptionType {

  ICasteType NULL_CASTE_TYPE = new ICasteType() {
    @Override
    public String getId() {
      return null;
    }
  };
}