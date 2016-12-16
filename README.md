# Example

```java
Eval e = new Eval();
e.exec("2 + 2 * 2"); // 6
e.exec("(2 + 2) * 2"); // 8

// ...

Double d = e.exec("...");

if(Double.isNaN(d) || Double.isInfinite(d))
{
  // fail
}
else
{
  // ok
}
```
