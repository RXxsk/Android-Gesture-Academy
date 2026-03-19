kotlin
package com.gestureacademy.core

import android.animation.ValueAnimator
import android.view.MotionEvent
import android.view.View
import androidx.core.view.ViewCompat
import kotlin.math.abs

/**
 * Clase para implementar funcionalidad de "deslizar para eliminar"
 * con soporte de accesibilidad incorporado
 */
class SwipeToDismiss(
    private val view: View,
    private val onDismissed: () -> Unit
) {
    
    private var initialX = 0f
    private var initialTranslationX = 0f
    private var isSwiping = false
    
    enum class Direction { LEFT, RIGHT, BOTH }
    
    fun setup(direction: Direction = Direction.LEFT) {
        view.setOnTouchListener { v, event ->
            handleTouch(event, direction)
            true
        }
        
        // Configurar accesibilidad
        ViewCompat.setAccessibilityDelegate(view, object : androidx.core.view.AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(
                host: View,
                info: androidx.core.view.accessibility.AccessibilityNodeInfoCompat
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info.addAction(
                    androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                        android.R.id.accessibilityActionClick,
                        "Deslizar para eliminar. Doble toque para confirmar."
                    )
                )
            }
        })
    }
    
    private fun handleTouch(event: MotionEvent, allowedDirection: Direction): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                initialX = event.rawX
                initialTranslationX = view.translationX
                isSwiping = true
            }
            
            MotionEvent.ACTION_MOVE -> {
                if (!isSwiping) return false
                
                val deltaX = event.rawX - initialX
                
                // Verificar dirección permitida
                when (allowedDirection) {
                    Direction.LEFT -> if (deltaX > 0) return false
                    Direction.RIGHT -> if (deltaX < 0) return false
                    Direction.BOTH -> { /* Permitir ambas */ }
                }
                
                view.translationX = initialTranslationX + deltaX
                
                // Feedback visual basado en qué tan lejos se deslizó
                val threshold = view.width * 0.3f
                view.alpha = 1f - minOf(abs(deltaX) / threshold, 0.5f)
            }
            
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                if (!isSwiping) return false
                
                val deltaX = event.rawX - initialX
                val threshold = view.width * 0.3f
                
                if (abs(deltaX) > threshold) {
                    // Deslizó lo suficiente - eliminar
                    animateDismiss()
                } else {
                    // No deslizó lo suficiente - regresar
                    animateReset()
                }
                
                isSwiping = false
            }
        }
        return true
    }
    
    private fun animateDismiss() {
        val animator = ValueAnimator.ofFloat(view.translationX, view.width * 1.5f)
        animator.duration = 200
        animator.addUpdateListener {
            view.translationX = it.animatedValue as Float
        }
        animator.start()
        
        // Anunciar para TalkBack
        ViewCompat.announceForAccessibility(view, "Elemento eliminado")
        
        // Ejecutar callback después de la animación
        view.postDelayed({
            onDismissed.invoke()
            view.visibility = View.GONE
        }, 200)
    }
    
    private fun animateReset() {
        val animator = ValueAnimator.ofFloat(view.translationX, 0f)
        animator.duration = 200
        animator.addUpdateListener {
            view.translationX = it.animatedValue as Float
            view.alpha = 1f
        }
        animator.start()
    }
}
