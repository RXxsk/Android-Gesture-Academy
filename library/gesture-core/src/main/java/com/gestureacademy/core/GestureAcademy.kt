kotlin
package com.gestureacademy.core

import android.view.View

/**
 * Punto de entrada principal para la librería
 * Proporciona métodos de extensión para facilitar el uso
 */
object GestureAcademy {
    
    // Versión de la librería
    const val VERSION = "1.0.0"
}

/**
 * Extension function para configurar swipe to dismiss fácilmente
 */
fun View.setupSwipeToDismiss(
    direction: SwipeToDismiss.Direction = SwipeToDismiss.Direction.LEFT,
    onDismissed: () -> Unit
) {
    val swipeHandler = SwipeToDismiss(this, onDismissed)
    swipeHandler.setup(direction)
}

// Aquí irán más extension functions para otros gestos
