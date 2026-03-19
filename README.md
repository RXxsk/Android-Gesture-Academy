# 🤖 Android Gesture Academy

<div align="center">
  <h3>🎯 Gestos elegantes, accesibles y educativos para Android</h3>
  <p><i>La librería que te enseña mejores prácticas mientras programas</i></p>
  
  [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
  [![Android API](https://img.shields.io/badge/API-21%2B-brightgreen.svg)](https://android-arsenal.com/api?level=21)
  [![Kotlin](https://img.shields.io/badge/Kotlin-1.9.20-orange.svg)](https://kotlinlang.org)
</div>

## ✨ Características

- 🎯 **Gestos pre-construidos**: Swipe to dismiss, Drag & Drop, Pinch to Zoom y más
- ♿ **Accesibilidad por defecto**: Compatible con TalkBack y Switch Access
- 📚 **Educativo**: El plugin de Android Studio te enseña mejores prácticas
- ⚡ **Rendimiento optimizado**: 60fps garantizados
- 🔧 **Fácil integración**: Una línea de código para gestos complejos

## 🚀 Instalación rápida

### Gradle
```gradle
dependencies {
    implementation 'com.gestureacademy:core:1.0.0'
}
````

📖 Uso básico

```kotlin
// Swipe to dismiss en una sola línea
swipeableView.setupSwipeToDismiss {
    // Acción al eliminar
    showMessage("Elemento eliminado")
}

// Drag and drop
draggableIcon.setupDragAndDrop(
    boundaries = parentLayout,
    onPositionChanged = { x, y ->
        updateCoordinates(x, y)
    }
)
```

🎓 Aprende más

· 📘 Documentación completa
· 🎥 Tutoriales en video
· 📱 App de ejemplo

🤝 Contribuir

¿Quieres ayudar? ¡Genial! Revisa nuestra guía de contribución

📄 Licencia

Este proyecto está bajo la licencia Apache 2.0 - ver el archivo LICENSE para más detalles.

```
