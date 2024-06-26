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

import net.sf.jasperreports6.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports6.engine.xml.JRBaseFactory;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 */
public class JRCrosstabCellFactory extends JRBaseFactory
{
	public static final String ELEMENT_crosstabCell = "crosstabCell";

	public static final String ATTRIBUTE_rowTotalGroup = "rowTotalGroup";
	public static final String ATTRIBUTE_columnTotalGroup = "columnTotalGroup";
	public static final String ATTRIBUTE_width = "width";
	public static final String ATTRIBUTE_height = "height";
	
	@Override
	public Object createObject(Attributes attributes)
	{
		JRDesignCrosstabCell cell = new JRDesignCrosstabCell();
		
		cell.setRowTotalGroup(attributes.getValue(ATTRIBUTE_rowTotalGroup));
		cell.setColumnTotalGroup(attributes.getValue(ATTRIBUTE_columnTotalGroup));
		
		String widthAttr = attributes.getValue(ATTRIBUTE_width);
		if (widthAttr != null)
		{
			cell.setWidth(Integer.valueOf(widthAttr));
		}
		
		String heightAttr = attributes.getValue(ATTRIBUTE_height);
		if (heightAttr != null)
		{
			cell.setHeight(Integer.valueOf(heightAttr));
		}

		return cell;
	}
}
