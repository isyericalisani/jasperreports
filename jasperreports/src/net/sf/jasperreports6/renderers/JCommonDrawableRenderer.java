/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2023 Cloud Software Group, Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Contributors:
 * Adrian Jackson - iapetus@users.sourceforge.net
 * David Taylor - exodussystems@users.sourceforge.net
 * Lars Kristensen - llk@users.sourceforge.net
 */
package net.sf.jasperreports6.renderers;

import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;

import org.jfree.ui.Drawable;

import net.sf.jasperreports6.engine.JRConstants;
import net.sf.jasperreports6.engine.JasperReportsContext;


/**
 * A wrapper for the Drawable interface in the JCommon library: you will need the
 * JCommon classes in your classpath to compile this class. In particular this can be
 * used to allow JFreeChart objects to be included in the output report in vector form.
 *
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @deprecated Replaced by {@link JCommonDrawableRendererImpl}.
 */
public class JCommonDrawableRenderer extends net.sf.jasperreports6.engine.JRAbstractSvgRenderer implements Graphics2DRenderable
{

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/**
	 *
	 */	
	private Drawable drawable;


	/**
	 *
	 */	
	public JCommonDrawableRenderer(Drawable drawable) 
	{
		this.drawable = drawable;
	}

	@Override
	public Dimension2D getDimension(JasperReportsContext jasperReportsContext) 
	{
		return null;
	}

	@Override
	public void render(JasperReportsContext jasperReportsContext, Graphics2D grx, Rectangle2D rectangle) 
	{
		if (drawable != null) 
		{
			drawable.draw(grx, rectangle);
		}
	}
	
}
