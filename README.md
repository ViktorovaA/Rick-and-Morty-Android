# Rick and Morty Characters App

Приложение на Android, созданное с использованием Jetpack Compose, отображающее список персонажей из Rick and Morty API. Включает поддержку загрузки данных из интернета и локальной базы Room, Koin для DI и обработку ошибок.

## 🚀 Стек технологий

- **Jetpack Compose** — UI
- **Koin** — внедрение зависимостей (DI)
- **Room** — локальное хранилище
- **Ktor Client** — сетевые запросы
- **Kotlinx.serialization** — сериализация JSON
- **Coil** — загрузка изображений
- **ViewModel + StateFlow** — управление состоянием

## 📱 Функциональность

- Загрузка и отображение списка персонажей
- Загрузка из сети или из Room при отсутствии интернета
- Обработка состояний: загрузка, ошибка, контент
- Кнопка обновления списка
- Архитектура с разделением UI и ViewModel

https://github.com/user-attachments/assets/28a77968-44d8-4318-b85f-30431fce3ea0

## Особенности архитектуры

Код разделен на следующие уровни по принципу чистой архитектуры:
- Уровень presentation (зависит только от domain) отвечает за взаимодействие с пользователем
- Уровень data (зависит только от domain) содержит бизнес-логику и компоненты, отвечающие за работу с данными
- Уровень domain выполняет бизнес-логику и координирует работу между уровнями представления и data
- Уровень инфраструктуры (common) занимается связью с внешними системами (бд, API) и общими настройками

Также есть разделение по принципу MVVM:
- model - repositpry предоставляет данные
- view-model - view-model связывает модель и представление через механизм привязки данных StateFlow
- view - ui отображает данные пользователям
