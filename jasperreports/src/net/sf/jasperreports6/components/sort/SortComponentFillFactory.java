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
package net.sf.jasperreports6.components.sort;

import net.sf.jasperreports6.engine.component.Component;
import net.sf.jasperreports6.engine.component.ComponentFillFactory;
import net.sf.jasperreports6.engine.component.FillComponent;
import net.sf.jasperreports6.engine.fill.JRFillCloneFactory;
import net.sf.jasperreports6.engine.fill.JRFillObjectFactory;

/**
 * @author Narcis Marcu (narcism@users.sourceforge.net)
 */
public class SortComponentFillFactory implements ComponentFillFactory
{

	@Override
	public FillComponent toFillComponent(Component component,
			JRFillObjectFactory factory)
	{
		SortComponent sortComponent = (SortComponent) component;
		return new SortComponentFill(sortComponent);
	}

	@Override
	public FillComponent cloneFillComponent(FillComponent component,
			JRFillCloneFactory factory)
	{
		SortComponentFill sortComponentFill = (SortComponentFill) component;
		return new SortComponentFill(sortComponentFill.getSortComponent());
	}

}
