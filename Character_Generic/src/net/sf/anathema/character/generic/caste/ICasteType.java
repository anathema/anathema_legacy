package net.sf.anathema.character.generic.caste;

public interface ICasteType extends ITypedDescriptionType {

	public static final String noFirst = "NOFIRST";
	
  public static final ICasteType NULL_CASTE_TYPE = new ICasteType() {
    public String getId() {
      return null;
    }
  };
}