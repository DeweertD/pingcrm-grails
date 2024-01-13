/*
 * Copyright 2022-2023 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pingcrm

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import dev.nicklasw.squiggly.Squiggly
import dev.nicklasw.squiggly.context.provider.ThreadLocalContextProvider
import groovy.transform.CompileStatic
import io.micronaut.context.annotation.Factory
import io.micronaut.core.annotation.Nullable
import jakarta.inject.Named
import jakarta.inject.Singleton

/**
 * A factory bean that initializes the Squiggly library.
 *
 * @author Mattias Reichel
 * @since 1.0.0
 */
@Factory
@CompileStatic
class SquigglyObjectMapperFactory {

    private final ObjectMapper objectMapper

    SquigglyObjectMapperFactory(@Nullable JsonFactory jsonFactory) {
        this.objectMapper = new ObjectMapper(jsonFactory)
    }

    /**
     * This is a Jackson ObjectMapper that in cooperation with Squiggly (https://github.com/NicklasWallgren/squiggly)
     * will help us select which properties to send to the client side.
     */
    @Singleton
    @Named('publicDataMapper')
    ObjectMapper getObjectMapper() throws Exception {
        Squiggly.init(objectMapper, new ThreadLocalContextProvider())
    }
}
