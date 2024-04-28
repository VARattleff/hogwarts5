## Testing Prefect Functions

In my project, i have a function `appointPrefectValidationCheck` in the `StudentService` class that validates whether a student can be appointed as a prefect. Here's how you can test this function:

1. Navigate to the `StudentServiceTest` class in the `src/test/java/dk/kea/dat3js/hogwarts5/students` directory.

2. In the `appointPrefectValidationCheck` test method, we first fetch an existing student from the repository.

3. We then call the `appointPrefectValidationCheck` method with the ID of the fetched student and assert that the student can be appointed as a prefect.

4. Next, we fetch another existing student from the repository, set them as a prefect, and save the changes.

5. We call the `appointPrefectValidationCheck` method again with the ID of the first student and assert that they can no longer be appointed as a prefect, because there's already a prefect of the same gender in the same house.

