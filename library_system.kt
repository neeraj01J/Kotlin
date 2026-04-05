data class Book(
    val id: Int,
    val name: String,
    val author: String,
    var available: Boolean = true,
    var issuedTo: String = "",
    var issueDay: Int = 0
)

fun main() {

    val books = mutableListOf(
        Book(1, "Java Programming", "James Gosling"),
        Book(2, "Kotlin Basics", "JetBrains"),
        Book(3, "Data Structures", "Mark Allen"),
        Book(4, "Operating Systems", "Galvin"),
        Book(5, "Computer Networks", "Tanenbaum"),
        Book(6, "C Programming", "Dennis Ritchie"),
        Book(7, "Python Guide", "Guido van Rossum"),
        Book(8, "Database Systems", "Korth"),
        Book(9, "Software Engineering", "Pressman"),
        Book(10, "Artificial Intelligence", "Russell")
    )

    var choice: Int

    do {
        println("\n===== LIBRARY MANAGEMENT SYSTEM =====")
        println("1. View All Books")
        println("2. Add Book")
        println("3. Remove Book")
        println("4. Issue Book")
        println("5. Return Book")
        println("6. Search Book")
        println("7. View Issued Books")
        println("8. Exit")
        print("Enter choice: ")

        choice = readln().toInt()

        when (choice) {

            1 -> {
                println("\n--- All Books ---")
                for (b in books) {
                    val status = if (b.available) "Available" else "Issued to ${b.issuedTo}"
                    println("${b.id}. ${b.name} by ${b.author} - $status")
                }
            }

            2 -> {
                print("Enter Book ID: ")
                val id = readln().toInt()

                // Check duplicate ID
                if (books.any { it.id == id }) {
                    println("Book ID already exists")
                    continue
                }

                print("Enter Book Name: ")
                val name = readln()

                print("Enter Author: ")
                val author = readln()

                books.add(Book(id, name, author))
                println("Book added successfully")
            }

            3 -> {
                print("Enter Book ID to remove: ")
                val id = readln().toInt()

                val removed = books.removeIf { it.id == id }

                if (removed) {
                    println("Book removed successfully")
                } else {
                    println("Book not found")
                }
            }

            4 -> {
                print("Enter Book ID to issue: ")
                val id = readln().toInt()

                val book = books.find { it.id == id }

                if (book != null && book.available) {
                    print("Enter Student Name: ")
                    val student = readln()

                    print("Enter Issue Day (number): ")
                    val day = readln().toInt()

                    book.available = false
                    book.issuedTo = student
                    book.issueDay = day

                    println("Book issued successfully")
                } else {
                    println("Book not available")
                }
            }

            5 -> {
                print("Enter Book ID to return: ")
                val id = readln().toInt()

                val book = books.find { it.id == id }

                if (book != null && !book.available) {

                    print("Enter Return Day (number): ")
                    val returnDay = readln().toInt()

                    val daysUsed = returnDay - book.issueDay
                    var fine = 0

                    if (daysUsed > 7) {
                        fine = (daysUsed - 7) * 5
                    }

                    book.available = true
                    book.issuedTo = ""
                    book.issueDay = 0

                    println("Book returned successfully")
                    println("Days used: $daysUsed")
                    println("Fine: ₹$fine")
                } else {
                    println("Invalid return")
                }
            }

            6 -> {
                print("Enter book name to search: ")
                val search = readln()

                val result = books.filter { it.name.contains(search, true) }

                if (result.isEmpty()) {
                    println("No book found")
                } else {
                    result.forEach {
                        val status = if (it.available) "Available" else "Issued"
                        println("${it.id}. ${it.name} - $status")
                    }
                }
            }

            7 -> {
                println("\n--- Issued Books ---")
                val issued = books.filter { !it.available }

                if (issued.isEmpty()) {
                    println("No books issued")
                } else {
                    issued.forEach {
                        println("${it.id}. ${it.name} issued to ${it.issuedTo}")
                    }
                }
            }

            8 -> println("Exit")

            else -> println("Invalid choice")
        }

    } while (choice != 8)
}