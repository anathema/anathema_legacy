package net.sf.anathema.character.generic.caste;

public interface CasteType extends ITypedDescriptionType {

  CasteType NULL_CASTE_TYPE = new CasteType() {
    @Override
    public String getId() {
      return null;
    }
  };
}