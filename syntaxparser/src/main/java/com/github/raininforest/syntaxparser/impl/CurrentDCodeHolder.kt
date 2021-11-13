package com.github.raininforest.syntaxparser.impl

import com.github.raininforest.syntaxparser.impl.commands.operations.DOperationCommand

/**
 * Current d-code holder. (For deprecated practice support, when command contains only coordinate data)
 *
 * Created by Sergey Velesko on 13.11.2021
 */
class CurrentDCodeHolder {
    private var _currentDCode: DOperationCommand.DCode = DOperationCommand.DCode.D01

    var currentDCode
        set(value) {
            _currentDCode = value
        }
        get() = _currentDCode
}