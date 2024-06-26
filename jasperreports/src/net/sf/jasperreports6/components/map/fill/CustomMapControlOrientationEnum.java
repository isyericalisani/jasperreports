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
package net.sf.jasperreports6.components.map.fill;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports6.engine.type.EnumUtil;
import net.sf.jasperreports6.engine.type.NamedEnum;

/**
 * @author Narcis Marcu (narcism@users.sourceforge.net)
 */
public enum CustomMapControlOrientationEnum implements NamedEnum {

    HORIZONTAL("horizontal"),

    VERTICAL("vertical");

    private final transient String name;

    private CustomMapControlOrientationEnum(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    static CustomMapControlOrientationEnum getByName(String name)
    {
        return EnumUtil.getEnumByName(values(), name);
    }

    static String getAllNames() {
        CustomMapControlOrientationEnum[] positionEnums = values();
        List<String> names = new ArrayList<>();
        for (CustomMapControlOrientationEnum positionEnum: positionEnums) {
            names.add(positionEnum.getName());
        }
        return String.join(", ", names);
    }
}
