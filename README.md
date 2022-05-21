# StructuredConcurencyExample with Coroutines

Unstructured concurrency does not guarantee all the task to completed so it might thrown an unexpected behaviour/error, and its not recommended

Structured concurrency in other case is guarantee all the task is completed because its wait to all the child to complete. it also thrown a handle exception so whenever there is an error, we can clearly see whats happened, so it is recommended to use Structured concurrency
