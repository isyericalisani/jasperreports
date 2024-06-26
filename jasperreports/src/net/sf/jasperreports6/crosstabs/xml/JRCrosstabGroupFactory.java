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
package net.sf.jasperreports6.crosstabs.xml;

import org.xml.sax.Attributes;

import net.sf.jasperreports6.crosstabs.design.JRDesignCrosstabGroup;
import net.sf.jasperreports6.crosstabs.type.CrosstabTotalPositionEnum;
import net.sf.jasperreports6.engine.xml.JRBaseFactory;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 */
public abstract class JRCrosstabGroupFactory extends JRBaseFactory
{
	public static final String ELEMENT_columnGroup = "columnGroup";
	public static final String ELEMENT_crosstabColumnHeader = "crosstabColumnHeader";
	public static final String ELEMENT_crosstabTotalColumnHeader = "crosstabTotalColumnHeader";

	public static final String ATTRIBUTE_name = "name";
	public static final String ATTRIBUTE_totalPosition = "totalPosition";
	public static final String ATTRIBUTE_mergeHeaderCells = "mergeHeaderCells";
	
	protected final void setGroupAtts(Attributes atts, JRDesignCrosstabGroup group)
	{
		group.setName(atts.getValue(ATTRIBUTE_name));
		
		CrosstabTotalPositionEnum totalPosition = CrosstabTotalPositionEnum.getByName(atts.getValue(ATTRIBUTE_totalPosition));
		if (totalPosition != null)
		{
			group.setTotalPosition(totalPosition);
		}
		
		String mergeAttr = atts.getValue(ATTRIBUTE_mergeHeaderCells);
		if (mergeAttr != null)
		{
			group.setMergeHeaderCells(Boolean.valueOf(mergeAttr));//FIXME handle 1 as per the xsd boolean type
		}
	}
}
