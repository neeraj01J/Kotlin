data class Product(val id: Int, val name: String, val price: Double, val category: String)

fun main() {

    val products = listOf(
        Product(1, "Laptop", 50000.0, "Electronics"),
        Product(2, "Mobile", 20000.0, "Electronics"),
        Product(3, "Headphones", 2000.0, "Electronics"),
        Product(4, "Keyboard", 1500.0, "Electronics"),
        Product(5, "Mouse", 800.0, "Electronics"),

        Product(6, "Shirt", 1200.0, "Clothing"),
        Product(7, "Jeans", 2000.0, "Clothing"),
        Product(8, "Jacket", 3000.0, "Clothing"),

        Product(9, "Shoes", 2500.0, "Footwear"),
        Product(10, "Sandals", 1000.0, "Footwear"),

        Product(11, "Book", 500.0, "Education"),
        Product(12, "Notebook", 100.0, "Education"),
        Product(13, "Pen Pack", 150.0, "Education")
    )

    val cart = mutableMapOf<Product, Int>()
    var choice: Int

    do {
        println("\n===== ONLINE SHOPPING SYSTEM =====")
        println("1. View All Products")
        println("2. View Products by Category")
        println("3. Add to Cart")
        println("4. Remove from Cart")
        println("5. View Cart")
        println("6. Checkout")
        println("7. Exit")
        print("Enter choice: ")

        choice = readln().toInt()

        when (choice) {

            1 -> {
                println("\nAll Products:")
                products.forEach {
                    println("${it.id}. ${it.name} (${it.category}) - ₹${it.price}")
                }
            }

            2 -> {
                println("\nCategories: Electronics, Clothing, Footwear, Education")
                print("Enter category: ")
                val cat = readln()

                val filtered = products.filter { it.category.equals(cat, true) }

                if (filtered.isEmpty()) {
                    println("No products found")
                } else {
                    filtered.forEach {
                        println("${it.id}. ${it.name} - ₹${it.price}")
                    }
                }
            }

            3 -> {
                print("Enter Product ID: ")
                val id = readln().toInt()
                val item = products.find { it.id == id }

                if (item != null) {
                    print("Enter quantity: ")
                    val qty = readln().toInt()

                    cart[item] = cart.getOrDefault(item, 0) + qty
                    println("${item.name} added to cart")
                } else {
                    println("Invalid Product ID")
                }
            }

            4 -> {
                print("Enter Product ID to remove: ")
                val id = readln().toInt()
                val item = products.find { it.id == id }

                if (item != null && cart.containsKey(item)) {
                    cart.remove(item)
                    println("${item.name} removed from cart")
                } else {
                    println("Item not found in cart")
                }
            }

            5 -> {
                println("\nYour Cart:")
                if (cart.isEmpty()) {
                    println("Cart is empty")
                } else {
                    var total = 0.0
                    cart.forEach { (product, qty) ->
                        val cost = product.price * qty
                        println("${product.name} x$qty = ₹$cost")
                        total += cost
                    }
                    println("Total: ₹$total")
                }
            }

            6 -> {
                if (cart.isEmpty()) {
                    println("Cart is empty")
                } else {
                    var total = 0.0

                    println("\n===== BILL RECEIPT =====")
                    cart.forEach { (product, qty) ->
                        val cost = product.price * qty
                        println("${product.name} x$qty = ₹$cost")
                        total += cost
                    }

                    var discount = 0.0
                    if (total > 50000) {
                        discount = total * 0.10
                        println("10% discount applied")
                    } else if (total > 20000) {
                        discount = total * 0.05
                        println("5% discount applied")
                    }

                    val finalAmount = total - discount

                    println("----------------------")
                    println("Subtotal: ₹$total")
                    println("Discount: ₹$discount")
                    println("Final Amount: ₹$finalAmount")
                    println("Thank you for shopping")

                    cart.clear()
                }
            }

            7 -> println("Exit")

            else -> println("Invalid choice")
        }

    } while (choice != 7)
}