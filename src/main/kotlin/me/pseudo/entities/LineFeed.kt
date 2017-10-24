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

package me.pseudo.entities

import me.pseudo.exception.LineFeedReadException

class LineFeed(var feed: List<Line>?) {
    private var currentLine = 0

    fun nextLine() {
        if (!hasNextLine())
            throw LineFeedReadException("No more lines exist within this feed! Feed size=${feed?.size}, currentLine=$currentLine")
        else {
            feed?.get(currentLine)
            currentLine++
        }
    }

    fun hasNextLine(): Boolean = (currentLine + 1) < feed?.size ?: currentLine + 2
}

class LineFeedBuilder() {
    private var feed = mutableListOf<Line>()
    fun addLine(vararg lines: Line) = apply { feed.addAll(lines) }
    fun removeLine(vararg lines: Line) = apply { feed.removeAll(lines) }

    fun build() = LineFeed(feed)
}

data class Line(val pseudo: String, val pseudoComment: String?, val devComment: String?, val ignore: Boolean = false)

class LineBuilder(private val pseudo: String) {
    private var pseudoComment: String? = null
    private var devComment: String? = null
    private var ignore: Boolean = false

    fun build() = Line(pseudo, pseudoComment, devComment, ignore)
}
