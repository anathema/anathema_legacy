package net.sf.anathema.character.generic.caste;

public interface ICasteType<V extends ICasteTypeVisitor> extends ITypedDescriptionType {

  public static final ICasteType<ICasteTypeVisitor> NULL_CASTE_TYPE = new ICasteType<ICasteTypeVisitor>() {
    public String getId() {
      return null;
    }

    public void accept(ICasteTypeVisitor visitor) {
      // nothing to do
    }
  };

  public void accept(V visitor);
}