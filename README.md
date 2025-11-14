# ☕ Café Kappa

Café Kappa is a Advanced Java OOP application that simulates a digital ordering system for a cozy coffee shop. Users can browse menu items, customize drinks with add-ons, and print receipts.

---

## 🚀 Features

- 📋 **Menu Loader**: Loads menu items from CSV files
- 🧋 **Custom Drink Builder**: Supports size selection and optional add-ons
- 🥐 **Food Items**: Fixed-price bakery goods
- 🛒 **Order Management**: Add, review, remove, and checkout items
- 🧾 **Receipt Generation**: Writes formatted receipts to a file
- 🎨 **Color-coded UI** using ANSI terminal codes

---

## 🧱 Project Structure

<img width="6333" height="5669" alt="Mermaid Chart - Create complex, visual diagrams with text -2025-11-14-101245" src="https://github.com/user-attachments/assets/3ed6c4b9-5ec5-4b27-9764-82e439dd59d4" />


### ✅ Main Components

| Class           | Responsibility |
|----------------|----------------|
| `MainMenu`     | Entry point, main loop |
| `OrderScreen`  | Core order workflow UI |
| `MenuDisplay`  | Category-based menu display |
| `Order`        | Tracks all `OrderItem`s, totals, receipt |
| `OrderItem`    | Holds one menu item, size, add-ons, quantity |
| `AddOn`        | Optional modifiers for drinks |
| `CustomDrink`  | Wraps a `Drink` and add-ons |
| `ReceiptWriter`| Saves order summary to `.txt` file |
| `MenuLoader`   | Loads items from CSV files |
| `AddOnLoader`  | Loads add-ons from CSV |
| `Colors`       | ANSI terminal formatting |
| `LogoDisplay`  | Banner and mascot display |
| `Loading`      | Animated console loader |

---

## 📁 File Inputs

The app expects the following CSV files in the root directory:

- `coffee.csv`
- `tea.csv`
- `signaturedrinks.csv`
- `food.csv`
- `addons.csv`

Each file should have headers and use commas to separate fields.

---

## 🏃 Running Code

<img width="482" height="412" alt="image" src="https://github.com/user-attachments/assets/e00471fc-b060-432e-b50a-7596102939fd" />

---

## 🔍 Interesting Bit of Code

This snippet from the MenuLoader class demonstrates how the program maps CSV menu entries to the correct subclass (Coffee, Tea, SignatureDrink, Food, or base MenuItem) based on keyword matching in the category field:

<img width="686" height="314" alt="image" src="https://github.com/user-attachments/assets/145085a1-7bce-4418-bb34-ec5ec908f27b" />

This design allows the app to classify menu items based on descriptive keywords from the CSV — keeping the system flexible.

---

## 🖥 How to Run

1. **Clone or download** the project:
   ```bash
   git clone https://github.com/your-repo/cafe-kappa.git
   cd cafe-kappa

2. Ensure required CSV files are present in the root directory:
- coffee.csv
- tea.csv
- signaturedrinks.csv
- food.csv
- addons.csv

3. Compile the Java files (Java 17+ recommended):
- javac *.java

4. Run the application:
- java MainMenu

5. Follow the on-screen instructions to:
- Browse or search the menu
- Add items and customizations
- Checkout and receive a receipt in the /receipts folder
