/*
 * Copyright 2017 DevJake
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.pseudo.io

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import me.pseudo.Main
import me.pseudo.entities.ParsedPseudoReader
import me.pseudo.entities.PseudoReader
import me.pseudo.entities.Reader
import java.io.File


object StorageHandle {
    val sdir = File(File(Main::class.java.protectionDomain.codeSource.location.toURI()).parentFile, "Resources")
    private val pseudo = PseudoReader()
    private val parsedPseudo = ParsedPseudoReader()
    val pseudoExtension = ".pseudo"
    val parsedPseudoExtension = ".ppseudo"
    val mapper =
            ObjectMapper(YAMLFactory()
                    .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
                    .enable(YAMLGenerator.Feature.LITERAL_BLOCK_STYLE)
                    .enable(YAMLGenerator.Feature.SPLIT_LINES)
                    .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER))
                    .setSerializationInclusion(JsonInclude.Include.NON_ABSENT)

    fun getByType(type: ReaderType): Reader = when (type) {
        ReaderType.PSEUDO -> pseudo
        ReaderType.PARSED_PSEUDO -> parsedPseudo
    }

    //TODO add read/write/append for .pseudo and .ppseudo
}

enum class ReaderType {
    PSEUDO,
    PARSED_PSEUDO;
}
