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
package net.sf.jasperreports6.engine.xml;

import org.xml.sax.Attributes;

import net.sf.jasperreports6.engine.design.JRDesignSortField;
import net.sf.jasperreports6.engine.type.SortFieldTypeEnum;
import net.sf.jasperreports6.engine.type.SortOrderEnum;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class JRSortFieldFactory extends JRBaseFactory
{

	@Override
	public Object createObject(Attributes atts)
	{
		JRDesignSortField sortField = new JRDesignSortField();
		
		sortField.setName(atts.getValue(JRXmlConstants.ATTRIBUTE_name));

		SortOrderEnum order = SortOrderEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_order));
		if (order != null)
		{
			sortField.setOrder(order);
		}

		SortFieldTypeEnum type = SortFieldTypeEnum.getByName(atts.getValue(JRXmlConstants.ATTRIBUTE_type));
		if (type != null)
		{
			sortField.setType(type);
		}

		return sortField;
	}
	

}
