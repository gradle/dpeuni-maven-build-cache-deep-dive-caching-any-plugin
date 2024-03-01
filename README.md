# DPE University Training

[<img width="10%" height="10%" src="https://user-images.githubusercontent.com/120980/174325546-8558160b-7f16-42cb-af0f-511849f22ebc.png">](https://dpeuniversity.gradle.com/)
Checkout all the **free** Maven, Gradle, and DPE courses at the [DPE University][dpe-university]!

## Maven Build Cache Deep Dive - Lab 03: Add Build Cache Support to any Maven Plugin

This is a hands-on exercise to go along with the [Maven Build Cache Deep Dive][course-url] training module. In this exercise you will go over the following:

- How to add build cache support to any Maven plugin.


## Prerequisites

- Finished going through the _Caching for Other Plugins_ section in Maven Build Cache Deep Dive.
- Java 11+

Steps
-----

1. View the `pom.xml`, pay attention to the `exec-maven-plugin` and the command executes a command similar to this:
   
   ```shell
   # Change directory to target/
   cd target/
   # read the contents of a file, assign it to shell variable `person`
   person=`cat ../name.txt`
   # write `hello $person` to the file `hello.txt`
   echo "hello $person" > hello.txt
   ```

   From the root directory, you can think of this script as having the input of `name.txt` and an output of `target/hello.txt`

2. If you have not completed the previous labs, authenticate Maven with the Develocity server.

> [!NOTE]
> As part of taking this **free** course, you have access to a training instance of Develocity located at:
> ```
>  https://dpeuniversity-develocity.gradle.com/
>  ```
> [Sign in][develocity-url] to this Develocity server using the same account you use for the DPE University.
>
> This server is configured so users can only access the Build Scan® and Build Cache entries they publish.

   Run the following command and follow the instructions in your terminal:

    ```shell
    ./mvnw com.gradle:develocity-maven-extension:provision-access-key
    ```
> [!NOTE]
> For more ways to authenticate, see the [authentication guide](https://docs.gradle.com/enterprise/maven-extension/#authenticating_with_gradle_enterprise) to see how to provide credentials.

3. Enable the build cache debug logging to view additional details:

   ```shell
   export MAVEN_OPTS="-Dorg.slf4j.simpleLogger.log.develocity.goal.cache=debug"
   ```

4. Execute the build:

   ```shell
    ./mvnw clean verify
    ```

5. Create a `pluginManagement` section in the `pom.xml`.

> [!NOTE]
> The free [build cache training course][course-url] walks through the following steps in detail.
 
6. Add the `develocity-maven-extension` to `pluginManagement`.

7. Add a [configuration entry](https://docs.gradle.com/enterprise/maven-extension/#making_other_goals_cacheable) for the `exec-maven-plugin`.

8. Set a value for the `cacheableBecause` argument.

9. Add the `name.txt` file [as an input](https://docs.gradle.com/enterprise/maven-extension/#declaring_additional_inputs). This is similar to what you did in the [missing inputs lab][missing-inputs-lab].

10. Define the `hello.txt` file [as an output](https://docs.gradle.com/enterprise/maven-extension/#declaring_additional_outputs).

11. Run the build:

   ```shell
   ./mvnw clean verify
   ```
   
12. Scroll back to the `exec-maven-plugin` in the console output, and note the log message, stating caching was not enabled for this goal (this message can also be viewed in the Build Scan).

13. Add the missing [parameters as inputs](https://docs.gradle.com/enterprise/maven-extension/#making_other_goals_cacheable).
    
> [!NOTE]
> If you are familiar with the plugin, you can walk through configuring each argument, adding or ignoring each one.
> In this lab we will iteratively run the build do demonstrate the types of error you may see when enabling build caching for other plugins.  

14. Run the build:

   ```shell
   ./mvnw clean verify
   ```

15. Once again, scroll back in the console or view the Build Scan to see the reason why the build was not cached.

16. Configure the extension to ignore the file parameters (`workingDirectory`, `buildDirectory`, and `basedir`) as they are not used in this use case (e.g. we are explicitly configuring each of the input and output files).

17. Run the build:

   ```shell
   ./mvnw clean verify
   ```

18. Again, scroll back in the console (or view the Build Scan).
    Note that one of the input types is unsupported. For this use case, the `project`, `pluginDependencies`, and `session`, can all be ignored (as we are concerned with only the script execution).

19. Configure the `skip` property.
   Remove the `skip` property as an input, and define a [`skipIfTrue` section](https://docs.gradle.com/enterprise/maven-extension/#conditionally_skipping_goal_execution) in the configuration.

20. Run the build **multiple times**:

   ```shell
   ./mvnw clean verify
   ```

The goal is now cachable! Open the Build Scan to view its details.

## Solution Reference

To see the solution to the lab, check out the [`solution`](https://github.com/gradle/caching-any-plugin-maven-build-cache-lab/commit/solution) branch of this project.

## More Free Labs & Courses

Be sure to check out our other **free** [courses][dpe-university] and [labs](https://github.com/gradle?q=dpe-university)!

**Related courses:**
- [Maven - Build Cache Deep Dive][course-url]
- [Maven - Maintaining an Optimized Build Cache](https://dpeuniversity.gradle.com/c/42cf9d626302011526c4a0536b26af929b5bef58)
- [Develocity - How to Use Build Scans](https://dpeuniversity.gradle.com/c/0b0b3e4a8d21709ff39074e9962eee6ca4276dc1)

**Related labs:**
- [Lab 01 - Using the local build cache](https://github.com/gradle/getting-started-maven-build-cache-lab)
- [Lab 02 - Missing Inputs With Build Caching](https://github.com/gradle/missing-inputs-maven-build-cache-lab)
- [Lab 03 - Add Build Cache Support to any Maven Plugin](https://github.com/gradle/caching-any-plugin-maven-build-cache-lab)
- [Lab 04 - Handling Cache Misses with Normalization](https://github.com/gradle/cache-misses-maven-build-cache-lab)
- [Lab 05 - Outputs Overwrite Inputs](https://github.com/gradle/outputs-overwrite-inputs-maven-build-cache-lab)
- [Lab 06 - Maintaining an Optimized Build Cache](https://github.com/gradle/maintaining-optimized-cache-maven-build-cache-lab)

[course-url]: https://dpeuniversity.gradle.com/c/47262fea1e74b719afb590d8cb3f8280bf2af732
[dpe-university]: https://dpeuniversity.gradle.com/
[develocity-url]: https://dpeuniversity-develocity.gradle.com/
[missing-inputs-lab]: https://github.com/gradle/dpeuni-maven-build-cache-deep-dive-missing-inputs