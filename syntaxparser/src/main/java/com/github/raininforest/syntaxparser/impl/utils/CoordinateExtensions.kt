package com.github.raininforest.syntaxparser.impl.utils


fun Int.supportMentorFormat(minIntCount: Int): Int = if (this < minIntCount) minIntCount else this
