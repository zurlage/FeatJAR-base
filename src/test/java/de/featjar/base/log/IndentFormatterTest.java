/*
 * Copyright (C) 2025 FeatJAR-Development-Team
 *
 * This file is part of FeatJAR-FeatJAR-base.
 *
 * FeatJAR-base is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License,
 * or (at your option) any later version.
 *
 * FeatJAR-base is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FeatJAR-base. If not, see <https://www.gnu.org/licenses/>.
 *
 * See <https://github.com/FeatureIDE/FeatJAR-base> for further information.
 */
package de.featjar.base.log;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IndentFormatterTest {
    @Test
    void getPrefix() {
        IndentFormatter indentFormatter = new IndentFormatter();
        assertEquals("", indentFormatter.getPrefix(null, null));
        indentFormatter.addIndent();
        assertEquals("\t", indentFormatter.getPrefix(null, null));
        indentFormatter.addIndent();
        assertEquals("\t\t", indentFormatter.getPrefix(null, null));
        indentFormatter.removeIndent();
        indentFormatter.removeIndent();
        assertEquals("", indentFormatter.getPrefix(null, null));
        indentFormatter.removeIndent();
        assertEquals("", indentFormatter.getPrefix(null, null));
        indentFormatter.setLevel(2);
        assertEquals("\t\t", indentFormatter.getPrefix(null, null));
        indentFormatter.setLevel(0);
        assertEquals("", indentFormatter.getPrefix(null, null));
        indentFormatter.setLevel(-1);
        assertEquals("", indentFormatter.getPrefix(null, null));
        indentFormatter.setSymbol("  ");
        indentFormatter.setLevel(2);
        assertEquals("    ", indentFormatter.getPrefix(null, null));
        indentFormatter.setLevel(0);
        assertEquals("", indentFormatter.getPrefix(null, null));
    }
}
