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
package net.sf.jasperreports6.crosstabs;

import net.sf.jasperreports6.engine.JRDatasetParameter;
import net.sf.jasperreports6.engine.JRParameter;

/**
 * Crosstab parameters interface.
 * <p>
 * Crosstab parameters are used to use external values inside a crosstab.
 * <p>
 * A parameter consists of a name and a value expression.  The values can also
 * be set by using a {@link java.util.Map Map} object with name/value mappings.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @see net.sf.jasperreports6.crosstabs.JRCrosstab#getParameters()
 * @see net.sf.jasperreports6.crosstabs.JRCrosstab#getParametersMapExpression()
 */
public interface JRCrosstabParameter extends JRParameter, JRDatasetParameter
{
}
