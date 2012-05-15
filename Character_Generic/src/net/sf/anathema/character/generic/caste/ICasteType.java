package net.sf.anathema.character.generic.caste;

public interface ICasteType extends ITypedDescriptionType {

	String noFirst = "NOFIRST";
	
  ICasteType NULL_CASTE_TYPE = new ICasteType() {
    @Override
    public String getId() {
      return null;
    }
  };
}