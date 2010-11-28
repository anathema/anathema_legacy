package net.sf.anathema.lib.io.file;

import net.sf.anathema.lib.resources.IResources;

public final class XmlFileFilter extends AbstractFileTypeFilter {

	private static final String XML = "xml"; //$NON-NLS-1$
	private IResources resources;

	public XmlFileFilter(IResources resources) {
		this.resources = resources;
	}

	@Override
	protected boolean acceptExtension(String extension) {
		return XML.equalsIgnoreCase(extension);
	}

	@Override
	public String getDescription() {
		return resources.getString("Filetype.xml"); //$NON-NLS-1$
	}
}
