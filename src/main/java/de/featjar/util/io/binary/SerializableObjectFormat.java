/*
 * Copyright (C) 2022 Sebastian Krieter, Elias Kuiter
 *
 * This file is part of util.
 *
 * util is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License,
 * or (at your option) any later version.
 *
 * util is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with util. If not, see <https://www.gnu.org/licenses/>.
 *
 * See <https://github.com/FeatureIDE/FeatJAR-util> for further information.
 */
package de.featjar.util.io.binary;

import de.featjar.util.Feat;
import de.featjar.util.data.Result;
import de.featjar.util.io.InputMapper;
import de.featjar.util.io.OutputMapper;
import de.featjar.util.io.format.Format;
import de.featjar.util.log.Log;

import java.io.*;
import java.util.Optional;

/**
 * Parses and writes serializable objects.
 *
 * @param <T> the type of the object
 * @author Sebastian Krieter
 */
public class SerializableObjectFormat<T extends Serializable> implements Format<T> {
    @Override
    public String getName() {
        return "Serializable Object";
    }

    @Override
    public Optional<String> getFileExtension() {
        return Optional.empty();
    }

    @Override
    public boolean supportsParse() {
        return true;
    }

    @Override
    public boolean supportsSerialize() {
        return true;
    }

    @Override
    public Result<T> parse(InputMapper inputMapper) {
        try (ObjectInputStream in = new ObjectInputStream(inputMapper.get().getInputStream())) {
            @SuppressWarnings("unchecked")
            final T readObject = (T) in.readObject();
            return Result.of(readObject);
        } catch (final Exception e) {
            Feat.log().error(e);
            return Result.empty(e);
        }
    }

    @Override
    public void write(T object, OutputMapper outputMapper) throws IOException {
        final OutputStream outputStream = outputMapper.get().getOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(object);
            oos.flush();
        } catch (final Exception e) {
            Feat.log().error(e);
        }
    }
}
