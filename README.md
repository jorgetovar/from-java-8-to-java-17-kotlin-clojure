
# Java Evolution: From Java 8 to Java 17, Compared with Kotlin and Clojure

This README provides a brief overview and code examples of some notable Java language features introduced in recent versions of Java. Each section corresponds to a specific feature.

## Table of Contents

- [Local-Variable Syntax for Lambda Parameters](#local-variable-syntax-for-lambda-parameters)
- [Pattern Matching](#pattern-matching)
- [Records](#records)
- [Sealed Classes](#sealed-classes)
- [Text Blocks](#text-blocks)
- [Switch Expressions](#switch-expressions)

## Local-Variable Syntax for Lambda Parameters

In Java, starting from version 11, you can use the `var` keyword for lambda parameters, simplifying lambda expressions by allowing type inference. Here's an example:

```java
// Java 11 Lambda Expression with var
(var a, var b) -> a + b;
```

## Pattern Matching

Java introduced pattern matching in later versions, enhancing the way you work with `instanceof` checks and type casting. It provides more concise and readable code. Here's a simplified example:

```java
// Pattern Matching in Java
public static boolean available(Book book) {
    return switch (book) {
        case FictionBook fictionBook -> fictionBook.available();
        case ProgrammingBook programmingBook -> programmingBook.exists();
        case PsychologicalBook psychologicalBook -> psychologicalBook.imAAvailable();
        case null, default -> false;
    };
}
```

## Records

Records, introduced in Java 16, provide a compact way to declare classes that are simple data carriers. They automatically generate methods like `equals()`, `hashCode()`, and `toString()`. Example:

```java
// Java Record
public record BookResponse(String json, Double version) {
}
BookResponse bookResponse = new BookResponse("{}", 2d);
System.out.println(bookResponse); // Automatically calls toString()
```

## Sealed Classes

Java introduced sealed classes to restrict which other classes or interfaces may extend or implement them. It helps in defining a finite set of subclasses. Example:

```java
// Sealed Class in Java
public sealed class Book permits FictionBook, ProgrammingBook, PsychologicalBook 
```

## Text Blocks

Text Blocks, available from Java 13 onwards, simplify working with multi-line strings. They allow you to write formatted text with minimal escape sequences:

```java
// Java Text Block
json += """
        {"title":"%s",
        "author":"%s",
        "pages":%s,
        "karma":%s,
        "eBook":%s,
        "rate":%s,
        "category":"%s"}
        """
```

## Switch Expressions

Java introduced switch expressions in Java 12. They allow you to use a switch statement as an expression, making code more concise and readable:

```java
// Java Switch Expression
var book = switch (category) {
    case fiction -> {
        karma = 25;
        yield new FictionBook(title, author);
    }
    case programming -> {
        karma = 40;
        yield new ProgrammingBook(title, author);
    }
    case psychological -> {
        karma = 30;
        yield new PsychologicalBook(title, author);
    }
};
```

These features have been introduced to make Java code more expressive and readable while reducing boilerplate code. You can choose the appropriate feature based on your project's requirements and Java version.

## Conclusion

While I believe that Java 17 and its new releases are undeniably impressive, I can't help but acknowledge that Kotlin offers a multitude of features that enhance the developer experience and make coding more enjoyable. Clojure, on the other hand, has been a revelation for me, highlighting that you don't necessarily need classes and object-oriented programming to elegantly solve complex business logic challenges. It's a reminder that diverse programming languages can offer unique perspectives and solutions.