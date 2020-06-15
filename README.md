# KotlinDI
Dagger2 Dependancy Injection with Kotlin example.

## This applications purpose
The applications purpose is to layout a simple architectural pattern in Kotlin on android using Dagger2. This readme also includes some basic knowledge on what dependancy injection is. It is in no way complete and does not claim to be a perfect source of knowledge. It is more exploratory and will iteratively be improved overtime. It is also not a written tutorial for dagger2. That can be found all over the net. However the code base itself is effectively the tutorial.

## Application functionality
The application will make use of stackoverflows api to show a list of the most recent questions. The user will be able to select the question in the list and it will show it on a new screen.



# What is Dependancy Injection?
An important question and in most case answered either in an incomplete manner or entirely wrong. 

A quick search for any information about dependancy injection leads to the most upvote answer which paraphrased is, "Dependency Injection is the passing dependencies to other objects or framework, which is done via injection (constructor, method or field injection)"
Why then is Dependancy injection such a difficult issue, especially in Android. If dependancy injection was "just passing of dependancies" why is there a usage of large libraries such as Dagger2 and many offshoot libraries such as koin?

# Dependancy Injection as an Architectural Pattern 
It is important to look at Dependancy Injection (abbrv. as DI) as an architectural pattern. If we look at DI as an architectural pattern we can gain a better understanding of why large libraries such as Dagger2 exist and how they work.
Remember that an Architectural pattern considers the design of an application at the highest level of abstraction rather than solving a localised problem. This is emphasised as in most cases Dependancy injection is explained more as a solution to localised problem (injection of dependacies to a framework or class) rather than an architectural design pattern. This consideration leads to a better understanding on why large DI libraries exist such as dagger2.

## Dependancy injection architectural pattern core concepts
Lets abbreviate this as DIAP. DIAP main charecteristics is to seperate an applications logic into two discrete sets of classes. Its important to note that these two sets of classes are __disjoint__ i.e. they do not contain overlapping elements and do not have elements in common.

* Functional set
    * contains the classes that encapsulate the core application functionality
* Construction set
    * contains the classes that resolve dependancies and instantiate objects from functional set


### Some examples & rules & considerations
* if a class contains core application functionality (eg from the _functional set_) it cannot resolve dependancies or instantiate objects.
* This segregation of classes is a manifestation of _seperation of concerns_.
    * By doing this seperation we seperate the construction concern from the functional concern


## Dagger2
Dagger is a good but complex dependancy injection library. It contains various complexities associated with it that include:
* Making use of code generation instead of reflection
* Poor, Confusing & Conflicting documentation
* Lack of best practicise, (breaking of architctural patterns such as SOLID, law of demeter)
* Too many features
* Overly steep learning curve

There are many different guides out there that explain how to use dagger2. Please consider making use of one of these as a full guide on dagger2 is beyond the point of this project.
However with that being said take a look at the project files to see how an implementation of dagger2 dependancy injection can be achieved and hopefully you will be able to learn something __new__!



