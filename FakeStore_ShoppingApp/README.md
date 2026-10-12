# 🛒 FakeStore REST API Shopping App

An ultra-premium, modern Android application demonstrating **Retrofit**, **REST APIs**, **Coroutines**, and **Jetpack Compose Material 3**.

Built using the public [FakeStore API](https://fakestoreapi.com).

---

## 📸 Screenshots & UI Highlights

- **Obsidian Dark Luxury Theme**: Customized palette with glowing vibrant orange accents, gold star ratings, and soft elevated surfaces.
- **Featured Collection Banner**: High-end hero carousel for top items with "BESTSELLER" tag badges.
- **Live Shopping Cart**: Interactive cart state managed via `StateFlow` with badge counter, slide-up `ModalBottomSheet`, and quantity adjustment.
- **Real-Time Filtering & Search**: Instant filtering by search query and product categories.

---

## 🚀 REST API Endpoints Integrated

This project consumes the following **FakeStore REST API** endpoints using **Retrofit**:

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/products` | Fetches complete product catalog |
| `GET` | `/products/{id}` | Fetches individual product details |
| `GET` | `/products/categories` | Dynamically loads available categories |
| `GET` | `/products/category/{category}` | Filters products by selected category |

---

## 🛠 Tech Stack & Architecture

- **Language**: Kotlin 100%
- **UI Framework**: Jetpack Compose (Material 3)
- **Architecture**: MVVM + Clean Repository Pattern
- **Networking**:
  - [Retrofit 2](https://square.github.io/retrofit/) - Type-safe HTTP client for Android
  - [Gson Converter Factory](https://github.com/google/gson) - Automatic JSON deserialization
  - [OkHttp Logging Interceptor](https://github.com/square/okhttp) - Full HTTP request/response logging
- **Image Loading**: [Coil Compose](https://coil-kt.github.io/coil/) - Asynchronous image loading
- **Async & Reactive Flow**: Kotlin Coroutines & `StateFlow`
- **Navigation**: Jetpack Navigation Compose

---

## 📂 Project Structure

```
com.example.workshopshopingapp
├── data
│   ├── model          # Product, Rating, and CartItem data models
│   ├── remote         # FakeStoreApiService (Retrofit Interface) & RetrofitInstance
│   └── repository     # ProductRepository handling API calls & NetworkResult
├── ui
│   ├── components     # ProductCard, CategoryChips, and CartBottomSheet
│   ├── navigation     # AppNavGraph & Route definitions
│   ├── screens        # ProductListScreen & ProductDetailScreen
│   ├── state          # ProductsUiState & ProductDetailUiState
│   ├── theme          # Luxury Dark Color, Typography, and Theme
│   └── viewmodel      # ProductViewModel
└── util               # NetworkResult wrapper (Success, Error, Loading)
```

---

## 📋 How to Run the Project

1. Clone or extract this repository into Android Studio.
2. Ensure you are connected to the internet (app fetches live data from `https://fakestoreapi.com`).
3. Click **Run (▶)** in Android Studio or execute:
   ```bash
   ./gradlew assembleDebug
   ```
4. Deploy to your connected Android phone or Virtual Device (Emulator).

---

## 📜 License

Distributed under the MIT License. See `LICENSE` for more information.
