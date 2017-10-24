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

package me.pseudo

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator
import me.pseudo.entities.Line
import me.pseudo.entities.LineFeedBuilder
import me.pseudo.io.ReaderType
import me.pseudo.io.StorageHandle

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val mapper = ObjectMapper(YAMLFactory()
                .enable(YAMLGenerator.Feature.MINIMIZE_QUOTES)
                .enable(YAMLGenerator.Feature.LITERAL_BLOCK_STYLE)
                .enable(YAMLGenerator.Feature.SPLIT_LINES)
                .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER))
                .setSerializationInclusion(JsonInclude.Include.NON_ABSENT)

        StorageHandle.getByType(ReaderType.PARSED_PSEUDO).write("ExampleFeed",
                LineFeedBuilder()
                        .addLine(Line("Pseudo1", "PseudoComment1", "DevComment1"))
                        .addLine(Line("Pseudo2", "PseudoComment2", "DevComment2"))
                        .addLine(Line("Pseudo2", "PseudoComment2", "DevComment2", true))
                        .build())

        println(StorageHandle.getByType(ReaderType.PARSED_PSEUDO).read("ExampleFeed").feed.toString())
    }
}

//TODO .pseudo for storing raw pseudo-code (raw text, not YAML), .ppseudo for storing parsed-pseudo (pseudo complete with any comments, formatted as YAML)

