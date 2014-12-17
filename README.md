SimpleCode
==========

Utils to help create easily maintainable code

Usage
-----

```java
@Awesome("12-05-2099")
public class MyAwesomeClass {
    public void awesomeStuff() {
    }
}
```
MyAwesomeClass will no longer compile after the 12th of May, 2099 - indicating that it is time to review what once was considered awesome :)


```java
@Temporary("12-05-2031")
public class NotSoSureAboutThis {
    public void dubiousStuff() {
    }
}
```
NotSoSureAboutThis will no longer compile after May, 2031 - indicating it is time to repay your tech debt.


```java
@DeprecatedReview("12-05-2022")
public class LetsGetRidOfThisSoon {
    public void deprecatedStuff() {
    }
}
```
Again, LetsGetRidOfThisSoon will not compile after 2022.


Using with Maven
----------------


```
<repositories>
    <repository>
        <id>simple-code-mvn-repo</id>
        <url>https://raw.github.com/cbensemann/SimpleCode/mvn-repo/</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
</repositories>
```
