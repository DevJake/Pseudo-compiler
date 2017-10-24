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

package me.pseudo.parser

import me.pseudo.entities.LineFeed
import java.util.regex.Pattern

object Parser {

    //Parse single line
    fun parse(raw: String) {

    }

    //Parse multi-line
    fun parse(raw: LineFeed) {
        //TODO feed into #parse(String)
    }
}

private enum class ParserPattern(pattern: Pattern) {
    //for i<-2 to n do Supports spacing, negative values, varying casing and var names (including range of lengths), wording etc.
    SIMPLE_FOR_LOOP(Pattern.compile("for\\s*([a-zA-Z]+)\\s*(?:is|as|gets|set|<-)\\s*(-?\\d+)\\s*(?:to|upto|until)\\s*([a-zA-Z]+)+\\s*(?:do|perform|->)?"))
}

//TODO add pattern type inference (perhaps complex but do-able)
//TODO store pseudo data to .pseudodat files (an indexed map on all pseudo vars, data types, values etc)
//TODO conditionals, such as 'n' in a for loop not being less than zero; ensuring only incremental operation exists
//TODO include a range of example structures for each supported pattern